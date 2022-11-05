package de.niklasbednarczyk.openweathermap.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.openweathermap.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto
import java.io.InputStream
import java.io.OutputStream
import java.util.*

internal object SettingsDisplaySerializer : Serializer<SettingsDisplayProto> {

    private val isImperial: Boolean = Locale.getDefault() == Locale.US

    private val defaultTemperatureUnit: SettingsDisplayProto.TemperatureUnitProto =
        if (isImperial) {
            SettingsDisplayProto.TemperatureUnitProto.FAHRENHEIT
        } else {
            SettingsDisplayProto.TemperatureUnitProto.CELSIUS
        }

    private val defaultWindSpeedUnit: SettingsDisplayProto.WindSpeedUnitProto =
        if (isImperial) {
            SettingsDisplayProto.WindSpeedUnitProto.MILE_PER_HOUR
        } else {
            SettingsDisplayProto.WindSpeedUnitProto.KILOMETRE_PER_HOUR
        }

    private val defaultPressureUnit: SettingsDisplayProto.PressureUnitProto =
        if (isImperial) {
            SettingsDisplayProto.PressureUnitProto.INCH_OF_MERCURY
        } else {
            SettingsDisplayProto.PressureUnitProto.HECTOPASCAL
        }

    private val defaultDistanceUnit: SettingsDisplayProto.DistanceUnitProto =
        if (isImperial) {
            SettingsDisplayProto.DistanceUnitProto.MILE
        } else {
            SettingsDisplayProto.DistanceUnitProto.KILOMETRE
        }

    private val defaultPrecipitationUnit: SettingsDisplayProto.PrecipitationUnitProto =
        if (isImperial) {
            SettingsDisplayProto.PrecipitationUnitProto.INCH
        } else {
            SettingsDisplayProto.PrecipitationUnitProto.MILLIMETRE
        }

    private val defaultTimeFormat: SettingsDisplayProto.TimeFormatProto =
        SettingsDisplayProto.TimeFormatProto.SYSTEM_DEFAULT


    override val defaultValue: SettingsDisplayProto = SettingsDisplayProto.newBuilder()
        .setTemperatureUnit(defaultTemperatureUnit)
        .setWindSpeedUnit(defaultWindSpeedUnit)
        .setPressureUnit(defaultPressureUnit)
        .setDistanceUnit(defaultDistanceUnit)
        .setPrecipitationUnit(defaultPrecipitationUnit)
        .setTimeFormat(defaultTimeFormat)
        .build()

    override suspend fun readFrom(input: InputStream): SettingsDisplayProto {
        try {
            return SettingsDisplayProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsDisplayProto, output: OutputStream) = t.writeTo(output)


}