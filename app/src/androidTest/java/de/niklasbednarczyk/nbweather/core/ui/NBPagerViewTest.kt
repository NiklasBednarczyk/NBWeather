package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onRoot
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerView
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals

class NBPagerViewTest : NBComposableTest() {

    companion object {

        private val testPagerItem1 = Pair(1, "Text 1")
        private val testPagerItem2 = Pair(2, "Text 2")
        private val testPagerItem3 = Pair(3, "Text 3")

    }

    @Test
    fun initialKey_null_shouldStartPagerOnFirstPage() {
        // Arrange
        val viewData = createTestViewData(
            initialItem = null
        )

        // Act
        setPagerContent(viewData)

        // Assert
        assertCompose {
            assertItemIsDisplayed(testPagerItem1)
        }
    }

    @Test
    fun initialKey_notNull_shouldStartPagerOnPageOfKey() {
        // Arrange
        val viewData = createTestViewData(
            initialItem = testPagerItem2
        )

        // Act
        setPagerContent(viewData)

        // Assert
        assertCompose {
            assertItemIsDisplayed(testPagerItem2)
        }
    }

    @Test
    fun pagerIndicator_onePagerItem_shouldNotShow() {
        // Arrange
        val viewData = createTestViewData(
            items = listOf(testPagerItem1)
        )

        // Act
        setPagerContent(viewData)

        // Assert
        assertCompose {
            assertArePagerIndicatorsShown(false)
        }
    }

    @Test
    fun pagerIndicator_multiplePagerItems_shouldShow() {
        // Arrange
        val viewData = createTestViewData(
            items = listOf(testPagerItem1, testPagerItem2, testPagerItem3)
        )

        // Act
        setPagerContent(viewData)

        // Assert
        assertCompose {
            assertArePagerIndicatorsShown(true)
        }
    }

    @Test
    fun pager_shouldSwipeCorrectly() {
        // Arrange
        val viewData = createTestViewData()

        // Act
        setPagerContent(viewData)

        // Assert
        assertCompose {
            assertItemIsDisplayed(testPagerItem1)

            swipeLeft()

            assertItemIsDisplayed(testPagerItem2)

            swipeLeft()

            assertItemIsDisplayed(testPagerItem3)

            swipeLeft()

            assertItemIsDisplayed(testPagerItem3)

            swipeRight()

            assertItemIsDisplayed(testPagerItem2)

            swipeRight()

            assertItemIsDisplayed(testPagerItem1)

            swipeRight()

            assertItemIsDisplayed(testPagerItem1)
        }
    }


    private fun createTestViewData(
        items: List<Pair<Int, String>> = listOf(testPagerItem1, testPagerItem2, testPagerItem3),
        initialItem: Pair<Int, String>? = null
    ) = object : NBPagerViewData<Int, Pair<Int, String>> {

        override val items: List<Pair<Int, String>>
            get() = items

        override val initialKey: Int?
            get() = initialItem?.first

        override fun getItemKey(item: Pair<Int, String>): Int {
            return item.first
        }
    }


    private fun setPagerContent(
        viewData: NBPagerViewData<Int, Pair<Int, String>>
    ) {
        setContent {
            NBPagerView(viewData) { pagerItem ->
                Text(text = pagerItem.second)
            }
        }
    }

    private fun ComposeContentTestRule.assertItemIsDisplayed(
        item: Pair<Int, String>
    ) {
        onRoot()
            .onChild()
            .onChildren()
            .onFirst()
            .assertTextContains(item.second)
    }

    private fun ComposeContentTestRule.assertArePagerIndicatorsShown(
        expectedArePagerIndicatorsShown: Boolean
    ) {
        val rootNode = onRoot().fetchSemanticsNode()
        val childNode = rootNode.children.first()
        val actualArePagerIndicatorsShown =
            rootNode.layoutInfo.height != childNode.layoutInfo.height
        assertEquals(expectedArePagerIndicatorsShown, actualArePagerIndicatorsShown)
    }

}