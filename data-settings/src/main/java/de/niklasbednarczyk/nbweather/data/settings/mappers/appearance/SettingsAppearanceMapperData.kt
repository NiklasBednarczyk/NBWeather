package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object SettingsAppearanceMapperData :
    OneWayMapperDisk<SettingsAppearanceProto, SettingsAppearanceModelData> {
    override fun protoToData(proto: SettingsAppearanceProto): SettingsAppearanceModelData {
        return SettingsAppearanceModelData(
            theme = ThemeMapperData.protoToData(proto.theme),
            colorScheme = ColorSchemeMapperData.protoToData(proto.colorScheme)
        )
    }
}