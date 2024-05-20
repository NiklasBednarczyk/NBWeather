package de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models

import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test

class SettingsOverviewModelsTest : NBTest {

    @Test
    fun items_isVariableFontAvailableFalse_shouldConvertCorrectly() {
        testItems(
            isVariableFontAvailable = false,
            expectedItemsSize = 3
        )
    }

    @Test
    fun items_isVariableFontAvailableTrue_shouldConvertCorrectly() {
        testItems(
            isVariableFontAvailable = true,
            expectedItemsSize = 4
        )
    }

    private fun testItems(
        isVariableFontAvailable: Boolean,
        expectedItemsSize: Int
    ) {
        // Arrange + Act
        val items = SettingsOverviewItemType.from(
            isVariableFontAvailable = isVariableFontAvailable
        )

        // Assert
        assertListHasSize(items, expectedItemsSize)
    }

}