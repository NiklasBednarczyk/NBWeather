package de.niklasbednarczyk.nbweather.test.ui.screens

import androidx.annotation.StringRes
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.test.espresso.Espresso
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
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

    fun createNBDateTimeModel(
        epochSeconds: Long = 1672531200,
        timezoneOffsetHours: Long = 0
    ): NBDateTimeModel? {
        val timezoneOffset = timezoneOffsetHours.times(3600)
        return NBDateTimeModel.from(epochSeconds, timezoneOffset)
    }

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
        useUnmergedTree: Boolean = false
    ) = onNodeWithContentDescription(
        icon.contentDescription.asString(context),
        useUnmergedTree = useUnmergedTree
    )

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

    fun SemanticsNodeInteractionsProvider.swipeLeft() = onRoot().swipeLeft()

    fun SemanticsNodeInteractionsProvider.swipeRight() = onRoot().swipeRight()

    fun SemanticsNodeInteraction.swipeLeft() = performTouchInput { swipeLeft() }

    fun SemanticsNodeInteraction.swipeRight() = performTouchInput { swipeRight() }

    fun SemanticsNodeInteractionsProvider.assertStringIsNotDisplayed(string: NBString?) =
        onAllNodesWithText(string)
            .assertCountEquals(0)

    fun isOfFontFamily(fontFamily: FontFamily): SemanticsMatcher = SemanticsMatcher(
        "${SemanticsProperties.Text.name} is of font family '$fontFamily'"
    ) { node ->
        val textLayoutResults = mutableListOf<TextLayoutResult>()
        node.config.getOrNull(SemanticsActions.GetTextLayoutResult)
            ?.action
            ?.invoke(textLayoutResults)
        return@SemanticsMatcher if (textLayoutResults.isEmpty()) {
            false
        } else {
            textLayoutResults.first().layoutInput.style.fontFamily == fontFamily
        }
    }


    fun pressBack() = Espresso.pressBackUnconditionally()


}