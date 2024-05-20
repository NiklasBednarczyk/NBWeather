package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models.SettingsAppearanceItem
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsAppearanceScreenTest : NBComposableTest() {

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
            onNodeWithText(R.string.screen_settings_appearance_title)
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
        var selectedSegmentControlKey: NBThemeType? = null
        var isSwitchChecked = false

        val header = SettingsAppearanceItem.Header(
            text = createNBString("Header")
        )

        val segmentedControl = SettingsAppearanceItem.SegmentedControl(
            segmentedControl = NBSegmentedControlModel(
                selectedKey = selectedSegmentControlKey,
                buttons = NBThemeType.entries.map { theme ->
                    NBSegmentedButtonModel(
                        key = theme,
                        text = theme.displayText
                    )
                },
                onItemSelected = { theme ->
                    selectedSegmentControlKey = theme
                }
            )
        )

        val switch = SettingsAppearanceItem.SwitchItem(
            title = createNBString("SwitchItem Title"),
            value = createNBString("SwitchItem Value"),
            checked = isSwitchChecked,
            onCheckedChange = { checked ->
                isSwitchChecked = checked
            }
        )

        val uiState = createTestUiState(
            items = listOf(
                header,
                segmentedControl,
                switch
            )
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(header.text)
                .assertIsDisplayed()

            onNodeWithText(NBThemeType.DARK.displayText)
                .assertIsDisplayed()
            onNodeWithText(NBThemeType.LIGHT.displayText)
                .assertIsDisplayed()
                .performClick()

            onNodeWithText(switch.title)
                .assertIsDisplayed()
                .performClick()
        }

        assertNotNull(selectedSegmentControlKey)
        assertEquals(NBThemeType.LIGHT, selectedSegmentControlKey)

        assertTrue(isSwitchChecked)
    }

    private fun createTestUiState(
        items: List<SettingsAppearanceItem> = emptyList()
    ): SettingsAppearanceUiState {
        return SettingsAppearanceUiState(
            items = items
        )
    }

    private fun setScreenContent(
        uiState: SettingsAppearanceUiState = createTestUiState(),
        popBackStack: () -> Unit = {}
    ) {
        setContent {
            SettingsAppearanceScreen(
                uiState = uiState,
                popBackStack = popBackStack
            )
        }
    }

}