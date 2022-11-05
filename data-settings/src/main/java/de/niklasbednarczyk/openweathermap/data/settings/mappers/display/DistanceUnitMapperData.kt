package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.DistanceUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object DistanceUnitMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.DistanceUnitProto, DistanceUnitTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.DistanceUnitProto): DistanceUnitTypeData {
        return when (proto) {
            SettingsDisplayProto.DistanceUnitProto.KILOMETRE -> DistanceUnitTypeData.KILOMETRE
            SettingsDisplayProto.DistanceUnitProto.MILE -> DistanceUnitTypeData.MILE
            SettingsDisplayProto.DistanceUnitProto.UNRECOGNIZED -> DistanceUnitTypeData.KILOMETRE
        }
    }

    override fun diskToProto(disk: DistanceUnitTypeData): SettingsDisplayProto.DistanceUnitProto {
        return when (disk) {
            DistanceUnitTypeData.KILOMETRE -> SettingsDisplayProto.DistanceUnitProto.KILOMETRE
            DistanceUnitTypeData.MILE -> SettingsDisplayProto.DistanceUnitProto.MILE
        }
    }

}