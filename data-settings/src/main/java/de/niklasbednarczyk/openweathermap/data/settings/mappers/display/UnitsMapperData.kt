package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object UnitsMapperData :
    TwoWayMapperDisk<SettingsDisplayProto.UnitsProto, OwmUnitsType> {
    override fun protoToDisk(proto: SettingsDisplayProto.UnitsProto): OwmUnitsType {
        return when (proto) {
            SettingsDisplayProto.UnitsProto.STANDARD -> OwmUnitsType.STANDARD
            SettingsDisplayProto.UnitsProto.METRIC -> OwmUnitsType.METRIC
            SettingsDisplayProto.UnitsProto.IMPERIAL -> OwmUnitsType.IMPERIAL
            SettingsDisplayProto.UnitsProto.UNRECOGNIZED -> OwmUnitsType.METRIC
        }
    }

    override fun diskToProto(disk: OwmUnitsType): SettingsDisplayProto.UnitsProto {
        return when (disk) {
            OwmUnitsType.STANDARD -> SettingsDisplayProto.UnitsProto.STANDARD
            OwmUnitsType.METRIC -> SettingsDisplayProto.UnitsProto.METRIC
            OwmUnitsType.IMPERIAL -> SettingsDisplayProto.UnitsProto.IMPERIAL
        }
    }

}