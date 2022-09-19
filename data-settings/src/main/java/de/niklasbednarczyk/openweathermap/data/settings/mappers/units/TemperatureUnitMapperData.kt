package de.niklasbednarczyk.openweathermap.data.settings.mappers.units

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.units.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.units.SettingsUnitsProto

internal object TemperatureUnitMapperData : TwoWayMapperDisk<SettingsUnitsProto.TemperatureUnitProto, TemperatureUnitTypeData> {
    override fun protoToDisk(proto: SettingsUnitsProto.TemperatureUnitProto): TemperatureUnitTypeData {
        return when (proto) {
            SettingsUnitsProto.TemperatureUnitProto.CELSIUS -> TemperatureUnitTypeData.CELSIUS
            SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT -> TemperatureUnitTypeData.FAHRENHEIT
            SettingsUnitsProto.TemperatureUnitProto.UNRECOGNIZED -> TemperatureUnitTypeData.CELSIUS
        }
    }

    override fun diskToProto(disk: TemperatureUnitTypeData): SettingsUnitsProto.TemperatureUnitProto {
        return when (disk) {
            TemperatureUnitTypeData.CELSIUS -> SettingsUnitsProto.TemperatureUnitProto.CELSIUS
            TemperatureUnitTypeData.FAHRENHEIT -> SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT
        }
    }

}