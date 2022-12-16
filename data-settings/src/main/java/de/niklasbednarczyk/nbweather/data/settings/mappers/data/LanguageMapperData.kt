package de.niklasbednarczyk.nbweather.data.settings.mappers.data

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto

internal object LanguageMapperData :
    TwoWayMapperDisk<SettingsDataProto.LanguageProto, NBLanguageType> {
    override fun protoToDisk(proto: SettingsDataProto.LanguageProto): NBLanguageType {
        return when (proto) {
            SettingsDataProto.LanguageProto.AFRIKAANS -> NBLanguageType.AFRIKAANS
            SettingsDataProto.LanguageProto.ALBANIAN -> NBLanguageType.ALBANIAN
            SettingsDataProto.LanguageProto.ARABIC -> NBLanguageType.ARABIC
            SettingsDataProto.LanguageProto.AZERBAIJANI -> NBLanguageType.AZERBAIJANI
            SettingsDataProto.LanguageProto.BULGARIAN -> NBLanguageType.BULGARIAN
            SettingsDataProto.LanguageProto.CATALAN -> NBLanguageType.CATALAN
            SettingsDataProto.LanguageProto.CZECH -> NBLanguageType.CZECH
            SettingsDataProto.LanguageProto.DANISH -> NBLanguageType.DANISH
            SettingsDataProto.LanguageProto.GERMAN -> NBLanguageType.GERMAN
            SettingsDataProto.LanguageProto.GREEK -> NBLanguageType.GREEK
            SettingsDataProto.LanguageProto.ENGLISH -> NBLanguageType.ENGLISH
            SettingsDataProto.LanguageProto.BASQUE -> NBLanguageType.BASQUE
            SettingsDataProto.LanguageProto.PERSIAN_FARSI -> NBLanguageType.PERSIAN_FARSI
            SettingsDataProto.LanguageProto.FINNISH -> NBLanguageType.FINNISH
            SettingsDataProto.LanguageProto.FRENCH -> NBLanguageType.FRENCH
            SettingsDataProto.LanguageProto.GALICIAN -> NBLanguageType.GALICIAN
            SettingsDataProto.LanguageProto.HEBREW -> NBLanguageType.HEBREW
            SettingsDataProto.LanguageProto.HINDI -> NBLanguageType.HINDI
            SettingsDataProto.LanguageProto.CROATIAN -> NBLanguageType.CROATIAN
            SettingsDataProto.LanguageProto.HUNGARIAN -> NBLanguageType.HUNGARIAN
            SettingsDataProto.LanguageProto.INDONESIAN -> NBLanguageType.INDONESIAN
            SettingsDataProto.LanguageProto.ITALIAN -> NBLanguageType.ITALIAN
            SettingsDataProto.LanguageProto.JAPANESE -> NBLanguageType.JAPANESE
            SettingsDataProto.LanguageProto.KOREAN -> NBLanguageType.KOREAN
            SettingsDataProto.LanguageProto.LATVIAN -> NBLanguageType.LATVIAN
            SettingsDataProto.LanguageProto.LITHUANIAN -> NBLanguageType.LITHUANIAN
            SettingsDataProto.LanguageProto.MACEDONIAN -> NBLanguageType.MACEDONIAN
            SettingsDataProto.LanguageProto.NORWEGIAN -> NBLanguageType.NORWEGIAN
            SettingsDataProto.LanguageProto.DUTCH -> NBLanguageType.DUTCH
            SettingsDataProto.LanguageProto.POLISH -> NBLanguageType.POLISH
            SettingsDataProto.LanguageProto.PORTUGUESE -> NBLanguageType.PORTUGUESE
            SettingsDataProto.LanguageProto.PORTUGUES_BRASIL -> NBLanguageType.PORTUGUES_BRASIL
            SettingsDataProto.LanguageProto.ROMANIAN -> NBLanguageType.ROMANIAN
            SettingsDataProto.LanguageProto.RUSSIAN -> NBLanguageType.RUSSIAN
            SettingsDataProto.LanguageProto.SWEDISH -> NBLanguageType.SWEDISH
            SettingsDataProto.LanguageProto.SLOVAK -> NBLanguageType.SLOVAK
            SettingsDataProto.LanguageProto.SLOVENIAN -> NBLanguageType.SLOVENIAN
            SettingsDataProto.LanguageProto.SPANISH -> NBLanguageType.SPANISH
            SettingsDataProto.LanguageProto.SERBIAN -> NBLanguageType.SERBIAN
            SettingsDataProto.LanguageProto.THAI -> NBLanguageType.THAI
            SettingsDataProto.LanguageProto.TURKISH -> NBLanguageType.TURKISH
            SettingsDataProto.LanguageProto.UKRAINIAN -> NBLanguageType.UKRAINIAN
            SettingsDataProto.LanguageProto.VIETNAMESE -> NBLanguageType.VIETNAMESE
            SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED -> NBLanguageType.CHINESE_SIMPLIFIED
            SettingsDataProto.LanguageProto.CHINESE_TRADITIONAL -> NBLanguageType.CHINESE_TRADITIONAL
            SettingsDataProto.LanguageProto.ZULU -> NBLanguageType.ZULU
            SettingsDataProto.LanguageProto.UNRECOGNIZED -> NBLanguageType.ENGLISH
        }
    }

    override fun diskToProto(disk: NBLanguageType): SettingsDataProto.LanguageProto {
        return when (disk) {
            NBLanguageType.AFRIKAANS -> SettingsDataProto.LanguageProto.AFRIKAANS
            NBLanguageType.ALBANIAN -> SettingsDataProto.LanguageProto.ALBANIAN
            NBLanguageType.ARABIC -> SettingsDataProto.LanguageProto.ARABIC
            NBLanguageType.AZERBAIJANI -> SettingsDataProto.LanguageProto.AZERBAIJANI
            NBLanguageType.BULGARIAN -> SettingsDataProto.LanguageProto.BULGARIAN
            NBLanguageType.CATALAN -> SettingsDataProto.LanguageProto.CATALAN
            NBLanguageType.CZECH -> SettingsDataProto.LanguageProto.CZECH
            NBLanguageType.DANISH -> SettingsDataProto.LanguageProto.DANISH
            NBLanguageType.GERMAN -> SettingsDataProto.LanguageProto.GERMAN
            NBLanguageType.GREEK -> SettingsDataProto.LanguageProto.GREEK
            NBLanguageType.ENGLISH -> SettingsDataProto.LanguageProto.ENGLISH
            NBLanguageType.BASQUE -> SettingsDataProto.LanguageProto.BASQUE
            NBLanguageType.PERSIAN_FARSI -> SettingsDataProto.LanguageProto.PERSIAN_FARSI
            NBLanguageType.FINNISH -> SettingsDataProto.LanguageProto.FINNISH
            NBLanguageType.FRENCH -> SettingsDataProto.LanguageProto.FRENCH
            NBLanguageType.GALICIAN -> SettingsDataProto.LanguageProto.GALICIAN
            NBLanguageType.HEBREW -> SettingsDataProto.LanguageProto.HEBREW
            NBLanguageType.HINDI -> SettingsDataProto.LanguageProto.HINDI
            NBLanguageType.CROATIAN -> SettingsDataProto.LanguageProto.CROATIAN
            NBLanguageType.HUNGARIAN -> SettingsDataProto.LanguageProto.HUNGARIAN
            NBLanguageType.INDONESIAN -> SettingsDataProto.LanguageProto.INDONESIAN
            NBLanguageType.ITALIAN -> SettingsDataProto.LanguageProto.ITALIAN
            NBLanguageType.JAPANESE -> SettingsDataProto.LanguageProto.JAPANESE
            NBLanguageType.KOREAN -> SettingsDataProto.LanguageProto.KOREAN
            NBLanguageType.LATVIAN -> SettingsDataProto.LanguageProto.LATVIAN
            NBLanguageType.LITHUANIAN -> SettingsDataProto.LanguageProto.LITHUANIAN
            NBLanguageType.MACEDONIAN -> SettingsDataProto.LanguageProto.MACEDONIAN
            NBLanguageType.NORWEGIAN -> SettingsDataProto.LanguageProto.NORWEGIAN
            NBLanguageType.DUTCH -> SettingsDataProto.LanguageProto.DUTCH
            NBLanguageType.POLISH -> SettingsDataProto.LanguageProto.POLISH
            NBLanguageType.PORTUGUESE -> SettingsDataProto.LanguageProto.PORTUGUESE
            NBLanguageType.PORTUGUES_BRASIL -> SettingsDataProto.LanguageProto.PORTUGUES_BRASIL
            NBLanguageType.ROMANIAN -> SettingsDataProto.LanguageProto.ROMANIAN
            NBLanguageType.RUSSIAN -> SettingsDataProto.LanguageProto.RUSSIAN
            NBLanguageType.SWEDISH -> SettingsDataProto.LanguageProto.SWEDISH
            NBLanguageType.SLOVAK -> SettingsDataProto.LanguageProto.SLOVAK
            NBLanguageType.SLOVENIAN -> SettingsDataProto.LanguageProto.SLOVENIAN
            NBLanguageType.SPANISH -> SettingsDataProto.LanguageProto.SPANISH
            NBLanguageType.SERBIAN -> SettingsDataProto.LanguageProto.SERBIAN
            NBLanguageType.THAI -> SettingsDataProto.LanguageProto.THAI
            NBLanguageType.TURKISH -> SettingsDataProto.LanguageProto.TURKISH
            NBLanguageType.UKRAINIAN -> SettingsDataProto.LanguageProto.UKRAINIAN
            NBLanguageType.VIETNAMESE -> SettingsDataProto.LanguageProto.VIETNAMESE
            NBLanguageType.CHINESE_SIMPLIFIED -> SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED
            NBLanguageType.CHINESE_TRADITIONAL -> SettingsDataProto.LanguageProto.CHINESE_TRADITIONAL
            NBLanguageType.ZULU -> SettingsDataProto.LanguageProto.ZULU
        }
    }

}