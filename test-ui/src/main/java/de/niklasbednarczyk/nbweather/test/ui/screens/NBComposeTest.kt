package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.test.espresso.Espresso
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
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

    fun createNBString(value: String) = NBString.Value.from(value)!!

    fun <T> createNBResource(data: T) = NBResource.Success(data)

    fun createNBDateTimeModel(
        epochSeconds: Long = 1672531200,
        timezoneOffsetHours: Long = 0
    ): NBDateTimeDisplayModel {
        val timezoneOffset = timezoneOffsetHours * 3600
        return NBDateTimeDisplayModel.from(
            dateTime = NBDateTimeValue.from(epochSeconds),
            timezoneOffset = NBTimezoneOffsetValue.from(timezoneOffset)
        )!!
    }

    fun ComposeContentTestRule.onNodeWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onNodeWithText(getString(resId), substring = substring)

    fun ComposeContentTestRule.onNodeWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onNodeWithText(string.asString(context), substring = substring)

    fun ComposeContentTestRule.onNodeWithContentDescription(
        string: NBString?
    ) = onNodeWithContentDescription(string.asString(context))

    fun ComposeContentTestRule.onNodeWithIcon(
        icon: NBIconModel,
        useUnmergedTree: Boolean = false
    ) = onNodeWithContentDescription(
        icon.contentDescription.asString(context),
        useUnmergedTree = useUnmergedTree
    )

    fun ComposeContentTestRule.onAllNodesWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) =
        onAllNodesWithText(getString(resId), substring = substring)

    fun ComposeContentTestRule.onAllNodesWithText(
        string: NBString?,
        substring: Boolean = false
    ) =
        onAllNodesWithText(string.asString(context), substring = substring)

    fun ComposeContentTestRule.onAllNodesWithIcon(icon: NBIconModel) =
        onAllNodesWithContentDescription(icon.contentDescription.asString(context))

    fun SemanticsNodeInteractionCollection.performClickOnAllNodes() =
        fetchSemanticsNodes().forEachIndexed { index, _ ->
            get(index).performClick()
        }

    fun SemanticsNodeInteractionCollection.getNodeWithMostChildren(): SemanticsNodeInteraction {
        val semanticNodes = fetchSemanticsNodes()
        val index = semanticNodes.indices.maxBy { index ->
            semanticNodes[index].children.size
        }
        return get(index)
    }

    fun ComposeContentTestRule.swipeLeft() = onRoot().swipeLeft()

    fun ComposeContentTestRule.swipeRight() = onRoot().swipeRight()

    fun SemanticsNodeInteraction.swipeLeft() = performTouchInput { swipeLeft() }

    fun SemanticsNodeInteraction.swipeRight() = performTouchInput { swipeRight() }

    fun ComposeContentTestRule.assertStringIsNotDisplayed(string: NBString?) =
        onAllNodesWithText(string)
            .assertCountEquals(0)

    fun ComposeContentTestRule.assertStringIsNotDisplayed(
        text: String,
        substring: Boolean = false
    ) = onAllNodesWithText(text, substring = substring)
        .assertCountEquals(0)

    fun ComposeContentTestRule.assertIconIsNotDisplayed(
        icon: NBIconModel
    ) = onAllNodesWithIcon(icon)
        .assertCountEquals(0)

    fun ComposeContentTestRule.assertNoClickAction() =
        onAllNodes(hasClickAction())
            .assertCountEquals(0)

    fun SemanticsNodeInteraction.assertTextContains(value: NBString?) =
        assertTextContains(value.asString(context))

    fun ComposeContentTestRule.waitUntilAtLeastOneExistsWithText(
        text: String,
        substring: Boolean = false
    ) = waitUntilAtLeastOneExists(hasText(text, substring = substring))

    fun ComposeContentTestRule.waitUntilAtLeastOneExistsWithText(
        @StringRes resId: Int,
        substring: Boolean = false
    ) = waitUntilAtLeastOneExists(hasText(getString(resId), substring = substring))

    fun pressBack() = Espresso.pressBackUnconditionally()


}