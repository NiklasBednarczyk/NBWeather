package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
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
                updateItems = { items ->
                    updatedItems = items
                },
                getKey = { item ->
                    item
                },
                headlineContent = { item ->
                    Text(
                        text = item
                    )
                }
            )
        }

        // Assert
        assertCompose {
            startingItems.forEach { item ->
                onNodeWithText(item)
                    .assertIsDisplayed()
            }

            onNodeWithText(startingItems.last())
                .performTouchInput {
                    longClick()
                }
        }
        assertNotNull(updatedItems)
    }


}