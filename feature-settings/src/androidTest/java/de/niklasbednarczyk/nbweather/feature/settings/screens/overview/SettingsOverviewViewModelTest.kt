package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBUiTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import javax.inject.Inject

@HiltAndroidTest
class SettingsOverviewViewModelTest : NBUiTest {

    @Inject
    lateinit var settingsAppearanceRepository: SettingsAppearanceRepository

    @Inject
    lateinit var settingsDataRepository: SettingsDataRepository

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: SettingsOverviewViewModel

    @Before
    override fun setUp() {
        super.setUp()
        subject = SettingsOverviewViewModel(
            settingsAppearanceRepository = settingsAppearanceRepository,
            settingsDataRepository = settingsDataRepository
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