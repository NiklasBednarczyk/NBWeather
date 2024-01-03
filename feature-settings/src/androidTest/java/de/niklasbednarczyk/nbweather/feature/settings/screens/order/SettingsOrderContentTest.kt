package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertNotNull

class SettingsOrderContentTest : NBComposableTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        val startingItems = SettingsOrderItemType.entries
        var updatedItems: List<SettingsOrderItemType>? = null

        val uiState = SettingsOrderUiState(
            items = startingItems
        )

        // Act
        setContent {
            SettingsOrderContent(
                uiState = uiState,
                updateOrder = { items ->
                    updatedItems = items
                }
            )
        }

        // Assert
        assertCompose {
            startingItems.forEach { item ->
                onNodeWithText(item.title)
                    .assertIsDisplayed()
            }

            onNodeWithText(startingItems.last().title)
                .performTouchInput {
                    longClick()
                }
        }
        assertNotNull(updatedItems)
    }

}