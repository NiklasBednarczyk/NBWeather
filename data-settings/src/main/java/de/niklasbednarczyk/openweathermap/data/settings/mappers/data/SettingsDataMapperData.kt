package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.SettingsDataModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object SettingsDataMapperData :
    OneWayMapperDisk<SettingsDataProto, SettingsDataModelData> {
    override fun protoToDisk(proto: SettingsDataProto): SettingsDataModelData {
        return SettingsDataModelData(
            units = UnitsMapperData.protoToDisk(proto.units),
            dataLanguage = DataLanguageMapperData.protoToDisk(proto.dataLanguage)
        )
    }
}