package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object SettingsAppearanceMapperData :
    OneWayMapperDisk<SettingsAppearanceProto, SettingsAppearanceModelData> {
    override fun protoToDisk(proto: SettingsAppearanceProto): SettingsAppearanceModelData {
        return SettingsAppearanceModelData(
            theme = ThemeMapperData.protoToDisk(proto.theme),
            colorScheme = ColorSchemeMapperData.protoToDisk(proto.colorScheme)
        )
    }
}