package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBPressureUnitMapperData :
    TwoWayMapperDisk<SettingsUnitsProto.PressureUnitProto, NBPressureUnitType> {
    override fun protoToData(proto: SettingsUnitsProto.PressureUnitProto): NBPressureUnitType {
        return when (proto) {
            SettingsUnitsProto.PressureUnitProto.HECTOPASCAL -> NBPressureUnitType.HECTOPASCAL
            SettingsUnitsProto.PressureUnitProto.INCH_OF_MERCURY -> NBPressureUnitType.INCH_OF_MERCURY
            SettingsUnitsProto.PressureUnitProto.MILLIMETER_OF_MERCURY -> NBPressureUnitType.MILLIMETER_OF_MERCURY
            SettingsUnitsProto.PressureUnitProto.UNRECOGNIZED -> NBPressureUnitType.HECTOPASCAL
        }
    }

    override fun dataToProto(data: NBPressureUnitType): SettingsUnitsProto.PressureUnitProto {
        return when (data) {
            NBPressureUnitType.HECTOPASCAL -> SettingsUnitsProto.PressureUnitProto.HECTOPASCAL
            NBPressureUnitType.INCH_OF_MERCURY -> SettingsUnitsProto.PressureUnitProto.INCH_OF_MERCURY
            NBPressureUnitType.MILLIMETER_OF_MERCURY -> SettingsUnitsProto.PressureUnitProto.MILLIMETER_OF_MERCURY
        }
    }

}