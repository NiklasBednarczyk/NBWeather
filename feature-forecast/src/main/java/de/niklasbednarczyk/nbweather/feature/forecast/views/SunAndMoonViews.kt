package de.niklasbednarczyk.nbweather.feature.forecast.views

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridIconModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon

@Composable
fun MoonPhaseGridView(
    moonPhase: MoonPhaseType
) {
    val moonPhaseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moon_phase_title),
        icon = NBGridIconModel(moonPhase.icon),
        value = moonPhase.displayText
    )
    val gridItems = listOf(moonPhaseGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}

@Composable
fun MoonTimesGridView(
    moonrise: NBDateTimeDisplayModel,
    moonset: NBDateTimeDisplayModel
) {
    val moonriseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moonrise_title),
        icon = NBGridIconModel(NBIcons.Moonrise),
        value = moonrise.time
    )
    val moonsetGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moonset_title),
        icon = NBGridIconModel(NBIcons.Moonset),
        value = moonset.time
    )
    val gridItems = listOf(moonriseGridItem, moonsetGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}

@Composable
fun SunGridView(
    sunrise: NBDateTimeDisplayModel,
    sunset: NBDateTimeDisplayModel
) {
    val sunriseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_sunrise_title),
        icon = NBGridIconModel(NBIcons.Sunrise),
        value = sunrise.time
    )
    val sunsetGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_sunset_title),
        icon = NBGridIconModel(NBIcons.Sunset),
        value = sunset.time
    )
    val gridItems = listOf(sunriseGridItem, sunsetGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}
