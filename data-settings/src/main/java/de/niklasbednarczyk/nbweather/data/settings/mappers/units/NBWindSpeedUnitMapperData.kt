package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBWindSpeedUnitMapperData :
    TwoWayMapperDisk<SettingsUnitsProto.WindSpeedUnitProto, NBWindSpeedUnitType> {
    override fun protoToData(proto: SettingsUnitsProto.WindSpeedUnitProto): NBWindSpeedUnitType {
        return when (proto) {
            SettingsUnitsProto.WindSpeedUnitProto.KILOMETER_PER_HOUR -> NBWindSpeedUnitType.KILOMETER_PER_HOUR
            SettingsUnitsProto.WindSpeedUnitProto.METER_PER_SECOND -> NBWindSpeedUnitType.METER_PER_SECOND
            SettingsUnitsProto.WindSpeedUnitProto.MILE_PER_HOUR -> NBWindSpeedUnitType.MILE_PER_HOUR
            SettingsUnitsProto.WindSpeedUnitProto.UNRECOGNIZED -> NBWindSpeedUnitType.METER_PER_SECOND
        }
    }

    override fun dataToProto(data: NBWindSpeedUnitType): SettingsUnitsProto.WindSpeedUnitProto {
        return when (data) {
            NBWindSpeedUnitType.KILOMETER_PER_HOUR -> SettingsUnitsProto.WindSpeedUnitProto.KILOMETER_PER_HOUR
            NBWindSpeedUnitType.METER_PER_SECOND -> SettingsUnitsProto.WindSpeedUnitProto.METER_PER_SECOND
            NBWindSpeedUnitType.MILE_PER_HOUR -> SettingsUnitsProto.WindSpeedUnitProto.MILE_PER_HOUR
        }
    }

}