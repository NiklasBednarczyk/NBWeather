package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.common.data.UnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object UnitsMapperData : TwoWayMapperDisk<SettingsDataProto.UnitsProto, UnitsType> {
    override fun protoToDisk(proto: SettingsDataProto.UnitsProto): UnitsType {
        return when (proto) {
            SettingsDataProto.UnitsProto.STANDARD -> UnitsType.STANDARD
            SettingsDataProto.UnitsProto.METRIC -> UnitsType.METRIC
            SettingsDataProto.UnitsProto.IMPERIAL -> UnitsType.IMPERIAL
            SettingsDataProto.UnitsProto.UNRECOGNIZED -> UnitsType.METRIC
        }
    }

    override fun diskToProto(disk: UnitsType): SettingsDataProto.UnitsProto {
        return when (disk) {
            UnitsType.STANDARD -> SettingsDataProto.UnitsProto.STANDARD
            UnitsType.METRIC -> SettingsDataProto.UnitsProto.METRIC
            UnitsType.IMPERIAL -> SettingsDataProto.UnitsProto.IMPERIAL
        }
    }

}