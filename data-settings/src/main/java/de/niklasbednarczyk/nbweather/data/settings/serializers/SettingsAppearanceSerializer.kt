package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.nbweather.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import java.io.InputStream
import java.io.OutputStream

internal object SettingsAppearanceSerializer : Serializer<SettingsAppearanceProto> {

    private val defaultTheme: SettingsAppearanceProto.ThemeProto =
        SettingsAppearanceProto.ThemeProto.SYSTEM_DEFAULT

    private val defaultColorScheme: SettingsAppearanceProto.ColorSchemeProto =
        SettingsAppearanceProto.ColorSchemeProto.YELLOW

    override val defaultValue: SettingsAppearanceProto = SettingsAppearanceProto.newBuilder()
        .setTheme(defaultTheme)
        .setColorScheme(defaultColorScheme)
        .build()

    override suspend fun readFrom(input: InputStream): SettingsAppearanceProto {
        try {
            return SettingsAppearanceProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsAppearanceProto, output: OutputStream) =
        t.writeTo(output)


}