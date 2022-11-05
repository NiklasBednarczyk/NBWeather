package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.TimeFormatTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object TimeFormatMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.TimeFormatProto, TimeFormatTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.TimeFormatProto): TimeFormatTypeData {
        return when (proto) {
            SettingsDisplayProto.TimeFormatProto.HOUR_12 -> TimeFormatTypeData.HOUR_12
            SettingsDisplayProto.TimeFormatProto.HOUR_24 -> TimeFormatTypeData.HOUR_24
            SettingsDisplayProto.TimeFormatProto.SYSTEM_DEFAULT -> TimeFormatTypeData.SYSTEM_DEFAULT
            SettingsDisplayProto.TimeFormatProto.UNRECOGNIZED -> TimeFormatTypeData.SYSTEM_DEFAULT
        }
    }

    override fun diskToProto(disk: TimeFormatTypeData): SettingsDisplayProto.TimeFormatProto {
        return when (disk) {
            TimeFormatTypeData.HOUR_12 -> SettingsDisplayProto.TimeFormatProto.HOUR_12
            TimeFormatTypeData.HOUR_24 -> SettingsDisplayProto.TimeFormatProto.HOUR_24
            TimeFormatTypeData.SYSTEM_DEFAULT -> SettingsDisplayProto.TimeFormatProto.SYSTEM_DEFAULT
        }
    }

}