package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals


class AboutOverviewContentTest : NBContentTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        val uiState = AboutOverviewUiState()

        var clickCounterGithub = 0
        var clickCounterWebsite = 0
        var clickCounterElse = 0

        val countGithubButtons = 2
        val countWebsiteButtons = 2

        // Act
        setContent {
            AboutOverviewContent(
                uiState = uiState,
                onIntent = { intent ->
                    val data = intent.data
                    if (data != null) {
                        if (data.toString().startsWith("https://github.com")) {
                            clickCounterGithub++
                        } else if (data.toString().startsWith("https://")) {
                            clickCounterWebsite++
                        } else {
                            clickCounterElse++
                        }
                    } else {
                        clickCounterElse++
                    }
                }
            )
        }

        // Assert
        assertCompose {
            onAllNodesWithText(R.string.screen_about_overview_button_git_hub)
                .assertCountEquals(countGithubButtons)
                .assertAll(hasClickAction())
                .performClickOnAllNodes()
            onAllNodesWithText(R.string.screen_about_overview_button_website)
                .assertCountEquals(countWebsiteButtons)
                .assertAll(hasClickAction())
                .performClickOnAllNodes()

            onAllNodesWithText(R.string.screen_about_overview_text_erik_flowers)
                .assertCountEquals(1)
                .assertAll(hasNoClickAction())
            onAllNodesWithText(R.string.screen_about_overview_text_niklas_bednarczyk)
                .assertCountEquals(1)
                .assertAll(hasNoClickAction())
            onAllNodesWithText(R.string.screen_about_overview_text_open_weather)
                .assertCountEquals(1)
                .assertAll(hasNoClickAction())
        }
        assertEquals(countGithubButtons, clickCounterGithub)
        assertEquals(countWebsiteButtons, clickCounterWebsite)
        assertEquals(0, clickCounterElse)
    }


}