package de.niklasbednarczyk.nbweather.core.data.localremote.remote.extensions

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType

val NBLanguageType.remoteName: String
    get() = when (this) {
        NBLanguageType.AFRIKAANS -> "af"
        NBLanguageType.ALBANIAN -> "al"
        NBLanguageType.ARABIC -> "ar"
        NBLanguageType.AZERBAIJANI -> "az"
        NBLanguageType.BULGARIAN -> "bg"
        NBLanguageType.CATALAN -> "ca"
        NBLanguageType.CZECH -> "cz"
        NBLanguageType.DANISH -> "da"
        NBLanguageType.GERMAN -> "de"
        NBLanguageType.GREEK -> "el"
        NBLanguageType.ENGLISH -> "en"
        NBLanguageType.BASQUE -> "eu"
        NBLanguageType.PERSIAN_FARSI -> "fa"
        NBLanguageType.FINNISH -> "fi"
        NBLanguageType.FRENCH -> "fr"
        NBLanguageType.GALICIAN -> "gl"
        NBLanguageType.HEBREW -> "he"
        NBLanguageType.HINDI -> "hi"
        NBLanguageType.CROATIAN -> "hr"
        NBLanguageType.HUNGARIAN -> "hu"
        NBLanguageType.INDONESIAN -> "id"
        NBLanguageType.ITALIAN -> "it"
        NBLanguageType.JAPANESE -> "ja"
        NBLanguageType.KOREAN -> "kr"
        NBLanguageType.LATVIAN -> "la"
        NBLanguageType.LITHUANIAN -> "lt"
        NBLanguageType.MACEDONIAN -> "mk"
        NBLanguageType.NORWEGIAN -> "no"
        NBLanguageType.DUTCH -> "nl"
        NBLanguageType.POLISH -> "pl"
        NBLanguageType.PORTUGUESE -> "pt"
        NBLanguageType.PORTUGUES_BRASIL -> "pt_br"
        NBLanguageType.ROMANIAN -> "ro"
        NBLanguageType.RUSSIAN -> "ru"
        NBLanguageType.SWEDISH -> "sv"
        NBLanguageType.SLOVAK -> "sk"
        NBLanguageType.SLOVENIAN -> "sl"
        NBLanguageType.SPANISH -> "es"
        NBLanguageType.SERBIAN -> "sr"
        NBLanguageType.THAI -> "th"
        NBLanguageType.TURKISH -> "tr"
        NBLanguageType.UKRAINIAN -> "uk"
        NBLanguageType.VIETNAMESE -> "vi"
        NBLanguageType.CHINESE_SIMPLIFIED -> "zh_cn"
        NBLanguageType.CHINESE_TRADITIONAL -> "zh_tw"
        NBLanguageType.ZULU -> "zu"
    }

val NBUnitsType.remoteName: String
    get() = when (this) {
        NBUnitsType.STANDARD -> "standard"
        NBUnitsType.METRIC -> "metric"
        NBUnitsType.IMPERIAL -> "imperial"
    }