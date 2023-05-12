package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.theme.LocalNBTheme
import de.niklasbednarczyk.nbweather.core.ui.theme.NBThemeModel
import org.junit.Rule

abstract class NBContentTest : NBComposeTest {

    @get:Rule
    override val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule()


    protected fun setContent(
        content: @Composable () -> Unit
    ) {
        val nbTheme = NBThemeModel(
            sourceColorInt = 0xFFFF00,
            isLightTheme = true
        )
        composeTestRule.setContent {
            CompositionLocalProvider(LocalNBTheme provides nbTheme) {
                MaterialTheme {
                    content()
                }
            }
        }
    }




}