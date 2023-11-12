package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.ForecastHourlyViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import java.util.Locale

class ForecastHourlyContentTest : NBComposableTest() {

    companion object {
        private const val DT = 1672614000 // Sunday, 1 January 2023 23:00:00 (GMT)

        private const val DAY_OF_WEEK_START = "Sun"
        private const val DAY_OF_WEEK_END = "Mon"

        private val filterChipHumidity = R.string.screen_forecast_common_forecast_name_humidity
        private val filterChipPressure = R.string.screen_forecast_common_forecast_name_pressure
        private val filterChipVisibility = R.string.screen_forecast_common_forecast_name_visibility
    }

    @Test
    fun filterChips_shouldRenderCorrectly() {
        // Arrange
        val uiState = createTestUiState(2)

        // Act
        setContent {
            ForecastHourlyContent(
                uiState = uiState
            )
        }

        // Assert
        assertCompose {
            assertFilterChipIsSelected(filterChipPressure)
            assertFilterChipIsNotSelected(filterChipHumidity)
            assertFilterChipIsNotSelected(filterChipVisibility)

            performClickOnFilterChip(filterChipVisibility)

            assertFilterChipIsNotSelected(filterChipPressure)
            assertFilterChipIsNotSelected(filterChipHumidity)
            assertFilterChipIsSelected(filterChipVisibility)

            performClickOnFilterChip(filterChipHumidity)

            assertFilterChipIsNotSelected(filterChipPressure)
            assertFilterChipIsSelected(filterChipHumidity)
            assertFilterChipIsNotSelected(filterChipVisibility)
        }
    }

    @Test
    fun headline_shouldShowCorrectDate() {
        // Arrange
        setLocale(Locale.US)

        val uiState = createTestUiState(20)

        // Act
        setContent {
            ForecastHourlyContent(
                uiState = uiState
            )
        }

        // Assert
        assertCompose {
            assertHeadlineIsDisplayed(DAY_OF_WEEK_START)
            assertHeadlineIsNotDisplayed(DAY_OF_WEEK_END)

            swipeLeft()

            assertHeadlineIsDisplayed(DAY_OF_WEEK_END)
            assertHeadlineIsNotDisplayed(DAY_OF_WEEK_START)

            swipeRight()

            assertHeadlineIsDisplayed(DAY_OF_WEEK_START)
            assertHeadlineIsNotDisplayed(DAY_OF_WEEK_END)
        }
    }


    private fun createTestUiState(
        size: Int
    ): ForecastHourlyUiState {
        val axes = List(size) { index ->
            createNBDateTimeModel(epochSeconds = DT + index * 3600L)
        }

        val pressureGraph = List(size) { index ->
            PressureForecastValue.from(index.toLong())!!
        }
        val humidityGraph = List(size) { index ->
            HumidityForecastValue.from(index.toLong())!!
        }
        val visibilityGraph = List(size) { index ->
            VisibilityForecastValue.from(index.toLong())!!
        }
        val graphs = listOf(
            pressureGraph,
            humidityGraph,
            visibilityGraph
        )

        val viewData = ForecastHourlyViewData(
            axes = axes,
            graphs = graphs
        )
        return ForecastHourlyUiState(
            viewDataResource = createNBResource(viewData)
        )
    }

    private fun ComposeContentTestRule.assertHeadlineIsDisplayed(
        dayOfWeek: String
    ) {
        onNodeWithText(dayOfWeek, substring = true)
            .assertIsDisplayed()
    }

    private fun ComposeContentTestRule.assertFilterChipIsSelected(
        @StringRes resId: Int
    ) {
        onNodeWithText(resId, substring = true)
            .assertIsDisplayed()
            .assertIsSelected()
    }

    private fun ComposeContentTestRule.assertFilterChipIsNotSelected(
        @StringRes resId: Int
    ) {
        onNodeWithText(resId, substring = true)
            .assertIsDisplayed()
            .assertIsNotSelected()
    }

    private fun ComposeContentTestRule.performClickOnFilterChip(
        @StringRes resId: Int
    ) {
        onNodeWithText(resId, substring = true)
            .performClick()
    }

    private fun ComposeContentTestRule.assertHeadlineIsNotDisplayed(
        dayOfWeek: String
    ) {
        assertStringIsNotDisplayed(dayOfWeek, substring = true)

    }

}