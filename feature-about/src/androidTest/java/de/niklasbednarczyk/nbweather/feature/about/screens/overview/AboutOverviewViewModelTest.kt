package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import de.niklasbednarczyk.nbweather.core.common.flow.collectUntil
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AboutOverviewViewModelTest : NBViewModelTest {

    private lateinit var subject: AboutOverviewViewModel

    @Before
    override fun setUp() {
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