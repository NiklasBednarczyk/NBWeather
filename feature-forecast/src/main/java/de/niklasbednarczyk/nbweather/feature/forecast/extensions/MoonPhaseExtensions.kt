package de.niklasbednarczyk.nbweather.feature.forecast.extensions

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem

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
        return NBString.ResString(resId)
    }

val SunAndMoonItem.MoonPhase.icon: NBIconItem
    @Composable
    get() {
        val isDarkTheme = NBSettings.isDarkTheme
        return if (coordinates.isNorthernHemisphere) {
            if (isDarkTheme) {
                moonPhase.iconNorthDark
            } else {
                moonPhase.iconNorthLight
            }
        } else {
            if (isDarkTheme) {
                moonPhase.iconSouthDark
            } else {
                moonPhase.iconSouthLight
            }
        }
    }

private val MoonPhaseType.iconNorthDark: NBIconItem
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> NBIcons.MoonPhaseNorthDarkNewMoon
        MoonPhaseType.WAXING_CRESCENT_1 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> NBIcons.MoonPhaseNorthDarkWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> NBIcons.MoonPhaseNorthDarkFirstQuarterMoon
        MoonPhaseType.WAXING_GIBBOUS_1 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> NBIcons.MoonPhaseNorthDarkWaxingGibbous6
        MoonPhaseType.FULL_MOON -> NBIcons.MoonPhaseNorthDarkFullMoon
        MoonPhaseType.WANING_GIBBOUS_1 -> NBIcons.MoonPhaseNorthDarkWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> NBIcons.MoonPhaseNorthDarkWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> NBIcons.MoonPhaseNorthDarkWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> NBIcons.MoonPhaseNorthDarkWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> NBIcons.MoonPhaseNorthDarkWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> NBIcons.MoonPhaseNorthDarkWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> NBIcons.MoonPhaseNorthDarkLastQuarterMoon
        MoonPhaseType.WANING_CRESCENT_1 -> NBIcons.MoonPhaseNorthDarkWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> NBIcons.MoonPhaseNorthDarkWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> NBIcons.MoonPhaseNorthDarkWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> NBIcons.MoonPhaseNorthDarkWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> NBIcons.MoonPhaseNorthDarkWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> NBIcons.MoonPhaseNorthDarkWaningCrescent6
    }

private val MoonPhaseType.iconNorthLight: NBIconItem
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> NBIcons.MoonPhaseNorthLightNewMoon
        MoonPhaseType.WAXING_CRESCENT_1 -> NBIcons.MoonPhaseNorthLightWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> NBIcons.MoonPhaseNorthLightWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> NBIcons.MoonPhaseNorthLightWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> NBIcons.MoonPhaseNorthLightWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> NBIcons.MoonPhaseNorthLightWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> NBIcons.MoonPhaseNorthLightWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> NBIcons.MoonPhaseNorthLightFirstQuarterMoon
        MoonPhaseType.WAXING_GIBBOUS_1 -> NBIcons.MoonPhaseNorthLightWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> NBIcons.MoonPhaseNorthLightWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> NBIcons.MoonPhaseNorthLightWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> NBIcons.MoonPhaseNorthLightWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> NBIcons.MoonPhaseNorthLightWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> NBIcons.MoonPhaseNorthLightWaxingGibbous6
        MoonPhaseType.FULL_MOON -> NBIcons.MoonPhaseNorthLightFullMoon
        MoonPhaseType.WANING_GIBBOUS_1 -> NBIcons.MoonPhaseNorthLightWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> NBIcons.MoonPhaseNorthLightWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> NBIcons.MoonPhaseNorthLightWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> NBIcons.MoonPhaseNorthLightWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> NBIcons.MoonPhaseNorthLightWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> NBIcons.MoonPhaseNorthLightWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> NBIcons.MoonPhaseNorthLightLastQuarterMoon
        MoonPhaseType.WANING_CRESCENT_1 -> NBIcons.MoonPhaseNorthLightWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> NBIcons.MoonPhaseNorthLightWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> NBIcons.MoonPhaseNorthLightWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> NBIcons.MoonPhaseNorthLightWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> NBIcons.MoonPhaseNorthLightWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> NBIcons.MoonPhaseNorthLightWaningCrescent6
    }

private val MoonPhaseType.iconSouthDark: NBIconItem
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> NBIcons.MoonPhaseSouthDarkNewMoon
        MoonPhaseType.WAXING_CRESCENT_1 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> NBIcons.MoonPhaseSouthDarkWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> NBIcons.MoonPhaseSouthDarkFirstQuarterMoon
        MoonPhaseType.WAXING_GIBBOUS_1 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> NBIcons.MoonPhaseSouthDarkWaxingGibbous6
        MoonPhaseType.FULL_MOON -> NBIcons.MoonPhaseSouthDarkFullMoon
        MoonPhaseType.WANING_GIBBOUS_1 -> NBIcons.MoonPhaseSouthDarkWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> NBIcons.MoonPhaseSouthDarkWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> NBIcons.MoonPhaseSouthDarkWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> NBIcons.MoonPhaseSouthDarkWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> NBIcons.MoonPhaseSouthDarkWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> NBIcons.MoonPhaseSouthDarkWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> NBIcons.MoonPhaseSouthDarkLastQuarterMoon
        MoonPhaseType.WANING_CRESCENT_1 -> NBIcons.MoonPhaseSouthDarkWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> NBIcons.MoonPhaseSouthDarkWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> NBIcons.MoonPhaseSouthDarkWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> NBIcons.MoonPhaseSouthDarkWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> NBIcons.MoonPhaseSouthDarkWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> NBIcons.MoonPhaseSouthDarkWaningCrescent6
    }

private val MoonPhaseType.iconSouthLight: NBIconItem
    get() = when (this) {
        MoonPhaseType.NEW_MOON -> NBIcons.MoonPhaseSouthLightNewMoon
        MoonPhaseType.WAXING_CRESCENT_1 -> NBIcons.MoonPhaseSouthLightWaxingCrescent1
        MoonPhaseType.WAXING_CRESCENT_2 -> NBIcons.MoonPhaseSouthLightWaxingCrescent2
        MoonPhaseType.WAXING_CRESCENT_3 -> NBIcons.MoonPhaseSouthLightWaxingCrescent3
        MoonPhaseType.WAXING_CRESCENT_4 -> NBIcons.MoonPhaseSouthLightWaxingCrescent4
        MoonPhaseType.WAXING_CRESCENT_5 -> NBIcons.MoonPhaseSouthLightWaxingCrescent5
        MoonPhaseType.WAXING_CRESCENT_6 -> NBIcons.MoonPhaseSouthLightWaxingCrescent6
        MoonPhaseType.FIRST_QUARTER_MOON -> NBIcons.MoonPhaseSouthLightFirstQuarterMoon
        MoonPhaseType.WAXING_GIBBOUS_1 -> NBIcons.MoonPhaseSouthLightWaxingGibbous1
        MoonPhaseType.WAXING_GIBBOUS_2 -> NBIcons.MoonPhaseSouthLightWaxingGibbous2
        MoonPhaseType.WAXING_GIBBOUS_3 -> NBIcons.MoonPhaseSouthLightWaxingGibbous3
        MoonPhaseType.WAXING_GIBBOUS_4 -> NBIcons.MoonPhaseSouthLightWaxingGibbous4
        MoonPhaseType.WAXING_GIBBOUS_5 -> NBIcons.MoonPhaseSouthLightWaxingGibbous5
        MoonPhaseType.WAXING_GIBBOUS_6 -> NBIcons.MoonPhaseSouthLightWaxingGibbous6
        MoonPhaseType.FULL_MOON -> NBIcons.MoonPhaseSouthLightFullMoon
        MoonPhaseType.WANING_GIBBOUS_1 -> NBIcons.MoonPhaseSouthLightWaningGibbous1
        MoonPhaseType.WANING_GIBBOUS_2 -> NBIcons.MoonPhaseSouthLightWaningGibbous2
        MoonPhaseType.WANING_GIBBOUS_3 -> NBIcons.MoonPhaseSouthLightWaningGibbous3
        MoonPhaseType.WANING_GIBBOUS_4 -> NBIcons.MoonPhaseSouthLightWaningGibbous4
        MoonPhaseType.WANING_GIBBOUS_5 -> NBIcons.MoonPhaseSouthLightWaningGibbous5
        MoonPhaseType.WANING_GIBBOUS_6 -> NBIcons.MoonPhaseSouthLightWaningGibbous6
        MoonPhaseType.LAST_QUARTER_MOON -> NBIcons.MoonPhaseSouthLightLastQuarterMoon
        MoonPhaseType.WANING_CRESCENT_1 -> NBIcons.MoonPhaseSouthLightWaningCrescent1
        MoonPhaseType.WANING_CRESCENT_2 -> NBIcons.MoonPhaseSouthLightWaningCrescent2
        MoonPhaseType.WANING_CRESCENT_3 -> NBIcons.MoonPhaseSouthLightWaningCrescent3
        MoonPhaseType.WANING_CRESCENT_4 -> NBIcons.MoonPhaseSouthLightWaningCrescent4
        MoonPhaseType.WANING_CRESCENT_5 -> NBIcons.MoonPhaseSouthLightWaningCrescent5
        MoonPhaseType.WANING_CRESCENT_6 -> NBIcons.MoonPhaseSouthLightWaningCrescent6
    }
