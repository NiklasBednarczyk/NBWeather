package de.niklasbednarczyk.nbweather.core.common

import android.content.Intent
import de.niklasbednarczyk.nbweather.core.common.intent.createEmailIntent
import de.niklasbednarczyk.nbweather.core.common.intent.createUrlIntent
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NBIntentTest : NBTest {

    @Test
    fun createEmailIntent_shouldCreateCorrectEmailIntent() {
        // Arrange
        val emailAddress = "john.doe@example.com"

        // Act
        val intent = createEmailIntent(emailAddress)

        // Assert
        assertEquals(Intent.ACTION_SENDTO, intent.action)
        assertEquals("mailto:$emailAddress", intent.dataString)
    }

    @Test
    fun createUrlIntent_withHttp_shouldCreateCorrectUrlIntent() {
        // Arrange
        val url = "http://www.example.com"

        // Act
        val intent = createUrlIntent(url)

        // Assert
        assertUrlIntent(url, intent)
    }

    @Test
    fun createUrlIntent_withHttps_shouldCreateCorrectUrlIntent() {
        // Arrange
        val url = "https://www.example.com"

        // Act
        val intent = createUrlIntent(url)

        // Assert
        assertUrlIntent(url, intent)
    }

    @Test
    fun createUrlIntent_withoutHttpOrHttps_shouldCreateCorrectUrlIntent() {
        // Arrange
        val url = "www.example.com"

        // Act
        val intent = createUrlIntent(url)

        // Assert
        assertUrlIntent(url, intent)
    }

    private fun assertUrlIntent(
        url: String,
        intent: Intent
    ) {
        assertEquals(Intent.ACTION_VIEW, intent.action)
        assertListDoesContain(intent.categories.toList(), Intent.CATEGORY_DEFAULT)

        val dataString = intent.dataString!!

        val startsWithHttp = dataString.startsWith("http://")
        val startsWithHttps = dataString.startsWith("https://")
        assertTrue(startsWithHttp || startsWithHttps)

        assertTrue(dataString.contains(url))
    }

}