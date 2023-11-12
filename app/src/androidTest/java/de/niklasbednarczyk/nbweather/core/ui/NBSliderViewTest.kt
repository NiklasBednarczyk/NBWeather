package de.niklasbednarczyk.nbweather.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderModel
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderView
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertTrue

class NBSliderViewTest : NBComposableTest() {

    @Test(expected = IllegalArgumentException::class)
    fun minValue_sameAsMaxValue_shouldThrowException() {
        // Arrange
        val model = createTestSlider(
            value = 0f,
            minValue = 0f,
            maxValue = 0f
        )

        // Act + Arrange
        setContent {
            NBSliderView(
                model = model
            )
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun minValue_greaterThanMaxValue_shouldThrowException() {
        // Arrange
        val model = createTestSlider(
            value = 0f,
            minValue = 1f,
            maxValue = 0f
        )

        // Act + Arrange
        setContent {
            NBSliderView(
                model = model
            )
        }
    }

    @Test
    fun fractionDigits_shouldCalculateStepsCorrectly() {
        // Arrange
        val value = 0f
        val minValue = -1f
        val maxValue = 1f

        val modelFractionsDigits0 = createTestSlider(
            title = "FractionDigits0",
            value = value,
            minValue = minValue,
            maxValue = maxValue,
            fractionDigits = 0
        )
        val modelFractionsDigits1 = createTestSlider(
            title = "FractionDigits1",
            value = value,
            minValue = minValue,
            maxValue = maxValue,
            fractionDigits = 1
        )

        // Act
        setContent {
            Column {
                NBSliderView(
                    model = modelFractionsDigits0
                )
                NBSliderView(
                    model = modelFractionsDigits1
                )
            }
        }

        // Assert
        val expectedRangeInfoFractionDigits0 = ProgressBarRangeInfo(
            current = value,
            range = minValue..maxValue,
            steps = 1
        )
        val expectedRangeInfoFractionDigits1 = ProgressBarRangeInfo(
            current = value,
            range = minValue..maxValue,
            steps = 19
        )

        assertCompose {
            onSwitch(modelFractionsDigits0)
                .assertRangeInfoEquals(expectedRangeInfoFractionDigits0)

            onSwitch(modelFractionsDigits1)
                .assertRangeInfoEquals(expectedRangeInfoFractionDigits1)
        }
    }

    @Test
    fun onValueChange_shouldChangeValueOnSwipe() {
        // Arrange
        var value = 0f
        val minValue = -1f
        val maxValue = 1f
        val model = createTestSlider(
            value = value,
            minValue = minValue,
            maxValue = maxValue,
            onValueChange = { v -> value = v }
        )

        // Act
        setContent {
            NBSliderView(
                model = model
            )
        }

        // Assert
        assertCompose {
            onSwitch(model)
                .swipeLeft()
            assertValue(minValue, value)

            onSwitch(model)
                .swipeRight()
            assertValue(maxValue, value)
        }
    }

    @Test
    fun onValueChangeFinished_shouldBeCalledAfterSwipe() {
        // Arrange
        var valueChangeFinishedCalled = false
        val model = createTestSlider(
            onValueChangeFinished = {
                valueChangeFinishedCalled = true
            }
        )

        // Act
        setContent {
            NBSliderView(
                model = model
            )
        }

        // Assert
        assertCompose {
            onSwitch(model)
                .swipeLeft()
            assertTrue(valueChangeFinishedCalled)
        }
    }

    private fun createTestSlider(
        title: String = "Title",
        value: Float = 0f,
        minValue: Float = -1f,
        maxValue: Float = 1f,
        fractionDigits: Int = 0,
        onValueChange: (Float) -> Unit = {},
        onValueChangeFinished: (() -> Unit)? = null
    ): NBSliderModel {
        return NBSliderModel(
            title = createNBString(title),
            value = value,
            minValue = minValue,
            maxValue = maxValue,
            fractionDigits = fractionDigits,
            onValueChange = onValueChange,
            onValueChangeFinished = onValueChangeFinished
        )
    }

    private fun ComposeContentTestRule.onSwitch(
        model: NBSliderModel
    ) = onNodeWithContentDescription(model.title)

}