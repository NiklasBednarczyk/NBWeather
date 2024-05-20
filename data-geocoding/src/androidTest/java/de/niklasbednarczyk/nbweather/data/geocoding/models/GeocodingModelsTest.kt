package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.locale.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GeocodingModelsTest : NBTest {

    @Test
    fun dataToLocal_shouldConvertCorrectly() {
        // Arrange
        val locationData = createTestLocationData()
        val localNamesData = locationData.localNames

        // Act
        val locationLocal = LocationModelData.dataToLocal(
            data = locationData
        )
        val localNamesLocal = locationLocal.localNames

        // Assert
        assertNotNull(localNamesData)
        assertNotNull(localNamesLocal)
        assertEquals(localNamesData.af, localNamesLocal.af)
        assertEquals(localNamesData.sq, localNamesLocal.sq)
        assertEquals(localNamesData.ar, localNamesLocal.ar)
        assertEquals(localNamesData.az, localNamesLocal.az)
        assertEquals(localNamesData.bg, localNamesLocal.bg)
        assertEquals(localNamesData.ca, localNamesLocal.ca)
        assertEquals(localNamesData.cs, localNamesLocal.cs)
        assertEquals(localNamesData.da, localNamesLocal.da)
        assertEquals(localNamesData.de, localNamesLocal.de)
        assertEquals(localNamesData.el, localNamesLocal.el)
        assertEquals(localNamesData.en, localNamesLocal.en)
        assertEquals(localNamesData.eu, localNamesLocal.eu)
        assertEquals(localNamesData.fa, localNamesLocal.fa)
        assertEquals(localNamesData.fi, localNamesLocal.fi)
        assertEquals(localNamesData.fr, localNamesLocal.fr)
        assertEquals(localNamesData.gl, localNamesLocal.gl)
        assertEquals(localNamesData.he, localNamesLocal.he)
        assertEquals(localNamesData.hi, localNamesLocal.hi)
        assertEquals(localNamesData.hr, localNamesLocal.hr)
        assertEquals(localNamesData.hu, localNamesLocal.hu)
        assertEquals(localNamesData.id, localNamesLocal.id)
        assertEquals(localNamesData.it, localNamesLocal.it)
        assertEquals(localNamesData.ja, localNamesLocal.ja)
        assertEquals(localNamesData.ko, localNamesLocal.ko)
        assertEquals(localNamesData.lv, localNamesLocal.lv)
        assertEquals(localNamesData.lt, localNamesLocal.lt)
        assertEquals(localNamesData.mk, localNamesLocal.mk)
        assertEquals(localNamesData.no, localNamesLocal.no)
        assertEquals(localNamesData.nl, localNamesLocal.nl)
        assertEquals(localNamesData.pl, localNamesLocal.pl)
        assertEquals(localNamesData.pt, localNamesLocal.pt)
        assertEquals(localNamesData.ro, localNamesLocal.ro)
        assertEquals(localNamesData.ru, localNamesLocal.ru)
        assertEquals(localNamesData.sv, localNamesLocal.sv)
        assertEquals(localNamesData.sk, localNamesLocal.sk)
        assertEquals(localNamesData.sl, localNamesLocal.sl)
        assertEquals(localNamesData.es, localNamesLocal.es)
        assertEquals(localNamesData.sr, localNamesLocal.sr)
        assertEquals(localNamesData.th, localNamesLocal.th)
        assertEquals(localNamesData.tr, localNamesLocal.tr)
        assertEquals(localNamesData.uk, localNamesLocal.uk)
        assertEquals(localNamesData.vi, localNamesLocal.vi)
        assertEquals(localNamesData.zh, localNamesLocal.zh)
        assertEquals(localNamesData.zu, localNamesLocal.zu)

        assertEquals(locationData.latitude, locationLocal.latitude)
        assertEquals(locationData.longitude, locationLocal.longitude)
        assertEquals(locationData.name, locationLocal.name)
        assertEquals(locationData.country, locationLocal.country)
        assertEquals(locationData.state, locationLocal.state)
        assertEquals(
            locationData.lastVisitedTimestampEpochSeconds,
            locationLocal.lastVisitedTimestampEpochSeconds
        )
        assertEquals(locationData.order, locationLocal.order)
    }

    @Test
    fun localToData_shouldConvertCorrectly() {
        // Arrange
        val locationLocal = createTestLocationLocal()
        val localNamesLocal = locationLocal.localNames

        // Act
        val locationData = LocationModelData.localToData(
            local = locationLocal
        )
        val localNamesData = locationData?.localNames

        // Assert
        assertNotNull(locationData)

        assertNotNull(localNamesLocal)
        assertNotNull(localNamesData)
        assertEquals(localNamesLocal.af, localNamesData.af)
        assertEquals(localNamesLocal.sq, localNamesData.sq)
        assertEquals(localNamesLocal.ar, localNamesData.ar)
        assertEquals(localNamesLocal.az, localNamesData.az)
        assertEquals(localNamesLocal.bg, localNamesData.bg)
        assertEquals(localNamesLocal.ca, localNamesData.ca)
        assertEquals(localNamesLocal.cs, localNamesData.cs)
        assertEquals(localNamesLocal.da, localNamesData.da)
        assertEquals(localNamesLocal.de, localNamesData.de)
        assertEquals(localNamesLocal.el, localNamesData.el)
        assertEquals(localNamesLocal.en, localNamesData.en)
        assertEquals(localNamesLocal.eu, localNamesData.eu)
        assertEquals(localNamesLocal.fa, localNamesData.fa)
        assertEquals(localNamesLocal.fi, localNamesData.fi)
        assertEquals(localNamesLocal.fr, localNamesData.fr)
        assertEquals(localNamesLocal.gl, localNamesData.gl)
        assertEquals(localNamesLocal.he, localNamesData.he)
        assertEquals(localNamesLocal.hi, localNamesData.hi)
        assertEquals(localNamesLocal.hr, localNamesData.hr)
        assertEquals(localNamesLocal.hu, localNamesData.hu)
        assertEquals(localNamesLocal.id, localNamesData.id)
        assertEquals(localNamesLocal.it, localNamesData.it)
        assertEquals(localNamesLocal.ja, localNamesData.ja)
        assertEquals(localNamesLocal.ko, localNamesData.ko)
        assertEquals(localNamesLocal.lv, localNamesData.lv)
        assertEquals(localNamesLocal.lt, localNamesData.lt)
        assertEquals(localNamesLocal.mk, localNamesData.mk)
        assertEquals(localNamesLocal.no, localNamesData.no)
        assertEquals(localNamesLocal.nl, localNamesData.nl)
        assertEquals(localNamesLocal.pl, localNamesData.pl)
        assertEquals(localNamesLocal.pt, localNamesData.pt)
        assertEquals(localNamesLocal.ro, localNamesData.ro)
        assertEquals(localNamesLocal.ru, localNamesData.ru)
        assertEquals(localNamesLocal.sv, localNamesData.sv)
        assertEquals(localNamesLocal.sk, localNamesData.sk)
        assertEquals(localNamesLocal.sl, localNamesData.sl)
        assertEquals(localNamesLocal.es, localNamesData.es)
        assertEquals(localNamesLocal.sr, localNamesData.sr)
        assertEquals(localNamesLocal.th, localNamesData.th)
        assertEquals(localNamesLocal.tr, localNamesData.tr)
        assertEquals(localNamesLocal.uk, localNamesData.uk)
        assertEquals(localNamesLocal.vi, localNamesData.vi)
        assertEquals(localNamesLocal.zh, localNamesData.zh)
        assertEquals(localNamesLocal.zu, localNamesData.zu)

        assertEquals(locationLocal.latitude, locationData.latitude)
        assertEquals(locationLocal.longitude, locationData.longitude)
        assertEquals(locationLocal.name, locationData.name)
        assertEquals(locationLocal.country, locationData.country)
        assertEquals(locationLocal.state, locationData.state)
        assertEquals(
            locationLocal.lastVisitedTimestampEpochSeconds,
            locationData.lastVisitedTimestampEpochSeconds
        )
        assertEquals(locationLocal.order, locationData.order)
    }

    @Test
    fun remoteToData_shouldConvertCorrectly() {
        // Arrange
        val locationRemote = createTestLocationRemote()
        val localNamesRemote = locationRemote.localNames

        // Act
        val locationData = LocationModelData.remoteToData(
            remote = locationRemote
        )
        val localNamesData = locationData?.localNames

        // Assert
        assertNotNull(locationData)

        assertNotNull(localNamesRemote)
        assertNotNull(localNamesData)
        assertEquals(localNamesRemote.af, localNamesData.af)
        assertEquals(localNamesRemote.sq, localNamesData.sq)
        assertEquals(localNamesRemote.ar, localNamesData.ar)
        assertEquals(localNamesRemote.az, localNamesData.az)
        assertEquals(localNamesRemote.bg, localNamesData.bg)
        assertEquals(localNamesRemote.ca, localNamesData.ca)
        assertEquals(localNamesRemote.cs, localNamesData.cs)
        assertEquals(localNamesRemote.da, localNamesData.da)
        assertEquals(localNamesRemote.de, localNamesData.de)
        assertEquals(localNamesRemote.el, localNamesData.el)
        assertEquals(localNamesRemote.en, localNamesData.en)
        assertEquals(localNamesRemote.eu, localNamesData.eu)
        assertEquals(localNamesRemote.fa, localNamesData.fa)
        assertEquals(localNamesRemote.fi, localNamesData.fi)
        assertEquals(localNamesRemote.fr, localNamesData.fr)
        assertEquals(localNamesRemote.gl, localNamesData.gl)
        assertEquals(localNamesRemote.he, localNamesData.he)
        assertEquals(localNamesRemote.hi, localNamesData.hi)
        assertEquals(localNamesRemote.hr, localNamesData.hr)
        assertEquals(localNamesRemote.hu, localNamesData.hu)
        assertEquals(localNamesRemote.id, localNamesData.id)
        assertEquals(localNamesRemote.it, localNamesData.it)
        assertEquals(localNamesRemote.ja, localNamesData.ja)
        assertEquals(localNamesRemote.ko, localNamesData.ko)
        assertEquals(localNamesRemote.lv, localNamesData.lv)
        assertEquals(localNamesRemote.lt, localNamesData.lt)
        assertEquals(localNamesRemote.mk, localNamesData.mk)
        assertEquals(localNamesRemote.no, localNamesData.no)
        assertEquals(localNamesRemote.nl, localNamesData.nl)
        assertEquals(localNamesRemote.pl, localNamesData.pl)
        assertEquals(localNamesRemote.pt, localNamesData.pt)
        assertEquals(localNamesRemote.ro, localNamesData.ro)
        assertEquals(localNamesRemote.ru, localNamesData.ru)
        assertEquals(localNamesRemote.sv, localNamesData.sv)
        assertEquals(localNamesRemote.sk, localNamesData.sk)
        assertEquals(localNamesRemote.sl, localNamesData.sl)
        assertEquals(localNamesRemote.es, localNamesData.es)
        assertEquals(localNamesRemote.sr, localNamesData.sr)
        assertEquals(localNamesRemote.th, localNamesData.th)
        assertEquals(localNamesRemote.tr, localNamesData.tr)
        assertEquals(localNamesRemote.uk, localNamesData.uk)
        assertEquals(localNamesRemote.vi, localNamesData.vi)
        assertEquals(localNamesRemote.zh, localNamesData.zh)
        assertEquals(localNamesRemote.zu, localNamesData.zu)

        assertEquals(locationRemote.lat, locationData.latitude)
        assertEquals(locationRemote.lon, locationData.longitude)
        assertEquals(locationRemote.name, locationData.name)
        assertEquals(locationRemote.country, locationData.country)
        assertEquals(locationRemote.state, locationData.state)
        assertNull(locationData.lastVisitedTimestampEpochSeconds)
        assertNull(locationData.order)
    }

    @Test
    fun remoteToLocal_shouldConvertCorrectly() {
        // Arrange
        val locationRemote = createTestLocationRemote()
        val localNamesRemote = locationRemote.localNames

        // Act
        val locationLocal = LocationModelData.remoteToLocal(
            remote = locationRemote
        )
        val localNamesLocal = locationLocal.localNames

        // Assert
        assertNotNull(locationLocal)

        assertNotNull(localNamesRemote)
        assertNotNull(localNamesLocal)
        assertEquals(localNamesRemote.af, localNamesLocal.af)
        assertEquals(localNamesRemote.sq, localNamesLocal.sq)
        assertEquals(localNamesRemote.ar, localNamesLocal.ar)
        assertEquals(localNamesRemote.az, localNamesLocal.az)
        assertEquals(localNamesRemote.bg, localNamesLocal.bg)
        assertEquals(localNamesRemote.ca, localNamesLocal.ca)
        assertEquals(localNamesRemote.cs, localNamesLocal.cs)
        assertEquals(localNamesRemote.da, localNamesLocal.da)
        assertEquals(localNamesRemote.de, localNamesLocal.de)
        assertEquals(localNamesRemote.el, localNamesLocal.el)
        assertEquals(localNamesRemote.en, localNamesLocal.en)
        assertEquals(localNamesRemote.eu, localNamesLocal.eu)
        assertEquals(localNamesRemote.fa, localNamesLocal.fa)
        assertEquals(localNamesRemote.fi, localNamesLocal.fi)
        assertEquals(localNamesRemote.fr, localNamesLocal.fr)
        assertEquals(localNamesRemote.gl, localNamesLocal.gl)
        assertEquals(localNamesRemote.he, localNamesLocal.he)
        assertEquals(localNamesRemote.hi, localNamesLocal.hi)
        assertEquals(localNamesRemote.hr, localNamesLocal.hr)
        assertEquals(localNamesRemote.hu, localNamesLocal.hu)
        assertEquals(localNamesRemote.id, localNamesLocal.id)
        assertEquals(localNamesRemote.it, localNamesLocal.it)
        assertEquals(localNamesRemote.ja, localNamesLocal.ja)
        assertEquals(localNamesRemote.ko, localNamesLocal.ko)
        assertEquals(localNamesRemote.lv, localNamesLocal.lv)
        assertEquals(localNamesRemote.lt, localNamesLocal.lt)
        assertEquals(localNamesRemote.mk, localNamesLocal.mk)
        assertEquals(localNamesRemote.no, localNamesLocal.no)
        assertEquals(localNamesRemote.nl, localNamesLocal.nl)
        assertEquals(localNamesRemote.pl, localNamesLocal.pl)
        assertEquals(localNamesRemote.pt, localNamesLocal.pt)
        assertEquals(localNamesRemote.ro, localNamesLocal.ro)
        assertEquals(localNamesRemote.ru, localNamesLocal.ru)
        assertEquals(localNamesRemote.sv, localNamesLocal.sv)
        assertEquals(localNamesRemote.sk, localNamesLocal.sk)
        assertEquals(localNamesRemote.sl, localNamesLocal.sl)
        assertEquals(localNamesRemote.es, localNamesLocal.es)
        assertEquals(localNamesRemote.sr, localNamesLocal.sr)
        assertEquals(localNamesRemote.th, localNamesLocal.th)
        assertEquals(localNamesRemote.tr, localNamesLocal.tr)
        assertEquals(localNamesRemote.uk, localNamesLocal.uk)
        assertEquals(localNamesRemote.vi, localNamesLocal.vi)
        assertEquals(localNamesRemote.zh, localNamesLocal.zh)
        assertEquals(localNamesRemote.zu, localNamesLocal.zu)

        assertEquals(locationRemote.lat, locationLocal.latitude)
        assertEquals(locationRemote.lon, locationLocal.longitude)
        assertEquals(locationRemote.name, locationLocal.name)
        assertEquals(locationRemote.country, locationLocal.country)
        assertEquals(locationRemote.state, locationLocal.state)
        assertNull(locationLocal.lastVisitedTimestampEpochSeconds)
        assertNull(locationLocal.order)
    }

    @Test
    fun languageTypeEnglish_shouldLocalizeCorrectly() {
        testLocalization(
            languageType = NBLanguageType.ENGLISH
        )
    }

    @Test
    fun languageTypeGerman_shouldLocalizeCorrectly() {
        testLocalization(
            languageType = NBLanguageType.GERMAN
        )
    }

    private fun testLocalization(
        languageType: NBLanguageType
    ) {
        // Arrange
        val locale = when (languageType) {
            NBLanguageType.ENGLISH -> Locale.ENGLISH
            NBLanguageType.GERMAN -> Locale.GERMAN
        }
        setLocale(locale)

        val name = "name"
        val localNamesDe = "de"
        val localNamesEn = "en"
        val country = "country"

        val localName = when (languageType) {
            NBLanguageType.ENGLISH -> localNamesEn
            NBLanguageType.GERMAN -> localNamesDe
        }
        val localNameAndCountry = "$localName, $country"
        val nameAndCountry = "$name, $country"

        // Act
        val locationNull = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = null
        )
        val locationCountry = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = country
        )
        val locationLocalNames = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null
        )
        val locationLocalNamesAndCountry = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country
        )
        val locationName = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = null
        )
        val locationNameAndCountry = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = country
        )
        val locationNameAndLocalNames = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null
        )
        val locationAll = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country
        )

        // Assert
        assertNullOrEmpty(locationNull.localizedName.asString(context))
        assertNullOrEmpty(locationCountry.localizedName.asString(context))
        assertEquals(
            localName,
            locationLocalNames.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationLocalNamesAndCountry.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationName.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationNameAndCountry.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationNameAndLocalNames.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationAll.localizedName.asString(context)
        )

        assertNullOrEmpty(locationNull.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(locationCountry.localizedNameAndCountry.asString(context))
        assertEquals(
            localName,
            locationLocalNames.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationLocalNamesAndCountry.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            name,
            locationName.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            nameAndCountry,
            locationNameAndCountry.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localName,
            locationNameAndLocalNames.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationAll.localizedNameAndCountry.asString(context)
        )
    }

    private fun createTestLocationData(
        name: String? = "name",
        localNamesDe: String? = "de",
        localNamesEn: String? = "en",
        country: String? = "country"
    ): LocationModelData {
        return LocationModelData(
            latitude = 1.0,
            longitude = 2.0,
            name = name,
            localNames = LocalNamesModelData(
                af = "af",
                sq = "sq",
                ar = "ar",
                az = "az",
                bg = "bg",
                ca = "ca",
                cs = "cs",
                da = "da",
                de = localNamesDe,
                el = "el",
                en = localNamesEn,
                eu = "eu",
                fa = "fa",
                fi = "fi",
                fr = "fr",
                gl = "gl",
                he = "he",
                hi = "hi",
                hr = "hr",
                hu = "hu",
                id = "id",
                it = "it",
                ja = "ja",
                ko = "ko",
                lv = "lv",
                lt = "lt",
                mk = "mk",
                no = "no",
                nl = "nl",
                pl = "pl",
                pt = "pt",
                ro = "ro",
                ru = "ru",
                sv = "sv",
                sk = "sk",
                sl = "sl",
                es = "es",
                sr = "sr",
                th = "th",
                tr = "tr",
                uk = "uk",
                vi = "vi",
                zh = "zh",
                zu = "zu"
            ),
            country = country,
            state = "state",
            lastVisitedTimestampEpochSeconds = 3,
            order = 4
        )
    }

    private fun createTestLocationLocal(): LocationModelLocal {
        return LocationModelLocal(
            latitude = 1.0,
            longitude = 2.0,
            name = "name",
            localNames = LocalNamesModelLocal(
                af = "af",
                sq = "sq",
                ar = "ar",
                az = "az",
                bg = "bg",
                ca = "ca",
                cs = "cs",
                da = "da",
                de = "de",
                el = "el",
                en = "en",
                eu = "eu",
                fa = "fa",
                fi = "fi",
                fr = "fr",
                gl = "gl",
                he = "he",
                hi = "hi",
                hr = "hr",
                hu = "hu",
                id = "id",
                it = "it",
                ja = "ja",
                ko = "ko",
                lv = "lv",
                lt = "lt",
                mk = "mk",
                no = "no",
                nl = "nl",
                pl = "pl",
                pt = "pt",
                ro = "ro",
                ru = "ru",
                sv = "sv",
                sk = "sk",
                sl = "sl",
                es = "es",
                sr = "sr",
                th = "th",
                tr = "tr",
                uk = "uk",
                vi = "vi",
                zh = "zh",
                zu = "zu"
            ),
            country = "country",
            state = "state",
            lastVisitedTimestampEpochSeconds = 3,
            order = 4
        )
    }

    private fun createTestLocationRemote(): LocationModelRemote {
        return LocationModelRemote(
            name = "name",
            localNames = LocalNamesModelRemote(
                af = "af",
                sq = "sq",
                ar = "ar",
                az = "az",
                bg = "bg",
                ca = "ca",
                cs = "cs",
                da = "da",
                de = "de",
                el = "el",
                en = "en",
                eu = "eu",
                fa = "fa",
                fi = "fi",
                fr = "fr",
                gl = "gl",
                he = "he",
                hi = "hi",
                hr = "hr",
                hu = "hu",
                id = "id",
                it = "it",
                ja = "ja",
                ko = "ko",
                lv = "lv",
                lt = "lt",
                mk = "mk",
                no = "no",
                nl = "nl",
                pl = "pl",
                pt = "pt",
                ro = "ro",
                ru = "ru",
                sv = "sv",
                sk = "sk",
                sl = "sl",
                es = "es",
                sr = "sr",
                th = "th",
                tr = "tr",
                uk = "uk",
                vi = "vi",
                zh = "zh",
                zu = "zu",
                ascii = "ascii",
                featureName = "featureName"
            ),
            lat = 1.0,
            lon = 2.0,
            country = "country",
            state = "state"
        )
    }

}