package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.models.SettingsUnitsItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsUnitsScreenTest : NBComposableTest() {

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
            onNodeWithText(R.string.screen_settings_units_title)
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
        var selectedSegmentControlKey: NBTemperatureUnitType? = null

        val item = SettingsUnitsItemModel(
            headerText = createNBString("Header"),
            segmentedControl = NBSegmentedControlModel(
                selectedKey = selectedSegmentControlKey,
                buttons = NBTemperatureUnitType.entries.map { temperatureUnit ->
                    NBSegmentedButtonModel(
                        key = temperatureUnit,
                        text = temperatureUnit.symbol
                    )
                },
                onItemSelected = { temperatureUnit ->
                    selectedSegmentControlKey = temperatureUnit
                }
            )
        )

        val uiState = createTestUiState(
            item = item
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(item.headerText)
                .assertIsDisplayed()

            onNodeWithText(NBTemperatureUnitType.CELSIUS.symbol)
                .assertIsDisplayed()
            onNodeWithText(NBTemperatureUnitType.FAHRENHEIT.symbol)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(NBTemperatureUnitType.KELVIN.symbol)
                .assertIsDisplayed()
        }

        assertNotNull(selectedSegmentControlKey)
        assertEquals(NBTemperatureUnitType.FAHRENHEIT, selectedSegmentControlKey)
    }

    private fun createTestUiState(
        item: SettingsUnitsItemModel? = null
    ): SettingsUnitsUiState {
        return SettingsUnitsUiState(
            items = listOfNotNull(item)
        )
    }

    private fun setScreenContent(
        uiState: SettingsUnitsUiState = createTestUiState(),
        popBackStack: () -> Unit = {}
    ) {
        setContent {
            SettingsUnitsScreen(
                uiState = uiState,
                popBackStack = popBackStack
            )
        }
    }

}
