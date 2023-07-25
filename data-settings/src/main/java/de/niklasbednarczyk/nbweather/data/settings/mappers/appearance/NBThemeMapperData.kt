package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object NBThemeMapperData :
    TwoWayMapperDisk<SettingsAppearanceProto.ThemeProto, NBThemeType> {
    override fun protoToData(proto: SettingsAppearanceProto.ThemeProto): NBThemeType {
        return when (proto) {
            SettingsAppearanceProto.ThemeProto.LIGHT -> NBThemeType.LIGHT
            SettingsAppearanceProto.ThemeProto.DARK -> NBThemeType.DARK
            SettingsAppearanceProto.ThemeProto.UNRECOGNIZED -> NBThemeType.DARK
        }
    }

    override fun dataToProto(data: NBThemeType): SettingsAppearanceProto.ThemeProto {
        return when (data) {
            NBThemeType.LIGHT -> SettingsAppearanceProto.ThemeProto.LIGHT
            NBThemeType.DARK -> SettingsAppearanceProto.ThemeProto.DARK
        }
    }

}