package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ForecastHourlyViewModelTest : NBForecastViewModelTest {

    private lateinit var subjectWithoutArgsAndWithoutOneCall: ForecastHourlyViewModel
    private lateinit var subjectWithoutArgsAndWithOneCall: ForecastHourlyViewModel
    private lateinit var subjectWithArgsAndWithoutOneCall: ForecastHourlyViewModel
    private lateinit var subjectWithArgsAndWithOneCall: ForecastHourlyViewModel

    @Before
    override fun setUp() = testScope.runTest {
        val oneCallRepositoryWithoutOneCall = OneCallRepository.createFake(context)
        val oneCallRepositoryWithOneCall = OneCallRepository.createFake(context)

        oneCallRepositoryWithOneCall.getOneCall(
            coordinates = coordinates
        ).nbCollectUntilResource {
            val savedStateHandleWithoutArgs = createTestSaveStateHandle()
            val savedStateHandleWithArgs = createTestSaveStateHandle(
                NBArgumentKeys.Latitude to coordinates.latitude,
                NBArgumentKeys.Longitude to coordinates.longitude
            )

            subjectWithoutArgsAndWithoutOneCall = ForecastHourlyViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithoutArgsAndWithOneCall = ForecastHourlyViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithOneCall
            )
            subjectWithArgsAndWithoutOneCall = ForecastHourlyViewModel(
                savedStateHandle = savedStateHandleWithArgs,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithArgsAndWithOneCall = ForecastHourlyViewModel(
                savedStateHandle = savedStateHandleWithArgs,
                oneCallRepository = oneCallRepositoryWithOneCall
            )
        }
    }

    @Test
    fun uiState_viewDataResource_withoutArgs_withoutOneCall_shouldBeError() = testScope.runTest {
        subjectWithoutArgsAndWithoutOneCall.testUiStateViewDataResourceExpectedError()
    }

    @Test
    fun uiState_viewDataResource_withoutArgs_withOneCall_shouldBeError() = testScope.runTest {
        subjectWithoutArgsAndWithOneCall.testUiStateViewDataResourceExpectedError()
    }

    @Test
    fun uiState_viewDataResource_withArgs_withoutOneCall_shouldBeError() = testScope.runTest {
        subjectWithArgsAndWithoutOneCall.testUiStateViewDataResourceExpectedError()
    }

    @Test
    fun uiState_viewDataResource_withArgs_withOneCall_shouldBeSuccess() = testScope.runTest {
        subjectWithArgsAndWithOneCall.testUiStateViewDataResourceExpectedSuccess()
    }

    private suspend fun ForecastHourlyViewModel.testUiStateViewDataResourceExpectedError() {
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsError(uiState.viewDataResource)
            }
        )
    }


    private suspend fun ForecastHourlyViewModel.testUiStateViewDataResourceExpectedSuccess() {
        // Arrange + Act
        uiState.collectUntil(
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