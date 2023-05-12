package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.annotation.StringRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.espresso.Espresso
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest

interface NBComposeTest : NBTest {

    val composeTestRule: AndroidComposeTestRule<*, *>

    fun getString(@StringRes resId: Int) =
        composeTestRule.activity.getString(resId)

    fun assertCompose(
        block: AndroidComposeTestRule<*, *>.() -> Unit
    ) {
        composeTestRule.apply(block)
    }

    fun createNBString(value: String) = NBString.Value.from(value)

    fun <T> createNBResource(data: T) = NBResource.Success(data)

    fun SemanticsNodeInteractionsProvider.onNodeWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onNodeWithText(getString(resId), substring = substring)

    fun SemanticsNodeInteractionsProvider.onNodeWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onNodeWithText(string.asString(context), substring = substring)

    fun SemanticsNodeInteractionsProvider.onNodeWithIcon(
        icon: NBIconModel,
        useUnmergedTree: Boolean =false
    ) = onNodeWithContentDescription(icon.contentDescription.asString(context), useUnmergedTree = useUnmergedTree)

    fun SemanticsNodeInteractionsProvider.onAllNodesWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onAllNodesWithText(getString(resId), substring = substring)

    fun SemanticsNodeInteractionsProvider.onAllNodesWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onAllNodesWithText(string.asString(context), substring = substring)

    fun SemanticsNodeInteractionsProvider.onAllNodesWithIcon(icon: NBIconModel) =
        onAllNodesWithContentDescription(icon.contentDescription.asString(context))

    fun SemanticsNodeInteractionCollection.performClickOnAllNodes() =
        fetchSemanticsNodes().forEachIndexed { index, _ ->
            get(index).performClick()
        }

    fun SemanticsNodeInteractionsProvider.swipeLeft() =
        onRoot().performTouchInput { swipeLeft() }

    fun SemanticsNodeInteractionsProvider.swipeRight() =
        onRoot().performTouchInput { swipeRight() }

    fun SemanticsNodeInteractionsProvider.assertStringIsNotDisplayed(string: NBString?) =
        onAllNodesWithText(string)
            .assertCountEquals(0)

    fun pressBack() = Espresso.pressBackUnconditionally()


}