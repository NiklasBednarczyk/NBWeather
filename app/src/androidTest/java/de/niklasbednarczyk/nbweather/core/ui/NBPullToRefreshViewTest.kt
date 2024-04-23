package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import de.niklasbednarczyk.nbweather.core.ui.pulltorefresh.NBPullToRefreshView
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals

class NBPullToRefreshViewTest : NBComposableTest() {

    @Test
    fun nbPullToRefreshView_shouldRenderCorrectly() {
        // Arrange
        val item = "Item"
        val items = listOf(item)

        var refreshDataCalled = 0

        // Act
        setContent {
            NBPullToRefreshView(
                refreshData = {
                    refreshDataCalled++
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items) { item ->
                        Text(item)
                    }
                }
            }
        }

        // Assert
        assertCompose {
            onNodeWithText(item)
                .assertIsDisplayed()

            swipeDown()
            awaitIdleBlocking()
        }

        assertEquals(1, refreshDataCalled)
    }

}