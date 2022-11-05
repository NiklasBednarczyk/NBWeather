package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.WindSpeedUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object WindSpeedUnitMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.WindSpeedUnitProto, WindSpeedUnitTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.WindSpeedUnitProto): WindSpeedUnitTypeData {
        return when (proto) {
            SettingsDisplayProto.WindSpeedUnitProto.KILOMETRE_PER_HOUR -> WindSpeedUnitTypeData.KILOMETRE_PER_HOUR
            SettingsDisplayProto.WindSpeedUnitProto.MILE_PER_HOUR -> WindSpeedUnitTypeData.MILE_PER_HOUR
            SettingsDisplayProto.WindSpeedUnitProto.METRE_PER_SECOND -> WindSpeedUnitTypeData.METRE_PER_SECOND
            SettingsDisplayProto.WindSpeedUnitProto.UNRECOGNIZED -> WindSpeedUnitTypeData.KILOMETRE_PER_HOUR
        }
    }

    override fun diskToProto(disk: WindSpeedUnitTypeData): SettingsDisplayProto.WindSpeedUnitProto {
        return when (disk) {
            WindSpeedUnitTypeData.KILOMETRE_PER_HOUR -> SettingsDisplayProto.WindSpeedUnitProto.KILOMETRE_PER_HOUR
            WindSpeedUnitTypeData.MILE_PER_HOUR -> SettingsDisplayProto.WindSpeedUnitProto.MILE_PER_HOUR
            WindSpeedUnitTypeData.METRE_PER_SECOND -> SettingsDisplayProto.WindSpeedUnitProto.METRE_PER_SECOND
        }
    }

}