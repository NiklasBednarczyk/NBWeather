package de.niklasbednarczyk.nbweather.data.settings.mappers.data

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto

internal object TimeFormatMapperData :
    TwoWayMapperDisk<SettingsDataProto.TimeFormatProto, NBTimeFormatType> {
    override fun protoToData(proto: SettingsDataProto.TimeFormatProto): NBTimeFormatType {
        return when (proto) {
            SettingsDataProto.TimeFormatProto.HOUR_12 -> NBTimeFormatType.HOUR_12
            SettingsDataProto.TimeFormatProto.HOUR_24 -> NBTimeFormatType.HOUR_24
            SettingsDataProto.TimeFormatProto.UNRECOGNIZED -> NBTimeFormatType.HOUR_24
        }
    }

    override fun dataToProto(data: NBTimeFormatType): SettingsDataProto.TimeFormatProto {
        return when (data) {
            NBTimeFormatType.HOUR_12 -> SettingsDataProto.TimeFormatProto.HOUR_12
            NBTimeFormatType.HOUR_24 -> SettingsDataProto.TimeFormatProto.HOUR_24
        }
    }

}