package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull

class ForecastOverviewViewModelTest : NBViewModelTest {

    companion object {
        private const val LATITUDE = 40.17396
        private const val LONGITUDE = -80.2461714
    }

    private lateinit var subject: ForecastOverviewViewModel

    private lateinit var geocodingRepository: GeocodingRepository

    @Before
    override fun setUp() {
        geocodingRepository = GeocodingRepository.createFake(context)

        subject = ForecastOverviewViewModel(
            geocodingRepository = geocodingRepository,
            oneCallRepository = OneCallRepository.createFake(context)
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

    private suspend fun setCurrentLocation() {
        geocodingRepository.setCurrentLocation(LATITUDE, LONGITUDE)
    }

}