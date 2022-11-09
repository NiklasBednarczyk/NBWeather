package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object TimeFormatMapperData :
    TwoWayMapperDisk<SettingsDataProto.TimeFormatProto, OwmTimeFormatType> {
    override fun protoToDisk(proto: SettingsDataProto.TimeFormatProto): OwmTimeFormatType {
        return when (proto) {
            SettingsDataProto.TimeFormatProto.HOUR_12 -> OwmTimeFormatType.HOUR_12
            SettingsDataProto.TimeFormatProto.HOUR_24 -> OwmTimeFormatType.HOUR_24
            SettingsDataProto.TimeFormatProto.UNRECOGNIZED -> OwmTimeFormatType.HOUR_24
        }
    }

    override fun diskToProto(disk: OwmTimeFormatType): SettingsDataProto.TimeFormatProto {
        return when (disk) {
            OwmTimeFormatType.HOUR_12 -> SettingsDataProto.TimeFormatProto.HOUR_12
            OwmTimeFormatType.HOUR_24 -> SettingsDataProto.TimeFormatProto.HOUR_24
        }
    }

}