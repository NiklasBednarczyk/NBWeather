package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBUiTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@HiltAndroidTest
class AboutOverviewViewModelTest : NBUiTest {

    @BindValue
    @get:Rule(order = NBUiTest.TEMPORARY_FOLDER_ORDER)
    override val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    private lateinit var subject: AboutOverviewViewModel

    @Before
    override fun setUp() {
        super.setUp()
        subject = AboutOverviewViewModel()
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
                    dividerClassJava = AboutOverviewItem.Divider::class.java
                )
            }
        )
    }

}