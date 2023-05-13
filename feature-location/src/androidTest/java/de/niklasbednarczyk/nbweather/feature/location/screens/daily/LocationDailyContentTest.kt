package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.compose.ui.test.*
import androidx.compose.foundation.pager.rememberPagerState
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyDayModel
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyViewData
import de.niklasbednarczyk.nbweather.feature.location.tests.LocationCardTest
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test

class LocationDailyContentTest : NBContentTest(), LocationCardTest {

    @Test
    fun pagerInfo_shouldRenderCorrectly() {
        // Arrange
        val item1 = testItem(1)
        val item2 = testItem(2)
        val item3 = testItem(3)

        val viewData = LocationDailyViewData(
            initialKey = item2.forecastTime,
            items = listOf(
                item1,
                item2,
                item3
            )
        )

        // Act
        setContent {
            val pagerState = rememberPagerState(viewData.initialPage)
            val currentPage = pagerState.currentPage
            val currentItem = viewData.items.getOrNull(currentPage)

            val pagerInfo = if (currentItem != null) NBPagerInfoModel(
                count = viewData.items.size,
                pagerState = pagerState,
                item = currentItem
            ) else {
                null
            }

            LocationDailyContent(
                pagerInfo = pagerInfo
            )
        }

        // Assert
        assertCompose {
            assertDisplayed(item2, listOf(item1, item3))

            swipeRight()

            assertDisplayed(item1, listOf(item3, item3))

            swipeRight()

            assertDisplayed(item1, listOf(item3, item3))

            swipeLeft()

            assertDisplayed(item2, listOf(item1, item3))

            swipeLeft()

            assertDisplayed(item3, listOf(item1, item2))

            swipeLeft()

            assertDisplayed(item3, listOf(item1, item2))

            swipeRight()

            assertDisplayed(item2, listOf(item1, item3))
        }

    }

    private fun SemanticsNodeInteractionsProvider.assertDisplayed(
        displayedItem: LocationDailyDayModel,
        notDisplayedItems: List<LocationDailyDayModel>
    ) {
        onAllNodesWithText(displayedItem.cardItems.testCardTitle)
            .onFirst()
            .assertIsDisplayed()
        notDisplayedItems.forEach { notDisplayedItem ->
            assertStringIsNotDisplayed(notDisplayedItem.cardItems.testCardTitle)
        }
    }

    private fun testItem(forecastTime: Long): LocationDailyDayModel = LocationDailyDayModel(
        forecastTime = forecastTime,
        title = createNBString("Title $forecastTime"),
        cardItems = createTestCards(forecastTime.toString())
    )


}