package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
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

    private lateinit var subjectWithoutArgs: ForecastDailyViewModel
    private lateinit var subjectWithoutForecastTime: ForecastDailyViewModel
    private lateinit var subjectWithForecastTime: ForecastDailyViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)

        subjectWithoutArgs = ForecastDailyViewModel(
            savedStateHandle = createTestSaveStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithoutForecastTime = ForecastDailyViewModel(
            savedStateHandle = createTestSaveStateHandle(
                NBArgumentKeys.Latitude to latitude,
                NBArgumentKeys.Longitude to longitude
            ),
            oneCallRepository = oneCallRepository
        )
        subjectWithForecastTime = ForecastDailyViewModel(
            savedStateHandle = createTestSaveStateHandle(
                NBArgumentKeys.ForecastTime to forecastTime,
                NBArgumentKeys.Latitude to latitude,
                NBArgumentKeys.Longitude to longitude
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
    fun uiState_viewDataResource_withoutForecastTime_shouldBeSuccess() = testScope.runTest {
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
    fun uiState_viewDataResource_withForecastTime_shouldBeSuccess() = testScope.runTest {
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
                assertEquals(forecastTime, viewData.initialKey)
            }
        )
    }

}