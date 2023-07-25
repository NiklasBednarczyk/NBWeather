package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest

class SettingsAppearanceSerializerTest : NBSerializerTest<SettingsAppearanceProto>() {

    override fun createSerializer(): Serializer<SettingsAppearanceProto> {
        return SettingsAppearanceSerializer(context)
    }

    override val expectedDefaultValue: SettingsAppearanceProto
        get() {
            val theme = if (SettingsAppearanceSerializer.isSystemInDarkTheme(context)) {
                SettingsAppearanceProto.ThemeProto.DARK
            } else {
                SettingsAppearanceProto.ThemeProto.LIGHT
            }

            return SettingsAppearanceProto.newBuilder()
                .setUseDeviceTheme(true)
                .setTheme(theme)
                .setUseDynamicColorScheme(true)
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.YELLOW)
                .build()
        }

    override val expectedNewValue: SettingsAppearanceProto
        get() {
            val theme = if (SettingsAppearanceSerializer.isSystemInDarkTheme(context)) {
                SettingsAppearanceProto.ThemeProto.LIGHT
            } else {
                SettingsAppearanceProto.ThemeProto.DARK
            }

            return SettingsAppearanceProto.newBuilder()
                .setUseDeviceTheme(false)
                .setTheme(theme)
                .setUseDynamicColorScheme(false)
                .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
                .build()
        }

}