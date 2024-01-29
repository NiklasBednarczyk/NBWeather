package de.niklasbednarczyk.nbweather.core.common

import de.niklasbednarczyk.nbweather.core.common.locale.NBLanguageType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertEquals

class NBLanguageTypeTest : NBTest {

    @Test
    fun language_de_shouldConvertCorrectly() {
        testLanguage(
            language = "de",
            expectedLanguageType = NBLanguageType.GERMAN
        )
    }

    @Test
    fun language_en_shouldConvertCorrectly() {
        testLanguage(
            language = "en",
            expectedLanguageType = NBLanguageType.ENGLISH
        )
    }

    @Test
    fun language_null_shouldBeNull() {
        testLanguage(
            language = null,
            expectedLanguageType = null
        )
    }

    @Test
    fun language_undefined_shouldBeNull() {
        testLanguage(
            language = "undefined",
            expectedLanguageType = null
        )
    }

    @Test
    fun locale_germany_shouldConvertCorrectly() {
        testLocale(
            locale = Locale.GERMANY,
            expectedLanguageType = NBLanguageType.GERMAN
        )
    }

    @Test
    fun locale_us_shouldConvertCorrectly() {
        testLocale(
            locale = Locale.US,
            expectedLanguageType = NBLanguageType.ENGLISH
        )
    }

    private fun testLanguage(
        language: String?,
        expectedLanguageType: NBLanguageType?
    ) {
        // Arrange + Act
        val languageType = NBLanguageType.from(language)

        // Assert
        assertEquals(expectedLanguageType, languageType)
    }

    private fun testLocale(
        locale: Locale,
        expectedLanguageType: NBLanguageType
    ) {
        // Arrange
        setLocale(locale)

        // Act
        val languageType = NBLanguageType.fromLocale()

        // Assert
        assertEquals(expectedLanguageType, languageType)
    }

}