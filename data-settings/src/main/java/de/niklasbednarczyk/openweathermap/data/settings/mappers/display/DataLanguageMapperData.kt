package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.common.display.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object DataLanguageMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.DataLanguageProto, DataLanguageType> {
    override fun protoToDisk(proto: SettingsDisplayProto.DataLanguageProto): DataLanguageType {
        return when (proto) {
            SettingsDisplayProto.DataLanguageProto.AFRIKAANS -> DataLanguageType.AFRIKAANS
            SettingsDisplayProto.DataLanguageProto.ALBANIAN -> DataLanguageType.ALBANIAN
            SettingsDisplayProto.DataLanguageProto.ARABIC -> DataLanguageType.ARABIC
            SettingsDisplayProto.DataLanguageProto.AZERBAIJANI -> DataLanguageType.AZERBAIJANI
            SettingsDisplayProto.DataLanguageProto.BULGARIAN -> DataLanguageType.BULGARIAN
            SettingsDisplayProto.DataLanguageProto.CATALAN -> DataLanguageType.CATALAN
            SettingsDisplayProto.DataLanguageProto.CZECH -> DataLanguageType.CZECH
            SettingsDisplayProto.DataLanguageProto.DANISH -> DataLanguageType.DANISH
            SettingsDisplayProto.DataLanguageProto.GERMAN -> DataLanguageType.GERMAN
            SettingsDisplayProto.DataLanguageProto.GREEK -> DataLanguageType.GREEK
            SettingsDisplayProto.DataLanguageProto.ENGLISH -> DataLanguageType.ENGLISH
            SettingsDisplayProto.DataLanguageProto.BASQUE -> DataLanguageType.BASQUE
            SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI -> DataLanguageType.PERSIAN_FARSI
            SettingsDisplayProto.DataLanguageProto.FINNISH -> DataLanguageType.FINNISH
            SettingsDisplayProto.DataLanguageProto.FRENCH -> DataLanguageType.FRENCH
            SettingsDisplayProto.DataLanguageProto.GALICIAN -> DataLanguageType.GALICIAN
            SettingsDisplayProto.DataLanguageProto.HEBREW -> DataLanguageType.HEBREW
            SettingsDisplayProto.DataLanguageProto.HINDI -> DataLanguageType.HINDI
            SettingsDisplayProto.DataLanguageProto.CROATIAN -> DataLanguageType.CROATIAN
            SettingsDisplayProto.DataLanguageProto.HUNGARIAN -> DataLanguageType.HUNGARIAN
            SettingsDisplayProto.DataLanguageProto.INDONESIAN -> DataLanguageType.INDONESIAN
            SettingsDisplayProto.DataLanguageProto.ITALIAN -> DataLanguageType.ITALIAN
            SettingsDisplayProto.DataLanguageProto.JAPANESE -> DataLanguageType.JAPANESE
            SettingsDisplayProto.DataLanguageProto.KOREAN -> DataLanguageType.KOREAN
            SettingsDisplayProto.DataLanguageProto.LATVIAN -> DataLanguageType.LATVIAN
            SettingsDisplayProto.DataLanguageProto.LITHUANIAN -> DataLanguageType.LITHUANIAN
            SettingsDisplayProto.DataLanguageProto.MACEDONIAN -> DataLanguageType.MACEDONIAN
            SettingsDisplayProto.DataLanguageProto.NORWEGIAN -> DataLanguageType.NORWEGIAN
            SettingsDisplayProto.DataLanguageProto.DUTCH -> DataLanguageType.DUTCH
            SettingsDisplayProto.DataLanguageProto.POLISH -> DataLanguageType.POLISH
            SettingsDisplayProto.DataLanguageProto.PORTUGUESE -> DataLanguageType.PORTUGUESE
            SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL -> DataLanguageType.PORTUGUES_BRASIL
            SettingsDisplayProto.DataLanguageProto.ROMANIAN -> DataLanguageType.ROMANIAN
            SettingsDisplayProto.DataLanguageProto.RUSSIAN -> DataLanguageType.RUSSIAN
            SettingsDisplayProto.DataLanguageProto.SWEDISH -> DataLanguageType.SWEDISH
            SettingsDisplayProto.DataLanguageProto.SLOVAK -> DataLanguageType.SLOVAK
            SettingsDisplayProto.DataLanguageProto.SLOVENIAN -> DataLanguageType.SLOVENIAN
            SettingsDisplayProto.DataLanguageProto.SPANISH -> DataLanguageType.SPANISH
            SettingsDisplayProto.DataLanguageProto.SERBIAN -> DataLanguageType.SERBIAN
            SettingsDisplayProto.DataLanguageProto.THAI -> DataLanguageType.THAI
            SettingsDisplayProto.DataLanguageProto.TURKISH -> DataLanguageType.TURKISH
            SettingsDisplayProto.DataLanguageProto.UKRAINIAN -> DataLanguageType.UKRAINIAN
            SettingsDisplayProto.DataLanguageProto.VIETNAMESE -> DataLanguageType.VIETNAMESE
            SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED -> DataLanguageType.CHINESE_SIMPLIFIED
            SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL -> DataLanguageType.CHINESE_TRADITIONAL
            SettingsDisplayProto.DataLanguageProto.ZULU -> DataLanguageType.ZULU
            SettingsDisplayProto.DataLanguageProto.UNRECOGNIZED -> DataLanguageType.ENGLISH
        }
    }

    override fun diskToProto(disk: DataLanguageType): SettingsDisplayProto.DataLanguageProto {
        return when (disk) {
            DataLanguageType.AFRIKAANS -> SettingsDisplayProto.DataLanguageProto.AFRIKAANS
            DataLanguageType.ALBANIAN -> SettingsDisplayProto.DataLanguageProto.ALBANIAN
            DataLanguageType.ARABIC -> SettingsDisplayProto.DataLanguageProto.ARABIC
            DataLanguageType.AZERBAIJANI -> SettingsDisplayProto.DataLanguageProto.AZERBAIJANI
            DataLanguageType.BULGARIAN -> SettingsDisplayProto.DataLanguageProto.BULGARIAN
            DataLanguageType.CATALAN -> SettingsDisplayProto.DataLanguageProto.CATALAN
            DataLanguageType.CZECH -> SettingsDisplayProto.DataLanguageProto.CZECH
            DataLanguageType.DANISH -> SettingsDisplayProto.DataLanguageProto.DANISH
            DataLanguageType.GERMAN -> SettingsDisplayProto.DataLanguageProto.GERMAN
            DataLanguageType.GREEK -> SettingsDisplayProto.DataLanguageProto.GREEK
            DataLanguageType.ENGLISH -> SettingsDisplayProto.DataLanguageProto.ENGLISH
            DataLanguageType.BASQUE -> SettingsDisplayProto.DataLanguageProto.BASQUE
            DataLanguageType.PERSIAN_FARSI -> SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI
            DataLanguageType.FINNISH -> SettingsDisplayProto.DataLanguageProto.FINNISH
            DataLanguageType.FRENCH -> SettingsDisplayProto.DataLanguageProto.FRENCH
            DataLanguageType.GALICIAN -> SettingsDisplayProto.DataLanguageProto.GALICIAN
            DataLanguageType.HEBREW -> SettingsDisplayProto.DataLanguageProto.HEBREW
            DataLanguageType.HINDI -> SettingsDisplayProto.DataLanguageProto.HINDI
            DataLanguageType.CROATIAN -> SettingsDisplayProto.DataLanguageProto.CROATIAN
            DataLanguageType.HUNGARIAN -> SettingsDisplayProto.DataLanguageProto.HUNGARIAN
            DataLanguageType.INDONESIAN -> SettingsDisplayProto.DataLanguageProto.INDONESIAN
            DataLanguageType.ITALIAN -> SettingsDisplayProto.DataLanguageProto.ITALIAN
            DataLanguageType.JAPANESE -> SettingsDisplayProto.DataLanguageProto.JAPANESE
            DataLanguageType.KOREAN -> SettingsDisplayProto.DataLanguageProto.KOREAN
            DataLanguageType.LATVIAN -> SettingsDisplayProto.DataLanguageProto.LATVIAN
            DataLanguageType.LITHUANIAN -> SettingsDisplayProto.DataLanguageProto.LITHUANIAN
            DataLanguageType.MACEDONIAN -> SettingsDisplayProto.DataLanguageProto.MACEDONIAN
            DataLanguageType.NORWEGIAN -> SettingsDisplayProto.DataLanguageProto.NORWEGIAN
            DataLanguageType.DUTCH -> SettingsDisplayProto.DataLanguageProto.DUTCH
            DataLanguageType.POLISH -> SettingsDisplayProto.DataLanguageProto.POLISH
            DataLanguageType.PORTUGUESE -> SettingsDisplayProto.DataLanguageProto.PORTUGUESE
            DataLanguageType.PORTUGUES_BRASIL -> SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL
            DataLanguageType.ROMANIAN -> SettingsDisplayProto.DataLanguageProto.ROMANIAN
            DataLanguageType.RUSSIAN -> SettingsDisplayProto.DataLanguageProto.RUSSIAN
            DataLanguageType.SWEDISH -> SettingsDisplayProto.DataLanguageProto.SWEDISH
            DataLanguageType.SLOVAK -> SettingsDisplayProto.DataLanguageProto.SLOVAK
            DataLanguageType.SLOVENIAN -> SettingsDisplayProto.DataLanguageProto.SLOVENIAN
            DataLanguageType.SPANISH -> SettingsDisplayProto.DataLanguageProto.SPANISH
            DataLanguageType.SERBIAN -> SettingsDisplayProto.DataLanguageProto.SERBIAN
            DataLanguageType.THAI -> SettingsDisplayProto.DataLanguageProto.THAI
            DataLanguageType.TURKISH -> SettingsDisplayProto.DataLanguageProto.TURKISH
            DataLanguageType.UKRAINIAN -> SettingsDisplayProto.DataLanguageProto.UKRAINIAN
            DataLanguageType.VIETNAMESE -> SettingsDisplayProto.DataLanguageProto.VIETNAMESE
            DataLanguageType.CHINESE_SIMPLIFIED -> SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED
            DataLanguageType.CHINESE_TRADITIONAL -> SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL
            DataLanguageType.ZULU -> SettingsDisplayProto.DataLanguageProto.ZULU
        }
    }
}