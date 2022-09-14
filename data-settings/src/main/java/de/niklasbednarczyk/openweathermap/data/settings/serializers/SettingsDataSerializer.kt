package de.niklasbednarczyk.openweathermap.data.settings.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import de.niklasbednarczyk.openweathermap.core.data.disk.constants.ConstantsCoreDisk
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto
import java.io.InputStream
import java.io.OutputStream
import java.util.*

internal object SettingsDataSerializer : Serializer<SettingsDataProto> {

    private fun getDefaultUnits(): SettingsDataProto.UnitsProto {
        return when (Locale.getDefault()) {
            Locale.US -> SettingsDataProto.UnitsProto.IMPERIAL
            else -> SettingsDataProto.UnitsProto.METRIC
        }
    }

    private fun getDefaultDataLanguage(): SettingsDataProto.DataLanguageProto {
        return when (Locale.getDefault().language) {
            getLanguage("af") -> SettingsDataProto.DataLanguageProto.AFRIKAANS
            getLanguage("sq") -> SettingsDataProto.DataLanguageProto.ALBANIAN
            getLanguage("ar") -> SettingsDataProto.DataLanguageProto.ARABIC
            getLanguage("az") -> SettingsDataProto.DataLanguageProto.AZERBAIJANI
            getLanguage("bg") -> SettingsDataProto.DataLanguageProto.BULGARIAN
            getLanguage("ca") -> SettingsDataProto.DataLanguageProto.CATALAN
            getLanguage("cs") -> SettingsDataProto.DataLanguageProto.CZECH
            getLanguage("da") -> SettingsDataProto.DataLanguageProto.DANISH
            getLanguage("de") -> SettingsDataProto.DataLanguageProto.GERMAN
            getLanguage("el") -> SettingsDataProto.DataLanguageProto.GREEK
            getLanguage("en") -> SettingsDataProto.DataLanguageProto.ENGLISH
            getLanguage("eu") -> SettingsDataProto.DataLanguageProto.BASQUE
            getLanguage("fa") -> SettingsDataProto.DataLanguageProto.PERSIAN_FARSI
            getLanguage("fi") -> SettingsDataProto.DataLanguageProto.FINNISH
            getLanguage("fr") -> SettingsDataProto.DataLanguageProto.FRENCH
            getLanguage("gl") -> SettingsDataProto.DataLanguageProto.GALICIAN
            getLanguage("he") -> SettingsDataProto.DataLanguageProto.HEBREW
            getLanguage("hi") -> SettingsDataProto.DataLanguageProto.HINDI
            getLanguage("hr") -> SettingsDataProto.DataLanguageProto.CROATIAN
            getLanguage("hu") -> SettingsDataProto.DataLanguageProto.HUNGARIAN
            getLanguage("id") -> SettingsDataProto.DataLanguageProto.INDONESIAN
            getLanguage("it") -> SettingsDataProto.DataLanguageProto.ITALIAN
            getLanguage("ja") -> SettingsDataProto.DataLanguageProto.JAPANESE
            getLanguage("ko") -> SettingsDataProto.DataLanguageProto.KOREAN
            getLanguage("lv") -> SettingsDataProto.DataLanguageProto.LATVIAN
            getLanguage("lt") -> SettingsDataProto.DataLanguageProto.LITHUANIAN
            getLanguage("mk") -> SettingsDataProto.DataLanguageProto.MACEDONIAN
            getLanguage("no") -> SettingsDataProto.DataLanguageProto.NORWEGIAN
            getLanguage("nl") -> SettingsDataProto.DataLanguageProto.DUTCH
            getLanguage("pl") -> SettingsDataProto.DataLanguageProto.POLISH
            getLanguage("pt") -> SettingsDataProto.DataLanguageProto.PORTUGUESE
            getLanguage("ro") -> SettingsDataProto.DataLanguageProto.ROMANIAN
            getLanguage("ru") -> SettingsDataProto.DataLanguageProto.RUSSIAN
            getLanguage("sv") -> SettingsDataProto.DataLanguageProto.SWEDISH
            getLanguage("sk") -> SettingsDataProto.DataLanguageProto.SLOVAK
            getLanguage("sl") -> SettingsDataProto.DataLanguageProto.SLOVENIAN
            getLanguage("es") -> SettingsDataProto.DataLanguageProto.SPANISH
            getLanguage("sr") -> SettingsDataProto.DataLanguageProto.SERBIAN
            getLanguage("th") -> SettingsDataProto.DataLanguageProto.THAI
            getLanguage("tr") -> SettingsDataProto.DataLanguageProto.TURKISH
            getLanguage("uk") -> SettingsDataProto.DataLanguageProto.UKRAINIAN
            getLanguage("vi") -> SettingsDataProto.DataLanguageProto.VIETNAMESE
            getLanguage("zh") -> SettingsDataProto.DataLanguageProto.CHINESE_TRADITIONAL
            getLanguage("zu") -> SettingsDataProto.DataLanguageProto.ZULU
            else -> SettingsDataProto.DataLanguageProto.ENGLISH
        }
    }

    private fun getLanguage(language: String): String {
        return Locale(language).language
    }

    override val defaultValue: SettingsDataProto = SettingsDataProto.newBuilder()
        .setUnits(getDefaultUnits())
        .setDataLanguage(getDefaultDataLanguage())
        .build()

    override suspend fun readFrom(input: InputStream): SettingsDataProto {
        try {
            return SettingsDataProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(ConstantsCoreDisk.Serializer.ERROR_MESSAGE, exception)
        }
    }

    override suspend fun writeTo(t: SettingsDataProto, output: OutputStream) = t.writeTo(output)


}