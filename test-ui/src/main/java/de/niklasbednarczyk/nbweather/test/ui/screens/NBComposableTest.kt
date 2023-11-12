package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.ui.colors.LocalNBColors
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColorsModel
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBAppearance
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBFont
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBOrder
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBUnits
import org.junit.Rule

abstract class NBComposableTest : NBComposeTest {

    @get:Rule
    override val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule()


    protected fun setContent(
        appearance: NBAppearanceModel = NBAppearanceModel.createFake(),
        colors: NBColorsModel = NBColorsModel.createFake(),
        font: NBFontModel = NBFontModel.createFake(),
        order: NBOrderModel = NBOrderModel.createFake(),
        units: NBUnitsModel = NBUnitsModel.createFake(),
        content: @Composable () -> Unit
    ) {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalNBAppearance provides appearance,
                LocalNBFont provides font,
                LocalNBOrder provides order,
                LocalNBUnits provides units,
                LocalNBColors provides colors,
            ) {
                MaterialTheme {
                    content()
                }
            }
        }
    }

}