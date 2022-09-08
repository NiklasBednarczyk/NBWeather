package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.common.display.UnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object UnitsMapperData : TwoWayMapperDisk<SettingsDisplayProto.UnitsProto, UnitsType> {
    override fun protoToDisk(proto: SettingsDisplayProto.UnitsProto): UnitsType {
        return when (proto) {
            SettingsDisplayProto.UnitsProto.STANDARD -> UnitsType.STANDARD
            SettingsDisplayProto.UnitsProto.METRIC -> UnitsType.METRIC
            SettingsDisplayProto.UnitsProto.IMPERIAL -> UnitsType.IMPERIAL
            SettingsDisplayProto.UnitsProto.UNRECOGNIZED -> UnitsType.METRIC
        }
    }

    override fun diskToProto(disk: UnitsType): SettingsDisplayProto.UnitsProto {
        return when (disk) {
            UnitsType.STANDARD -> SettingsDisplayProto.UnitsProto.STANDARD
            UnitsType.METRIC -> SettingsDisplayProto.UnitsProto.METRIC
            UnitsType.IMPERIAL -> SettingsDisplayProto.UnitsProto.IMPERIAL
        }
    }

}