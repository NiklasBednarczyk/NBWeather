package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import androidx.compose.ui.test.assertIsDisplayed
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewViewData
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import de.niklasbednarczyk.nbweather.feature.location.tests.LocationCardTest
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import java.time.DayOfWeek

class LocationOverviewContentTest : NBContentTest(), LocationCardTest {

    private val dailyText = createNBString("Daily")
    private val hourlyText = createNBString("HourlyItem")

    @Test
    fun viewData_today_shouldRenderCorrectly() {
        testViewData(
            selectedNavigationBarItem = LocationOverviewNavigationBarItem.TODAY,
            assert = { todayText ->
                assertCompose {
                    onNodeWithText(todayText)
                        .assertIsDisplayed()

                    assertStringIsNotDisplayed(hourlyText)

                    assertStringIsNotDisplayed(dailyText)
                }
            }
        )
    }

    @Test
    fun viewData_hourly_shouldRenderCorrectly() {
        testViewData(
            selectedNavigationBarItem = LocationOverviewNavigationBarItem.HOURLY,
            assert = { todayText ->
                assertCompose {
                    assertStringIsNotDisplayed(todayText)

                    onNodeWithText(hourlyText)
                        .assertIsDisplayed()

                    assertStringIsNotDisplayed(dailyText)
                }
            }
        )
    }

    @Test
    fun viewData_daily_shouldRenderCorrectly() {
        testViewData(
            selectedNavigationBarItem = LocationOverviewNavigationBarItem.DAILY,
            assert = { todayText ->
                assertCompose {
                    assertStringIsNotDisplayed(todayText)

                    assertStringIsNotDisplayed(hourlyText)

                    onNodeWithText(dailyText)
                        .assertIsDisplayed()
                }
            }
        )
    }

    private fun testViewData(
        selectedNavigationBarItem: LocationOverviewNavigationBarItem,
        assert: (todayText: NBString?) -> Unit
    ) {
        // Arrange
        val hourlyItems = listOf(
            NBGridItem.OneLine(
                value = NBValueItem.Text(hourlyText)
            )
        )

        val viewData = LocationOverviewViewData(
            todayCardItems = createTestCards("Today"),
            hourlyMap = mapOf(
                DayOfWeek.MONDAY to listOf(
                    LocationOverviewHourlyModel(
                        forecastTime = createNBDateTimeModel(1),
                        itemsCompact = hourlyItems,
                        itemsMedium = hourlyItems,
                        itemsExpanded = hourlyItems
                    )
                )
            ),
            dailyModels = listOf(
                LocationOverviewDailyModel(
                    forecastTime = createNBDateTimeModel(1),
                    weatherIcon = NBIcons.WeatherDayClearSky,
                    probabilityOfPrecipitation = NBValueItem.Text(dailyText),
                    minDailyTemperature = TemperatureValue.from(0.0),
                    maxDailyTemperature = TemperatureValue.from(10.0),
                    minTemperatureAll = TemperatureValue.from(0.0),
                    maxTemperatureAll = TemperatureValue.from(10.0),
                ),
            )
        )


        val uiState = LocationOverviewUiState(
            selectedNavigationBarItem = selectedNavigationBarItem,
            locationResource = null,
            viewDataResource = createNBResource(viewData)
        )

        // Act
        setContent {
            LocationOverviewContent(
                uiState = uiState,
                viewDataFlow = null,
                navigateToAlerts = { _, _ -> },
                navigateToDaily = { _, _, _ -> },
                navigateToHourly = { _, _, _ -> },
            )
        }

        // Assert
        val todayText = viewData.todayCardItems.testCardTitle
        assert(todayText)
    }


}