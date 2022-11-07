package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object SettingsDataMapperData :
    OneWayMapperDisk<SettingsDataProto, SettingsDataModelData> {
    override fun protoToDisk(proto: SettingsDataProto): SettingsDataModelData {
        return SettingsDataModelData(
            language = LanguageMapperData.protoToDisk(proto.language),
            units = UnitsMapperData.protoToDisk(proto.units)
        )
    }
}