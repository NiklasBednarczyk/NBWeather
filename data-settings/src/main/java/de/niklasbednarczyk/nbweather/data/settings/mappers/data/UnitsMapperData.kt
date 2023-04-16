package de.niklasbednarczyk.nbweather.data.settings.mappers.data

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto

internal object UnitsMapperData :
    TwoWayMapperDisk<SettingsDataProto.UnitsProto, NBUnitsType> {
    override fun protoToData(proto: SettingsDataProto.UnitsProto): NBUnitsType {
        return when (proto) {
            SettingsDataProto.UnitsProto.STANDARD -> NBUnitsType.STANDARD
            SettingsDataProto.UnitsProto.METRIC -> NBUnitsType.METRIC
            SettingsDataProto.UnitsProto.IMPERIAL -> NBUnitsType.IMPERIAL
            SettingsDataProto.UnitsProto.UNRECOGNIZED -> NBUnitsType.METRIC
        }
    }

    override fun dataToProto(data: NBUnitsType): SettingsDataProto.UnitsProto {
        return when (data) {
            NBUnitsType.STANDARD -> SettingsDataProto.UnitsProto.STANDARD
            NBUnitsType.METRIC -> SettingsDataProto.UnitsProto.METRIC
            NBUnitsType.IMPERIAL -> SettingsDataProto.UnitsProto.IMPERIAL
        }
    }

}