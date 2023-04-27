package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SettingsTimeFormatViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsTimeFormatViewModel

    private lateinit var settingsDataRepository: SettingsDataRepository

    @Before
    override fun setUp() {
        settingsDataRepository = SettingsDataRepository.createFake(temporaryFolder, context)
        subject = SettingsTimeFormatViewModel(
            settingsDataRepository = settingsDataRepository
        )
    }

    @Test
    fun uiState_shouldHaveCorrectValues() = testScope.runTest {
        // Arrange
        val settingsData = settingsDataRepository.getData().first()
        val expectedSize = NBTimeFormatType.values().size

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.radioGroup != null
            },
            collectData = { uiState ->
                // Assert
                assertEquals(uiState.radioGroup?.selectedKey, settingsData.timeFormat)
                assertListHasSize(uiState.radioGroup?.options, expectedSize)
            }
        )
    }

    @Test
    fun updateTimeFormat_shouldUpdateCorrectly() = testScope.runTest {
        // Arrange
        val settingsDataArrange = settingsDataRepository.getData().first()
        val newValue = NBTimeFormatType.HOUR_24

        // Act
        subject.updateTimeFormat(newValue)
        settingsDataRepository.getData().collectUntil(
            stopCollecting = { settingsDataAct ->
                settingsDataAct.timeFormat == newValue
            },
            collectData = { settingsDataAct ->
                // Assert
                assertEquals(settingsDataAct.timeFormat, newValue)
                assertNotEquals(settingsDataArrange.timeFormat, settingsDataAct.timeFormat)
            }
        )
    }

}