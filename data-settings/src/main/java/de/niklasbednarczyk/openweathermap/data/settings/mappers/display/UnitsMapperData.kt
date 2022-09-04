package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.UnitsTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object UnitsMapperData : TwoWayMapperDisk<SettingsDisplayProto.UnitsProto, UnitsTypeData> {
    override fun protoToDisk(proto: SettingsDisplayProto.UnitsProto): UnitsTypeData {
        return when (proto) {
            SettingsDisplayProto.UnitsProto.STANDARD -> UnitsTypeData.STANDARD
            SettingsDisplayProto.UnitsProto.METRIC -> UnitsTypeData.METRIC
            SettingsDisplayProto.UnitsProto.IMPERIAL -> UnitsTypeData.IMPERIAL
            SettingsDisplayProto.UnitsProto.UNRECOGNIZED -> UnitsTypeData.METRIC
        }
    }

    override fun diskToProto(disk: UnitsTypeData): SettingsDisplayProto.UnitsProto {
        return when (disk) {
            UnitsTypeData.STANDARD -> SettingsDisplayProto.UnitsProto.STANDARD
            UnitsTypeData.METRIC -> SettingsDisplayProto.UnitsProto.METRIC
            UnitsTypeData.IMPERIAL -> SettingsDisplayProto.UnitsProto.IMPERIAL
        }
    }

}