package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ForecastHourlyViewModelTest : NBForecastViewModelTest {

    private lateinit var subjectWithoutArgs: ForecastHourlyViewModel
    private lateinit var subjectWithArgs: ForecastHourlyViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)

        subjectWithoutArgs = ForecastHourlyViewModel(
            savedStateHandle = SavedStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithArgs = ForecastHourlyViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsForecast.Hourly.KEY_LATITUDE to latitude.toString(),
                    DestinationsForecast.Hourly.KEY_LONGITUDE to longitude.toString()
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

                val viewData = uiState.viewDataResource.dataOrNull!!

                assertListIsNotEmpty(viewData.axes)

                assertListIsNotEmpty(viewData.graphs)
                viewData.graphs.forEach { graph ->
                    assertEquals(viewData.axes.size, graph.size)
                }
            }
        )
    }

}