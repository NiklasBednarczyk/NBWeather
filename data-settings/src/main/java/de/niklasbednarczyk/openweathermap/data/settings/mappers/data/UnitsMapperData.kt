package de.niklasbednarczyk.openweathermap.data.settings.mappers.data

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto

internal object UnitsMapperData :
    TwoWayMapperDisk<SettingsDataProto.UnitsProto, OwmUnitsType> {
    override fun protoToDisk(proto: SettingsDataProto.UnitsProto): OwmUnitsType {
        return when (proto) {
            SettingsDataProto.UnitsProto.STANDARD -> OwmUnitsType.STANDARD
            SettingsDataProto.UnitsProto.METRIC -> OwmUnitsType.METRIC
            SettingsDataProto.UnitsProto.IMPERIAL -> OwmUnitsType.IMPERIAL
            SettingsDataProto.UnitsProto.UNRECOGNIZED -> OwmUnitsType.METRIC
        }
    }

    override fun diskToProto(disk: OwmUnitsType): SettingsDataProto.UnitsProto {
        return when (disk) {
            OwmUnitsType.STANDARD -> SettingsDataProto.UnitsProto.STANDARD
            OwmUnitsType.METRIC -> SettingsDataProto.UnitsProto.METRIC
            OwmUnitsType.IMPERIAL -> SettingsDataProto.UnitsProto.IMPERIAL
        }
    }

}