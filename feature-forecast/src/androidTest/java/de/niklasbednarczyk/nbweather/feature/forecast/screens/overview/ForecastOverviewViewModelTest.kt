package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

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
    fun uiState_locationResource_shouldNotGetDataWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.locationResource)
                assertNull(uiState.locationResource.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_locationResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        insertCurrentLocation()

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
    fun uiState_viewDataResource_shouldNotGetDataWhenCurrentLocationNotSet() = testScope.runTest {
        // Arrange + Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource is NBResource.Loading
            },
            collectData = { uiState ->
                assertResourceIsLoading(uiState.itemsResource)
                assertNull(uiState.itemsResource.dataOrNull)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldGetDataWhenCurrentLocationSet() = testScope.runTest {
        // Arrange
        insertCurrentLocation()

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsSuccess(uiState.itemsResource)
                assertListIsNotEmpty(uiState.itemsResource.dataOrNull)
            }
        )
    }

    private suspend fun insertCurrentLocation() {
        geocodingRepository.insertOrUpdateCurrentLocation(LATITUDE, LONGITUDE)
    }

}