package de.niklasbednarczyk.openweathermap.feature.settings.extensions

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType
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

fun OwmDataLanguageType.getString(): OwmString {
    val resId = when (this) {
        OwmDataLanguageType.AFRIKAANS -> R.string.screen_settings_data_language_value_afrikaans
        OwmDataLanguageType.ALBANIAN -> R.string.screen_settings_data_language_value_albanian
        OwmDataLanguageType.ARABIC -> R.string.screen_settings_data_language_value_arabic
        OwmDataLanguageType.AZERBAIJANI -> R.string.screen_settings_data_language_value_azerbaijani
        OwmDataLanguageType.BULGARIAN -> R.string.screen_settings_data_language_value_bulgarian
        OwmDataLanguageType.CATALAN -> R.string.screen_settings_data_language_value_catalan
        OwmDataLanguageType.CZECH -> R.string.screen_settings_data_language_value_czech
        OwmDataLanguageType.DANISH -> R.string.screen_settings_data_language_value_danish
        OwmDataLanguageType.GERMAN -> R.string.screen_settings_data_language_value_german
        OwmDataLanguageType.GREEK -> R.string.screen_settings_data_language_value_greek
        OwmDataLanguageType.ENGLISH -> R.string.screen_settings_data_language_value_english
        OwmDataLanguageType.BASQUE -> R.string.screen_settings_data_language_value_basque
        OwmDataLanguageType.PERSIAN_FARSI -> R.string.screen_settings_data_language_value_persian_farsi
        OwmDataLanguageType.FINNISH -> R.string.screen_settings_data_language_value_finnish
        OwmDataLanguageType.FRENCH -> R.string.screen_settings_data_language_value_french
        OwmDataLanguageType.GALICIAN -> R.string.screen_settings_data_language_value_galician
        OwmDataLanguageType.HEBREW -> R.string.screen_settings_data_language_value_hebrew
        OwmDataLanguageType.HINDI -> R.string.screen_settings_data_language_value_hindi
        OwmDataLanguageType.CROATIAN -> R.string.screen_settings_data_language_value_croatian
        OwmDataLanguageType.HUNGARIAN -> R.string.screen_settings_data_language_value_hungarian
        OwmDataLanguageType.INDONESIAN -> R.string.screen_settings_data_language_value_indonesian
        OwmDataLanguageType.ITALIAN -> R.string.screen_settings_data_language_value_italian
        OwmDataLanguageType.JAPANESE -> R.string.screen_settings_data_language_value_japanese
        OwmDataLanguageType.KOREAN -> R.string.screen_settings_data_language_value_korean
        OwmDataLanguageType.LATVIAN -> R.string.screen_settings_data_language_value_latvian
        OwmDataLanguageType.LITHUANIAN -> R.string.screen_settings_data_language_value_lithuanian
        OwmDataLanguageType.MACEDONIAN -> R.string.screen_settings_data_language_value_macedonian
        OwmDataLanguageType.NORWEGIAN -> R.string.screen_settings_data_language_value_norwegian
        OwmDataLanguageType.DUTCH -> R.string.screen_settings_data_language_value_dutch
        OwmDataLanguageType.POLISH -> R.string.screen_settings_data_language_value_polish
        OwmDataLanguageType.PORTUGUESE -> R.string.screen_settings_data_language_value_portuguese
        OwmDataLanguageType.PORTUGUES_BRASIL -> R.string.screen_settings_data_language_value_portugues_brasil
        OwmDataLanguageType.ROMANIAN -> R.string.screen_settings_data_language_value_romanian
        OwmDataLanguageType.RUSSIAN -> R.string.screen_settings_data_language_value_russian
        OwmDataLanguageType.SWEDISH -> R.string.screen_settings_data_language_value_swedish
        OwmDataLanguageType.SLOVAK -> R.string.screen_settings_data_language_value_slovak
        OwmDataLanguageType.SLOVENIAN -> R.string.screen_settings_data_language_value_slovenian
        OwmDataLanguageType.SPANISH -> R.string.screen_settings_data_language_value_spanish
        OwmDataLanguageType.SERBIAN -> R.string.screen_settings_data_language_value_serbian
        OwmDataLanguageType.THAI -> R.string.screen_settings_data_language_value_thai
        OwmDataLanguageType.TURKISH -> R.string.screen_settings_data_language_value_turkish
        OwmDataLanguageType.UKRAINIAN -> R.string.screen_settings_data_language_value_ukrainian
        OwmDataLanguageType.VIETNAMESE -> R.string.screen_settings_data_language_value_vietnamese
        OwmDataLanguageType.CHINESE_SIMPLIFIED -> R.string.screen_settings_data_language_value_chinese_simplified
        OwmDataLanguageType.CHINESE_TRADITIONAL -> R.string.screen_settings_data_language_value_chinese_traditional
        OwmDataLanguageType.ZULU -> R.string.screen_settings_data_language_value_zulu
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
        OwmTimeFormatType.SYSTEM_DEFAULT -> R.string.screen_settings_time_format_value_hour_system_default
    }
    return OwmString.Resource(resId)
}
