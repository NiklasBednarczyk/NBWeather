package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object DataLanguageMapperData :
    TwoWayMapperDisk<SettingsDataProto.DataLanguageProto, DataLanguageType> {
    override fun protoToDisk(proto: SettingsDataProto.DataLanguageProto): DataLanguageType {
        return when (proto) {
            SettingsDataProto.DataLanguageProto.AFRIKAANS -> DataLanguageType.AFRIKAANS
            SettingsDataProto.DataLanguageProto.ALBANIAN -> DataLanguageType.ALBANIAN
            SettingsDataProto.DataLanguageProto.ARABIC -> DataLanguageType.ARABIC
            SettingsDataProto.DataLanguageProto.AZERBAIJANI -> DataLanguageType.AZERBAIJANI
            SettingsDataProto.DataLanguageProto.BULGARIAN -> DataLanguageType.BULGARIAN
            SettingsDataProto.DataLanguageProto.CATALAN -> DataLanguageType.CATALAN
            SettingsDataProto.DataLanguageProto.CZECH -> DataLanguageType.CZECH
            SettingsDataProto.DataLanguageProto.DANISH -> DataLanguageType.DANISH
            SettingsDataProto.DataLanguageProto.GERMAN -> DataLanguageType.GERMAN
            SettingsDataProto.DataLanguageProto.GREEK -> DataLanguageType.GREEK
            SettingsDataProto.DataLanguageProto.ENGLISH -> DataLanguageType.ENGLISH
            SettingsDataProto.DataLanguageProto.BASQUE -> DataLanguageType.BASQUE
            SettingsDataProto.DataLanguageProto.PERSIAN_FARSI -> DataLanguageType.PERSIAN_FARSI
            SettingsDataProto.DataLanguageProto.FINNISH -> DataLanguageType.FINNISH
            SettingsDataProto.DataLanguageProto.FRENCH -> DataLanguageType.FRENCH
            SettingsDataProto.DataLanguageProto.GALICIAN -> DataLanguageType.GALICIAN
            SettingsDataProto.DataLanguageProto.HEBREW -> DataLanguageType.HEBREW
            SettingsDataProto.DataLanguageProto.HINDI -> DataLanguageType.HINDI
            SettingsDataProto.DataLanguageProto.CROATIAN -> DataLanguageType.CROATIAN
            SettingsDataProto.DataLanguageProto.HUNGARIAN -> DataLanguageType.HUNGARIAN
            SettingsDataProto.DataLanguageProto.INDONESIAN -> DataLanguageType.INDONESIAN
            SettingsDataProto.DataLanguageProto.ITALIAN -> DataLanguageType.ITALIAN
            SettingsDataProto.DataLanguageProto.JAPANESE -> DataLanguageType.JAPANESE
            SettingsDataProto.DataLanguageProto.KOREAN -> DataLanguageType.KOREAN
            SettingsDataProto.DataLanguageProto.LATVIAN -> DataLanguageType.LATVIAN
            SettingsDataProto.DataLanguageProto.LITHUANIAN -> DataLanguageType.LITHUANIAN
            SettingsDataProto.DataLanguageProto.MACEDONIAN -> DataLanguageType.MACEDONIAN
            SettingsDataProto.DataLanguageProto.NORWEGIAN -> DataLanguageType.NORWEGIAN
            SettingsDataProto.DataLanguageProto.DUTCH -> DataLanguageType.DUTCH
            SettingsDataProto.DataLanguageProto.POLISH -> DataLanguageType.POLISH
            SettingsDataProto.DataLanguageProto.PORTUGUESE -> DataLanguageType.PORTUGUESE
            SettingsDataProto.DataLanguageProto.PORTUGUES_BRASIL -> DataLanguageType.PORTUGUES_BRASIL
            SettingsDataProto.DataLanguageProto.ROMANIAN -> DataLanguageType.ROMANIAN
            SettingsDataProto.DataLanguageProto.RUSSIAN -> DataLanguageType.RUSSIAN
            SettingsDataProto.DataLanguageProto.SWEDISH -> DataLanguageType.SWEDISH
            SettingsDataProto.DataLanguageProto.SLOVAK -> DataLanguageType.SLOVAK
            SettingsDataProto.DataLanguageProto.SLOVENIAN -> DataLanguageType.SLOVENIAN
            SettingsDataProto.DataLanguageProto.SPANISH -> DataLanguageType.SPANISH
            SettingsDataProto.DataLanguageProto.SERBIAN -> DataLanguageType.SERBIAN
            SettingsDataProto.DataLanguageProto.THAI -> DataLanguageType.THAI
            SettingsDataProto.DataLanguageProto.TURKISH -> DataLanguageType.TURKISH
            SettingsDataProto.DataLanguageProto.UKRAINIAN -> DataLanguageType.UKRAINIAN
            SettingsDataProto.DataLanguageProto.VIETNAMESE -> DataLanguageType.VIETNAMESE
            SettingsDataProto.DataLanguageProto.CHINESE_SIMPLIFIED -> DataLanguageType.CHINESE_SIMPLIFIED
            SettingsDataProto.DataLanguageProto.CHINESE_TRADITIONAL -> DataLanguageType.CHINESE_TRADITIONAL
            SettingsDataProto.DataLanguageProto.ZULU -> DataLanguageType.ZULU
            SettingsDataProto.DataLanguageProto.UNRECOGNIZED -> DataLanguageType.ENGLISH
        }
    }

    override fun diskToProto(disk: DataLanguageType): SettingsDataProto.DataLanguageProto {
        return when (disk) {
            DataLanguageType.AFRIKAANS -> SettingsDataProto.DataLanguageProto.AFRIKAANS
            DataLanguageType.ALBANIAN -> SettingsDataProto.DataLanguageProto.ALBANIAN
            DataLanguageType.ARABIC -> SettingsDataProto.DataLanguageProto.ARABIC
            DataLanguageType.AZERBAIJANI -> SettingsDataProto.DataLanguageProto.AZERBAIJANI
            DataLanguageType.BULGARIAN -> SettingsDataProto.DataLanguageProto.BULGARIAN
            DataLanguageType.CATALAN -> SettingsDataProto.DataLanguageProto.CATALAN
            DataLanguageType.CZECH -> SettingsDataProto.DataLanguageProto.CZECH
            DataLanguageType.DANISH -> SettingsDataProto.DataLanguageProto.DANISH
            DataLanguageType.GERMAN -> SettingsDataProto.DataLanguageProto.GERMAN
            DataLanguageType.GREEK -> SettingsDataProto.DataLanguageProto.GREEK
            DataLanguageType.ENGLISH -> SettingsDataProto.DataLanguageProto.ENGLISH
            DataLanguageType.BASQUE -> SettingsDataProto.DataLanguageProto.BASQUE
            DataLanguageType.PERSIAN_FARSI -> SettingsDataProto.DataLanguageProto.PERSIAN_FARSI
            DataLanguageType.FINNISH -> SettingsDataProto.DataLanguageProto.FINNISH
            DataLanguageType.FRENCH -> SettingsDataProto.DataLanguageProto.FRENCH
            DataLanguageType.GALICIAN -> SettingsDataProto.DataLanguageProto.GALICIAN
            DataLanguageType.HEBREW -> SettingsDataProto.DataLanguageProto.HEBREW
            DataLanguageType.HINDI -> SettingsDataProto.DataLanguageProto.HINDI
            DataLanguageType.CROATIAN -> SettingsDataProto.DataLanguageProto.CROATIAN
            DataLanguageType.HUNGARIAN -> SettingsDataProto.DataLanguageProto.HUNGARIAN
            DataLanguageType.INDONESIAN -> SettingsDataProto.DataLanguageProto.INDONESIAN
            DataLanguageType.ITALIAN -> SettingsDataProto.DataLanguageProto.ITALIAN
            DataLanguageType.JAPANESE -> SettingsDataProto.DataLanguageProto.JAPANESE
            DataLanguageType.KOREAN -> SettingsDataProto.DataLanguageProto.KOREAN
            DataLanguageType.LATVIAN -> SettingsDataProto.DataLanguageProto.LATVIAN
            DataLanguageType.LITHUANIAN -> SettingsDataProto.DataLanguageProto.LITHUANIAN
            DataLanguageType.MACEDONIAN -> SettingsDataProto.DataLanguageProto.MACEDONIAN
            DataLanguageType.NORWEGIAN -> SettingsDataProto.DataLanguageProto.NORWEGIAN
            DataLanguageType.DUTCH -> SettingsDataProto.DataLanguageProto.DUTCH
            DataLanguageType.POLISH -> SettingsDataProto.DataLanguageProto.POLISH
            DataLanguageType.PORTUGUESE -> SettingsDataProto.DataLanguageProto.PORTUGUESE
            DataLanguageType.PORTUGUES_BRASIL -> SettingsDataProto.DataLanguageProto.PORTUGUES_BRASIL
            DataLanguageType.ROMANIAN -> SettingsDataProto.DataLanguageProto.ROMANIAN
            DataLanguageType.RUSSIAN -> SettingsDataProto.DataLanguageProto.RUSSIAN
            DataLanguageType.SWEDISH -> SettingsDataProto.DataLanguageProto.SWEDISH
            DataLanguageType.SLOVAK -> SettingsDataProto.DataLanguageProto.SLOVAK
            DataLanguageType.SLOVENIAN -> SettingsDataProto.DataLanguageProto.SLOVENIAN
            DataLanguageType.SPANISH -> SettingsDataProto.DataLanguageProto.SPANISH
            DataLanguageType.SERBIAN -> SettingsDataProto.DataLanguageProto.SERBIAN
            DataLanguageType.THAI -> SettingsDataProto.DataLanguageProto.THAI
            DataLanguageType.TURKISH -> SettingsDataProto.DataLanguageProto.TURKISH
            DataLanguageType.UKRAINIAN -> SettingsDataProto.DataLanguageProto.UKRAINIAN
            DataLanguageType.VIETNAMESE -> SettingsDataProto.DataLanguageProto.VIETNAMESE
            DataLanguageType.CHINESE_SIMPLIFIED -> SettingsDataProto.DataLanguageProto.CHINESE_SIMPLIFIED
            DataLanguageType.CHINESE_TRADITIONAL -> SettingsDataProto.DataLanguageProto.CHINESE_TRADITIONAL
            DataLanguageType.ZULU -> SettingsDataProto.DataLanguageProto.ZULU
        }
    }
}