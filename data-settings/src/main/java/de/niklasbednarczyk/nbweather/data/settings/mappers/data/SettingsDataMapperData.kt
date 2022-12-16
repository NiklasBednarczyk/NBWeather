package de.niklasbednarczyk.nbweather.data.settings.mappers.data

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto

internal object SettingsDataMapperData :
    OneWayMapperDisk<SettingsDataProto, SettingsDataModelData> {
    override fun protoToDisk(proto: SettingsDataProto): SettingsDataModelData {
        return SettingsDataModelData(
            language = LanguageMapperData.protoToDisk(proto.language),
            units = UnitsMapperData.protoToDisk(proto.units),
            timeFormat = TimeFormatMapperData.protoToDisk(proto.timeFormat)
        )
    }
}