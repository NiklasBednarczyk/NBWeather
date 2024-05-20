package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models

import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlin.test.Test

class SettingsAppearanceModelsTest : NBTest {

    @Test
    fun items_isDynamicColorAvailableFalse_shouldConvertCorrectly() {
        testItems(
            isDynamicColorAvailable = false,
            expectedSwitchItemCount = 1
        )
    }

    @Test
    fun items_isDynamicColorAvailableTrue_shouldConvertCorrectly() {
        testItems(
            isDynamicColorAvailable = true,
            expectedSwitchItemCount = 2
        )
    }

    private fun testItems(
        isDynamicColorAvailable: Boolean,
        expectedSwitchItemCount: Int
    ) {
        // Arrange
        val appearance = NBAppearanceModel(
            useDeviceTheme = true,
            theme = NBThemeType.LIGHT,
            useDynamicColorScheme = true,
            colorScheme = NBColorSchemeType.RED
        )

        // Act
        val items = SettingsAppearanceItem.from(
            appearance = appearance,
            isDynamicColorAvailable = isDynamicColorAvailable,
            updateUseDeviceTheme = {},
            updateTheme = {},
            updateUseDynamicColorScheme = {},
            updateColorScheme = {}
        )

        // Assert
        assertListDoesContainClass(
            actual = items,
            klass = SettingsAppearanceItem.Divider::class.java,
            size = 1
        )
        assertListDoesContainClass(
            actual = items,
            klass = SettingsAppearanceItem.Header::class.java,
            size = 2
        )
        assertListDoesContainClass(
            actual = items,
            klass = SettingsAppearanceItem.SegmentedControl::class.java,
            size = 2
        )
        assertListDoesContainClass(
            actual = items,
            klass = SettingsAppearanceItem.SwitchItem::class.java,
            size = expectedSwitchItemCount
        )
    }

}