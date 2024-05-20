package de.niklasbednarczyk.nbweather.feature.settings.screens.font.models

import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test

class SettingsFontModelsTest : NBTest {

    @Test
    fun items_fontNull_shouldConvertCorrectly() {
        testItems(
            fontIsSet = false,
            expectedItemsIsEmpty = true
        )
    }

    @Test
    fun items_fontNotNull_shouldConvertCorrectly() {
        testItems(
            fontIsSet = true,
            expectedItemsIsEmpty = false
        )
    }

    private fun testItems(
        fontIsSet: Boolean,
        expectedItemsIsEmpty: Boolean
    ) {
        // Arrange
        val font = if (fontIsSet) {
            NBFontModel(
                slant = 1.0f,
                width = 2.0f,
                weight = 3.0f,
                grade = 4.0f,
                counterWidth = 5.0f,
                thinStroke = 6.0f,
                ascenderHeight = 7.0f,
                descenderDepth = 8.0f,
                figureHeight = 9.0f,
                lowercaseHeight = 10.0f,
                uppercaseHeight = 11.0f
            )
        } else {
            null
        }

        // Act
        val items = SettingsFontItemModel.from(
            font = font,
            updateSlantGlobally = {},
            updateSlantLocally = {},
            updateWidthGlobally = {},
            updateWidthLocally = {},
            updateWeightGlobally = {},
            updateWeightLocally = {},
            updateGradeGlobally = {},
            updateGradeLocally = {},
            updateCounterWidthGlobally = {},
            updateCounterWidthLocally = {},
            updateThinStrokeGlobally = {},
            updateThinStrokeLocally = {},
            updateAscenderHeightGlobally = {},
            updateAscenderHeightLocally = {},
            updateDescenderDepthGlobally = {},
            updateDescenderDepthLocally = {},
            updateFigureHeightGlobally = {},
            updateFigureHeightLocally = {},
            updateLowercaseHeightGlobally = {},
            updateLowercaseHeightLocally = {},
            updateUppercaseHeightGlobally = {},
            updateUppercaseHeightLocally = {}
        )

        // Assert
        if (expectedItemsIsEmpty) {
            assertListIsEmpty(items)
        } else {
            assertListHasSize(items, 11)
        }
    }

}