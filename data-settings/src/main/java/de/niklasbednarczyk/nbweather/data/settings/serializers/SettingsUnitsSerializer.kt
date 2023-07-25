package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.nbweather.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto
import java.io.InputStream
import java.io.OutputStream
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SettingsUnitsSerializer @Inject constructor() : Serializer<SettingsUnitsProto> {

    companion object {

        internal fun usesImperialSystem() = Locale.getDefault() == Locale.US

        internal fun createDefault(usesImperialSystem: Boolean): SettingsUnitsProto {

            val defaultPressureUnit = SettingsUnitsProto.PressureUnitProto.HECTOPASCAL

            return if (usesImperialSystem) {
                SettingsUnitsProto.newBuilder()
                    .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT)
                    .setPrecipitationUnit(SettingsUnitsProto.PrecipitationUnitProto.INCH)
                    .setDistanceUnit(SettingsUnitsProto.DistanceUnitProto.MILE)
                    .setPressureUnit(defaultPressureUnit)
                    .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.MILE_PER_HOUR)
                    .build()
            } else {
                SettingsUnitsProto.newBuilder()
                    .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.CELSIUS)
                    .setPrecipitationUnit(SettingsUnitsProto.PrecipitationUnitProto.MILLIMETER)
                    .setDistanceUnit(SettingsUnitsProto.DistanceUnitProto.KILOMETER)
                    .setPressureUnit(defaultPressureUnit)
                    .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.METER_PER_SECOND)
                    .build()
            }
        }

    }

    override val defaultValue: SettingsUnitsProto = createDefault(usesImperialSystem())

    override suspend fun readFrom(input: InputStream): SettingsUnitsProto {
        try {
            return SettingsUnitsProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsUnitsProto, output: OutputStream) = t.writeTo(output)


}