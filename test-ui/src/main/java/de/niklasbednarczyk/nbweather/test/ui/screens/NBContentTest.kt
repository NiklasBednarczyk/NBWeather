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
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Rule

abstract class NBContentTest : NBTest {

    @get:Rule
    val composeContentTestRule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule()


    protected fun setContent(
        content: @Composable () -> Unit
    ) {
        val nbTheme = NBThemeModel(
            sourceColorInt = 0xFFFF00,
            isLightTheme = true
        )
        composeContentTestRule.setContent {
            CompositionLocalProvider(LocalNBTheme provides nbTheme) {
                MaterialTheme {
                    content()
                }
            }
        }
    }

    private fun getString(@StringRes resId: Int) =
        composeContentTestRule.activity.getString(resId)

    protected fun assertCompose(
        block: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.() -> Unit
    ) {
        composeContentTestRule.apply(block)
    }

    protected fun createNBString(value: String) = NBString.Value.from(value)

    protected fun <T> createNBResource(data: T) = NBResource.Success(data)


    protected fun SemanticsNodeInteractionsProvider.onNodeWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onNodeWithText(getString(resId), substring = substring)

    protected fun SemanticsNodeInteractionsProvider.onNodeWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onNodeWithText(string.asString(context), substring = substring)

    protected fun SemanticsNodeInteractionsProvider.onAllNodesWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onAllNodesWithText(getString(resId), substring = substring)

    protected fun SemanticsNodeInteractionsProvider.onAllNodesWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onAllNodesWithText(string.asString(context), substring = substring)

    protected fun SemanticsNodeInteractionsProvider.onAllNodesWithIcon(icon: NBIconModel) =
        onAllNodesWithContentDescription(icon.contentDescription.asString(context))

    protected fun SemanticsNodeInteractionCollection.performClickOnAllNodes() =
        fetchSemanticsNodes().forEachIndexed { index, _ ->
            get(index).performClick()
        }


    protected fun SemanticsNodeInteractionsProvider.swipeLeft() =
        onRoot().performTouchInput { swipeLeft() }

    protected fun SemanticsNodeInteractionsProvider.swipeRight() =
        onRoot().performTouchInput { swipeRight() }


    protected fun SemanticsNodeInteractionsProvider.assertStringIsNotDisplayed(string: NBString?) =
        onAllNodesWithText(string)
            .assertCountEquals(0)

    protected fun pressBack() = Espresso.pressBackUnconditionally()


}