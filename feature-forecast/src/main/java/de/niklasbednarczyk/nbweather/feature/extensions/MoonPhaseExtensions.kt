package de.niklasbednarczyk.nbweather.feature.extensions

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseType

val MoonPhaseType.displayText: NBString
    get() {
        val resId = when (this) {
            MoonPhaseType.NEW_MOON -> R.string.screen_forecast_common_moon_phase_new_moon
            MoonPhaseType.WAXING_CRESCENT_1,
            MoonPhaseType.WAXING_CRESCENT_2,
            MoonPhaseType.WAXING_CRESCENT_3,
            MoonPhaseType.WAXING_CRESCENT_4,
            MoonPhaseType.WAXING_CRESCENT_5,
            MoonPhaseType.WAXING_CRESCENT_6 -> R.string.screen_forecast_common_moon_phase_waxing_crescent

            MoonPhaseType.FIRST_QUARTER_MOON -> R.string.screen_forecast_common_moon_phase_first_quarter_moon
            MoonPhaseType.WAXING_GIBBOUS_1,
            MoonPhaseType.WAXING_GIBBOUS_2,
            MoonPhaseType.WAXING_GIBBOUS_3,
            MoonPhaseType.WAXING_GIBBOUS_4,
            MoonPhaseType.WAXING_GIBBOUS_5,
            MoonPhaseType.WAXING_GIBBOUS_6 -> R.string.screen_forecast_common_moon_phase_waxing_gibbous

            MoonPhaseType.FULL_MOON -> R.string.screen_forecast_common_moon_phase_full_moon
            MoonPhaseType.WANING_GIBBOUS_1,
            MoonPhaseType.WANING_GIBBOUS_2,
            MoonPhaseType.WANING_GIBBOUS_3,
            MoonPhaseType.WANING_GIBBOUS_4,
            MoonPhaseType.WANING_GIBBOUS_5,
            MoonPhaseType.WANING_GIBBOUS_6 -> R.string.screen_forecast_common_moon_phase_waning_gibbous

            MoonPhaseType.LAST_QUARTER_MOON -> R.string.screen_forecast_common_moon_phase_last_quarter_moon
            MoonPhaseType.WANING_CRESCENT_1,
            MoonPhaseType.WANING_CRESCENT_2,
            MoonPhaseType.WANING_CRESCENT_3,
            MoonPhaseType.WANING_CRESCENT_4,
            MoonPhaseType.WANING_CRESCENT_5,
            MoonPhaseType.WANING_CRESCENT_6 -> R.string.screen_forecast_common_moon_phase_waning_crescent
        }
        return NBString.Resource(resId)
    }

val MoonPhaseType.icon: NBIconModel
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> NBIcons.MoonPhaseNew
        MoonPhaseType.WAXING_CRESCENT_1 -> NBIcons.MoonPhaseWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> NBIcons.MoonPhaseWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> NBIcons.MoonPhaseWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> NBIcons.MoonPhaseWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> NBIcons.MoonPhaseWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> NBIcons.MoonPhaseWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> NBIcons.MoonPhaseFirstQuarter
        MoonPhaseType.WAXING_GIBBOUS_1 -> NBIcons.MoonPhaseWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> NBIcons.MoonPhaseWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> NBIcons.MoonPhaseWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> NBIcons.MoonPhaseWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> NBIcons.MoonPhaseWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> NBIcons.MoonPhaseWaxingGibbous6
        MoonPhaseType.FULL_MOON -> NBIcons.MoonPhaseFull
        MoonPhaseType.WANING_GIBBOUS_1 -> NBIcons.MoonPhaseWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> NBIcons.MoonPhaseWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> NBIcons.MoonPhaseWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> NBIcons.MoonPhaseWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> NBIcons.MoonPhaseWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> NBIcons.MoonPhaseWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> NBIcons.MoonPhaseLastQuarter
        MoonPhaseType.WANING_CRESCENT_1 -> NBIcons.MoonPhaseWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> NBIcons.MoonPhaseWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> NBIcons.MoonPhaseWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> NBIcons.MoonPhaseWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> NBIcons.MoonPhaseWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> NBIcons.MoonPhaseWaningCrescent6
    }