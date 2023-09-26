package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.common.displayValue
import de.niklasbednarczyk.nbweather.core.ui.common.displayValueWithSymbol
import de.niklasbednarczyk.nbweather.core.ui.common.symbol
import de.niklasbednarczyk.nbweather.core.ui.graphs.NBGraphsView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.color

@Composable
fun ForecastHourlyContent(
    uiState: ForecastHourlyUiState
) {
    NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
        NBGraphsView(
            viewData = viewData,
            getSymbol = { value -> value.unitsValue.symbol },
            getDisplayValue = { value ->
                val unitsValue = value.unitsValue
                if (unitsValue is TemperatureUnitsValue.Long) {
                    unitsValue.toShort().displayValueWithSymbol
                } else {
                    unitsValue.displayValue
                }
            },
            getLineColor = { value -> value.unitsValue.color }
        )
    }
}