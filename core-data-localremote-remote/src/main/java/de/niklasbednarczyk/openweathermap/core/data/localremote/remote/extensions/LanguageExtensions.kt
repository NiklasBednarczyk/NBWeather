package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType

val OwmDataLanguageType.remoteName: String
    get() = when (this) {
        OwmDataLanguageType.AFRIKAANS -> "af"
        OwmDataLanguageType.ALBANIAN -> "al"
        OwmDataLanguageType.ARABIC -> "ar"
        OwmDataLanguageType.AZERBAIJANI -> "az"
        OwmDataLanguageType.BULGARIAN -> "bg"
        OwmDataLanguageType.CATALAN -> "ca"
        OwmDataLanguageType.CZECH -> "cz"
        OwmDataLanguageType.DANISH -> "da"
        OwmDataLanguageType.GERMAN -> "de"
        OwmDataLanguageType.GREEK -> "el"
        OwmDataLanguageType.ENGLISH -> "en"
        OwmDataLanguageType.BASQUE -> "eu"
        OwmDataLanguageType.PERSIAN_FARSI -> "fa"
        OwmDataLanguageType.FINNISH -> "fi"
        OwmDataLanguageType.FRENCH -> "fr"
        OwmDataLanguageType.GALICIAN -> "gl"
        OwmDataLanguageType.HEBREW -> "he"
        OwmDataLanguageType.HINDI -> "hi"
        OwmDataLanguageType.CROATIAN -> "hr"
        OwmDataLanguageType.HUNGARIAN -> "hu"
        OwmDataLanguageType.INDONESIAN -> "id"
        OwmDataLanguageType.ITALIAN -> "it"
        OwmDataLanguageType.JAPANESE -> "ja"
        OwmDataLanguageType.KOREAN -> "kr"
        OwmDataLanguageType.LATVIAN -> "la"
        OwmDataLanguageType.LITHUANIAN -> "lt"
        OwmDataLanguageType.MACEDONIAN -> "mk"
        OwmDataLanguageType.NORWEGIAN -> "no"
        OwmDataLanguageType.DUTCH -> "nl"
        OwmDataLanguageType.POLISH -> "pl"
        OwmDataLanguageType.PORTUGUESE -> "pt"
        OwmDataLanguageType.PORTUGUES_BRASIL -> "pt_br"
        OwmDataLanguageType.ROMANIAN -> "ro"
        OwmDataLanguageType.RUSSIAN -> "ru"
        OwmDataLanguageType.SWEDISH -> "sv"
        OwmDataLanguageType.SLOVAK -> "sk"
        OwmDataLanguageType.SLOVENIAN -> "sl"
        OwmDataLanguageType.SPANISH -> "es"
        OwmDataLanguageType.SERBIAN -> "sr"
        OwmDataLanguageType.THAI -> "th"
        OwmDataLanguageType.TURKISH -> "tr"
        OwmDataLanguageType.UKRAINIAN -> "uk"
        OwmDataLanguageType.VIETNAMESE -> "vi"
        OwmDataLanguageType.CHINESE_SIMPLIFIED -> "zh_cn"
        OwmDataLanguageType.CHINESE_TRADITIONAL -> "zh_tw"
        OwmDataLanguageType.ZULU -> "zu"
    }

val OwmUnitsType.remoteName: String
    get() = when (this) {
        OwmUnitsType.STANDARD -> "standard"
        OwmUnitsType.METRIC -> "metric"
        OwmUnitsType.IMPERIAL -> "imperial"
    }