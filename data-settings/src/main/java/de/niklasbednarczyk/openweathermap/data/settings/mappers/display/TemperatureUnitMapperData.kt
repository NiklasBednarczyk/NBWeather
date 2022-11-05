package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object TemperatureUnitMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.TemperatureUnitProto, TemperatureUnitTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.TemperatureUnitProto): TemperatureUnitTypeData {
        return when (proto) {
            SettingsDisplayProto.TemperatureUnitProto.CELSIUS -> TemperatureUnitTypeData.CELSIUS
            SettingsDisplayProto.TemperatureUnitProto.FAHRENHEIT -> TemperatureUnitTypeData.FAHRENHEIT
            SettingsDisplayProto.TemperatureUnitProto.KELVIN -> TemperatureUnitTypeData.KELVIN
            SettingsDisplayProto.TemperatureUnitProto.UNRECOGNIZED -> TemperatureUnitTypeData.CELSIUS
        }
    }

    override fun diskToProto(disk: TemperatureUnitTypeData): SettingsDisplayProto.TemperatureUnitProto {
        return when (disk) {
            TemperatureUnitTypeData.CELSIUS -> SettingsDisplayProto.TemperatureUnitProto.CELSIUS
            TemperatureUnitTypeData.FAHRENHEIT -> SettingsDisplayProto.TemperatureUnitProto.FAHRENHEIT
            TemperatureUnitTypeData.KELVIN -> SettingsDisplayProto.TemperatureUnitProto.KELVIN
        }
    }

}