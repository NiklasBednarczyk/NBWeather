package de.niklasbednarczyk.nbweather.feature.about.screens.overview.models

import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test

class AboutOverviewModelsTest : NBTest {

    @Test
    fun items_shouldConvertCorrectly() {
        // Arrange
        val expectedDividerCount = 3
        val expectedCreditCount = 4
        val expectedSize = expectedDividerCount + expectedCreditCount

        // Act
        val items = AboutOverviewItem.from()

        // Assert
        assertListDoesContainClass(
            actual = items,
            klass = AboutOverviewItem.Divider::class.java,
            size = expectedDividerCount
        )
        assertListDoesContainClass(
            actual = items,
            klass = AboutOverviewItem.Credit::class.java,
            size = expectedCreditCount
        )
        assertListHasSize(
            actual = items,
            size = expectedSize
        )
    }

}