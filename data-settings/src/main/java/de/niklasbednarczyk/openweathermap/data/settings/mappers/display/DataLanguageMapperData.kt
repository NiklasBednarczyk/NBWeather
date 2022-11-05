package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object DataLanguageMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.DataLanguageProto, OwmDataLanguageType> {
    override fun protoToDisk(proto: SettingsDisplayProto.DataLanguageProto): OwmDataLanguageType {
        return when (proto) {
            SettingsDisplayProto.DataLanguageProto.AFRIKAANS -> OwmDataLanguageType.AFRIKAANS
            SettingsDisplayProto.DataLanguageProto.ALBANIAN -> OwmDataLanguageType.ALBANIAN
            SettingsDisplayProto.DataLanguageProto.ARABIC -> OwmDataLanguageType.ARABIC
            SettingsDisplayProto.DataLanguageProto.AZERBAIJANI -> OwmDataLanguageType.AZERBAIJANI
            SettingsDisplayProto.DataLanguageProto.BULGARIAN -> OwmDataLanguageType.BULGARIAN
            SettingsDisplayProto.DataLanguageProto.CATALAN -> OwmDataLanguageType.CATALAN
            SettingsDisplayProto.DataLanguageProto.CZECH -> OwmDataLanguageType.CZECH
            SettingsDisplayProto.DataLanguageProto.DANISH -> OwmDataLanguageType.DANISH
            SettingsDisplayProto.DataLanguageProto.GERMAN -> OwmDataLanguageType.GERMAN
            SettingsDisplayProto.DataLanguageProto.GREEK -> OwmDataLanguageType.GREEK
            SettingsDisplayProto.DataLanguageProto.ENGLISH -> OwmDataLanguageType.ENGLISH
            SettingsDisplayProto.DataLanguageProto.BASQUE -> OwmDataLanguageType.BASQUE
            SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI -> OwmDataLanguageType.PERSIAN_FARSI
            SettingsDisplayProto.DataLanguageProto.FINNISH -> OwmDataLanguageType.FINNISH
            SettingsDisplayProto.DataLanguageProto.FRENCH -> OwmDataLanguageType.FRENCH
            SettingsDisplayProto.DataLanguageProto.GALICIAN -> OwmDataLanguageType.GALICIAN
            SettingsDisplayProto.DataLanguageProto.HEBREW -> OwmDataLanguageType.HEBREW
            SettingsDisplayProto.DataLanguageProto.HINDI -> OwmDataLanguageType.HINDI
            SettingsDisplayProto.DataLanguageProto.CROATIAN -> OwmDataLanguageType.CROATIAN
            SettingsDisplayProto.DataLanguageProto.HUNGARIAN -> OwmDataLanguageType.HUNGARIAN
            SettingsDisplayProto.DataLanguageProto.INDONESIAN -> OwmDataLanguageType.INDONESIAN
            SettingsDisplayProto.DataLanguageProto.ITALIAN -> OwmDataLanguageType.ITALIAN
            SettingsDisplayProto.DataLanguageProto.JAPANESE -> OwmDataLanguageType.JAPANESE
            SettingsDisplayProto.DataLanguageProto.KOREAN -> OwmDataLanguageType.KOREAN
            SettingsDisplayProto.DataLanguageProto.LATVIAN -> OwmDataLanguageType.LATVIAN
            SettingsDisplayProto.DataLanguageProto.LITHUANIAN -> OwmDataLanguageType.LITHUANIAN
            SettingsDisplayProto.DataLanguageProto.MACEDONIAN -> OwmDataLanguageType.MACEDONIAN
            SettingsDisplayProto.DataLanguageProto.NORWEGIAN -> OwmDataLanguageType.NORWEGIAN
            SettingsDisplayProto.DataLanguageProto.DUTCH -> OwmDataLanguageType.DUTCH
            SettingsDisplayProto.DataLanguageProto.POLISH -> OwmDataLanguageType.POLISH
            SettingsDisplayProto.DataLanguageProto.PORTUGUESE -> OwmDataLanguageType.PORTUGUESE
            SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL -> OwmDataLanguageType.PORTUGUES_BRASIL
            SettingsDisplayProto.DataLanguageProto.ROMANIAN -> OwmDataLanguageType.ROMANIAN
            SettingsDisplayProto.DataLanguageProto.RUSSIAN -> OwmDataLanguageType.RUSSIAN
            SettingsDisplayProto.DataLanguageProto.SWEDISH -> OwmDataLanguageType.SWEDISH
            SettingsDisplayProto.DataLanguageProto.SLOVAK -> OwmDataLanguageType.SLOVAK
            SettingsDisplayProto.DataLanguageProto.SLOVENIAN -> OwmDataLanguageType.SLOVENIAN
            SettingsDisplayProto.DataLanguageProto.SPANISH -> OwmDataLanguageType.SPANISH
            SettingsDisplayProto.DataLanguageProto.SERBIAN -> OwmDataLanguageType.SERBIAN
            SettingsDisplayProto.DataLanguageProto.THAI -> OwmDataLanguageType.THAI
            SettingsDisplayProto.DataLanguageProto.TURKISH -> OwmDataLanguageType.TURKISH
            SettingsDisplayProto.DataLanguageProto.UKRAINIAN -> OwmDataLanguageType.UKRAINIAN
            SettingsDisplayProto.DataLanguageProto.VIETNAMESE -> OwmDataLanguageType.VIETNAMESE
            SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED -> OwmDataLanguageType.CHINESE_SIMPLIFIED
            SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL -> OwmDataLanguageType.CHINESE_TRADITIONAL
            SettingsDisplayProto.DataLanguageProto.ZULU -> OwmDataLanguageType.ZULU
            SettingsDisplayProto.DataLanguageProto.UNRECOGNIZED -> OwmDataLanguageType.ENGLISH
        }
    }

    override fun diskToProto(disk: OwmDataLanguageType): SettingsDisplayProto.DataLanguageProto {
        return when (disk) {
            OwmDataLanguageType.AFRIKAANS -> SettingsDisplayProto.DataLanguageProto.AFRIKAANS
            OwmDataLanguageType.ALBANIAN -> SettingsDisplayProto.DataLanguageProto.ALBANIAN
            OwmDataLanguageType.ARABIC -> SettingsDisplayProto.DataLanguageProto.ARABIC
            OwmDataLanguageType.AZERBAIJANI -> SettingsDisplayProto.DataLanguageProto.AZERBAIJANI
            OwmDataLanguageType.BULGARIAN -> SettingsDisplayProto.DataLanguageProto.BULGARIAN
            OwmDataLanguageType.CATALAN -> SettingsDisplayProto.DataLanguageProto.CATALAN
            OwmDataLanguageType.CZECH -> SettingsDisplayProto.DataLanguageProto.CZECH
            OwmDataLanguageType.DANISH -> SettingsDisplayProto.DataLanguageProto.DANISH
            OwmDataLanguageType.GERMAN -> SettingsDisplayProto.DataLanguageProto.GERMAN
            OwmDataLanguageType.GREEK -> SettingsDisplayProto.DataLanguageProto.GREEK
            OwmDataLanguageType.ENGLISH -> SettingsDisplayProto.DataLanguageProto.ENGLISH
            OwmDataLanguageType.BASQUE -> SettingsDisplayProto.DataLanguageProto.BASQUE
            OwmDataLanguageType.PERSIAN_FARSI -> SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI
            OwmDataLanguageType.FINNISH -> SettingsDisplayProto.DataLanguageProto.FINNISH
            OwmDataLanguageType.FRENCH -> SettingsDisplayProto.DataLanguageProto.FRENCH
            OwmDataLanguageType.GALICIAN -> SettingsDisplayProto.DataLanguageProto.GALICIAN
            OwmDataLanguageType.HEBREW -> SettingsDisplayProto.DataLanguageProto.HEBREW
            OwmDataLanguageType.HINDI -> SettingsDisplayProto.DataLanguageProto.HINDI
            OwmDataLanguageType.CROATIAN -> SettingsDisplayProto.DataLanguageProto.CROATIAN
            OwmDataLanguageType.HUNGARIAN -> SettingsDisplayProto.DataLanguageProto.HUNGARIAN
            OwmDataLanguageType.INDONESIAN -> SettingsDisplayProto.DataLanguageProto.INDONESIAN
            OwmDataLanguageType.ITALIAN -> SettingsDisplayProto.DataLanguageProto.ITALIAN
            OwmDataLanguageType.JAPANESE -> SettingsDisplayProto.DataLanguageProto.JAPANESE
            OwmDataLanguageType.KOREAN -> SettingsDisplayProto.DataLanguageProto.KOREAN
            OwmDataLanguageType.LATVIAN -> SettingsDisplayProto.DataLanguageProto.LATVIAN
            OwmDataLanguageType.LITHUANIAN -> SettingsDisplayProto.DataLanguageProto.LITHUANIAN
            OwmDataLanguageType.MACEDONIAN -> SettingsDisplayProto.DataLanguageProto.MACEDONIAN
            OwmDataLanguageType.NORWEGIAN -> SettingsDisplayProto.DataLanguageProto.NORWEGIAN
            OwmDataLanguageType.DUTCH -> SettingsDisplayProto.DataLanguageProto.DUTCH
            OwmDataLanguageType.POLISH -> SettingsDisplayProto.DataLanguageProto.POLISH
            OwmDataLanguageType.PORTUGUESE -> SettingsDisplayProto.DataLanguageProto.PORTUGUESE
            OwmDataLanguageType.PORTUGUES_BRASIL -> SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL
            OwmDataLanguageType.ROMANIAN -> SettingsDisplayProto.DataLanguageProto.ROMANIAN
            OwmDataLanguageType.RUSSIAN -> SettingsDisplayProto.DataLanguageProto.RUSSIAN
            OwmDataLanguageType.SWEDISH -> SettingsDisplayProto.DataLanguageProto.SWEDISH
            OwmDataLanguageType.SLOVAK -> SettingsDisplayProto.DataLanguageProto.SLOVAK
            OwmDataLanguageType.SLOVENIAN -> SettingsDisplayProto.DataLanguageProto.SLOVENIAN
            OwmDataLanguageType.SPANISH -> SettingsDisplayProto.DataLanguageProto.SPANISH
            OwmDataLanguageType.SERBIAN -> SettingsDisplayProto.DataLanguageProto.SERBIAN
            OwmDataLanguageType.THAI -> SettingsDisplayProto.DataLanguageProto.THAI
            OwmDataLanguageType.TURKISH -> SettingsDisplayProto.DataLanguageProto.TURKISH
            OwmDataLanguageType.UKRAINIAN -> SettingsDisplayProto.DataLanguageProto.UKRAINIAN
            OwmDataLanguageType.VIETNAMESE -> SettingsDisplayProto.DataLanguageProto.VIETNAMESE
            OwmDataLanguageType.CHINESE_SIMPLIFIED -> SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED
            OwmDataLanguageType.CHINESE_TRADITIONAL -> SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL
            OwmDataLanguageType.ZULU -> SettingsDisplayProto.DataLanguageProto.ZULU
        }
    }

}