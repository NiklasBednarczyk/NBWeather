package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsListContentTest : NBComposableTest() {

    @Test
    fun stickyHeader_shouldRenderCorrectly() {
        // Arrange
        val text = createNBString("StickyHeader")
        val fontFamily = FontFamily.Cursive

        val uiState = SettingsListUiState(
            stickyHeader = NBStickyHeaderModel(
                text = text,
                fontFamily = fontFamily
            )
        )

        // Act
        setContent {
            SettingsListContent(
                uiState = uiState,
                navigate = { }
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(text)
                .assertIsDisplayed()
                .assert(isOfFontFamily(fontFamily))
        }

    }

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        var selectedButton: NBTemperatureUnitType? = null
        var selectedDestination: NBDestination.WithoutArguments? = null
        var selectedSliderValue = 0f
        var isSwitchChecked = false

        val header = SettingsListItemModel.Header(
            text = createNBString("Header")
        )
        val itemButtons = SettingsListItemModel.ItemButtons(
            segmentedControl = NBSegmentedControlModel(
                selectedKey = selectedButton,
                buttons = NBTemperatureUnitType.entries.map { temperatureUnit ->
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
            description = createNBString("ItemDestination 1 Value"),
            destination = DestinationsSettings.Appearance
        )
        val itemDestination2 = SettingsListItemModel.ItemDestination(
            icon = NBIcons.Units,
            title = createNBString("ItemDestination 2 Title"),
            description = createNBString("ItemDestination 2 Value"),
            destination = DestinationsSettings.Units
        )
        val itemSlider = SettingsListItemModel.ItemSlider(
            slider = NBSliderModel(
                title = createNBString("ItemSlider Title"),
                value = selectedSliderValue,
                minValue = 0f,
                maxValue = 1f,
                fractionDigits = 0,
                onValueChange = { value -> selectedSliderValue = value },
                onValueChangeFinished = {},
            )
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
                itemSlider,
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

            // ItemSlider
            onNodeWithContentDescription(itemSlider.slider.title)
                .assertIsDisplayed()
                .swipeRight()

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

        //      ItemSlider
        assertEquals(itemSlider.slider.maxValue, selectedSliderValue)
        assertNotEquals(itemSlider.slider.minValue, selectedSliderValue)

        //      ItemSwitch
        assertTrue(isSwitchChecked)
    }

    private fun isOfFontFamily(fontFamily: FontFamily): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of font family '$fontFamily'"
    ) { node ->
        val textLayoutResults = mutableListOf<TextLayoutResult>()
        node.config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action
            ?.invoke(textLayoutResults)
        return@SemanticsMatcher if (textLayoutResults.isEmpty()) {
            false
        } else {
            textLayoutResults.first().layoutInput.style.fontFamily == fontFamily
        }
    }

}
