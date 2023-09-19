package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.airpollution.models.AirPollutionModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

sealed interface ForecastOverviewItem {

    val title: NBString?

    companion object {

        fun from(
            airPollution: AirPollutionModelData,
            oneCall: OneCallModelData
        ): List<ForecastOverviewItem> {
            val items = mutableListOf<ForecastOverviewItem?>()

            items.add(ForecastOverviewAlertsModel.from(oneCall))

            items.add(ForecastOverviewSummaryModel.from(oneCall))

            items.add(ForecastOverviewPrecipitationModel.from(oneCall))

            return items.filterNotNull()
        }

    }

}