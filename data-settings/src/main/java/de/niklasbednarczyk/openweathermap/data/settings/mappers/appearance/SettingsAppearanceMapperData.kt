package de.niklasbednarczyk.openweathermap.data.settings.mappers.appearance

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.appearance.SettingsAppearanceProto

internal object SettingsAppearanceMapperData :
    OneWayMapperDisk<SettingsAppearanceProto, SettingsAppearanceModelData> {
    override fun protoToDisk(proto: SettingsAppearanceProto): SettingsAppearanceModelData {
        return SettingsAppearanceModelData(
            theme = ThemeMapperData.protoToDisk(proto.theme),
            colorScheme = ColorSchemeMapperData.protoToDisk(proto.colorScheme)
        )
    }
}