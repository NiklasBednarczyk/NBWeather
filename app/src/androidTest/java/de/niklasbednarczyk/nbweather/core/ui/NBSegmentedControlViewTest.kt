package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlView
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NBSegmentedControlViewTest : NBComposableTest() {

    private val testButton1 = createTestButton(1)

    private val testButton2 = createTestButton(2)

    private val testButton3 = createTestButton(3)

    @Test
    fun onItemSelected_shouldSelectCorrectItem() {
        // Arrange
        var selectedKey: Int = testButton2.key

        val segmentedControl = createTestControl(
            buttons = listOf(testButton1, testButton2, testButton3),
            selectedKey = selectedKey,
            onItemSelected = { key ->
                selectedKey = key
            }
        )

        // Act
        setContent {
            NBSegmentedControlView(
                segmentedControl = segmentedControl
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(testButton1.text)
                .performClick()
        }
        assertEquals(testButton1.key, selectedKey)
        assertNotEquals(testButton2.key, selectedKey)
        assertNotEquals(testButton3.key, selectedKey)
    }

    @Test
    fun sortAlphabetically_false_shouldKeepOriginalOrder() {
        // Arrange
        val segmentedControl = createTestControl(
            buttons = listOf(testButton2, testButton3, testButton1),
            sortAlphabetically = false
        )

        // Act
        setContent {
            NBSegmentedControlView(
                segmentedControl = segmentedControl
            )
        }

        // Assert
        assertCompose {
            assertChildIsButton(1, testButton2)
            assertChildIsButton(2, testButton3)
            assertChildIsButton(3, testButton1)
        }
    }

    @Test
    fun sortAlphabetically_true_shouldSortButtonsAlphabetically() {
        // Arrange
        val segmentedControl = createTestControl(
            buttons = listOf(testButton2, testButton3, testButton1),
            sortAlphabetically = true
        )

        // Act
        setContent {
            NBSegmentedControlView(
                segmentedControl = segmentedControl
            )
        }

        // Assert
        assertCompose {
            assertChildIsButton(1, testButton1)
            assertChildIsButton(2, testButton2)
            assertChildIsButton(3, testButton3)
        }
    }

    private fun createTestButton(number: Int): NBSegmentedButtonModel<Int> {
        return NBSegmentedButtonModel(
            key = number,
            text = createNBString("Text $number")
        )
    }

    private fun createTestControl(
        buttons: List<NBSegmentedButtonModel<Int>>,
        selectedKey: Int = buttons.first().key,
        onItemSelected: (Int) -> Unit = {},
        sortAlphabetically: Boolean = true
    ): NBSegmentedControlModel<Int> {
        return NBSegmentedControlModel(
            selectedKey = selectedKey,
            buttons = buttons,
            onItemSelected = onItemSelected,
            sortAlphabetically = sortAlphabetically
        )
    }

    private fun ComposeContentTestRule.assertChildIsButton(
        position: Int,
        button: NBSegmentedButtonModel<Int>
    ) {
        onRoot()
            .onChildAt(position - 1)
            .assertTextContains(button.text)
    }

}