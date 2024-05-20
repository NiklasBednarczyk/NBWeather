package de.niklasbednarczyk.nbweather.feature.settings.screens.order.models

import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType.Companion.toOrder
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlin.test.assertNotNull
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SettingsOrderModelsTest : NBTest {

    @Test
    fun item_toOrder_shouldConvertCorrectly() {
        // Arrange
        val items0 = listOf<SettingsOrderItemType>()
        val items1 = listOf(
            SettingsOrderItemType.SUN_AND_MOON
        )
        val items2 = listOf(
            SettingsOrderItemType.SUN_AND_MOON,
            SettingsOrderItemType.PRECIPITATION
        )
        val items3 = listOf(
            SettingsOrderItemType.SUN_AND_MOON,
            SettingsOrderItemType.PRECIPITATION,
            SettingsOrderItemType.HOURLY
        )
        val items4 = listOf(
            SettingsOrderItemType.SUN_AND_MOON,
            SettingsOrderItemType.PRECIPITATION,
            SettingsOrderItemType.HOURLY,
            SettingsOrderItemType.DAILY
        )
        val items5 = listOf(
            SettingsOrderItemType.SUN_AND_MOON,
            SettingsOrderItemType.PRECIPITATION,
            SettingsOrderItemType.HOURLY,
            SettingsOrderItemType.DAILY,
            SettingsOrderItemType.CURRENT_WEATHER
        )

        // Act
        val order0 = items0.toOrder()
        val order1 = items1.toOrder()
        val order2 = items2.toOrder()
        val order3 = items3.toOrder()
        val order4 = items4.toOrder()
        val order5 = items5.toOrder()

        // Assert
        assertNull(order0)

        assertNull(order1)

        assertNull(order2)

        assertNull(order3)

        assertNull(order4)

        assertNotNull(order5)
        assertEquals(5, order5.currentWeatherOrder)
        assertEquals(4, order5.dailyOrder)
        assertEquals(3, order5.hourlyOrder)
        assertEquals(2, order5.precipitationOrder)
        assertEquals(1, order5.sunAndMoonOrder)
    }

    @Test
    fun items_shouldConvertCorrectly() {
        // Arrange
        val order = NBOrderModel(
            currentWeatherOrder = 5,
            dailyOrder = 4,
            hourlyOrder = 3,
            precipitationOrder = 2,
            sunAndMoonOrder = 1
        )

        // Act
        val items = SettingsOrderItemType.from(order)

        // Assert
        assertListsHaveSameSize(SettingsOrderItemType.entries, items)
        assertListsContainsItemInOrder(
            items,
            SettingsOrderItemType.SUN_AND_MOON,
            SettingsOrderItemType.PRECIPITATION,
            SettingsOrderItemType.HOURLY,
            SettingsOrderItemType.DAILY,
            SettingsOrderItemType.CURRENT_WEATHER
        )
    }

}