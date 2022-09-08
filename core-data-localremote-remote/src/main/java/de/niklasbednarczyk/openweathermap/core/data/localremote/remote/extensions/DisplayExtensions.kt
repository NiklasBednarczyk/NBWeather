package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions

import de.niklasbednarczyk.openweathermap.core.common.display.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.UnitsType

fun DataLanguageType.getRemoteName(): String {
    return when (this) {
        DataLanguageType.AFRIKAANS -> "af"
        DataLanguageType.ALBANIAN -> "al"
        DataLanguageType.ARABIC -> "ar"
        DataLanguageType.AZERBAIJANI -> "az"
        DataLanguageType.BULGARIAN -> "bg"
        DataLanguageType.CATALAN -> "ca"
        DataLanguageType.CZECH -> "cz"
        DataLanguageType.DANISH -> "da"
        DataLanguageType.GERMAN -> "de"
        DataLanguageType.GREEK -> "el"
        DataLanguageType.ENGLISH -> "en"
        DataLanguageType.BASQUE -> "eu"
        DataLanguageType.PERSIAN_FARSI -> "fa"
        DataLanguageType.FINNISH -> "fi"
        DataLanguageType.FRENCH -> "fr"
        DataLanguageType.GALICIAN -> "gl"
        DataLanguageType.HEBREW -> "he"
        DataLanguageType.HINDI -> "hi"
        DataLanguageType.CROATIAN -> "hr"
        DataLanguageType.HUNGARIAN -> "hu"
        DataLanguageType.INDONESIAN -> "id"
        DataLanguageType.ITALIAN -> "it"
        DataLanguageType.JAPANESE -> "ja"
        DataLanguageType.KOREAN -> "kr"
        DataLanguageType.LATVIAN -> "la"
        DataLanguageType.LITHUANIAN -> "lt"
        DataLanguageType.MACEDONIAN -> "mk"
        DataLanguageType.NORWEGIAN -> "no"
        DataLanguageType.DUTCH -> "nl"
        DataLanguageType.POLISH -> "pl"
        DataLanguageType.PORTUGUESE -> "pt"
        DataLanguageType.PORTUGUES_BRASIL -> "pt_br"
        DataLanguageType.ROMANIAN -> "ro"
        DataLanguageType.RUSSIAN -> "ru"
        DataLanguageType.SWEDISH -> "sv"
        DataLanguageType.SLOVAK -> "sk"
        DataLanguageType.SLOVENIAN -> "sl"
        DataLanguageType.SPANISH -> "es"
        DataLanguageType.SERBIAN -> "sr"
        DataLanguageType.THAI -> "th"
        DataLanguageType.TURKISH -> "tr"
        DataLanguageType.UKRAINIAN -> "uk"
        DataLanguageType.VIETNAMESE -> "vi"
        DataLanguageType.CHINESE_SIMPLIFIED -> "zh_cn"
        DataLanguageType.CHINESE_TRADITIONAL -> "zh_tw"
        DataLanguageType.ZULU -> "zu"
    }
}

fun UnitsType.getRemoteName(): String {
    return when (this) {
        UnitsType.STANDARD -> "standard"
        UnitsType.METRIC -> "metric"
        UnitsType.IMPERIAL -> "imperial"
    }
}
