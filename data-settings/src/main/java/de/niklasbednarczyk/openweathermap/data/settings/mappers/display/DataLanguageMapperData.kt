package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.DataLanguageTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object DataLanguageMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.DataLanguageProto, DataLanguageTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.DataLanguageProto): DataLanguageTypeData {
        return when (proto) {
            SettingsDisplayProto.DataLanguageProto.AFRIKAANS -> DataLanguageTypeData.AFRIKAANS
            SettingsDisplayProto.DataLanguageProto.ALBANIAN -> DataLanguageTypeData.ALBANIAN
            SettingsDisplayProto.DataLanguageProto.ARABIC -> DataLanguageTypeData.ARABIC
            SettingsDisplayProto.DataLanguageProto.AZERBAIJANI -> DataLanguageTypeData.AZERBAIJANI
            SettingsDisplayProto.DataLanguageProto.BULGARIAN -> DataLanguageTypeData.BULGARIAN
            SettingsDisplayProto.DataLanguageProto.CATALAN -> DataLanguageTypeData.CATALAN
            SettingsDisplayProto.DataLanguageProto.CZECH -> DataLanguageTypeData.CZECH
            SettingsDisplayProto.DataLanguageProto.DANISH -> DataLanguageTypeData.DANISH
            SettingsDisplayProto.DataLanguageProto.GERMAN -> DataLanguageTypeData.GERMAN
            SettingsDisplayProto.DataLanguageProto.GREEK -> DataLanguageTypeData.GREEK
            SettingsDisplayProto.DataLanguageProto.ENGLISH -> DataLanguageTypeData.ENGLISH
            SettingsDisplayProto.DataLanguageProto.BASQUE -> DataLanguageTypeData.BASQUE
            SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI -> DataLanguageTypeData.PERSIAN_FARSI
            SettingsDisplayProto.DataLanguageProto.FINNISH -> DataLanguageTypeData.FINNISH
            SettingsDisplayProto.DataLanguageProto.FRENCH -> DataLanguageTypeData.FRENCH
            SettingsDisplayProto.DataLanguageProto.GALICIAN -> DataLanguageTypeData.GALICIAN
            SettingsDisplayProto.DataLanguageProto.HEBREW -> DataLanguageTypeData.HEBREW
            SettingsDisplayProto.DataLanguageProto.HINDI -> DataLanguageTypeData.HINDI
            SettingsDisplayProto.DataLanguageProto.CROATIAN -> DataLanguageTypeData.CROATIAN
            SettingsDisplayProto.DataLanguageProto.HUNGARIAN -> DataLanguageTypeData.HUNGARIAN
            SettingsDisplayProto.DataLanguageProto.INDONESIAN -> DataLanguageTypeData.INDONESIAN
            SettingsDisplayProto.DataLanguageProto.ITALIAN -> DataLanguageTypeData.ITALIAN
            SettingsDisplayProto.DataLanguageProto.JAPANESE -> DataLanguageTypeData.JAPANESE
            SettingsDisplayProto.DataLanguageProto.KOREAN -> DataLanguageTypeData.KOREAN
            SettingsDisplayProto.DataLanguageProto.LATVIAN -> DataLanguageTypeData.LATVIAN
            SettingsDisplayProto.DataLanguageProto.LITHUANIAN -> DataLanguageTypeData.LITHUANIAN
            SettingsDisplayProto.DataLanguageProto.MACEDONIAN -> DataLanguageTypeData.MACEDONIAN
            SettingsDisplayProto.DataLanguageProto.NORWEGIAN -> DataLanguageTypeData.NORWEGIAN
            SettingsDisplayProto.DataLanguageProto.DUTCH -> DataLanguageTypeData.DUTCH
            SettingsDisplayProto.DataLanguageProto.POLISH -> DataLanguageTypeData.POLISH
            SettingsDisplayProto.DataLanguageProto.PORTUGUESE -> DataLanguageTypeData.PORTUGUESE
            SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL -> DataLanguageTypeData.PORTUGUES_BRASIL
            SettingsDisplayProto.DataLanguageProto.ROMANIAN -> DataLanguageTypeData.ROMANIAN
            SettingsDisplayProto.DataLanguageProto.RUSSIAN -> DataLanguageTypeData.RUSSIAN
            SettingsDisplayProto.DataLanguageProto.SWEDISH -> DataLanguageTypeData.SWEDISH
            SettingsDisplayProto.DataLanguageProto.SLOVAK -> DataLanguageTypeData.SLOVAK
            SettingsDisplayProto.DataLanguageProto.SLOVENIAN -> DataLanguageTypeData.SLOVENIAN
            SettingsDisplayProto.DataLanguageProto.SPANISH -> DataLanguageTypeData.SPANISH
            SettingsDisplayProto.DataLanguageProto.SERBIAN -> DataLanguageTypeData.SERBIAN
            SettingsDisplayProto.DataLanguageProto.THAI -> DataLanguageTypeData.THAI
            SettingsDisplayProto.DataLanguageProto.TURKISH -> DataLanguageTypeData.TURKISH
            SettingsDisplayProto.DataLanguageProto.UKRAINIAN -> DataLanguageTypeData.UKRAINIAN
            SettingsDisplayProto.DataLanguageProto.VIETNAMESE -> DataLanguageTypeData.VIETNAMESE
            SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED -> DataLanguageTypeData.CHINESE_SIMPLIFIED
            SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL -> DataLanguageTypeData.CHINESE_TRADITIONAL
            SettingsDisplayProto.DataLanguageProto.ZULU -> DataLanguageTypeData.ZULU
            SettingsDisplayProto.DataLanguageProto.UNRECOGNIZED -> DataLanguageTypeData.ENGLISH
        }
    }

    override fun diskToProto(disk: DataLanguageTypeData): SettingsDisplayProto.DataLanguageProto {
        return when (disk) {
            DataLanguageTypeData.AFRIKAANS -> SettingsDisplayProto.DataLanguageProto.AFRIKAANS
            DataLanguageTypeData.ALBANIAN -> SettingsDisplayProto.DataLanguageProto.ALBANIAN
            DataLanguageTypeData.ARABIC -> SettingsDisplayProto.DataLanguageProto.ARABIC
            DataLanguageTypeData.AZERBAIJANI -> SettingsDisplayProto.DataLanguageProto.AZERBAIJANI
            DataLanguageTypeData.BULGARIAN -> SettingsDisplayProto.DataLanguageProto.BULGARIAN
            DataLanguageTypeData.CATALAN -> SettingsDisplayProto.DataLanguageProto.CATALAN
            DataLanguageTypeData.CZECH -> SettingsDisplayProto.DataLanguageProto.CZECH
            DataLanguageTypeData.DANISH -> SettingsDisplayProto.DataLanguageProto.DANISH
            DataLanguageTypeData.GERMAN -> SettingsDisplayProto.DataLanguageProto.GERMAN
            DataLanguageTypeData.GREEK -> SettingsDisplayProto.DataLanguageProto.GREEK
            DataLanguageTypeData.ENGLISH -> SettingsDisplayProto.DataLanguageProto.ENGLISH
            DataLanguageTypeData.BASQUE -> SettingsDisplayProto.DataLanguageProto.BASQUE
            DataLanguageTypeData.PERSIAN_FARSI -> SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI
            DataLanguageTypeData.FINNISH -> SettingsDisplayProto.DataLanguageProto.FINNISH
            DataLanguageTypeData.FRENCH -> SettingsDisplayProto.DataLanguageProto.FRENCH
            DataLanguageTypeData.GALICIAN -> SettingsDisplayProto.DataLanguageProto.GALICIAN
            DataLanguageTypeData.HEBREW -> SettingsDisplayProto.DataLanguageProto.HEBREW
            DataLanguageTypeData.HINDI -> SettingsDisplayProto.DataLanguageProto.HINDI
            DataLanguageTypeData.CROATIAN -> SettingsDisplayProto.DataLanguageProto.CROATIAN
            DataLanguageTypeData.HUNGARIAN -> SettingsDisplayProto.DataLanguageProto.HUNGARIAN
            DataLanguageTypeData.INDONESIAN -> SettingsDisplayProto.DataLanguageProto.INDONESIAN
            DataLanguageTypeData.ITALIAN -> SettingsDisplayProto.DataLanguageProto.ITALIAN
            DataLanguageTypeData.JAPANESE -> SettingsDisplayProto.DataLanguageProto.JAPANESE
            DataLanguageTypeData.KOREAN -> SettingsDisplayProto.DataLanguageProto.KOREAN
            DataLanguageTypeData.LATVIAN -> SettingsDisplayProto.DataLanguageProto.LATVIAN
            DataLanguageTypeData.LITHUANIAN -> SettingsDisplayProto.DataLanguageProto.LITHUANIAN
            DataLanguageTypeData.MACEDONIAN -> SettingsDisplayProto.DataLanguageProto.MACEDONIAN
            DataLanguageTypeData.NORWEGIAN -> SettingsDisplayProto.DataLanguageProto.NORWEGIAN
            DataLanguageTypeData.DUTCH -> SettingsDisplayProto.DataLanguageProto.DUTCH
            DataLanguageTypeData.POLISH -> SettingsDisplayProto.DataLanguageProto.POLISH
            DataLanguageTypeData.PORTUGUESE -> SettingsDisplayProto.DataLanguageProto.PORTUGUESE
            DataLanguageTypeData.PORTUGUES_BRASIL -> SettingsDisplayProto.DataLanguageProto.PORTUGUES_BRASIL
            DataLanguageTypeData.ROMANIAN -> SettingsDisplayProto.DataLanguageProto.ROMANIAN
            DataLanguageTypeData.RUSSIAN -> SettingsDisplayProto.DataLanguageProto.RUSSIAN
            DataLanguageTypeData.SWEDISH -> SettingsDisplayProto.DataLanguageProto.SWEDISH
            DataLanguageTypeData.SLOVAK -> SettingsDisplayProto.DataLanguageProto.SLOVAK
            DataLanguageTypeData.SLOVENIAN -> SettingsDisplayProto.DataLanguageProto.SLOVENIAN
            DataLanguageTypeData.SPANISH -> SettingsDisplayProto.DataLanguageProto.SPANISH
            DataLanguageTypeData.SERBIAN -> SettingsDisplayProto.DataLanguageProto.SERBIAN
            DataLanguageTypeData.THAI -> SettingsDisplayProto.DataLanguageProto.THAI
            DataLanguageTypeData.TURKISH -> SettingsDisplayProto.DataLanguageProto.TURKISH
            DataLanguageTypeData.UKRAINIAN -> SettingsDisplayProto.DataLanguageProto.UKRAINIAN
            DataLanguageTypeData.VIETNAMESE -> SettingsDisplayProto.DataLanguageProto.VIETNAMESE
            DataLanguageTypeData.CHINESE_SIMPLIFIED -> SettingsDisplayProto.DataLanguageProto.CHINESE_SIMPLIFIED
            DataLanguageTypeData.CHINESE_TRADITIONAL -> SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL
            DataLanguageTypeData.ZULU -> SettingsDisplayProto.DataLanguageProto.ZULU
        }
    }
}