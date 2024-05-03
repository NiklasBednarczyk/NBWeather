package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class ForecastOverviewViewModelTest : NBForecastViewModelTest {

    private lateinit var subject: ForecastOverviewViewModel

    private lateinit var geocodingRepository: GeocodingRepository
    private lateinit var oneCallRepository: OneCallRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)
        oneCallRepository = OneCallRepository.createFake(context)

        subject = ForecastOverviewViewModel(
            geocodingRepository = geocodingRepository,
            oneCallRepository = oneCallRepository
        )
    }

    @Test
    fun uiState_locationResource_shouldBeErrorWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsError(uiState.locationResource)
            }
        )
    }

    @Test
    fun uiState_locationResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        setCurrentLocation()

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError && uiState.locationResource.dataOrNull != null
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.locationResource)
                assertNotNull(uiState.locationResource.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldBeErrorWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsError(uiState.itemsResource)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        setCurrentLocation()

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource.isSuccessOrError && uiState.itemsResource.dataOrNull != null
            },
            collectData = { uiState ->
                assertResourceIsSuccess(uiState.itemsResource)
                assertListIsNotEmpty(uiState.itemsResource.dataOrNull)
            }
        )
    }

    @Test
    fun refreshData_shouldRefreshData() = testScope.runTest {
        // Arrange
        oneCallRepository.getOneCall(
            latitude = latitude,
            longitude = longitude
        ).nbCollectUntilResource { oneCallBeforeRefresh ->
            // Act
            delayForDifferentTimestamps()

            subject.refreshData(
                latitude = latitude,
                longitude = longitude
            )

            oneCallRepository.getOneCall(
                latitude = latitude,
                longitude = longitude
            ).nbCollectUntilResource { oneCallAfterRefresh ->
                // Assert
                assertNotEquals(
                    oneCallBeforeRefresh.timestamp.value,
                    oneCallAfterRefresh.timestamp.value
                )
            }
        }
    }

    private suspend fun setCurrentLocation() {
        geocodingRepository.setCurrentLocation(latitude, longitude)
    }

}