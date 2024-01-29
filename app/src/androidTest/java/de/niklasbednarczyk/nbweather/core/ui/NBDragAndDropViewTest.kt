package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertNotNull

class NBDragAndDropViewTest : NBComposableTest() {

    @Test
    fun items_shouldRenderCorrectly() {
        // Arrange
        val startingItems = List(5) { i -> i.toString() }
        var updatedItems: List<String>? = null

        // Act
        setContent {
            NBDragAndDropView(
                items = startingItems,
                updateKeys = { items ->
                    updatedItems = items
                },
                getKey = { item ->
                    item
                },
                getListItem = { item ->
                    NBDragAndDropListItemModel(
                        headlineContent = {
                            Text(
                                text = "headlineContent $item"
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "trailingContent $item"
                            )
                        }
                    )
                }
            )
        }

        // Assert
        assertCompose {
            startingItems.forEach { item ->
                onNodeWithText("headlineContent $item")
                    .assertIsDisplayed()
                onNodeWithText("trailingContent $item")
                    .assertIsDisplayed()
            }

            onNodeWithText("headlineContent ${startingItems.last()}")
                .performLongClick()
        }
        assertNotNull(updatedItems)
    }

}