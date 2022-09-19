package de.niklasbednarczyk.openweathermap.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.openweathermap.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.units.SettingsUnitsProto
import java.io.InputStream
import java.io.OutputStream
import java.util.*

internal object SettingsUnitsSerializer : Serializer<SettingsUnitsProto> {

    private fun isImperial(): Boolean = Locale.getDefault() == Locale.US

    private fun getDefaultTemperatureUnit(): SettingsUnitsProto.TemperatureUnitProto {
        return if (isImperial()) {
            SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT
        } else {
            SettingsUnitsProto.TemperatureUnitProto.CELSIUS
        }
    }

    override val defaultValue: SettingsUnitsProto = SettingsUnitsProto.newBuilder()
        .setTemperatureUnit(getDefaultTemperatureUnit())
        .build()

    override suspend fun readFrom(input: InputStream): SettingsUnitsProto {
        try {
            return SettingsUnitsProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsUnitsProto, output: OutputStream) = t.writeTo(output)


}