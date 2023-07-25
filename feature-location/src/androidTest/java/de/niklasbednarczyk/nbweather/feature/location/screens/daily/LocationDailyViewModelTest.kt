package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LocationDailyViewModelTest : NBViewModelTest {

    companion object {
        private const val FORECAST_TIME = 111L
        private const val LAT_AND_LON = 50.0
    }

    private lateinit var subjectWithoutArgs: LocationDailyViewModel
    private lateinit var subjectWithArgs: LocationDailyViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)

        subjectWithoutArgs = LocationDailyViewModel(
            savedStateHandle = SavedStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithArgs = LocationDailyViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsLocation.Daily.KEY_FORECAST_TIME to FORECAST_TIME.toString(),
                    DestinationsLocation.Daily.KEY_LATITUDE to LAT_AND_LON.toString(),
                    DestinationsLocation.Daily.KEY_LONGITUDE to LAT_AND_LON.toString()
                )
            ),
            oneCallRepository = oneCallRepository
        )
    }


    @Test
    fun uiState_viewDataResource_shouldBeErrorWithoutArguments() = testScope.runTest {
        // Arrange + Act
        subjectWithoutArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsError(uiState.viewDataResource)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldBeSuccessWithArguments() = testScope.runTest {
        // Arrange + Act
        subjectWithArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.viewDataResource)
                assertListIsNotEmpty(uiState.viewDataResource?.dataOrNull?.items)
            }
        )
    }


}