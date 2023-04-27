package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SettingsOverviewViewModelTest : NBViewModelTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsOverviewViewModel

    @Before
    override fun setUp() {
        subject = SettingsOverviewViewModel(
            settingsAppearanceRepository = SettingsAppearanceRepository.createFake(temporaryFolder),
            settingsDataRepository = SettingsDataRepository.createFake(temporaryFolder, context)
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
                    dividerClassJava = SettingsOverviewItemModel.Divider::class.java,
                    headerClassJava = SettingsOverviewItemModel.Header::class.java
                )
            }
        )
    }

}