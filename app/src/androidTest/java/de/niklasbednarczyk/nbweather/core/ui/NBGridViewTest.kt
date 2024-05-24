package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridIconModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals

class NBGridViewTest : NBComposableTest() {

    companion object {

        private const val ROWS_PER_ITEM = 3 // Name; Icon; Value

    }

    private val testItem1 = createTestItem(
        number = 1,
        icon = NBIcons.MoonPhaseNorthLightFirstQuarterMoon
    )

    private val testItem2 = createTestItem(
        number = 2,
        icon = NBIcons.MoonPhaseNorthLightFullMoon
    )

    private val testItem3 = createTestItem(
        number = 3,
        icon = NBIcons.MoonPhaseNorthLightLastQuarterMoon
    )

    private val testItem4 = createTestItem(
        number = 4,
        icon = NBIcons.MoonPhaseNorthLightNewMoon
    )

    private val testItem5 = createTestItem(
        number = 5,
        icon = NBIcons.Sunrise
    )

    @Test
    fun rowItemCountLimit_set_shouldDisplayCorrectRows() {
        // Arrange
        val items = listOf(
            testItem1,
            testItem2,
            testItem3,
            testItem4,
            testItem5
        )
        val rowItemCountLimit = 2

        // Act
        setContent {
            NBGridView(
                items = items,
                rowItemCountLimit = rowItemCountLimit
            )
        }

        // Assert
        assertCompose {
            assertAllItemsAreDisplayed(items)

            assertItemRowCount(3)

            assertItemCount(
                itemRow = 1,
                expectedItemCount = 2
            )
            assertItemCount(
                itemRow = 2,
                expectedItemCount = 2
            )
            assertItemCount(
                itemRow = 3,
                expectedItemCount = 1
            )
        }
    }

    @Test
    fun rowItemCountLimit_notSet_shouldDisplayCorrectRows() {
        // Arrange
        val items = listOf(
            testItem1,
            testItem2,
            testItem3,
            testItem4,
            testItem5
        )

        // Act
        setContent {
            NBGridView(
                items = items
            )
        }

        // Assert
        assertCompose {
            assertAllItemsAreDisplayed(items)

            assertItemRowCount(2)

            assertItemCount(
                itemRow = 1,
                expectedItemCount = 3
            )
            assertItemCount(
                itemRow = 2,
                expectedItemCount = 2
            )
        }
    }

    @Test
    fun placeholders_shouldBeOnEndOfRow() {
        // Arrange
        val items = listOf(
            testItem1,
            testItem2,
            testItem3,
            testItem4,
            testItem5
        )
        val rowItemCountLimit = 3

        // Act
        setContent {
            NBGridView(
                items = items,
                rowItemCountLimit = rowItemCountLimit
            )
        }

        // Assert
        assertCompose {
            val positions = getPositions()
            val groupedPositions = positions.groupBy { position -> position.x }

            assertEquals(rowItemCountLimit, groupedPositions.size)
            groupedPositions.values.forEachIndexed { index, pos ->
                val actualItemColumnCount = pos.size / ROWS_PER_ITEM
                when (index) {
                    0 -> assertEquals(2, actualItemColumnCount) // Column 1
                    1 -> assertEquals(2, actualItemColumnCount) // Column 2
                    2 -> assertEquals(1, actualItemColumnCount) // Column 3
                }
            }
        }
    }

    @Test
    fun longText_shouldNotChangeOtherRows() {
        // Arrange
        val longText =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        val itemWithLongText = NBGridModel(
            name = createNBString(longText),
            icon = NBGridIconModel(
                icon = NBIcons.Sunrise
            ),
            value = createNBString(longText)
        )
        val items = listOf(
            testItem1,
            itemWithLongText,
            testItem2,
            testItem3
        )
        val rowItemCountLimit = 2

        // Act
        setContent {
            NBGridView(
                items = items,
                rowItemCountLimit = rowItemCountLimit
            )
        }

        // Assert
        assertCompose {
            val positions = getPositions()
            val groupedPositions = positions.groupBy { position -> position.y }

            assertEquals(6, groupedPositions.size)
            groupedPositions.values.forEach { pos ->
                assertEquals(rowItemCountLimit, pos.size)
            }
        }
    }

    @Test
    fun rotationDegrees_shouldRotateIcon() {
        // Arrange
        val itemWithRotationDegrees = createTestItem(
            number = 1,
            icon = NBIcons.WindDirection,
            rotationDegrees = 45f
        )
        val itemWithoutDegrees = createTestItem(
            number = 2,
            icon = NBIcons.WindDirection,
            rotationDegrees = 0f
        )
        val items = listOf(
            itemWithRotationDegrees,
            itemWithoutDegrees
        )
        val rowItemCountLimit = items.size

        // Act
        setContent {
            NBGridView(
                items = items,
                rowItemCountLimit = rowItemCountLimit
            )
        }

        // Assert
        assertCompose {
            val positions = getPositions()
            val groupedPositions = positions.groupBy { position -> position.y }

            assertEquals(ROWS_PER_ITEM + 1, groupedPositions.size)
            groupedPositions.values.forEachIndexed { index, pos ->
                val actualSize = pos.size
                when (index) {
                    0 -> assertEquals(rowItemCountLimit, actualSize) // Name row
                    1 -> assertEquals(1, actualSize) // Icon row without rotationDegrees
                    2 -> assertEquals(1, actualSize) // Icon row with rotationDegrees
                    3 -> assertEquals(rowItemCountLimit, actualSize) // Value row
                }
            }
        }
    }

    private fun createTestItem(
        number: Int,
        icon: NBIconItem,
        rotationDegrees: Float = 0f
    ): NBGridModel {
        return NBGridModel(
            name = createNBString("Name $number"),
            icon = NBGridIconModel(
                icon = icon,
                rotationDegrees = rotationDegrees
            ),
            value = createNBString("Value $number")
        )
    }

    private fun ComposeContentTestRule.getPositions(): List<Offset> {
        return onRoot()
            .fetchSemanticsNode()
            .children
            .map { child ->
                child.positionInRoot
            }
    }

    private fun ComposeContentTestRule.assertAllItemsAreDisplayed(
        items: List<NBGridModel>
    ) {
        items.forEach { item ->
            onNodeWithText(item.name)
                .assertIsDisplayed()
            onNodeWithIcon(item.icon.icon)
                .assertIsDisplayed()
            onNodeWithText(item.value)
                .assertIsDisplayed()
        }
    }

    private fun ComposeContentTestRule.assertItemRowCount(
        expectedItemRowCount: Int
    ) {
        val positions = getPositions()
        val groupedPositions = positions.groupBy { position -> position.y }
        val actualItemRowCount = groupedPositions.size / ROWS_PER_ITEM

        assertEquals(expectedItemRowCount, actualItemRowCount)
    }

    private fun ComposeContentTestRule.assertItemCount(
        itemRow: Int,
        expectedItemCount: Int
    ) {
        val positions = getPositions()
        val groupedPositions = positions.groupBy { position -> position.y }
        val chunkedPositions = groupedPositions.values.chunked(ROWS_PER_ITEM)
        val rowPositions = chunkedPositions[itemRow - 1]

        assertEquals(ROWS_PER_ITEM, rowPositions.size)
        rowPositions.forEach { rowPosition ->
            val actualItemCount = rowPosition.size
            assertEquals(expectedItemCount, actualItemCount)
        }
    }


}