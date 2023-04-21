package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
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
class SettingsLanguageViewModelTest : NBUiTest {

    @Inject
    lateinit var settingsDataRepository: SettingsDataRepository

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsLanguageViewModel

    @Before
    override fun setUp() {
        super.setUp()
        subject = SettingsLanguageViewModel(
            settingsDataRepository = settingsDataRepository
        )
    }

    @Test
    fun uiState_shouldHaveCorrectValues() = testScope.runTest {
        // Arrange
        val settingsData = settingsDataRepository.getData().first()
        val expectedSize = NBLanguageType.values().size

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.radioGroup != null
            },
            collectData = { uiState ->
                // Assert
                assertEquals(uiState.radioGroup?.selectedKey, settingsData.language)
                assertListHasSize(uiState.radioGroup?.options, expectedSize)
            }
        )
    }

    @Test
    fun updateLanguage_shouldUpdateCorrectly() = testScope.runTest {
        // Arrange
        val settingsDataArrange = settingsDataRepository.getData().first()
        val newValue = NBLanguageType.GERMAN

        // Act
        subject.updateLanguage(newValue)
        settingsDataRepository.getData().collectUntil(
            stopCollecting = { settingsDataAct ->
                settingsDataAct.language == newValue
            },
            collectData = { settingsDataAct ->
                // Assert
                assertEquals(settingsDataAct.language, newValue)
                assertNotEquals(settingsDataArrange.language, settingsDataAct.language)
            }
        )
    }

}