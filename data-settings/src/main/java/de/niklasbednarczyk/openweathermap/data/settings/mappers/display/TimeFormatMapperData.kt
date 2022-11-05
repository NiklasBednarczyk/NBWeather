package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object TimeFormatMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.TimeFormatProto, OwmTimeFormatType> {
    override fun protoToDisk(proto: SettingsDisplayProto.TimeFormatProto): OwmTimeFormatType {
        return when (proto) {
            SettingsDisplayProto.TimeFormatProto.HOUR_12 -> OwmTimeFormatType.HOUR_12
            SettingsDisplayProto.TimeFormatProto.HOUR_24 -> OwmTimeFormatType.HOUR_24
            SettingsDisplayProto.TimeFormatProto.SYSTEM_DEFAULT -> OwmTimeFormatType.SYSTEM_DEFAULT
            SettingsDisplayProto.TimeFormatProto.UNRECOGNIZED -> OwmTimeFormatType.SYSTEM_DEFAULT
        }
    }

    override fun diskToProto(disk: OwmTimeFormatType): SettingsDisplayProto.TimeFormatProto {
        return when (disk) {
            OwmTimeFormatType.HOUR_12 -> SettingsDisplayProto.TimeFormatProto.HOUR_12
            OwmTimeFormatType.HOUR_24 -> SettingsDisplayProto.TimeFormatProto.HOUR_24
            OwmTimeFormatType.SYSTEM_DEFAULT -> SettingsDisplayProto.TimeFormatProto.SYSTEM_DEFAULT
        }
    }

}