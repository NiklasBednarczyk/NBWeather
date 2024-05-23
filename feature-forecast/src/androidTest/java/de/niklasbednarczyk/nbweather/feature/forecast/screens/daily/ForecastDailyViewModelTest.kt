package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

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
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastDailyViewModelTest : NBForecastViewModelTest {

    private lateinit var subjectWithoutArgsAndWithoutOneCall: ForecastDailyViewModel
    private lateinit var subjectWithoutArgsAndWithOneCall: ForecastDailyViewModel
    private lateinit var subjectWithoutForecastTimeAndWithoutOneCall: ForecastDailyViewModel
    private lateinit var subjectWithoutForecastTimeAndWithOneCall: ForecastDailyViewModel
    private lateinit var subjectWithForecastTimeAndWithoutOneCall: ForecastDailyViewModel
    private lateinit var subjectWithForecastTimeAndWithOneCall: ForecastDailyViewModel

    @Before
    override fun setUp() = testScope.runTest {
        val oneCallRepositoryWithoutOneCall = OneCallRepository.createFake(context)
        val oneCallRepositoryWithOneCall = OneCallRepository.createFake(context)

        oneCallRepositoryWithOneCall.getOneCall(
            coordinates = coordinates
        ).nbCollectUntilResource {
            val savedStateHandleWithoutArgs = createTestSaveStateHandle()
            val savedStateHandleWithoutForecastTime = createTestSaveStateHandle(
                NBArgumentKeys.Latitude to coordinates.latitude,
                NBArgumentKeys.Longitude to coordinates.longitude
            )
            val savedStateHandleWithForecastTime = createTestSaveStateHandle(
                NBArgumentKeys.ForecastTime to forecastTime,
                NBArgumentKeys.Latitude to coordinates.latitude,
                NBArgumentKeys.Longitude to coordinates.longitude
            )

            subjectWithoutArgsAndWithoutOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithoutArgsAndWithOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithoutArgs,
                oneCallRepository = oneCallRepositoryWithOneCall
            )
            subjectWithoutForecastTimeAndWithoutOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithoutForecastTime,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithoutForecastTimeAndWithOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithoutForecastTime,
                oneCallRepository = oneCallRepositoryWithOneCall
            )
            subjectWithForecastTimeAndWithoutOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithForecastTime,
                oneCallRepository = oneCallRepositoryWithoutOneCall
            )
            subjectWithForecastTimeAndWithOneCall = ForecastDailyViewModel(
                savedStateHandle = savedStateHandleWithForecastTime,
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
    fun uiState_viewDataResource_withoutForecastTime_withoutOneCall_shouldBeError() =
        testScope.runTest {
            subjectWithoutForecastTimeAndWithoutOneCall.testUiStateViewDataResourceExpectedError()
        }

    @Test
    fun uiState_viewDataResource_withoutForecastTime_withOneCall_shouldBeError() =
        testScope.runTest {
            subjectWithoutForecastTimeAndWithOneCall.testUiStateViewDataResourceExpectedSuccess(
                expectedInitialKeyIsSet = false
            )
        }

    @Test
    fun uiState_viewDataResource_withForecastTime_withoutOneCall_shouldBeError() =
        testScope.runTest {
            subjectWithForecastTimeAndWithoutOneCall.testUiStateViewDataResourceExpectedError()
        }

    @Test
    fun uiState_viewDataResource_withForecastTime_withOneCall_shouldBeError() = testScope.runTest {
        subjectWithForecastTimeAndWithOneCall.testUiStateViewDataResourceExpectedSuccess(
            expectedInitialKeyIsSet = true
        )
    }

    private suspend fun ForecastDailyViewModel.testUiStateViewDataResourceExpectedError() {
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsError(uiState.viewDataResource)
            }
        )
    }

    private suspend fun ForecastDailyViewModel.testUiStateViewDataResourceExpectedSuccess(
        expectedInitialKeyIsSet: Boolean
    ) {
        uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.viewDataResource.isSuccessOrError
            },
            collectData = { uiState ->
                assertResourceIsSuccess(uiState.viewDataResource)

                val viewData = uiState.viewDataResource.dataOrNull!!

                assertListIsNotEmpty(viewData.items)

                if (expectedInitialKeyIsSet) {
                    assertNotNull(viewData.initialKey)
                    assertEquals(forecastTime, viewData.initialKey)
                } else {
                    assertNull(viewData.initialKey)
                }
            }
        )
    }

}