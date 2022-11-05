package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.PrecipitationUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object PrecipitationUnitMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.PrecipitationUnitProto, PrecipitationUnitTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.PrecipitationUnitProto): PrecipitationUnitTypeData {
        return when (proto) {
            SettingsDisplayProto.PrecipitationUnitProto.MILLIMETRE -> PrecipitationUnitTypeData.MILLIMETRE
            SettingsDisplayProto.PrecipitationUnitProto.INCH -> PrecipitationUnitTypeData.INCH
            SettingsDisplayProto.PrecipitationUnitProto.UNRECOGNIZED -> PrecipitationUnitTypeData.MILLIMETRE
        }
    }

    override fun diskToProto(disk: PrecipitationUnitTypeData): SettingsDisplayProto.PrecipitationUnitProto {
        return when (disk) {
            PrecipitationUnitTypeData.MILLIMETRE -> SettingsDisplayProto.PrecipitationUnitProto.MILLIMETRE
            PrecipitationUnitTypeData.INCH -> SettingsDisplayProto.PrecipitationUnitProto.INCH
        }
    }

}