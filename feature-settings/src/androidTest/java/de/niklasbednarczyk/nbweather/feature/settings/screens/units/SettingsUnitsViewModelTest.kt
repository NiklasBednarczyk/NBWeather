package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
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
class SettingsUnitsViewModelTest : NBUiTest {

    @Inject
    lateinit var settingsDataRepository: SettingsDataRepository

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsUnitsViewModel

    @Before
    override fun setUp() {
        super.setUp()
        subject = SettingsUnitsViewModel(
            settingsDataRepository = settingsDataRepository
        )
    }

    @Test
    fun uiState_shouldHaveCorrectValues() = testScope.runTest {
        // Arrange
        val settingsData = settingsDataRepository.getData().first()
        val expectedSize = NBUnitsType.values().size

        // Act
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.radioGroup != null
            },
            collectData = { uiState ->
                // Assert
                assertEquals(uiState.radioGroup?.selectedKey, settingsData.units)
                assertListHasSize(uiState.radioGroup?.options, expectedSize)
            }
        )
    }

    @Test
    fun updateUnits_shouldUpdateCorrectly() = testScope.runTest {
        // Arrange
        val settingsDataArrange = settingsDataRepository.getData().first()
        val newValue = NBUnitsType.IMPERIAL

        // Act
        subject.updateUnits(newValue)
        settingsDataRepository.getData().collectUntil(
            stopCollecting = { settingsDataAct ->
                settingsDataAct.units == newValue
            },
            collectData = { settingsDataAct ->
                // Assert
                assertEquals(settingsDataAct.units, newValue)
                assertNotEquals(settingsDataArrange.units, settingsDataAct.units)
            }
        )
    }

}