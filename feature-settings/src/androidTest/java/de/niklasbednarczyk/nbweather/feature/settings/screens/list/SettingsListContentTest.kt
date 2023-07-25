package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsListContentTest : NBContentTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        var selectedButton: NBTemperatureUnitType? = null
        var selectedDestination: NBDestination.WithoutArguments? = null
        var isSwitchChecked = false

        val header = SettingsListItemModel.Header(
            text = createNBString("Header")
        )
        val itemButtons = SettingsListItemModel.ItemButtons(
            segmentedControl = NBSegmentedControlModel(
                selectedKey = selectedButton,
                buttons = NBTemperatureUnitType.values().map { temperatureUnit ->
                    NBSegmentedButtonModel(
                        key = temperatureUnit,
                        text = temperatureUnit.symbol
                    )
                },
                onItemSelected = { button ->
                    selectedButton = button
                }
            )
        )
        val itemDestination1 = SettingsListItemModel.ItemDestination(
            icon = NBIcons.Appearance,
            title = createNBString("ItemDestination 1 Title"),
            value = createNBString("ItemDestination 1 Value"),
            destination = DestinationsSettings.Appearance
        )
        val itemDestination2 = SettingsListItemModel.ItemDestination(
            icon = NBIcons.Units,
            title = createNBString("ItemDestination 2 Title"),
            value = createNBString("ItemDestination 2 Value"),
            destination = DestinationsSettings.Units
        )
        val itemSwitch = SettingsListItemModel.ItemSwitch(
            title = createNBString("ItemSwitch Title"),
            value = createNBString("ItemSwitch Value"),
            checked = isSwitchChecked,
            onCheckedChange = { checked ->
                isSwitchChecked = checked
            }
        )

        val uiState = SettingsListUiState(
            items = listOf(
                header,
                itemButtons,
                itemDestination1,
                itemDestination2,
                itemSwitch
            )
        )

        // Act
        setContent {
            SettingsListContent(
                uiState = uiState,
                navigate = { destination ->
                    selectedDestination = destination
                }
            )
        }

        // Assert
        assertCompose {
            // Header
            onNodeWithText(header.text)
                .assertIsDisplayed()

            // ItemButtons
            onNodeWithText(NBTemperatureUnitType.CELSIUS.symbol)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(NBTemperatureUnitType.FAHRENHEIT.symbol)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(NBTemperatureUnitType.KELVIN.symbol)
                .assertIsDisplayed()
                .performClick()

            // ItemDestination
            onNodeWithText(itemDestination1.title)
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(itemDestination2.title)
                .assertIsDisplayed()

            // ItemSwitch
            onNodeWithText(itemSwitch.title)
                .assertIsDisplayed()
                .performClick()
        }

        //      ItemButtons
        assertNotNull(selectedButton)
        assertNotEquals(NBTemperatureUnitType.CELSIUS, selectedButton)
        assertNotEquals(NBTemperatureUnitType.FAHRENHEIT, selectedButton)
        assertEquals(NBTemperatureUnitType.KELVIN, selectedButton)

        //      ItemDestination
        assertNotNull(selectedDestination)
        assertEquals(itemDestination1.destination, selectedDestination)
        assertNotEquals(itemDestination2.destination, selectedDestination)

        //      ItemSwitch
        assertTrue(isSwitchChecked)
    }
}
