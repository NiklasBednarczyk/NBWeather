package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.airpollution.models.AirPollutionModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

sealed interface ForecastOverviewItem {

    companion object {

        fun from(
            airPollution: AirPollutionModelData,
            oneCall: OneCallModelData
        ): List<ForecastOverviewItem>? {
            val items = listOfNotNull(
                ForecastOverviewAlertsModel.from(oneCall),
                ForecastOverviewSummaryModel.from(oneCall),
                ForecastOverviewPrecipitationModel.from(oneCall),
                ForecastOverviewHourlyModel.from(oneCall),
                ForecastOverviewCurrentWeatherModel.from(oneCall)
            )
            return nbNullSafeList(items) { i -> i }
        }

    }

}