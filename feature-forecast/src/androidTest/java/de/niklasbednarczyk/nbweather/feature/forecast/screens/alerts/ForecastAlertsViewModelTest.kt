package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast
import de.niklasbednarczyk.nbweather.feature.forecast.screens.ForecastViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertNull

class ForecastAlertsViewModelTest : ForecastViewModelTest {

    private lateinit var subjectWithoutArgs: ForecastAlertsViewModel
    private lateinit var subjectWithArgs: ForecastAlertsViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)

        subjectWithoutArgs = ForecastAlertsViewModel(
            savedStateHandle = SavedStateHandle(),
            oneCallRepository = oneCallRepository
        )
        subjectWithArgs = ForecastAlertsViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsForecast.Alerts.KEY_LATITUDE to latitude.toString(),
                    DestinationsForecast.Alerts.KEY_LONGITUDE to longitude.toString()
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

                assertListIsNotEmpty(viewData.items)

                assertNull(viewData.initialKey)
            }
        )
    }

}