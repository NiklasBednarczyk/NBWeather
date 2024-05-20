package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import android.content.Intent
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.image.NBImages
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewButtonModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertTrue


class AboutOverviewScreenTest : NBComposableTest() {

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        var popBackStackCalled = false

        // Act
        setScreenContent(
            popBackStack = {
                popBackStackCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_about_overview_title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Back)
                .assertIsDisplayed()
                .performClick()

            assertTrue(popBackStackCalled)
        }
    }

    @Test
    fun content_items_shouldRenderCorrectly() {
        // Arrange
        val creditWithBanner = createTestCredit(true)
        val creditWithoutBanner = createTestCredit(false)

        val uiState = createTestUiState(
            items = listOf(creditWithBanner, creditWithoutBanner)
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithImage(creditWithBanner.banner!!)
                .assertIsDisplayed()
                .assertHasNoClickAction()
            onNodeWithText(creditWithBanner.text)
                .assertIsDisplayed()
                .assertHasNoClickAction()
            creditWithBanner.buttons.forEach { button ->
                onNodeWithText(button.label)
                    .assertIsDisplayed()
                    .assertHasClickAction()
                onNodeWithIcon(button.icon)
                    .assertIsDisplayed()
                    .assertHasClickAction()
            }

            onNodeWithText(creditWithoutBanner.text)
                .assertIsDisplayed()
                .assertHasNoClickAction()
            creditWithoutBanner.buttons.forEach { button ->
                onNodeWithText(button.label)
                    .assertIsDisplayed()
                    .assertHasClickAction()
                onNodeWithIcon(button.icon)
                    .assertIsDisplayed()
                    .assertHasClickAction()
            }
        }

    }

    private fun createTestCredit(
        isWithBanner: Boolean
    ): AboutOverviewItem.Credit {
        val prefix = if (isWithBanner) "WithBanner" else "WithoutBanner"
        val banner = if (isWithBanner) NBImages.OpenWeather else null
        val icon1 = if (isWithBanner) NBIcons.MaxTemperature else NBIcons.MinTemperature
        val icon2 = if (isWithBanner) NBIcons.MoonPhaseFull else NBIcons.MoonPhaseNew

        val intent = Intent()

        return AboutOverviewItem.Credit(
            banner = banner,
            text = createNBString("$prefix text"),
            buttons = listOf(
                AboutOverviewButtonModel(
                    label = createNBString("$prefix label 1"),
                    icon = icon1,
                    intent = intent
                ),
                AboutOverviewButtonModel(
                    label = createNBString("$prefix label 2"),
                    icon = icon2,
                    intent = intent
                )
            )
        )
    }

    private fun createTestUiState(
        items: List<AboutOverviewItem> = emptyList()
    ): AboutOverviewUiState {
        return AboutOverviewUiState(
            items = items
        )
    }

    private fun setScreenContent(
        uiState: AboutOverviewUiState = createTestUiState(),
        popBackStack: () -> Unit = {}
    ) {
        setContent {
            AboutOverviewScreen(
                uiState = uiState,
                popBackStack = popBackStack
            )
        }
    }

}