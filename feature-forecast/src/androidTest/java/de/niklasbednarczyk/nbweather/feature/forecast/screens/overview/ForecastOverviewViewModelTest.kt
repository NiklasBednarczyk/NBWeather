package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastViewModelTest
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNotEquals

class ForecastOverviewViewModelTest : NBForecastViewModelTest {

    private lateinit var subjectWithoutArgsAndWithoutLocation: ForecastOverviewViewModel
    private lateinit var subjectWithoutArgsAndWithLocation: ForecastOverviewViewModel
    private lateinit var subjectWithArgsAndWithoutLocation: ForecastOverviewViewModel
    private lateinit var subjectWithArgsAndWithLocation: ForecastOverviewViewModel

    private lateinit var oneCallRepository: OneCallRepository

    @Before
    override fun setUp() = testScope.runTest {
        val geocodingRepositoryWithoutLocation = GeocodingRepository.createFake(context)
        val geocodingRepositoryWithLocation = GeocodingRepository.createFake(context)
        oneCallRepository = OneCallRepository.createFake(context)

        geocodingRepositoryWithLocation.getAndInsertLocation(
            coordinates = coordinates
        )

        val savedStateHandleWithoutArgs = createTestSaveStateHandle()
        val savedStateHandleWithArgs = createTestSaveStateHandle(
            NBArgumentKeys.Latitude to coordinates.latitude,
            NBArgumentKeys.Longitude to coordinates.longitude
        )

        subjectWithoutArgsAndWithoutLocation = ForecastOverviewViewModel(
            savedStateHandle = savedStateHandleWithoutArgs,
            geocodingRepository = geocodingRepositoryWithoutLocation,
            oneCallRepository = oneCallRepository
        )
        subjectWithoutArgsAndWithLocation = ForecastOverviewViewModel(
            savedStateHandle = savedStateHandleWithoutArgs,
            geocodingRepository = geocodingRepositoryWithLocation,
            oneCallRepository = oneCallRepository
        )
        subjectWithArgsAndWithoutLocation = ForecastOverviewViewModel(
            savedStateHandle = savedStateHandleWithArgs,
            geocodingRepository = geocodingRepositoryWithoutLocation,
            oneCallRepository = oneCallRepository
        )
        subjectWithArgsAndWithLocation = ForecastOverviewViewModel(
            savedStateHandle = savedStateHandleWithArgs,
            geocodingRepository = geocodingRepositoryWithLocation,
            oneCallRepository = oneCallRepository
        )
    }

    @Test
    fun uiState_locationResource_withoutArgs_withoutLocation_shouldBeError() = testScope.runTest {
        subjectWithoutArgsAndWithoutLocation.testUiStateLocationResource(false)
    }

    @Test
    fun uiState_locationResource_withoutArgs_withLocation_shouldBeError() = testScope.runTest {
        subjectWithoutArgsAndWithLocation.testUiStateLocationResource(false)
    }

    @Test
    fun uiState_locationResource_withArgs_withoutLocation_shouldBeError() = testScope.runTest {
        subjectWithArgsAndWithoutLocation.testUiStateLocationResource(false)
    }

    @Test
    fun uiState_locationResource_withArgs_withLocation_shouldBeSuccess() = testScope.runTest {
        subjectWithArgsAndWithLocation.testUiStateLocationResource(true)
    }

    @Test
    fun uiState_itemsResource_withoutArgs_shouldBeError() = testScope.runTest {
        // Arrange + Act
        subjectWithoutArgsAndWithoutLocation.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsError(uiState.itemsResource)
            }
        )
    }

    @Test
    fun uiState_itemsResource_withArgs_shouldBeSuccess() = testScope.runTest {
        subjectWithArgsAndWithoutLocation.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.itemsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.itemsResource)
                assertListIsNotEmpty(uiState.itemsResource.dataOrNull)
            }
        )
    }

    @Test
    fun refreshData_shouldRefreshData() = testScope.runTest {
        // Arrange
        oneCallRepository.getOneCall(
            coordinates = coordinates
        ).nbCollectUntilResource { oneCallBeforeRefresh ->
            // Act
            delayForDifferentTimestamps()

            subjectWithoutArgsAndWithoutLocation.refreshData(
                coordinates = coordinates
            )

            oneCallRepository.getOneCall(
                coordinates = coordinates
            ).nbCollectUntilResource { oneCallAfterRefresh ->
                // Assert
                assertNotEquals(
                    oneCallBeforeRefresh.timestamp.value,
                    oneCallAfterRefresh.timestamp.value
                )
            }
        }
    }

    private suspend fun ForecastOverviewViewModel.testUiStateLocationResource(
        expectedSuccess: Boolean
    ) {
        // Arrange + Act
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.locationResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                if (expectedSuccess) {
                    assertResourceIsSuccess(uiState.locationResource)
                    assertNotNull(uiState.locationResource.dataOrNull)
                } else {
                    assertResourceIsError(uiState.locationResource)
                }
            }
        )
    }

}