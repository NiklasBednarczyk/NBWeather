package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object NBAppearanceMapperData :
    OneWayMapperDisk<SettingsAppearanceProto, NBAppearanceModel> {
    override fun protoToData(proto: SettingsAppearanceProto): NBAppearanceModel {
        return NBAppearanceModel(
            useDeviceTheme = proto.useDeviceTheme,
            theme = NBThemeMapperData.protoToData(proto.theme),
            useDynamicColorScheme = proto.useDynamicColorScheme,
            colorScheme = NBColorSchemeMapperData.protoToData(proto.colorScheme)
        )
    }
}