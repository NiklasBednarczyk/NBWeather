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

    private fun getLanguage(language: String): String {
        return Locale(language).language
    }

    private val defaultLanguage: SettingsDataProto.LanguageProto =
        when (Locale.getDefault().language) {
            getLanguage("af") -> SettingsDataProto.LanguageProto.AFRIKAANS
            getLanguage("sq") -> SettingsDataProto.LanguageProto.ALBANIAN
            getLanguage("ar") -> SettingsDataProto.LanguageProto.ARABIC
            getLanguage("az") -> SettingsDataProto.LanguageProto.AZERBAIJANI
            getLanguage("bg") -> SettingsDataProto.LanguageProto.BULGARIAN
            getLanguage("ca") -> SettingsDataProto.LanguageProto.CATALAN
            getLanguage("cs") -> SettingsDataProto.LanguageProto.CZECH
            getLanguage("da") -> SettingsDataProto.LanguageProto.DANISH
            getLanguage("de") -> SettingsDataProto.LanguageProto.GERMAN
            getLanguage("el") -> SettingsDataProto.LanguageProto.GREEK
            getLanguage("en") -> SettingsDataProto.LanguageProto.ENGLISH
            getLanguage("eu") -> SettingsDataProto.LanguageProto.BASQUE
            getLanguage("fa") -> SettingsDataProto.LanguageProto.PERSIAN_FARSI
            getLanguage("fi") -> SettingsDataProto.LanguageProto.FINNISH
            getLanguage("fr") -> SettingsDataProto.LanguageProto.FRENCH
            getLanguage("gl") -> SettingsDataProto.LanguageProto.GALICIAN
            getLanguage("he") -> SettingsDataProto.LanguageProto.HEBREW
            getLanguage("hi") -> SettingsDataProto.LanguageProto.HINDI
            getLanguage("hr") -> SettingsDataProto.LanguageProto.CROATIAN
            getLanguage("hu") -> SettingsDataProto.LanguageProto.HUNGARIAN
            getLanguage("id") -> SettingsDataProto.LanguageProto.INDONESIAN
            getLanguage("it") -> SettingsDataProto.LanguageProto.ITALIAN
            getLanguage("ja") -> SettingsDataProto.LanguageProto.JAPANESE
            getLanguage("ko") -> SettingsDataProto.LanguageProto.KOREAN
            getLanguage("lv") -> SettingsDataProto.LanguageProto.LATVIAN
            getLanguage("lt") -> SettingsDataProto.LanguageProto.LITHUANIAN
            getLanguage("mk") -> SettingsDataProto.LanguageProto.MACEDONIAN
            getLanguage("no") -> SettingsDataProto.LanguageProto.NORWEGIAN
            getLanguage("nl") -> SettingsDataProto.LanguageProto.DUTCH
            getLanguage("pl") -> SettingsDataProto.LanguageProto.POLISH
            getLanguage("pt") -> SettingsDataProto.LanguageProto.PORTUGUESE
            getLanguage("ro") -> SettingsDataProto.LanguageProto.ROMANIAN
            getLanguage("ru") -> SettingsDataProto.LanguageProto.RUSSIAN
            getLanguage("sv") -> SettingsDataProto.LanguageProto.SWEDISH
            getLanguage("sk") -> SettingsDataProto.LanguageProto.SLOVAK
            getLanguage("sl") -> SettingsDataProto.LanguageProto.SLOVENIAN
            getLanguage("es") -> SettingsDataProto.LanguageProto.SPANISH
            getLanguage("sr") -> SettingsDataProto.LanguageProto.SERBIAN
            getLanguage("th") -> SettingsDataProto.LanguageProto.THAI
            getLanguage("tr") -> SettingsDataProto.LanguageProto.TURKISH
            getLanguage("uk") -> SettingsDataProto.LanguageProto.UKRAINIAN
            getLanguage("vi") -> SettingsDataProto.LanguageProto.VIETNAMESE
            getLanguage("zh") -> SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED
            getLanguage("zu") -> SettingsDataProto.LanguageProto.ZULU
            else -> SettingsDataProto.LanguageProto.ENGLISH
        }

    private val defaultUnits: SettingsDataProto.UnitsProto =
        if (Locale.getDefault() == Locale.US) {
            SettingsDataProto.UnitsProto.IMPERIAL
        } else {
            SettingsDataProto.UnitsProto.METRIC
        }

    override val defaultValue: SettingsDataProto = SettingsDataProto.newBuilder()
        .setLanguage(defaultLanguage)
        .setUnits(defaultUnits)
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