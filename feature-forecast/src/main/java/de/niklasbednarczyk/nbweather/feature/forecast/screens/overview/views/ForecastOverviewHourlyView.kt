package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmallDp
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesHorizontal
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.divider.NBVerticalDivider
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.color
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewHourlyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly.ForecastOverviewHourlyItemModel

@Composable
fun ForecastOverviewHourlyView(
    hourly: ForecastOverviewHourlyModel
) {
    val lazyListState = rememberLazyListState()
    val firstVisibleItemIndex by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }

    Headline(
        firstVisibleItemPair = hourly.itemPairs.getOrNull(firstVisibleItemIndex)
    )
    LazyRow(
        state = lazyListState,
        horizontalArrangement = rowHorizontalArrangementBig,
        contentPadding = listContentPaddingValuesHorizontal
    ) {
        itemsIndexed(hourly.itemPairs) { index, itemPair ->
            ItemPair(
                index = index,
                itemPair = itemPair,
                lastIndex = hourly.itemPairs.lastIndex
            )

        }
    }
}

@Composable
private fun ForecastTime(
    forecastTime: NBDateTimeDisplayModel
) {
    Text(
        text = forecastTime.time.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun Headline(
    firstVisibleItemPair: Pair<ForecastOverviewHourlyItemModel, ForecastOverviewHourlyItemModel>?
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = firstVisibleItemPair?.first?.forecastTime?.date.asString(),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Item(
    item: ForecastOverviewHourlyItemModel
) {
    Column(
        modifier = Modifier.padding(
            vertical = columnVerticalArrangementSmallDp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        ForecastTime(
            forecastTime = item.forecastTime
        )
        WeatherIcon(
            weatherIcon = item.weatherIcon
        )
        Temperature(
            temperature = item.temperature
        )
        ProbabilityOfPrecipitation(
            probabilityOfPrecipitation = item.probabilityOfPrecipitation
        )
    }
}

@Composable
private fun ItemPair(
    index: Int,
    itemPair: Pair<ForecastOverviewHourlyItemModel, ForecastOverviewHourlyItemModel>,
    lastIndex: Int
) {
    val item = itemPair.first
    val nextItem = itemPair.second

    Row(
        modifier = Modifier.height(IntrinsicSize.Min),
        horizontalArrangement = rowHorizontalArrangementBig
    ) {
        Item(
            item = item
        )
        if (item.forecastTime.dayOfMonth != nextItem.forecastTime.dayOfMonth) {
            NBVerticalDivider()
        }
        if (index == lastIndex) {
            Item(
                item = nextItem
            )
        }
    }
}

@Composable
private fun ProbabilityOfPrecipitation(
    probabilityOfPrecipitation: NBUnitsValue
) {
    Text(
        text = probabilityOfPrecipitation.displayValueWithSymbol.asString(),
        style = MaterialTheme.typography.labelSmall,
        color = probabilityOfPrecipitation.color
    )
}

@Composable
private fun Temperature(
    temperature: NBUnitsValue
) {
    Text(
        text = temperature.displayValueWithSymbol.asString(),
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
private fun WeatherIcon(
    weatherIcon: WeatherIconType
) {
    NBIcon(
        icon = weatherIcon.icon
    )
}