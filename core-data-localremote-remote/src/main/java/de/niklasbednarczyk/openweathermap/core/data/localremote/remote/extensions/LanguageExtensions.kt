package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType

val OwmLanguageType.remoteName: String
    get() = when (this) {
        OwmLanguageType.AFRIKAANS -> "af"
        OwmLanguageType.ALBANIAN -> "al"
        OwmLanguageType.ARABIC -> "ar"
        OwmLanguageType.AZERBAIJANI -> "az"
        OwmLanguageType.BULGARIAN -> "bg"
        OwmLanguageType.CATALAN -> "ca"
        OwmLanguageType.CZECH -> "cz"
        OwmLanguageType.DANISH -> "da"
        OwmLanguageType.GERMAN -> "de"
        OwmLanguageType.GREEK -> "el"
        OwmLanguageType.ENGLISH -> "en"
        OwmLanguageType.BASQUE -> "eu"
        OwmLanguageType.PERSIAN_FARSI -> "fa"
        OwmLanguageType.FINNISH -> "fi"
        OwmLanguageType.FRENCH -> "fr"
        OwmLanguageType.GALICIAN -> "gl"
        OwmLanguageType.HEBREW -> "he"
        OwmLanguageType.HINDI -> "hi"
        OwmLanguageType.CROATIAN -> "hr"
        OwmLanguageType.HUNGARIAN -> "hu"
        OwmLanguageType.INDONESIAN -> "id"
        OwmLanguageType.ITALIAN -> "it"
        OwmLanguageType.JAPANESE -> "ja"
        OwmLanguageType.KOREAN -> "kr"
        OwmLanguageType.LATVIAN -> "la"
        OwmLanguageType.LITHUANIAN -> "lt"
        OwmLanguageType.MACEDONIAN -> "mk"
        OwmLanguageType.NORWEGIAN -> "no"
        OwmLanguageType.DUTCH -> "nl"
        OwmLanguageType.POLISH -> "pl"
        OwmLanguageType.PORTUGUESE -> "pt"
        OwmLanguageType.PORTUGUES_BRASIL -> "pt_br"
        OwmLanguageType.ROMANIAN -> "ro"
        OwmLanguageType.RUSSIAN -> "ru"
        OwmLanguageType.SWEDISH -> "sv"
        OwmLanguageType.SLOVAK -> "sk"
        OwmLanguageType.SLOVENIAN -> "sl"
        OwmLanguageType.SPANISH -> "es"
        OwmLanguageType.SERBIAN -> "sr"
        OwmLanguageType.THAI -> "th"
        OwmLanguageType.TURKISH -> "tr"
        OwmLanguageType.UKRAINIAN -> "uk"
        OwmLanguageType.VIETNAMESE -> "vi"
        OwmLanguageType.CHINESE_SIMPLIFIED -> "zh_cn"
        OwmLanguageType.CHINESE_TRADITIONAL -> "zh_tw"
        OwmLanguageType.ZULU -> "zu"
    }

val OwmUnitsType.remoteName: String
    get() = when (this) {
        OwmUnitsType.STANDARD -> "standard"
        OwmUnitsType.METRIC -> "metric"
        OwmUnitsType.IMPERIAL -> "imperial"
    }