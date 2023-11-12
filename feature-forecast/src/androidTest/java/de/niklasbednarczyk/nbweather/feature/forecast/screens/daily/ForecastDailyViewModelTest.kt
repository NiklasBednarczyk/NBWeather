package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastDailyViewModelTest : NBViewModelTest {

    companion object {
        private const val FORECAST_TIME = 50L
        private const val LATITUDE = -1.0
        private const val LONGITUDE = 1.0
    }

    private lateinit var subjectWithoutArgs: ForecastDailyViewModel
    private lateinit var subjectWithoutForecastTime: ForecastDailyViewModel
    private lateinit var subjectWithForecastTime: ForecastDailyViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)

        subjectWithoutArgs = ForecastDailyViewModel(
            savedStateHandle = SavedStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithoutForecastTime = ForecastDailyViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsForecast.Daily.KEY_LATITUDE to LATITUDE.toString(),
                    DestinationsForecast.Daily.KEY_LONGITUDE to LONGITUDE.toString()
                )
            ),
            oneCallRepository = oneCallRepository
        )
        subjectWithForecastTime = ForecastDailyViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsForecast.Daily.KEY_FORECAST_TIME to FORECAST_TIME.toString(),
                    DestinationsForecast.Daily.KEY_LATITUDE to LATITUDE.toString(),
                    DestinationsForecast.Daily.KEY_LONGITUDE to LONGITUDE.toString()
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
    fun uiState_viewDataResource_shouldBeSuccessWithoutForecastTime() = testScope.runTest {
        // Arrange + Act
        subjectWithoutForecastTime.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.viewDataResource)

                val viewData = uiState.viewDataResource.dataOrNull!!

                assertListIsNotEmpty(viewData.items)

                assertNull(viewData.initialKey)
            }
        )
    }

    @Test
    fun uiState_viewDataResource_shouldBeSuccessWithForecastTime() = testScope.runTest {
        // Arrange + Act
        subjectWithForecastTime.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.viewDataResource)

                val viewData = uiState.viewDataResource.dataOrNull!!

                assertListIsNotEmpty(viewData.items)

                assertNotNull(viewData.initialKey)
                assertEquals(FORECAST_TIME, viewData.initialKey)
            }
        )
    }

}