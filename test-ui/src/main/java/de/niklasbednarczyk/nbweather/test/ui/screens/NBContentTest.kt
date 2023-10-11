package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxes
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBAppearance
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBFont
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBOrder
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBUnits
import org.junit.Rule

abstract class NBContentTest : NBComposeTest {

    @get:Rule
    override val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule()


    protected fun setContent(
        appearance: NBAppearanceModel = testAppearance,
        font: NBFontModel = testFont,
        order: NBOrderModel = testOrder,
        units: NBUnitsModel = testUnits,
        content: @Composable () -> Unit
    ) {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalNBAppearance provides appearance,
                LocalNBFont provides font,
                LocalNBOrder provides order,
                LocalNBUnits provides units
            ) {
                MaterialTheme {
                    content()
                }
            }
        }
    }

    private val testAppearance = NBAppearanceModel(
        useDeviceTheme = false,
        theme = NBThemeType.DARK,
        useDynamicColorScheme = false,
        colorScheme = NBColorSchemeType.YELLOW
    )

    private val testFont = NBFontModel(
        slant = NBFontAxes.Slant.defaultValue,
        width = NBFontAxes.Width.defaultValue,
        weight = NBFontAxes.Weight.defaultValue,
        grade = NBFontAxes.Grade.defaultValue,
        counterWidth = NBFontAxes.CounterWidth.defaultValue,
        thinStroke = NBFontAxes.ThinStroke.defaultValue,
        ascenderHeight = NBFontAxes.AscenderHeight.defaultValue,
        descenderDepth = NBFontAxes.DescenderDepth.defaultValue,
        figureHeight = NBFontAxes.FigureHeight.defaultValue,
        lowercaseHeight = NBFontAxes.LowercaseHeight.defaultValue,
        uppercaseHeight = NBFontAxes.UppercaseHeight.defaultValue
    )

    private val testOrder = NBOrderModel(
        currentWeatherOrder = 1,
        dailyOrder = 2,
        hourlyOrder = 3,
        precipitationOrder = 4,
        sunAndMoonOrder = 5
    )

    private val testUnits = NBUnitsModel(
        temperatureUnit = NBTemperatureUnitType.CELSIUS,
        precipitationUnit = NBPrecipitationUnitType.MILLIMETER,
        distanceUnit = NBDistanceUnitType.KILOMETER,
        pressureUnit = NBPressureUnitType.HECTOPASCAL,
        windSpeedUnit = NBWindSpeedUnitType.METER_PER_SECOND
    )

}