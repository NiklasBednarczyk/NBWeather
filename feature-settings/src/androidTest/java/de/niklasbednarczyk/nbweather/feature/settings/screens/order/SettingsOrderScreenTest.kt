package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsOrderScreenTest : NBComposableTest() {

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        var popBackStackCalled = false
        var resetToDefaultCalled = false

        // Act
        setScreenContent(
            popBackStack = {
                popBackStackCalled = true
                resetToDefaultCalled = false
            },
            resetToDefault = {
                popBackStackCalled = false
                resetToDefaultCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_settings_order_title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Back)
                .assertIsDisplayed()
                .performClick()

            assertTrue(popBackStackCalled)
            assertFalse(resetToDefaultCalled)

            onNodeWithIcon(NBIcons.Reset)
                .assertIsDisplayed()
                .performClick()

            assertFalse(popBackStackCalled)
            assertTrue(resetToDefaultCalled)
        }
    }

    @Test
    fun content_items_shouldRenderCorrectly() {
        // Arrange
        val startingItems = SettingsOrderItemType.entries
        var updatedItems: List<SettingsOrderItemType>? = null

        val uiState = createTestUiState(
            items = startingItems
        )

        // Act
        setScreenContent(
            uiState = uiState,
            updateOrder = { items ->
                updatedItems = items
            }
        )

        // Assert
        assertCompose {
            startingItems.forEach { item ->
                onNodeWithText(item.title)
                    .assertIsDisplayed()
            }

            onNodeWithText(startingItems.last().title)
                .performLongClick()
        }
        assertNotNull(updatedItems)
    }

    private fun createTestUiState(
        items: List<SettingsOrderItemType> = emptyList()
    ): SettingsOrderUiState {
        return SettingsOrderUiState(
            items = items
        )
    }

    private fun setScreenContent(
        uiState: SettingsOrderUiState = createTestUiState(),
        popBackStack: () -> Unit = {},
        updateOrder: (items: List<SettingsOrderItemType>) -> Unit = {},
        resetToDefault: () -> Unit = {}
    ) {
        setContent {
            SettingsOrderScreen(
                uiState = uiState,
                popBackStack = popBackStack,
                updateOrder = updateOrder,
                resetToDefault = resetToDefault
            )
        }
    }

}