package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
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
            savedStateHandle = createTestSaveStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithArgs = ForecastHourlyViewModel(
            savedStateHandle = createTestSaveStateHandle(
                NBArgumentKeys.Latitude to coordinates.latitude,
                NBArgumentKeys.Longitude to coordinates.longitude
            ),
            oneCallRepository = oneCallRepository
        )
    }

    @Test
    fun uiState_viewDataResource_withoutArgs_shouldBeError() = testScope.runTest {
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
    fun uiState_viewDataResource_withArgs_shouldBeSuccess() = testScope.runTest {
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