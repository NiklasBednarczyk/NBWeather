package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNull

class ForecastAlertsViewModelTest : NBForecastViewModelTest {

    private lateinit var subjectWithoutArgsAndWithoutOneCall: ForecastAlertsViewModel
    private lateinit var subjectWithoutArgsAndWithOneCall: ForecastAlertsViewModel
    private lateinit var subjectWithArgsAndWithoutOneCall: ForecastAlertsViewModel
    private lateinit var subjectWithArgsAndWithOneCall: ForecastAlertsViewModel

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

            subjectWithoutArgsAndWithoutOneCall = ForecastAlertsViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithoutArgsAndWithOneCall = ForecastAlertsViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithOneCall
            )
            subjectWithArgsAndWithoutOneCall = ForecastAlertsViewModel(
                savedStateHandle = savedStateHandleWithArgs,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithArgsAndWithOneCall = ForecastAlertsViewModel(
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

    private suspend fun ForecastAlertsViewModel.testUiStateViewDataResourceExpectedError() {
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsError(uiState.viewDataResource)
            }
        )
    }


    private suspend fun ForecastAlertsViewModel.testUiStateViewDataResourceExpectedSuccess() {
        // Arrange + Act
        uiState.collectUntil(
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

}