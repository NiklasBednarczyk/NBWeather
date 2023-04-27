package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
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

class SettingsColorSchemeViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsColorSchemeViewModel

    private lateinit var settingsAppearanceRepository: SettingsAppearanceRepository

    @Before
    override fun setUp() {
        settingsAppearanceRepository = SettingsAppearanceRepository.createFake(temporaryFolder)
        subject = SettingsColorSchemeViewModel(
            settingsAppearanceRepository = settingsAppearanceRepository
        )
    }

    @Test
    fun uiState_shouldHaveCorrectValues() = testScope.runTest {
        // Arrange
        val settingsAppearance = settingsAppearanceRepository.getData().first()
        val expectedSize = ColorSchemeTypeData.values().size

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.radioGroup != null
            },
            collectData = { uiState ->
                // Assert
                assertEquals(uiState.radioGroup?.selectedKey, settingsAppearance.colorScheme)
                assertListHasSize(uiState.radioGroup?.options, expectedSize)
            }
        )
    }

    @Test
    fun updateColorScheme_shouldUpdateCorrectly() = testScope.runTest {
        // Arrange
        val settingsAppearanceArrange = settingsAppearanceRepository.getData().first()
        val newValue = ColorSchemeTypeData.BLUE

        // Act
        subject.updateColorScheme(newValue)
        settingsAppearanceRepository.getData().collectUntil(
            stopCollecting = { settingsAppearanceAct ->
                settingsAppearanceAct.colorScheme == newValue
            },
            collectData = { settingsAppearanceAct ->
                // Assert
                assertEquals(settingsAppearanceAct.colorScheme, newValue)
                assertNotEquals(
                    settingsAppearanceArrange.colorScheme,
                    settingsAppearanceAct.colorScheme
                )
            }
        )
    }

}