package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals


class AboutOverviewContentTest : NBComposableTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        val uiState = AboutOverviewUiState()

        var clickCounterGithub = 0
        var clickCounterWebsite = 0
        var clickCounterUnknown = 0

        val countGithubButtons = 2
        val countWebsiteButtons = 3

        // Act
        setContent {
            AboutOverviewContent(
                uiState = uiState,
                startIntent = { intent ->
                    val dataString = intent.dataString
                    if (dataString != null) {
                        if (dataString.startsWith("https://github.com")) {
                            clickCounterGithub++
                        } else if (dataString.startsWith("https://")) {
                            clickCounterWebsite++
                        } else {
                            clickCounterUnknown++
                        }
                    } else {
                        clickCounterUnknown++
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
        assertEquals(0, clickCounterUnknown)
    }


}