package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
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

class SettingsThemeViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsThemeViewModel

    private lateinit var settingsAppearanceRepository: SettingsAppearanceRepository

    @Before
    override fun setUp() {
        settingsAppearanceRepository = SettingsAppearanceRepository.createFake(temporaryFolder)
        subject = SettingsThemeViewModel(
            settingsAppearanceRepository = settingsAppearanceRepository
        )
    }

    @Test
    fun uiState_shouldHaveCorrectValues() = testScope.runTest {
        // Arrange
        val settingsAppearance = settingsAppearanceRepository.getData().first()
        val expectedSize = ThemeTypeData.values().size

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.radioGroup != null
            },
            collectData = { uiState ->
                // Assert
                assertEquals(uiState.radioGroup?.selectedKey, settingsAppearance.theme)
                assertListHasSize(uiState.radioGroup?.options, expectedSize)
            }
        )
    }

    @Test
    fun updateTheme_shouldUpdateCorrectly() = testScope.runTest {
        // Arrange
        val settingsAppearanceArrange = settingsAppearanceRepository.getData().first()
        val newValue = ThemeTypeData.DARK

        // Act
        subject.updateTheme(newValue)
        settingsAppearanceRepository.getData().collectUntil(
            stopCollecting = { settingsAppearanceAct ->
                settingsAppearanceAct.theme == newValue
            },
            collectData = { settingsAppearanceAct ->
                // Assert
                assertEquals(settingsAppearanceAct.theme, newValue)
                assertNotEquals(settingsAppearanceArrange.theme, settingsAppearanceAct.theme)
            }
        )
    }

}