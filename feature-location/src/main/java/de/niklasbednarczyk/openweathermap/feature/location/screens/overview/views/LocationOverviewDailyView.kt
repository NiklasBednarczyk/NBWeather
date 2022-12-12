package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.text.owmCombinedString
import de.niklasbednarczyk.openweathermap.core.ui.theme.*
import de.niklasbednarczyk.openweathermap.core.ui.theme.customcolors.OwmCustomColors
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueView
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getHeightSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.daily.LocationOverviewDailyModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.daily.LocationOverviewDailyTemperatureModel
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.daily.LocationOverviewDailyTemperaturesModel

private val temperatureTextStyle
    @Composable
    get() = MaterialTheme.typography.bodyLarge

@Composable
fun LocationOverviewDailyView(
    dailyModels: List<LocationOverviewDailyModel>
) {
    val titleStyle = MaterialTheme.typography.titleLarge

    val textAlign = TextAlign.Center

    val itemModifier = Modifier.fillMaxWidth()

    val heightSizeClass = getHeightSizeClass()

    BoxWithConstraints {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            LazyRow(
                contentPadding = listContentPaddingValuesHorizontal
            ) {
                items(dailyModels) { dailyModel ->
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .height(this@BoxWithConstraints.maxHeight)
                            .clickable {
                                //TODO (#9) Navigate to dailies screen
                            }
                            .padding(
                                horizontal = screenHorizontalPadding,
                                vertical = screenVerticalPadding
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = columnVerticalArrangementSmall
                    ) {
                        Text(
                            modifier = itemModifier,
                            text = dailyModel.weekday.asString(),
                            style = titleStyle,
                            textAlign = textAlign
                        )
                        ColumnSpacer()
                        Text(
                            modifier = itemModifier,
                            text = dailyModel.dayOfMonth.asString(),
                            style = titleStyle,
                            textAlign = textAlign
                        )
                        ColumnSpacer()
                        OwmIcon(
                            modifier = itemModifier,
                            icon = dailyModel.weatherIcon
                        )
                        ColumnSpacer()
                        Temperatures(
                            modifier = itemModifier.weight(1f),
                            heightSizeClass = heightSizeClass,
                            temperatures = dailyModel.temperatures
                        )
                        ColumnSpacer()
                        OwmValueView(
                            modifier = itemModifier,
                            value = dailyModel.probabilityOfPrecipitation
                        )
                    }
                }
            }
        }
    }


}

@Composable
private fun ColumnSpacer() {
    Spacer(modifier = Modifier.height(columnVerticalArrangementSmallDp))
}

@Composable
private fun Temperatures(
    modifier: Modifier,
    heightSizeClass: WindowHeightSizeClass?,
    temperatures: LocationOverviewDailyTemperaturesModel
) {
    when (heightSizeClass) {
        WindowHeightSizeClass.Compact, null -> {
            TemperaturesTexts(
                modifier = modifier,
                temperatures = temperatures
            )
        }
        WindowHeightSizeClass.Medium, WindowHeightSizeClass.Expanded -> {
            TemperaturesGraph(
                modifier = modifier,
                temperatures = temperatures,
            )
        }
    }
}

@Composable
private fun TemperaturesTexts(
    modifier: Modifier,
    temperatures: LocationOverviewDailyTemperaturesModel,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TemperaturesText(
            temperature = temperatures.minTemperature
        )
        TemperaturesText(
            temperature = temperatures.maxTemperature
        )
    }
}

@Composable
private fun TemperaturesText(
    temperature: LocationOverviewDailyTemperatureModel
) {
    val text = owmCombinedString(
        temperature.prefix,
        temperature.text
    )
    Text(
        text = text.asString(),
        style = temperatureTextStyle
    )
}

@Composable
private fun TemperaturesGraph(
    modifier: Modifier,
    temperatures: LocationOverviewDailyTemperaturesModel,
) {
    val itemModifier = Modifier.fillMaxWidth()

    val textAlign = TextAlign.Center

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = itemModifier.weight(temperatures.maxTemperature.factor)
        )
        Text(
            modifier = itemModifier,
            text = temperatures.maxTemperature.text.asString(),
            style = temperatureTextStyle,
            textAlign = textAlign
        )
        ColumnSpacer()
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .weight(temperatures.barFactor)
                .background(
                    brush = Brush.linearGradient(
                        0.0f to OwmCustomColors.colors.red,
                        1.0f to OwmCustomColors.colors.blue
                    ),
                    shape = MaterialTheme.shapes.large
                )
        )
        ColumnSpacer()
        Text(
            modifier = itemModifier,
            text = temperatures.minTemperature.text.asString(),
            style = temperatureTextStyle,
            textAlign = textAlign
        )
        Spacer(
            modifier = itemModifier.weight(temperatures.minTemperature.factor),
        )

    }
}