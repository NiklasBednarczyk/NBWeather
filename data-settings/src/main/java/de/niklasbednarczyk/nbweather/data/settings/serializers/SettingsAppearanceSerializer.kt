package de.niklasbednarczyk.nbweather.data.settings.serializers

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.nbweather.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class SettingsAppearanceSerializer @Inject constructor(
    @ApplicationContext private val context: Context
) : Serializer<SettingsAppearanceProto> {

    companion object {

        internal fun isSystemInDarkTheme(context: Context): Boolean {
            val uiMode = context.resources.configuration.uiMode
            return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        }

    }

    private val defaultUseDeviceTheme: Boolean = true

    private val defaultTheme: SettingsAppearanceProto.ThemeProto
        get() = if (isSystemInDarkTheme(context)) {
            SettingsAppearanceProto.ThemeProto.DARK
        } else {
            SettingsAppearanceProto.ThemeProto.LIGHT
        }

    private val defaultUseDynamicColorScheme: Boolean = true

    private val defaultColorScheme: SettingsAppearanceProto.ColorSchemeProto =
        SettingsAppearanceProto.ColorSchemeProto.YELLOW

    override val defaultValue: SettingsAppearanceProto = SettingsAppearanceProto.newBuilder()
        .setUseDeviceTheme(defaultUseDeviceTheme)
        .setTheme(defaultTheme)
        .setUseDynamicColorScheme(defaultUseDynamicColorScheme)
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