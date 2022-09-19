package de.niklasbednarczyk.openweathermap.data.settings.mappers.units

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.units.SettingsUnitsProto

internal object SettingsUnitsMapperData :
    OneWayMapperDisk<SettingsUnitsProto, SettingsUnitsModelData> {
    override fun protoToDisk(proto: SettingsUnitsProto): SettingsUnitsModelData {
        return SettingsUnitsModelData(
            temperatureUnit = TemperatureUnitMapperData.protoToDisk(proto.temperatureUnit)
        )
    }
}