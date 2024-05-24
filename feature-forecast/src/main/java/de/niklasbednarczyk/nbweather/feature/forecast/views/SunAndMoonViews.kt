package de.niklasbednarczyk.nbweather.feature.forecast.views

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.common.time
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridIconModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridModel
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.icon
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem

@Composable
fun MoonPhaseGridView(
    moonPhase: SunAndMoonItem.MoonPhase
) {
    val moonPhaseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moon_phase_title),
        icon = NBGridIconModel(moonPhase.icon),
        value = moonPhase.moonPhase.displayText
    )
    val gridItems = listOf(moonPhaseGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}

@Composable
fun MoonTimesGridView(
    moonTimes: SunAndMoonItem.MoonTimes
) {
    val moonriseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moonrise_title),
        icon = NBGridIconModel(NBIcons.Moonrise),
        value = moonTimes.moonrise.time
    )
    val moonsetGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_moonset_title),
        icon = NBGridIconModel(NBIcons.Moonset),
        value = moonTimes.moonset.time
    )
    val gridItems = listOf(moonriseGridItem, moonsetGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}

@Composable
fun SunTimesGridView(
    sunTimes: SunAndMoonItem.SunTimes
) {
    val sunriseGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_sunrise_title),
        icon = NBGridIconModel(NBIcons.Sunrise),
        value = sunTimes.sunrise.time
    )
    val sunsetGridItem = NBGridModel(
        name = NBString.ResString(R.string.screen_forecast_common_sun_and_moon_sunset_title),
        icon = NBGridIconModel(NBIcons.Sunset),
        value = sunTimes.sunset.time
    )
    val gridItems = listOf(sunriseGridItem, sunsetGridItem)

    NBGridView(
        items = gridItems,
        rowItemCountLimit = gridItems.size
    )
}
