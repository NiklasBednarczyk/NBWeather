package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemType
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingsOverviewScreenTest : NBComposableTest() {

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
            onNodeWithText(R.string.screen_settings_overview_title)
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
        var navigateToSettingsAppearanceCalled = false
        var navigateToSettingsFontCalled = false
        var navigateToSettingsOrderCalled = false
        var navigateToSettingsUnitsCalled = false

        val items = listOf(
            SettingsOverviewItemType.UNITS,
            SettingsOverviewItemType.ORDER,
            SettingsOverviewItemType.FONT,
            SettingsOverviewItemType.APPEARANCE
        )

        val uiState = createTestUiState(
            items = items
        )

        // Act
        setScreenContent(
            uiState = uiState,
            navigateToSettingsAppearance = {
                navigateToSettingsAppearanceCalled = true
                navigateToSettingsFontCalled = false
                navigateToSettingsOrderCalled = false
                navigateToSettingsUnitsCalled = false
            },
            navigateToSettingsFont = {
                navigateToSettingsAppearanceCalled = false
                navigateToSettingsFontCalled = true
                navigateToSettingsOrderCalled = false
                navigateToSettingsUnitsCalled = false
            },
            navigateToSettingsOrder = {
                navigateToSettingsAppearanceCalled = false
                navigateToSettingsFontCalled = false
                navigateToSettingsOrderCalled = true
                navigateToSettingsUnitsCalled = false
            },
            navigateToSettingsUnits = {
                navigateToSettingsAppearanceCalled = false
                navigateToSettingsFontCalled = false
                navigateToSettingsOrderCalled = false
                navigateToSettingsUnitsCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(SettingsOverviewItemType.APPEARANCE.title)
                .assertIsDisplayed()
                .performClick()

            assertTrue(navigateToSettingsAppearanceCalled)
            assertFalse(navigateToSettingsFontCalled)
            assertFalse(navigateToSettingsOrderCalled)
            assertFalse(navigateToSettingsUnitsCalled)

            onNodeWithText(SettingsOverviewItemType.FONT.title)
                .assertIsDisplayed()
                .performClick()

            assertFalse(navigateToSettingsAppearanceCalled)
            assertTrue(navigateToSettingsFontCalled)
            assertFalse(navigateToSettingsOrderCalled)
            assertFalse(navigateToSettingsUnitsCalled)

            onNodeWithText(SettingsOverviewItemType.ORDER.title)
                .assertIsDisplayed()
                .performClick()

            assertFalse(navigateToSettingsAppearanceCalled)
            assertFalse(navigateToSettingsFontCalled)
            assertTrue(navigateToSettingsOrderCalled)
            assertFalse(navigateToSettingsUnitsCalled)

            onNodeWithText(SettingsOverviewItemType.UNITS.title)
                .assertIsDisplayed()
                .performClick()

            assertFalse(navigateToSettingsAppearanceCalled)
            assertFalse(navigateToSettingsFontCalled)
            assertFalse(navigateToSettingsOrderCalled)
            assertTrue(navigateToSettingsUnitsCalled)
        }

    }

    private fun createTestUiState(
        items: List<SettingsOverviewItemType> = emptyList()
    ): SettingsOverviewUiState {
        return SettingsOverviewUiState(
            items = items
        )
    }

    private fun setScreenContent(
        uiState: SettingsOverviewUiState = createTestUiState(),
        popBackStack: () -> Unit = {},
        navigateToSettingsAppearance: () -> Unit = {},
        navigateToSettingsFont: () -> Unit = {},
        navigateToSettingsOrder: () -> Unit = {},
        navigateToSettingsUnits: () -> Unit = {}
    ) {
        setContent {
            SettingsOverviewScreen(
                uiState = uiState,
                popBackStack = popBackStack,
                navigateToSettingsAppearance = navigateToSettingsAppearance,
                navigateToSettingsFont = navigateToSettingsFont,
                navigateToSettingsOrder = navigateToSettingsOrder,
                navigateToSettingsUnits = navigateToSettingsUnits
            )
        }
    }

}
