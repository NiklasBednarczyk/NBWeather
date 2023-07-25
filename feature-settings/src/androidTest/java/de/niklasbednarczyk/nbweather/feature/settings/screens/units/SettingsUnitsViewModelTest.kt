package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SettingsUnitsViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsUnitsViewModel

    @Before
    override fun setUp() {
        subject = SettingsUnitsViewModel(
            settingsUnitsRepository = SettingsUnitsRepository.createFake(temporaryFolder)
        )
    }

    @Test
    fun uiState_items_shouldHaveCorrectDividers() = testScope.runTest {
        subject.uiState.collectUntil(
            stopCollecting = { uiState ->
                uiState.items.isNotEmpty()
            },
            collectData = { uiState ->
                testDividerList(
                    items = uiState.items,
                    dividerClassJava = SettingsListItemModel.Divider::class.java,
                    headerClassJava = SettingsListItemModel.Header::class.java
                )
            }
        )
    }

}