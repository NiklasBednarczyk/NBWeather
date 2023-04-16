package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsDataSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test
import kotlin.test.assertEquals

class SettingsDataRepositoryTest :
    NBDiskRepositoryTest<SettingsDataProto, SettingsDataModelData, SettingsDataRepository>() {

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME

    override val updateStartingProto: SettingsDataProto
        get() = SettingsDataProto
            .newBuilder()
            .setLanguage(SettingsDataProto.LanguageProto.SPANISH)
            .setUnits(SettingsDataProto.UnitsProto.IMPERIAL)
            .setTimeFormat(SettingsDataProto.TimeFormatProto.HOUR_12)
            .build()

    override fun createSerializer(): Serializer<SettingsDataProto> {
        return SettingsDataSerializer(context)
    }

    override fun createRepository(dataStore: DataStore<SettingsDataProto>): SettingsDataRepository {
        return SettingsDataRepository(dataStore)
    }

    @Test
    fun getData_languageAfrikaans_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.AFRIKAANS)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.AFRIKAANS, actual.language)
            }
        )
    }

    @Test
    fun getData_languageAlbanian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ALBANIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ALBANIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageArabic_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ARABIC)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ARABIC, actual.language)
            }
        )
    }

    @Test
    fun getData_languageAzerbaijani_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.AZERBAIJANI)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.AZERBAIJANI, actual.language)
            }
        )
    }

    @Test
    fun getData_languageBulgarian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.BULGARIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.BULGARIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageCatalan_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.CATALAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.CATALAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageCzech_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.CZECH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.CZECH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageDanish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.DANISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.DANISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageGerman_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.GERMAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.GERMAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageGreek_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.GREEK)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.GREEK, actual.language)
            }
        )
    }

    @Test
    fun getData_languageEnglish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ENGLISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ENGLISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageBasque_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.BASQUE)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.BASQUE, actual.language)
            }
        )
    }

    @Test
    fun getData_languagePersianFarsi_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.PERSIAN_FARSI)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.PERSIAN_FARSI, actual.language)
            }
        )
    }

    @Test
    fun getData_languageFinnish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.FINNISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.FINNISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageFrench_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.FRENCH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.FRENCH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageGalician_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.GALICIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.GALICIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageHebrew_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.HEBREW)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.HEBREW, actual.language)
            }
        )
    }

    @Test
    fun getData_languageHindi_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.HINDI)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.HINDI, actual.language)
            }
        )
    }

    @Test
    fun getData_languageCroatian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.CROATIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.CROATIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageHungarian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.HUNGARIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.HUNGARIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageIndonesian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.INDONESIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.INDONESIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageItalian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ITALIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ITALIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageJapanese_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.JAPANESE)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.JAPANESE, actual.language)
            }
        )
    }

    @Test
    fun getData_languageKorean_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.KOREAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.KOREAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageLatvian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.LATVIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.LATVIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageLithuanian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.LITHUANIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.LITHUANIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageMacedonian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.MACEDONIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.MACEDONIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageNorwegian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.NORWEGIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.NORWEGIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageDutch_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.DUTCH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.DUTCH, actual.language)
            }
        )
    }

    @Test
    fun getData_languagePolish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.POLISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.POLISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languagePortuguese_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.PORTUGUESE)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.PORTUGUESE, actual.language)
            }
        )
    }

    @Test
    fun getData_languagePortuguesBrasil_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.PORTUGUES_BRASIL)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.PORTUGUES_BRASIL, actual.language)
            }
        )
    }

    @Test
    fun getData_languageRomanian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ROMANIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ROMANIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageRussian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.RUSSIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.RUSSIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageSwedish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SWEDISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.SWEDISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageSlovak_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SLOVAK)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.SLOVAK, actual.language)
            }
        )
    }

    @Test
    fun getData_languageSlovenian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SLOVENIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.SLOVENIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageSpanish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SPANISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.SPANISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageSerbian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.SERBIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.SERBIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageThai_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.THAI)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.THAI, actual.language)
            }
        )
    }

    @Test
    fun getData_languageTurkish_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.TURKISH)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.TURKISH, actual.language)
            }
        )
    }

    @Test
    fun getData_languageUkrainian_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.UKRAINIAN)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.UKRAINIAN, actual.language)
            }
        )
    }

    @Test
    fun getData_languageVietnamese_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.VIETNAMESE)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.VIETNAMESE, actual.language)
            }
        )
    }

    @Test
    fun getData_languageChineseSimplified_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.CHINESE_SIMPLIFIED)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.CHINESE_SIMPLIFIED, actual.language)
            }
        )
    }

    @Test
    fun getData_languageChineseTraditional_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.CHINESE_TRADITIONAL)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.CHINESE_TRADITIONAL, actual.language)
            }
        )
    }

    @Test
    fun getData_languageZulu_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setLanguage(SettingsDataProto.LanguageProto.ZULU)
                .build(),
            assertValue = { actual ->
                assertValue(NBLanguageType.ZULU, actual.language)
            }
        )
    }

    @Test
    fun getData_unitsStandard_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setUnits(SettingsDataProto.UnitsProto.STANDARD)
                .build(),
            assertValue = { actual ->
                assertValue(NBUnitsType.STANDARD, actual.units)
            }
        )
    }

    @Test
    fun getData_unitsMetric_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setUnits(SettingsDataProto.UnitsProto.METRIC)
                .build(),
            assertValue = { actual ->
                assertValue(NBUnitsType.METRIC, actual.units)
            }
        )
    }

    @Test
    fun getData_unitsImperial_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setUnits(SettingsDataProto.UnitsProto.IMPERIAL)
                .build(),
            assertValue = { actual ->
                assertValue(NBUnitsType.IMPERIAL, actual.units)
            }
        )
    }

    @Test
    fun getData_timeFormatHour12_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setTimeFormat(SettingsDataProto.TimeFormatProto.HOUR_12)
                .build(),
            assertValue = { actual ->
                assertValue(NBTimeFormatType.HOUR_12, actual.timeFormat)
            }
        )
    }

    @Test
    fun getData_timeFormatHour24_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsDataProto
                .newBuilder()
                .setTimeFormat(SettingsDataProto.TimeFormatProto.HOUR_24)
                .build(),
            assertValue = { actual ->
                assertValue(NBTimeFormatType.HOUR_24, actual.timeFormat)
            }
        )
    }

    @Test
    fun updateLanguage__shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateLanguage(NBLanguageType.GERMAN)
            },
            assertValue = { actual ->
                assertValue(SettingsDataProto.LanguageProto.GERMAN, actual.language)
            }
        )
    }

    @Test
    fun updateUnits__shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateUnits(NBUnitsType.METRIC)
            },
            assertValue = { actual ->
                assertValue(SettingsDataProto.UnitsProto.METRIC, actual.units)
            }
        )
    }

    @Test
    fun updateTimeFormat__shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateTimeFormat(NBTimeFormatType.HOUR_24)
            },
            assertValue = { actual ->
                assertValue(SettingsDataProto.TimeFormatProto.HOUR_24, actual.timeFormat)
            }
        )
    }

}