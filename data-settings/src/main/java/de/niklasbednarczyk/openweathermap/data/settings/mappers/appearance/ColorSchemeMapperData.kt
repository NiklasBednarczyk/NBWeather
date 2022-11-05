package de.niklasbednarczyk.openweathermap.data.settings.mappers.appearance

import de.niklasbednarczyk.openweathermap.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.appearance.SettingsAppearanceProto

internal object ColorSchemeMapperData :
    TwoWayMapperDisk<SettingsAppearanceProto.ColorSchemeProto, ColorSchemeTypeData> {
    override fun protoToDisk(proto: SettingsAppearanceProto.ColorSchemeProto): ColorSchemeTypeData {
        return when (proto) {
            SettingsAppearanceProto.ColorSchemeProto.RED -> ColorSchemeTypeData.RED
            SettingsAppearanceProto.ColorSchemeProto.GREEN -> ColorSchemeTypeData.GREEN
            SettingsAppearanceProto.ColorSchemeProto.BLUE -> ColorSchemeTypeData.BLUE
            SettingsAppearanceProto.ColorSchemeProto.CYAN -> ColorSchemeTypeData.CYAN
            SettingsAppearanceProto.ColorSchemeProto.MAGENTA -> ColorSchemeTypeData.MAGENTA
            SettingsAppearanceProto.ColorSchemeProto.YELLOW -> ColorSchemeTypeData.YELLOW
            SettingsAppearanceProto.ColorSchemeProto.DYNAMIC_COLOR -> ColorSchemeTypeData.DYNAMIC_COLOR
            SettingsAppearanceProto.ColorSchemeProto.UNRECOGNIZED -> ColorSchemeTypeData.YELLOW
        }
    }

    override fun diskToProto(disk: ColorSchemeTypeData): SettingsAppearanceProto.ColorSchemeProto {
        return when (disk) {
            ColorSchemeTypeData.RED -> SettingsAppearanceProto.ColorSchemeProto.RED
            ColorSchemeTypeData.GREEN -> SettingsAppearanceProto.ColorSchemeProto.GREEN
            ColorSchemeTypeData.BLUE -> SettingsAppearanceProto.ColorSchemeProto.BLUE
            ColorSchemeTypeData.CYAN -> SettingsAppearanceProto.ColorSchemeProto.CYAN
            ColorSchemeTypeData.MAGENTA -> SettingsAppearanceProto.ColorSchemeProto.MAGENTA
            ColorSchemeTypeData.YELLOW -> SettingsAppearanceProto.ColorSchemeProto.YELLOW
            ColorSchemeTypeData.DYNAMIC_COLOR -> SettingsAppearanceProto.ColorSchemeProto.DYNAMIC_COLOR
        }
    }

}