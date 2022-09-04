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

    private fun getDefaultUnits(): SettingsDisplayProto.UnitsProto {
        return when (Locale.getDefault()) {
            Locale.US -> SettingsDisplayProto.UnitsProto.IMPERIAL
            else -> SettingsDisplayProto.UnitsProto.METRIC
        }
    }

    private fun getDefaultDataLanguage(): SettingsDisplayProto.DataLanguageProto {
        return when (Locale.getDefault().language) {
            getLanguage("af") -> SettingsDisplayProto.DataLanguageProto.AFRIKAANS
            getLanguage("sq") -> SettingsDisplayProto.DataLanguageProto.ALBANIAN
            getLanguage("ar") -> SettingsDisplayProto.DataLanguageProto.ARABIC
            getLanguage("az") -> SettingsDisplayProto.DataLanguageProto.AZERBAIJANI
            getLanguage("bg") -> SettingsDisplayProto.DataLanguageProto.BULGARIAN
            getLanguage("ca") -> SettingsDisplayProto.DataLanguageProto.CATALAN
            getLanguage("cs") -> SettingsDisplayProto.DataLanguageProto.CZECH
            getLanguage("da") -> SettingsDisplayProto.DataLanguageProto.DANISH
            getLanguage("de") -> SettingsDisplayProto.DataLanguageProto.GERMAN
            getLanguage("el") -> SettingsDisplayProto.DataLanguageProto.GREEK
            getLanguage("en") -> SettingsDisplayProto.DataLanguageProto.ENGLISH
            getLanguage("eu") -> SettingsDisplayProto.DataLanguageProto.BASQUE
            getLanguage("fa") -> SettingsDisplayProto.DataLanguageProto.PERSIAN_FARSI
            getLanguage("fi") -> SettingsDisplayProto.DataLanguageProto.FINNISH
            getLanguage("fr") -> SettingsDisplayProto.DataLanguageProto.FRENCH
            getLanguage("gl") -> SettingsDisplayProto.DataLanguageProto.GALICIAN
            getLanguage("he") -> SettingsDisplayProto.DataLanguageProto.HEBREW
            getLanguage("hi") -> SettingsDisplayProto.DataLanguageProto.HINDI
            getLanguage("hr") -> SettingsDisplayProto.DataLanguageProto.CROATIAN
            getLanguage("hu") -> SettingsDisplayProto.DataLanguageProto.HUNGARIAN
            getLanguage("id") -> SettingsDisplayProto.DataLanguageProto.INDONESIAN
            getLanguage("it") -> SettingsDisplayProto.DataLanguageProto.ITALIAN
            getLanguage("ja") -> SettingsDisplayProto.DataLanguageProto.JAPANESE
            getLanguage("ko") -> SettingsDisplayProto.DataLanguageProto.KOREAN
            getLanguage("lv") -> SettingsDisplayProto.DataLanguageProto.LATVIAN
            getLanguage("lt") -> SettingsDisplayProto.DataLanguageProto.LITHUANIAN
            getLanguage("mk") -> SettingsDisplayProto.DataLanguageProto.MACEDONIAN
            getLanguage("no") -> SettingsDisplayProto.DataLanguageProto.NORWEGIAN
            getLanguage("nl") -> SettingsDisplayProto.DataLanguageProto.DUTCH
            getLanguage("pl") -> SettingsDisplayProto.DataLanguageProto.POLISH
            getLanguage("pt") -> SettingsDisplayProto.DataLanguageProto.PORTUGUESE
            getLanguage("ro") -> SettingsDisplayProto.DataLanguageProto.ROMANIAN
            getLanguage("ru") -> SettingsDisplayProto.DataLanguageProto.RUSSIAN
            getLanguage("sv") -> SettingsDisplayProto.DataLanguageProto.SWEDISH
            getLanguage("sk") -> SettingsDisplayProto.DataLanguageProto.SLOVAK
            getLanguage("sl") -> SettingsDisplayProto.DataLanguageProto.SLOVENIAN
            getLanguage("es") -> SettingsDisplayProto.DataLanguageProto.SPANISH
            getLanguage("sr") -> SettingsDisplayProto.DataLanguageProto.SERBIAN
            getLanguage("th") -> SettingsDisplayProto.DataLanguageProto.THAI
            getLanguage("tr") -> SettingsDisplayProto.DataLanguageProto.TURKISH
            getLanguage("uk") -> SettingsDisplayProto.DataLanguageProto.UKRAINIAN
            getLanguage("vi") -> SettingsDisplayProto.DataLanguageProto.VIETNAMESE
            getLanguage("zh") -> SettingsDisplayProto.DataLanguageProto.CHINESE_TRADITIONAL
            getLanguage("zu") -> SettingsDisplayProto.DataLanguageProto.ZULU
            else -> SettingsDisplayProto.DataLanguageProto.ENGLISH
        }
    }

    private fun getLanguage(language: String): String {
        return Locale(language).language
    }

    override val defaultValue: SettingsDisplayProto = SettingsDisplayProto.newBuilder()
        .setUnits(getDefaultUnits())
        .setDataLanguage(getDefaultDataLanguage())
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