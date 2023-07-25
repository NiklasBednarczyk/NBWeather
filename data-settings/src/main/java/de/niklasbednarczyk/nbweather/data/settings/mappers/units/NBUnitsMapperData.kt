package de.niklasbednarczyk.nbweather.data.settings.mappers.units

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto

internal object NBUnitsMapperData :
    OneWayMapperDisk<SettingsUnitsProto, NBUnitsModel> {
    override fun protoToData(proto: SettingsUnitsProto): NBUnitsModel {
        return NBUnitsModel(
            temperatureUnit = NBTemperatureUnitMapperData.protoToData(proto.temperatureUnit),
            precipitationUnit = NBPrecipitationUnitMapperData.protoToData(proto.precipitationUnit),
            distanceUnit = NBDistanceUnitMapperData.protoToData(proto.distanceUnit),
            pressureUnit = NBPressureUnitMapperData.protoToData(proto.pressureUnit),
            windSpeedUnit = NBWindSpeedUnitMapperData.protoToData(proto.windSpeedUnit),
        )
    }
}