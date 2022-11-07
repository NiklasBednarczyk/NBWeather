package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object LanguageMapperData :
    TwoWayMapperDisk<SettingsDataProto.LanguageProto, OwmLanguageType> {
    override fun protoToDisk(proto: SettingsDataProto.LanguageProto): OwmLanguageType {
        return when (proto) {
            SettingsDataProto.LanguageProto.AFRIKAANS -> OwmLanguageType.AFRIKAANS
            SettingsDataProto.LanguageProto.ALBANIAN -> OwmLanguageType.ALBANIAN
            SettingsDataProto.LanguageProto.ARABIC -> OwmLanguageType.ARABIC
            SettingsDataProto.LanguageProto.AZERBAIJANI -> OwmLanguageType.AZERBAIJANI
            SettingsDataProto.LanguageProto.BULGARIAN -> OwmLanguageType.BULGARIAN
            SettingsDataProto.LanguageProto.CATALAN -> OwmLanguageType.CATALAN
            SettingsDataProto.LanguageProto.CZECH -> OwmLanguageType.CZECH
            SettingsDataProto.LanguageProto.DANISH -> OwmLanguageType.DANISH
            SettingsDataProto.LanguageProto.GERMAN -> OwmLanguageType.GERMAN
            SettingsDataProto.LanguageProto.GREEK -> OwmLanguageType.GREEK
            SettingsDataProto.LanguageProto.ENGLISH -> OwmLanguageType.ENGLISH
            SettingsDataProto.LanguageProto.BASQUE -> OwmLanguageType.BASQUE
            SettingsDataProto.LanguageProto.PERSIAN_FARSI -> OwmLanguageType.PERSIAN_FARSI
            SettingsDataProto.LanguageProto.FINNISH -> OwmLanguageType.FINNISH
            SettingsDataProto.LanguageProto.FRENCH -> OwmLanguageType.FRENCH
            SettingsDataProto.LanguageProto.GALICIAN -> OwmLanguageType.GALICIAN
            SettingsDataProto.LanguageProto.HEBREW -> OwmLanguageType.HEBREW
            SettingsDataProto.LanguageProto.HINDI -> OwmLanguageType.HINDI
            SettingsDataProto.LanguageProto.CROATIAN -> OwmLanguageType.CROATIAN
            SettingsDataProto.LanguageProto.HUNGARIAN -> OwmLanguageType.HUNGARIAN
            SettingsDataProto.LanguageProto.INDONESIAN -> OwmLanguageType.INDONESIAN
            SettingsDataProto.LanguageProto.ITALIAN -> OwmLanguageType.ITALIAN
            SettingsDataProto.LanguageProto.JAPANESE -> OwmLanguageType.JAPANESE
            SettingsDataProto.LanguageProto.KOREAN -> OwmLanguageType.KOREAN
            SettingsDataProto.LanguageProto.LATVIAN -> OwmLanguageType.LATVIAN
            SettingsDataProto.LanguageProto.LITHUANIAN -> OwmLanguageType.LITHUANIAN
            SettingsDataProto.LanguageProto.MACEDONIAN -> OwmLanguageType.MACEDONIAN
            SettingsDataProto.LanguageProto.NORWEGIAN -> OwmLanguageType.NORWEGIAN
            SettingsDataProto.LanguageProto.DUTCH -> OwmLanguageType.DUTCH
            SettingsDataProto.LanguageProto.POLISH -> OwmLanguageType.POLISH
            SettingsDataProto.LanguageProto.PORTUGUESE -> OwmLanguageType.PORTUGUESE
            SettingsDataProto.LanguageProto.PORTUGUES_BRASIL -> OwmLanguageType.PORTUGUES_BRASIL
            SettingsDataProto.LanguageProto.ROMANIAN -> OwmLanguageType.ROMANIAN
            SettingsDataProto.LanguageProto.RUSSIAN -> OwmLanguageType.RUSSIAN
            SettingsDataProto.LanguageProto.SWEDISH -> OwmLanguageType.SWEDISH
            SettingsDataProto.LanguageProto.SLOVAK -> OwmLanguageType.SLOVAK
            SettingsDataProto.LanguageProto.SLOVENIAN -> OwmLanguageType.SLOVENIAN
            SettingsDataProto.LanguageProto.SPANISH -> OwmLanguageType.SPANISH
            SettingsDataProto.LanguageProto.SERBIAN -> OwmLanguageType.SERBIAN
            SettingsDataProto.LanguageProto.THAI -> OwmLanguageType.THAI
            SettingsDataProto.LanguageProto.TURKISH -> OwmLanguageType.TURKISH
            SettingsDataProto.LanguageProto.UKRAINIAN -> OwmLanguageType.UKRAINIAN
            SettingsDataProto.LanguageProto.VIETNAMESE -> OwmLanguageType.VIETNAMESE
            SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED -> OwmLanguageType.CHINESE_SIMPLIFIED
            SettingsDataProto.LanguageProto.CHINESE_TRADITIONAL -> OwmLanguageType.CHINESE_TRADITIONAL
            SettingsDataProto.LanguageProto.ZULU -> OwmLanguageType.ZULU
            SettingsDataProto.LanguageProto.UNRECOGNIZED -> OwmLanguageType.ENGLISH
        }
    }

    override fun diskToProto(disk: OwmLanguageType): SettingsDataProto.LanguageProto {
        return when (disk) {
            OwmLanguageType.AFRIKAANS -> SettingsDataProto.LanguageProto.AFRIKAANS
            OwmLanguageType.ALBANIAN -> SettingsDataProto.LanguageProto.ALBANIAN
            OwmLanguageType.ARABIC -> SettingsDataProto.LanguageProto.ARABIC
            OwmLanguageType.AZERBAIJANI -> SettingsDataProto.LanguageProto.AZERBAIJANI
            OwmLanguageType.BULGARIAN -> SettingsDataProto.LanguageProto.BULGARIAN
            OwmLanguageType.CATALAN -> SettingsDataProto.LanguageProto.CATALAN
            OwmLanguageType.CZECH -> SettingsDataProto.LanguageProto.CZECH
            OwmLanguageType.DANISH -> SettingsDataProto.LanguageProto.DANISH
            OwmLanguageType.GERMAN -> SettingsDataProto.LanguageProto.GERMAN
            OwmLanguageType.GREEK -> SettingsDataProto.LanguageProto.GREEK
            OwmLanguageType.ENGLISH -> SettingsDataProto.LanguageProto.ENGLISH
            OwmLanguageType.BASQUE -> SettingsDataProto.LanguageProto.BASQUE
            OwmLanguageType.PERSIAN_FARSI -> SettingsDataProto.LanguageProto.PERSIAN_FARSI
            OwmLanguageType.FINNISH -> SettingsDataProto.LanguageProto.FINNISH
            OwmLanguageType.FRENCH -> SettingsDataProto.LanguageProto.FRENCH
            OwmLanguageType.GALICIAN -> SettingsDataProto.LanguageProto.GALICIAN
            OwmLanguageType.HEBREW -> SettingsDataProto.LanguageProto.HEBREW
            OwmLanguageType.HINDI -> SettingsDataProto.LanguageProto.HINDI
            OwmLanguageType.CROATIAN -> SettingsDataProto.LanguageProto.CROATIAN
            OwmLanguageType.HUNGARIAN -> SettingsDataProto.LanguageProto.HUNGARIAN
            OwmLanguageType.INDONESIAN -> SettingsDataProto.LanguageProto.INDONESIAN
            OwmLanguageType.ITALIAN -> SettingsDataProto.LanguageProto.ITALIAN
            OwmLanguageType.JAPANESE -> SettingsDataProto.LanguageProto.JAPANESE
            OwmLanguageType.KOREAN -> SettingsDataProto.LanguageProto.KOREAN
            OwmLanguageType.LATVIAN -> SettingsDataProto.LanguageProto.LATVIAN
            OwmLanguageType.LITHUANIAN -> SettingsDataProto.LanguageProto.LITHUANIAN
            OwmLanguageType.MACEDONIAN -> SettingsDataProto.LanguageProto.MACEDONIAN
            OwmLanguageType.NORWEGIAN -> SettingsDataProto.LanguageProto.NORWEGIAN
            OwmLanguageType.DUTCH -> SettingsDataProto.LanguageProto.DUTCH
            OwmLanguageType.POLISH -> SettingsDataProto.LanguageProto.POLISH
            OwmLanguageType.PORTUGUESE -> SettingsDataProto.LanguageProto.PORTUGUESE
            OwmLanguageType.PORTUGUES_BRASIL -> SettingsDataProto.LanguageProto.PORTUGUES_BRASIL
            OwmLanguageType.ROMANIAN -> SettingsDataProto.LanguageProto.ROMANIAN
            OwmLanguageType.RUSSIAN -> SettingsDataProto.LanguageProto.RUSSIAN
            OwmLanguageType.SWEDISH -> SettingsDataProto.LanguageProto.SWEDISH
            OwmLanguageType.SLOVAK -> SettingsDataProto.LanguageProto.SLOVAK
            OwmLanguageType.SLOVENIAN -> SettingsDataProto.LanguageProto.SLOVENIAN
            OwmLanguageType.SPANISH -> SettingsDataProto.LanguageProto.SPANISH
            OwmLanguageType.SERBIAN -> SettingsDataProto.LanguageProto.SERBIAN
            OwmLanguageType.THAI -> SettingsDataProto.LanguageProto.THAI
            OwmLanguageType.TURKISH -> SettingsDataProto.LanguageProto.TURKISH
            OwmLanguageType.UKRAINIAN -> SettingsDataProto.LanguageProto.UKRAINIAN
            OwmLanguageType.VIETNAMESE -> SettingsDataProto.LanguageProto.VIETNAMESE
            OwmLanguageType.CHINESE_SIMPLIFIED -> SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED
            OwmLanguageType.CHINESE_TRADITIONAL -> SettingsDataProto.LanguageProto.CHINESE_TRADITIONAL
            OwmLanguageType.ZULU -> SettingsDataProto.LanguageProto.ZULU
        }
    }

}