package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBDistanceUnitMapperData :
    TwoWayMapperDisk<SettingsUnitsProto.DistanceUnitProto, NBDistanceUnitType> {
    override fun protoToData(proto: SettingsUnitsProto.DistanceUnitProto): NBDistanceUnitType {
        return when (proto) {
            SettingsUnitsProto.DistanceUnitProto.KILOMETER -> NBDistanceUnitType.KILOMETER
            SettingsUnitsProto.DistanceUnitProto.MILE -> NBDistanceUnitType.MILE
            SettingsUnitsProto.DistanceUnitProto.UNRECOGNIZED -> NBDistanceUnitType.KILOMETER
        }
    }

    override fun dataToProto(data: NBDistanceUnitType): SettingsUnitsProto.DistanceUnitProto {
        return when (data) {
            NBDistanceUnitType.KILOMETER -> SettingsUnitsProto.DistanceUnitProto.KILOMETER
            NBDistanceUnitType.MILE -> SettingsUnitsProto.DistanceUnitProto.MILE
        }
    }

}