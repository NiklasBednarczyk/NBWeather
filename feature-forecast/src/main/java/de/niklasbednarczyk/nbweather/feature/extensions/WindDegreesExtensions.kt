package de.niklasbednarczyk.nbweather.feature.extensions

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesType

val WindDegreesType.displayText: NBString
    get() {
        val resId = when (this) {
            WindDegreesType.N -> R.string.screen_forecast_common_wind_degrees_n
            WindDegreesType.NNE -> R.string.screen_forecast_common_wind_degrees_nne
            WindDegreesType.NE -> R.string.screen_forecast_common_wind_degrees_ne
            WindDegreesType.ENE -> R.string.screen_forecast_common_wind_degrees_ene
            WindDegreesType.E -> R.string.screen_forecast_common_wind_degrees_e
            WindDegreesType.ESE -> R.string.screen_forecast_common_wind_degrees_ese
            WindDegreesType.SE -> R.string.screen_forecast_common_wind_degrees_se
            WindDegreesType.SSE -> R.string.screen_forecast_common_wind_degrees_sse
            WindDegreesType.S -> R.string.screen_forecast_common_wind_degrees_s
            WindDegreesType.SSW -> R.string.screen_forecast_common_wind_degrees_ssw
            WindDegreesType.SW -> R.string.screen_forecast_common_wind_degrees_sw
            WindDegreesType.WSW -> R.string.screen_forecast_common_wind_degrees_wsw
            WindDegreesType.W -> R.string.screen_forecast_common_wind_degrees_w
            WindDegreesType.WNW -> R.string.screen_forecast_common_wind_degrees_wnw
            WindDegreesType.NW -> R.string.screen_forecast_common_wind_degrees_nw
            WindDegreesType.NNW -> R.string.screen_forecast_common_wind_degrees_nnw
        }
        return NBString.Resource(resId)
    }