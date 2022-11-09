package de.niklasbednarczyk.openweathermap.feature.settings.extensions

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ThemeTypeData

fun ThemeTypeData.getString(): OwmString {
    val resId = when (this) {
        ThemeTypeData.LIGHT -> R.string.screen_settings_theme_value_light
        ThemeTypeData.DARK -> R.string.screen_settings_theme_value_dark
        ThemeTypeData.SYSTEM_DEFAULT -> R.string.screen_settings_theme_value_system_default
    }
    return OwmString.Resource(resId)
}

fun ColorSchemeTypeData.getString(): OwmString {
    val resId = when (this) {
        ColorSchemeTypeData.RED -> R.string.screen_settings_color_scheme_value_red
        ColorSchemeTypeData.GREEN -> R.string.screen_settings_color_scheme_value_green
        ColorSchemeTypeData.BLUE -> R.string.screen_settings_color_scheme_value_blue
        ColorSchemeTypeData.CYAN -> R.string.screen_settings_color_scheme_value_cyan
        ColorSchemeTypeData.MAGENTA -> R.string.screen_settings_color_scheme_value_magenta
        ColorSchemeTypeData.YELLOW -> R.string.screen_settings_color_scheme_value_yellow
        ColorSchemeTypeData.DYNAMIC_COLOR -> R.string.screen_settings_color_scheme_value_dynamic_color
    }
    return OwmString.Resource(resId)
}

fun OwmLanguageType.getString(): OwmString {
    val resId = when (this) {
        OwmLanguageType.AFRIKAANS -> R.string.screen_settings_language_value_afrikaans
        OwmLanguageType.ALBANIAN -> R.string.screen_settings_language_value_albanian
        OwmLanguageType.ARABIC -> R.string.screen_settings_language_value_arabic
        OwmLanguageType.AZERBAIJANI -> R.string.screen_settings_language_value_azerbaijani
        OwmLanguageType.BULGARIAN -> R.string.screen_settings_language_value_bulgarian
        OwmLanguageType.CATALAN -> R.string.screen_settings_language_value_catalan
        OwmLanguageType.CZECH -> R.string.screen_settings_language_value_czech
        OwmLanguageType.DANISH -> R.string.screen_settings_language_value_danish
        OwmLanguageType.GERMAN -> R.string.screen_settings_language_value_german
        OwmLanguageType.GREEK -> R.string.screen_settings_language_value_greek
        OwmLanguageType.ENGLISH -> R.string.screen_settings_language_value_english
        OwmLanguageType.BASQUE -> R.string.screen_settings_language_value_basque
        OwmLanguageType.PERSIAN_FARSI -> R.string.screen_settings_language_value_persian_farsi
        OwmLanguageType.FINNISH -> R.string.screen_settings_language_value_finnish
        OwmLanguageType.FRENCH -> R.string.screen_settings_language_value_french
        OwmLanguageType.GALICIAN -> R.string.screen_settings_language_value_galician
        OwmLanguageType.HEBREW -> R.string.screen_settings_language_value_hebrew
        OwmLanguageType.HINDI -> R.string.screen_settings_language_value_hindi
        OwmLanguageType.CROATIAN -> R.string.screen_settings_language_value_croatian
        OwmLanguageType.HUNGARIAN -> R.string.screen_settings_language_value_hungarian
        OwmLanguageType.INDONESIAN -> R.string.screen_settings_language_value_indonesian
        OwmLanguageType.ITALIAN -> R.string.screen_settings_language_value_italian
        OwmLanguageType.JAPANESE -> R.string.screen_settings_language_value_japanese
        OwmLanguageType.KOREAN -> R.string.screen_settings_language_value_korean
        OwmLanguageType.LATVIAN -> R.string.screen_settings_language_value_latvian
        OwmLanguageType.LITHUANIAN -> R.string.screen_settings_language_value_lithuanian
        OwmLanguageType.MACEDONIAN -> R.string.screen_settings_language_value_macedonian
        OwmLanguageType.NORWEGIAN -> R.string.screen_settings_language_value_norwegian
        OwmLanguageType.DUTCH -> R.string.screen_settings_language_value_dutch
        OwmLanguageType.POLISH -> R.string.screen_settings_language_value_polish
        OwmLanguageType.PORTUGUESE -> R.string.screen_settings_language_value_portuguese
        OwmLanguageType.PORTUGUES_BRASIL -> R.string.screen_settings_language_value_portugues_brasil
        OwmLanguageType.ROMANIAN -> R.string.screen_settings_language_value_romanian
        OwmLanguageType.RUSSIAN -> R.string.screen_settings_language_value_russian
        OwmLanguageType.SWEDISH -> R.string.screen_settings_language_value_swedish
        OwmLanguageType.SLOVAK -> R.string.screen_settings_language_value_slovak
        OwmLanguageType.SLOVENIAN -> R.string.screen_settings_language_value_slovenian
        OwmLanguageType.SPANISH -> R.string.screen_settings_language_value_spanish
        OwmLanguageType.SERBIAN -> R.string.screen_settings_language_value_serbian
        OwmLanguageType.THAI -> R.string.screen_settings_language_value_thai
        OwmLanguageType.TURKISH -> R.string.screen_settings_language_value_turkish
        OwmLanguageType.UKRAINIAN -> R.string.screen_settings_language_value_ukrainian
        OwmLanguageType.VIETNAMESE -> R.string.screen_settings_language_value_vietnamese
        OwmLanguageType.CHINESE_SIMPLIFIED -> R.string.screen_settings_language_value_chinese_simplified
        OwmLanguageType.CHINESE_TRADITIONAL -> R.string.screen_settings_language_value_chinese_traditional
        OwmLanguageType.ZULU -> R.string.screen_settings_language_value_zulu
    }
    return OwmString.Resource(resId)
}

fun OwmUnitsType.getString(): OwmString {
    val resId = when (this) {
        OwmUnitsType.STANDARD -> R.string.screen_settings_units_value_standard
        OwmUnitsType.METRIC -> R.string.screen_settings_units_value_metric
        OwmUnitsType.IMPERIAL -> R.string.screen_settings_units_value_imperial
    }
    return OwmString.Resource(resId)
}

fun OwmTimeFormatType.getString(): OwmString {
    val resId = when (this) {
        OwmTimeFormatType.HOUR_12 -> R.string.screen_settings_time_format_value_hour_12
        OwmTimeFormatType.HOUR_24 -> R.string.screen_settings_time_format_value_hour_24
    }
    return OwmString.Resource(resId)
}
