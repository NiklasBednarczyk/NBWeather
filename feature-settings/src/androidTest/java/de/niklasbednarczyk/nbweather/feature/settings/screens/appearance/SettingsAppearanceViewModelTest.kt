package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models.SettingsAppearanceItem
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SettingsAppearanceViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsAppearanceViewModel

    @Before
    override fun setUp() {
        subject = SettingsAppearanceViewModel(
            settingsAppearanceRepository = SettingsAppearanceRepository.createFake(
                temporaryFolder,
                context
            )
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
                    dividerKlass = SettingsAppearanceItem.Divider::class.java,
                    headerKlass = SettingsAppearanceItem.Header::class.java
                )
            }
        )
    }

}