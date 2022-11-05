package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.PressureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object PressureUnitMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.PressureUnitProto, PressureUnitTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.PressureUnitProto): PressureUnitTypeData {
        return when (proto) {
            SettingsDisplayProto.PressureUnitProto.HECTOPASCAL -> PressureUnitTypeData.HECTOPASCAL
            SettingsDisplayProto.PressureUnitProto.INCH_OF_MERCURY -> PressureUnitTypeData.INCH_OF_MERCURY
            SettingsDisplayProto.PressureUnitProto.UNRECOGNIZED -> PressureUnitTypeData.HECTOPASCAL
        }
    }

    override fun diskToProto(disk: PressureUnitTypeData): SettingsDisplayProto.PressureUnitProto {
        return when (disk) {
            PressureUnitTypeData.HECTOPASCAL -> SettingsDisplayProto.PressureUnitProto.HECTOPASCAL
            PressureUnitTypeData.INCH_OF_MERCURY -> SettingsDisplayProto.PressureUnitProto.INCH_OF_MERCURY
        }
    }

}