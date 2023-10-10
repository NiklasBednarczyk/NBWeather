package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

data class ForecastHourlyCanvasViewData(
    val axes: List<ForecastHourlyCanvasAxisModel>,
    val graphs: List<ForecastHourlyCanvasGraphModel>
) {

    val axesZipped = axes.zipWithNext()

}