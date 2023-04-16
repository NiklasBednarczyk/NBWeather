package de.niklasbednarczyk.nbweather.data.settings.mappers.appearance

import de.niklasbednarczyk.nbweather.core.data.disk.mappers.TwoWayMapperDisk
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto

internal object ColorSchemeMapperData :
    TwoWayMapperDisk<SettingsAppearanceProto.ColorSchemeProto, ColorSchemeTypeData> {
    override fun protoToData(proto: SettingsAppearanceProto.ColorSchemeProto): ColorSchemeTypeData {
        return when (proto) {
            SettingsAppearanceProto.ColorSchemeProto.RED -> ColorSchemeTypeData.RED
            SettingsAppearanceProto.ColorSchemeProto.GREEN -> ColorSchemeTypeData.GREEN
            SettingsAppearanceProto.ColorSchemeProto.BLUE -> ColorSchemeTypeData.BLUE
            SettingsAppearanceProto.ColorSchemeProto.CYAN -> ColorSchemeTypeData.CYAN
            SettingsAppearanceProto.ColorSchemeProto.MAGENTA -> ColorSchemeTypeData.MAGENTA
            SettingsAppearanceProto.ColorSchemeProto.YELLOW -> ColorSchemeTypeData.YELLOW
            SettingsAppearanceProto.ColorSchemeProto.UNRECOGNIZED -> ColorSchemeTypeData.YELLOW
        }
    }

    override fun dataToProto(data: ColorSchemeTypeData): SettingsAppearanceProto.ColorSchemeProto {
        return when (data) {
            ColorSchemeTypeData.RED -> SettingsAppearanceProto.ColorSchemeProto.RED
            ColorSchemeTypeData.GREEN -> SettingsAppearanceProto.ColorSchemeProto.GREEN
            ColorSchemeTypeData.BLUE -> SettingsAppearanceProto.ColorSchemeProto.BLUE
            ColorSchemeTypeData.CYAN -> SettingsAppearanceProto.ColorSchemeProto.CYAN
            ColorSchemeTypeData.MAGENTA -> SettingsAppearanceProto.ColorSchemeProto.MAGENTA
            ColorSchemeTypeData.YELLOW -> SettingsAppearanceProto.ColorSchemeProto.YELLOW
        }
    }

}