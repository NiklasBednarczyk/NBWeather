package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object SettingsDisplayMapperData :
    OneWayMapperDisk<SettingsDisplayProto, SettingsDisplayModelData> {
    override fun protoToDisk(proto: SettingsDisplayProto): SettingsDisplayModelData {
        return SettingsDisplayModelData(
            units = UnitsMapperData.protoToDisk(proto.units),
            dataLanguage = DataLanguageMapperData.protoToDisk(proto.dataLanguage)
        )
    }
}