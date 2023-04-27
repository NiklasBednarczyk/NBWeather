package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import androidx.lifecycle.SavedStateHandle
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.isSuccessOrError
import de.niklasbednarczyk.nbweather.data.onecall.repositories.OneCallRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LocationAlertsViewModelTest : NBViewModelTest {

    companion object {
        private const val LAT_AND_LON = 50.0
    }

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subjectWithoutArgs: LocationAlertsViewModel
    private lateinit var subjectWithArgs: LocationAlertsViewModel

    @Before
    override fun setUp() {
        val oneCallRepository = OneCallRepository.createFake(context)
        val settingsDataRepository = SettingsDataRepository.createFake(temporaryFolder, context)

        subjectWithoutArgs = LocationAlertsViewModel(
            savedStateHandle = SavedStateHandle(),
            oneCallRepository = oneCallRepository,
            settingsDataRepository = settingsDataRepository
        )
        subjectWithArgs = LocationAlertsViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    DestinationsLocation.Alerts.KEY_LATITUDE to LAT_AND_LON.toString(),
                    DestinationsLocation.Alerts.KEY_LONGITUDE to LAT_AND_LON.toString()
                )
            ),
            oneCallRepository = oneCallRepository,
            settingsDataRepository = settingsDataRepository
        )
    }


    @Test
    fun uiState_alertsResource_shouldBeErrorWithoutArguments() = testScope.runTest {
        // Arrange + Act
        subjectWithoutArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.alertsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsError(uiState.alertsResource)
            }
        )
    }

    @Test
    fun uiState_alertsResource_shouldBeSuccessWithArguments() = testScope.runTest {
        // Arrange + Act
        subjectWithArgs.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.alertsResource.isSuccessOrError
            },
            collectData = { uiState ->
                // Assert
                assertResourceIsSuccess(uiState.alertsResource)
                assertListIsNotEmpty(uiState.alertsResource?.dataOrNull)
            }
        )
    }


}