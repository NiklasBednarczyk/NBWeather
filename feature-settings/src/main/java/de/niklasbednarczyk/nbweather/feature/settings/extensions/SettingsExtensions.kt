package de.niklasbednarczyk.nbweather.feature.settings.extensions

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData

val ThemeTypeData.displayText: NBString
    get() {
        val resId = when (this) {
            ThemeTypeData.LIGHT -> R.string.screen_settings_theme_value_light
            ThemeTypeData.DARK -> R.string.screen_settings_theme_value_dark
            ThemeTypeData.SYSTEM_DEFAULT -> R.string.screen_settings_theme_value_system_default
        }
        return NBString.Resource(resId)
    }

val ColorSchemeTypeData.displayText: NBString
    get() {
        val resId = when (this) {
            ColorSchemeTypeData.RED -> R.string.screen_settings_color_scheme_value_red
            ColorSchemeTypeData.GREEN -> R.string.screen_settings_color_scheme_value_green
            ColorSchemeTypeData.BLUE -> R.string.screen_settings_color_scheme_value_blue
            ColorSchemeTypeData.CYAN -> R.string.screen_settings_color_scheme_value_cyan
            ColorSchemeTypeData.MAGENTA -> R.string.screen_settings_color_scheme_value_magenta
            ColorSchemeTypeData.YELLOW -> R.string.screen_settings_color_scheme_value_yellow
        }
        return NBString.Resource(resId)
    }

val NBLanguageType.displayText: NBString
    get() {
        val resId = when (this) {
            NBLanguageType.AFRIKAANS -> R.string.screen_settings_language_value_afrikaans
            NBLanguageType.ALBANIAN -> R.string.screen_settings_language_value_albanian
            NBLanguageType.ARABIC -> R.string.screen_settings_language_value_arabic
            NBLanguageType.AZERBAIJANI -> R.string.screen_settings_language_value_azerbaijani
            NBLanguageType.BULGARIAN -> R.string.screen_settings_language_value_bulgarian
            NBLanguageType.CATALAN -> R.string.screen_settings_language_value_catalan
            NBLanguageType.CZECH -> R.string.screen_settings_language_value_czech
            NBLanguageType.DANISH -> R.string.screen_settings_language_value_danish
            NBLanguageType.GERMAN -> R.string.screen_settings_language_value_german
            NBLanguageType.GREEK -> R.string.screen_settings_language_value_greek
            NBLanguageType.ENGLISH -> R.string.screen_settings_language_value_english
            NBLanguageType.BASQUE -> R.string.screen_settings_language_value_basque
            NBLanguageType.PERSIAN_FARSI -> R.string.screen_settings_language_value_persian_farsi
            NBLanguageType.FINNISH -> R.string.screen_settings_language_value_finnish
            NBLanguageType.FRENCH -> R.string.screen_settings_language_value_french
            NBLanguageType.GALICIAN -> R.string.screen_settings_language_value_galician
            NBLanguageType.HEBREW -> R.string.screen_settings_language_value_hebrew
            NBLanguageType.HINDI -> R.string.screen_settings_language_value_hindi
            NBLanguageType.CROATIAN -> R.string.screen_settings_language_value_croatian
            NBLanguageType.HUNGARIAN -> R.string.screen_settings_language_value_hungarian
            NBLanguageType.INDONESIAN -> R.string.screen_settings_language_value_indonesian
            NBLanguageType.ITALIAN -> R.string.screen_settings_language_value_italian
            NBLanguageType.JAPANESE -> R.string.screen_settings_language_value_japanese
            NBLanguageType.KOREAN -> R.string.screen_settings_language_value_korean
            NBLanguageType.LATVIAN -> R.string.screen_settings_language_value_latvian
            NBLanguageType.LITHUANIAN -> R.string.screen_settings_language_value_lithuanian
            NBLanguageType.MACEDONIAN -> R.string.screen_settings_language_value_macedonian
            NBLanguageType.NORWEGIAN -> R.string.screen_settings_language_value_norwegian
            NBLanguageType.DUTCH -> R.string.screen_settings_language_value_dutch
            NBLanguageType.POLISH -> R.string.screen_settings_language_value_polish
            NBLanguageType.PORTUGUESE -> R.string.screen_settings_language_value_portuguese
            NBLanguageType.PORTUGUES_BRASIL -> R.string.screen_settings_language_value_portugues_brasil
            NBLanguageType.ROMANIAN -> R.string.screen_settings_language_value_romanian
            NBLanguageType.RUSSIAN -> R.string.screen_settings_language_value_russian
            NBLanguageType.SWEDISH -> R.string.screen_settings_language_value_swedish
            NBLanguageType.SLOVAK -> R.string.screen_settings_language_value_slovak
            NBLanguageType.SLOVENIAN -> R.string.screen_settings_language_value_slovenian
            NBLanguageType.SPANISH -> R.string.screen_settings_language_value_spanish
            NBLanguageType.SERBIAN -> R.string.screen_settings_language_value_serbian
            NBLanguageType.THAI -> R.string.screen_settings_language_value_thai
            NBLanguageType.TURKISH -> R.string.screen_settings_language_value_turkish
            NBLanguageType.UKRAINIAN -> R.string.screen_settings_language_value_ukrainian
            NBLanguageType.VIETNAMESE -> R.string.screen_settings_language_value_vietnamese
            NBLanguageType.CHINESE_SIMPLIFIED -> R.string.screen_settings_language_value_chinese_simplified
            NBLanguageType.CHINESE_TRADITIONAL -> R.string.screen_settings_language_value_chinese_traditional
            NBLanguageType.ZULU -> R.string.screen_settings_language_value_zulu
        }
        return NBString.Resource(resId)
    }

val NBUnitsType.displayText: NBString
    get() {
        val resId = when (this) {
            NBUnitsType.STANDARD -> R.string.screen_settings_units_value_standard
            NBUnitsType.METRIC -> R.string.screen_settings_units_value_metric
            NBUnitsType.IMPERIAL -> R.string.screen_settings_units_value_imperial
        }
        return NBString.Resource(resId)
    }

val NBTimeFormatType.displayText: NBString
    get() {
        val resId = when (this) {
            NBTimeFormatType.HOUR_12 -> R.string.screen_settings_time_format_value_hour_12
            NBTimeFormatType.HOUR_24 -> R.string.screen_settings_time_format_value_hour_24
        }
        return NBString.Resource(resId)
    }
