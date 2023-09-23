package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.nbHyphenated
import de.niklasbednarczyk.nbweather.core.ui.windowsize.NBWindowSizeType
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthWindowSizeType
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ForecastUnitsItem
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.name
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewCurrentWeatherModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewCurrentWeatherModel.Companion.toRowItemsWithPlaceholders

@Composable
fun ForecastOverviewCurrentWeatherView(
    currentWeather: ForecastOverviewCurrentWeatherModel
) {
    val rowItemCount = when (getWidthWindowSizeType()) {
        NBWindowSizeType.COMPACT -> 3
        NBWindowSizeType.MEDIUM -> 4
        NBWindowSizeType.EXPANDED -> 5
    }

    Column(
        verticalArrangement = columnVerticalArrangementBig
    ) {
        currentWeather.items.chunked(rowItemCount).forEach { rowItems ->
            RowItems(
                rowItems = rowItems,
                rowItemCount = rowItemCount
            )
        }
    }
}

@Composable
private fun DisplayValueRow(
    rowItems: List<ForecastUnitsItem?>
) {
    GridRow(items = rowItems) { item, modifier ->
        Text(
            modifier = modifier,
            text = item.unitsValue.displayValueWithSymbol.asString(),
            style = MaterialTheme.typography.bodyMedium.nbHyphenated(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun <T> GridRow(
    items: List<T?>,
    item: @Composable (item: T, modifier: Modifier) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        val itemModifier = Modifier
            .weight(1f)
            .fillMaxHeight()

        items.forEach { item ->
            if (item != null) {
                item(item, itemModifier)
            } else {
                Spacer(modifier = itemModifier)
            }
        }
    }
}

@Composable
private fun IconRow(
    rowItems: List<ForecastUnitsItem?>
) {
    GridRow(items = rowItems) { item, modifier ->
        NBIcon(
            modifier = modifier,
            icon = item.icon
        )
    }
}

@Composable
private fun NameRow(
    rowItems: List<ForecastUnitsItem?>
) {
    GridRow(items = rowItems) { item, modifier ->
        Text(
            modifier = modifier,
            text = item.name.asString(),
            style = MaterialTheme.typography.titleSmall.nbHyphenated(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RowItems(
    rowItems: List<ForecastUnitsItem>,
    rowItemCount: Int
) {
    val rowItemsWithPlaceholders = rowItems.toRowItemsWithPlaceholders(rowItemCount)

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementSmall
    ) {
        NameRow(
            rowItems = rowItemsWithPlaceholders
        )
        IconRow(
            rowItems = rowItemsWithPlaceholders
        )
        DisplayValueRow(
            rowItems = rowItemsWithPlaceholders
        )
    }
}
