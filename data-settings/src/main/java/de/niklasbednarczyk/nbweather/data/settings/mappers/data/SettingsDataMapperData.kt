package de.niklasbednarczyk.nbweather.data.settings.mappers.data

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto

internal object SettingsDataMapperData :
    OneWayMapperDisk<SettingsDataProto, SettingsDataModelData> {
    override fun protoToData(proto: SettingsDataProto): SettingsDataModelData {
        return SettingsDataModelData(
            language = LanguageMapperData.protoToData(proto.language),
            units = UnitsMapperData.protoToData(proto.units),
            timeFormat = TimeFormatMapperData.protoToData(proto.timeFormat)
        )
    }
}