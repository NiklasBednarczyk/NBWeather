package de.niklasbednarczyk.nbweather.data.settings.serializers

import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.common.time.is24HourFormat
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.test.data.disk.serializers.NBSerializerTest
import java.util.*

class SettingsDataSerializerTest : NBSerializerTest<SettingsDataProto>() {

    override fun setUp() {
        super.setUp()
        setLocale(Locale.GERMANY)
    }

    override fun createSerializer(): Serializer<SettingsDataProto> {
        return SettingsDataSerializer(context)
    }

    override val expectedDefaultValue: SettingsDataProto
        get() {
            val timeFormat = if (context.is24HourFormat) {
                SettingsDataProto.TimeFormatProto.HOUR_24
            } else {
                SettingsDataProto.TimeFormatProto.HOUR_12
            }

            return SettingsDataProto.newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.GERMAN)
                .setUnits(SettingsDataProto.UnitsProto.METRIC)
                .setTimeFormat(timeFormat)
                .build()
        }

    override val expectedNewValue: SettingsDataProto
        get() {
            val timeFormat = when (expectedDefaultValue.timeFormat) {
                SettingsDataProto.TimeFormatProto.HOUR_12 -> SettingsDataProto.TimeFormatProto.HOUR_24
                SettingsDataProto.TimeFormatProto.HOUR_24 -> SettingsDataProto.TimeFormatProto.HOUR_12
                SettingsDataProto.TimeFormatProto.UNRECOGNIZED, null -> SettingsDataProto.TimeFormatProto.HOUR_12
            }

            return SettingsDataProto.newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SPANISH)
                .setUnits(SettingsDataProto.UnitsProto.IMPERIAL)
                .setTimeFormat(timeFormat)
                .build()
        }

}