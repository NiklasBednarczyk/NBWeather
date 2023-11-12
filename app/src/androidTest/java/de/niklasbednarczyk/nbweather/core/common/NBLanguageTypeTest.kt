package de.niklasbednarczyk.nbweather.core.common

import de.niklasbednarczyk.nbweather.core.common.language.NBLanguageType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale

class NBLanguageTypeTest : NBTest {

    @Test
    fun localeGermany_shouldConvertCorrectly() {
        testNBLanguageType(
            locale = Locale.GERMANY,
            expectedLanguageType = NBLanguageType.GERMAN
        )
    }

    @Test
    fun localeUS_shouldConvertCorrectly() {
        testNBLanguageType(
            locale = Locale.US,
            expectedLanguageType = NBLanguageType.ENGLISH
        )
    }

    private fun testNBLanguageType(
        locale: Locale,
        expectedLanguageType: NBLanguageType
    ) {
        // Arrange
        setLocale(locale)

        // Act
        val languageType = NBLanguageType.from()

        // Assert
        assertValue(expectedLanguageType, languageType)
    }

}