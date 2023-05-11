package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import androidx.compose.ui.test.*
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SettingsOverviewContentTest : NBContentTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        val header = SettingsOverviewItemModel.Header(
            text = createNBString("Header")
        )
        val item1 = SettingsOverviewItemModel.Item(
            icon = NBIcons.ColorScheme,
            title = createNBString("Item 1 Title"),
            value = createNBString("Item 1 Value"),
            destination = DestinationsSettings.ColorScheme
        )
        val item2 = SettingsOverviewItemModel.Item(
            icon = NBIcons.Theme,
            title = createNBString("Item 2 Title"),
            value = createNBString("Item 2 Value"),
            destination = DestinationsSettings.Theme
        )

        var selectedDestination: NBDestination.WithoutArguments? = null

        val uiState = SettingsOverviewUiState(
            items = listOf(
                header,
                item1,
                item2
            )
        )

        // Act
        setContent {
            SettingsOverviewContent(
                uiState = uiState,
                navigate = { destination ->
                    selectedDestination = destination
                }
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(header.text)
                .assertIsDisplayed()

            onNodeWithText(item1.title)
                .assertIsDisplayed()
                .performClick()

            onNodeWithText(item2.title)
                .assertIsDisplayed()
        }
        assertNotNull(selectedDestination)
        assertEquals(item1.destination, selectedDestination)
        assertNotEquals(item2.destination, selectedDestination)
    }

}