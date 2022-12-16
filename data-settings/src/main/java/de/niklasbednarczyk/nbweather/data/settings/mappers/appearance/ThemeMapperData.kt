package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object ThemeMapperData :
    TwoWayMapperDisk<SettingsAppearanceProto.ThemeProto, ThemeTypeData> {
    override fun protoToDisk(proto: SettingsAppearanceProto.ThemeProto): ThemeTypeData {
        return when (proto) {
            SettingsAppearanceProto.ThemeProto.LIGHT -> ThemeTypeData.LIGHT
            SettingsAppearanceProto.ThemeProto.DARK -> ThemeTypeData.DARK
            SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT -> ThemeTypeData.SYSTEM_DEFAULT
            SettingsAppearanceProto.ThemeProto.UNRECOGNIZED -> ThemeTypeData.SYSTEM_DEFAULT
        }
    }

    override fun diskToProto(disk: ThemeTypeData): SettingsAppearanceProto.ThemeProto {
        return when (disk) {
            ThemeTypeData.LIGHT -> SettingsAppearanceProto.ThemeProto.LIGHT
            ThemeTypeData.DARK -> SettingsAppearanceProto.ThemeProto.DARK
            ThemeTypeData.SYSTEM_DEFAULT -> SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT
        }
    }

}