package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object NBColorSchemeMapperData :
    TwoWayMapperDisk<SettingsAppearanceProto.ColorSchemeProto, NBColorSchemeType> {
    override fun protoToData(proto: SettingsAppearanceProto.ColorSchemeProto): NBColorSchemeType {
        return when (proto) {
            SettingsAppearanceProto.ColorSchemeProto.BLUE -> NBColorSchemeType.BLUE
            SettingsAppearanceProto.ColorSchemeProto.GREEN -> NBColorSchemeType.GREEN
            SettingsAppearanceProto.ColorSchemeProto.RED -> NBColorSchemeType.RED
            SettingsAppearanceProto.ColorSchemeProto.YELLOW -> NBColorSchemeType.YELLOW
            SettingsAppearanceProto.ColorSchemeProto.UNRECOGNIZED -> NBColorSchemeType.YELLOW
        }
    }

    override fun dataToProto(data: NBColorSchemeType): SettingsAppearanceProto.ColorSchemeProto {
        return when (data) {
            NBColorSchemeType.BLUE -> SettingsAppearanceProto.ColorSchemeProto.BLUE
            NBColorSchemeType.GREEN -> SettingsAppearanceProto.ColorSchemeProto.GREEN
            NBColorSchemeType.RED -> SettingsAppearanceProto.ColorSchemeProto.RED
            NBColorSchemeType.YELLOW -> SettingsAppearanceProto.ColorSchemeProto.YELLOW
        }
    }

}