package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBTemperatureUnitMapperData :
    TwoWayMapperDisk<SettingsUnitsProto.TemperatureUnitProto, NBTemperatureUnitType> {
    override fun protoToData(proto: SettingsUnitsProto.TemperatureUnitProto): NBTemperatureUnitType {
        return when (proto) {
            SettingsUnitsProto.TemperatureUnitProto.CELSIUS -> NBTemperatureUnitType.CELSIUS
            SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT -> NBTemperatureUnitType.FAHRENHEIT
            SettingsUnitsProto.TemperatureUnitProto.KELVIN -> NBTemperatureUnitType.KELVIN
            SettingsUnitsProto.TemperatureUnitProto.UNRECOGNIZED -> NBTemperatureUnitType.KELVIN
        }
    }

    override fun dataToProto(data: NBTemperatureUnitType): SettingsUnitsProto.TemperatureUnitProto {
        return when (data) {
            NBTemperatureUnitType.CELSIUS -> SettingsUnitsProto.TemperatureUnitProto.CELSIUS
            NBTemperatureUnitType.FAHRENHEIT -> SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT
            NBTemperatureUnitType.KELVIN -> SettingsUnitsProto.TemperatureUnitProto.KELVIN
        }
    }

}