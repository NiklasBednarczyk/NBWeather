package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest

class SettingsAppearanceSerializerTest : NBSerializerTest<SettingsAppearanceProto>() {

    override fun createSerializer(): Serializer<SettingsAppearanceProto> {
        return SettingsAppearanceSerializer()
    }

    override val expectedDefaultValue: SettingsAppearanceProto
        get() = SettingsAppearanceProto.newBuilder()
            .setTheme(SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT)
            .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.YELLOW)
            .build()

    override val expectedNewValue: SettingsAppearanceProto
        get() = SettingsAppearanceProto.newBuilder()
            .setTheme(SettingsAppearanceProto.ThemeProto.DARK)
            .setColorScheme(SettingsAppearanceProto.ColorSchemeProto.BLUE)
            .build()

}