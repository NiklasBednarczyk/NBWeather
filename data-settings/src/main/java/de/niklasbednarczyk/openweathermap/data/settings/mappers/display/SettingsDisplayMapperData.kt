package de.niklasbednarczyk.openweathermap.data.settings.mappers.display

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto

internal object SettingsDisplayMapperData :
    OneWayMapperDisk<SettingsDisplayProto, SettingsDisplayModelData> {
    override fun protoToDisk(proto: SettingsDisplayProto): SettingsDisplayModelData {
        return SettingsDisplayModelData(
            temperatureUnit = TemperatureUnitMapperData.protoToDisk(proto.temperatureUnit),
            windSpeedUnit = WindSpeedUnitMapperData.protoToDisk(proto.windSpeedUnit),
            pressureUnit = PressureUnitMapperData.protoToDisk(proto.pressureUnit),
            distanceUnit = DistanceUnitMapperData.protoToDisk(proto.distanceUnit),
            precipitationUnit = PrecipitationUnitMapperData.protoToDisk(proto.precipitationUnit),
            timeFormat = TimeFormatMapperData.protoToDisk(proto.timeFormat)
        )
    }
}