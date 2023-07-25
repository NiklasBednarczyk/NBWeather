package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBPrecipitationUnitMapperData :
    TwoWayMapperDisk<SettingsUnitsProto.PrecipitationUnitProto, NBPrecipitationUnitType> {
    override fun protoToData(proto: SettingsUnitsProto.PrecipitationUnitProto): NBPrecipitationUnitType {
        return when (proto) {
            SettingsUnitsProto.PrecipitationUnitProto.INCH -> NBPrecipitationUnitType.INCH
            SettingsUnitsProto.PrecipitationUnitProto.MILLIMETER -> NBPrecipitationUnitType.MILLIMETER
            SettingsUnitsProto.PrecipitationUnitProto.UNRECOGNIZED -> NBPrecipitationUnitType.MILLIMETER
        }
    }

    override fun dataToProto(data: NBPrecipitationUnitType): SettingsUnitsProto.PrecipitationUnitProto {
        return when (data) {
            NBPrecipitationUnitType.INCH -> SettingsUnitsProto.PrecipitationUnitProto.INCH
            NBPrecipitationUnitType.MILLIMETER -> SettingsUnitsProto.PrecipitationUnitProto.MILLIMETER
        }
    }

}