package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBUiTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@HiltAndroidTest
class SettingsThemeViewModelTest : NBUiTest {

    @Inject
    lateinit var settingsAppearanceRepository: SettingsAppearanceRepository

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsThemeViewModel

    @Before
    override fun setUp() {
        super.setUp()
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