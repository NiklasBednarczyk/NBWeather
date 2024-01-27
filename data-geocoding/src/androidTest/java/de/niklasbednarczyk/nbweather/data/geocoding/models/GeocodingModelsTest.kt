package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.language.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GeocodingModelsTest : NBTest {

    @Test
    fun dataToLocal_shouldConvertCorrectly() {
        // Arrange
        val dataLocation = createTestLocationData()
        val dataLocalNames = dataLocation.localNames

        // Act
        val localLocation = LocationModelData.dataToLocal(
            data = dataLocation
        )
        val localLocalNames = localLocation.localNames

        // Assert
        assertNotNull(dataLocalNames)
        assertNotNull(localLocalNames)
        assertEquals(dataLocalNames.af, localLocalNames.af)
        assertEquals(dataLocalNames.sq, localLocalNames.sq)
        assertEquals(dataLocalNames.ar, localLocalNames.ar)
        assertEquals(dataLocalNames.az, localLocalNames.az)
        assertEquals(dataLocalNames.bg, localLocalNames.bg)
        assertEquals(dataLocalNames.ca, localLocalNames.ca)
        assertEquals(dataLocalNames.cs, localLocalNames.cs)
        assertEquals(dataLocalNames.da, localLocalNames.da)
        assertEquals(dataLocalNames.de, localLocalNames.de)
        assertEquals(dataLocalNames.el, localLocalNames.el)
        assertEquals(dataLocalNames.en, localLocalNames.en)
        assertEquals(dataLocalNames.eu, localLocalNames.eu)
        assertEquals(dataLocalNames.fa, localLocalNames.fa)
        assertEquals(dataLocalNames.fi, localLocalNames.fi)
        assertEquals(dataLocalNames.fr, localLocalNames.fr)
        assertEquals(dataLocalNames.gl, localLocalNames.gl)
        assertEquals(dataLocalNames.he, localLocalNames.he)
        assertEquals(dataLocalNames.hi, localLocalNames.hi)
        assertEquals(dataLocalNames.hr, localLocalNames.hr)
        assertEquals(dataLocalNames.hu, localLocalNames.hu)
        assertEquals(dataLocalNames.id, localLocalNames.id)
        assertEquals(dataLocalNames.it, localLocalNames.it)
        assertEquals(dataLocalNames.ja, localLocalNames.ja)
        assertEquals(dataLocalNames.ko, localLocalNames.ko)
        assertEquals(dataLocalNames.lv, localLocalNames.lv)
        assertEquals(dataLocalNames.lt, localLocalNames.lt)
        assertEquals(dataLocalNames.mk, localLocalNames.mk)
        assertEquals(dataLocalNames.no, localLocalNames.no)
        assertEquals(dataLocalNames.nl, localLocalNames.nl)
        assertEquals(dataLocalNames.pl, localLocalNames.pl)
        assertEquals(dataLocalNames.pt, localLocalNames.pt)
        assertEquals(dataLocalNames.ro, localLocalNames.ro)
        assertEquals(dataLocalNames.ru, localLocalNames.ru)
        assertEquals(dataLocalNames.sv, localLocalNames.sv)
        assertEquals(dataLocalNames.sk, localLocalNames.sk)
        assertEquals(dataLocalNames.sl, localLocalNames.sl)
        assertEquals(dataLocalNames.es, localLocalNames.es)
        assertEquals(dataLocalNames.sr, localLocalNames.sr)
        assertEquals(dataLocalNames.th, localLocalNames.th)
        assertEquals(dataLocalNames.tr, localLocalNames.tr)
        assertEquals(dataLocalNames.uk, localLocalNames.uk)
        assertEquals(dataLocalNames.vi, localLocalNames.vi)
        assertEquals(dataLocalNames.zh, localLocalNames.zh)
        assertEquals(dataLocalNames.zu, localLocalNames.zu)

        assertEquals(dataLocation.latitude, localLocation.latitude)
        assertEquals(dataLocation.longitude, localLocation.longitude)
        assertEquals(dataLocation.name, localLocation.name)
        assertEquals(dataLocation.country, localLocation.country)
        assertEquals(dataLocation.state, localLocation.state)
        assertEquals(
            dataLocation.lastVisitedTimestampEpochSeconds,
            localLocation.lastVisitedTimestampEpochSeconds
        )
        assertEquals(dataLocation.order, localLocation.order)
    }

    @Test
    fun localToData_shouldConvertCorrectly() {
        // Arrange
        val localLocation = createTestLocationLocal()
        val localLocalNames = localLocation.localNames

        // Act
        val dataLocation = LocationModelData.localToData(
            local = localLocation
        )
        val dataLocalNames = dataLocation?.localNames

        // Assert
        assertNotNull(dataLocation)

        assertNotNull(localLocalNames)
        assertNotNull(dataLocalNames)
        assertEquals(localLocalNames.af, dataLocalNames.af)
        assertEquals(localLocalNames.sq, dataLocalNames.sq)
        assertEquals(localLocalNames.ar, dataLocalNames.ar)
        assertEquals(localLocalNames.az, dataLocalNames.az)
        assertEquals(localLocalNames.bg, dataLocalNames.bg)
        assertEquals(localLocalNames.ca, dataLocalNames.ca)
        assertEquals(localLocalNames.cs, dataLocalNames.cs)
        assertEquals(localLocalNames.da, dataLocalNames.da)
        assertEquals(localLocalNames.de, dataLocalNames.de)
        assertEquals(localLocalNames.el, dataLocalNames.el)
        assertEquals(localLocalNames.en, dataLocalNames.en)
        assertEquals(localLocalNames.eu, dataLocalNames.eu)
        assertEquals(localLocalNames.fa, dataLocalNames.fa)
        assertEquals(localLocalNames.fi, dataLocalNames.fi)
        assertEquals(localLocalNames.fr, dataLocalNames.fr)
        assertEquals(localLocalNames.gl, dataLocalNames.gl)
        assertEquals(localLocalNames.he, dataLocalNames.he)
        assertEquals(localLocalNames.hi, dataLocalNames.hi)
        assertEquals(localLocalNames.hr, dataLocalNames.hr)
        assertEquals(localLocalNames.hu, dataLocalNames.hu)
        assertEquals(localLocalNames.id, dataLocalNames.id)
        assertEquals(localLocalNames.it, dataLocalNames.it)
        assertEquals(localLocalNames.ja, dataLocalNames.ja)
        assertEquals(localLocalNames.ko, dataLocalNames.ko)
        assertEquals(localLocalNames.lv, dataLocalNames.lv)
        assertEquals(localLocalNames.lt, dataLocalNames.lt)
        assertEquals(localLocalNames.mk, dataLocalNames.mk)
        assertEquals(localLocalNames.no, dataLocalNames.no)
        assertEquals(localLocalNames.nl, dataLocalNames.nl)
        assertEquals(localLocalNames.pl, dataLocalNames.pl)
        assertEquals(localLocalNames.pt, dataLocalNames.pt)
        assertEquals(localLocalNames.ro, dataLocalNames.ro)
        assertEquals(localLocalNames.ru, dataLocalNames.ru)
        assertEquals(localLocalNames.sv, dataLocalNames.sv)
        assertEquals(localLocalNames.sk, dataLocalNames.sk)
        assertEquals(localLocalNames.sl, dataLocalNames.sl)
        assertEquals(localLocalNames.es, dataLocalNames.es)
        assertEquals(localLocalNames.sr, dataLocalNames.sr)
        assertEquals(localLocalNames.th, dataLocalNames.th)
        assertEquals(localLocalNames.tr, dataLocalNames.tr)
        assertEquals(localLocalNames.uk, dataLocalNames.uk)
        assertEquals(localLocalNames.vi, dataLocalNames.vi)
        assertEquals(localLocalNames.zh, dataLocalNames.zh)
        assertEquals(localLocalNames.zu, dataLocalNames.zu)

        assertEquals(localLocation.latitude, dataLocation.latitude)
        assertEquals(localLocation.longitude, dataLocation.longitude)
        assertEquals(localLocation.name, dataLocation.name)
        assertEquals(localLocation.country, dataLocation.country)
        assertEquals(localLocation.state, dataLocation.state)
        assertEquals(
            localLocation.lastVisitedTimestampEpochSeconds,
            dataLocation.lastVisitedTimestampEpochSeconds
        )
        assertEquals(localLocation.order, dataLocation.order)
    }

    @Test
    fun remoteToData_shouldConvertCorrectly() {
        // Arrange
        val remoteLocation = createTestLocationRemote()
        val remoteLocalNames = remoteLocation.localNames

        // Act
        val dataLocation = LocationModelData.remoteToData(
            remote = remoteLocation
        )
        val dataLocalNames = dataLocation?.localNames

        // Assert
        assertNotNull(dataLocation)

        assertNotNull(remoteLocalNames)
        assertNotNull(dataLocalNames)
        assertEquals(remoteLocalNames.af, dataLocalNames.af)
        assertEquals(remoteLocalNames.sq, dataLocalNames.sq)
        assertEquals(remoteLocalNames.ar, dataLocalNames.ar)
        assertEquals(remoteLocalNames.az, dataLocalNames.az)
        assertEquals(remoteLocalNames.bg, dataLocalNames.bg)
        assertEquals(remoteLocalNames.ca, dataLocalNames.ca)
        assertEquals(remoteLocalNames.cs, dataLocalNames.cs)
        assertEquals(remoteLocalNames.da, dataLocalNames.da)
        assertEquals(remoteLocalNames.de, dataLocalNames.de)
        assertEquals(remoteLocalNames.el, dataLocalNames.el)
        assertEquals(remoteLocalNames.en, dataLocalNames.en)
        assertEquals(remoteLocalNames.eu, dataLocalNames.eu)
        assertEquals(remoteLocalNames.fa, dataLocalNames.fa)
        assertEquals(remoteLocalNames.fi, dataLocalNames.fi)
        assertEquals(remoteLocalNames.fr, dataLocalNames.fr)
        assertEquals(remoteLocalNames.gl, dataLocalNames.gl)
        assertEquals(remoteLocalNames.he, dataLocalNames.he)
        assertEquals(remoteLocalNames.hi, dataLocalNames.hi)
        assertEquals(remoteLocalNames.hr, dataLocalNames.hr)
        assertEquals(remoteLocalNames.hu, dataLocalNames.hu)
        assertEquals(remoteLocalNames.id, dataLocalNames.id)
        assertEquals(remoteLocalNames.it, dataLocalNames.it)
        assertEquals(remoteLocalNames.ja, dataLocalNames.ja)
        assertEquals(remoteLocalNames.ko, dataLocalNames.ko)
        assertEquals(remoteLocalNames.lv, dataLocalNames.lv)
        assertEquals(remoteLocalNames.lt, dataLocalNames.lt)
        assertEquals(remoteLocalNames.mk, dataLocalNames.mk)
        assertEquals(remoteLocalNames.no, dataLocalNames.no)
        assertEquals(remoteLocalNames.nl, dataLocalNames.nl)
        assertEquals(remoteLocalNames.pl, dataLocalNames.pl)
        assertEquals(remoteLocalNames.pt, dataLocalNames.pt)
        assertEquals(remoteLocalNames.ro, dataLocalNames.ro)
        assertEquals(remoteLocalNames.ru, dataLocalNames.ru)
        assertEquals(remoteLocalNames.sv, dataLocalNames.sv)
        assertEquals(remoteLocalNames.sk, dataLocalNames.sk)
        assertEquals(remoteLocalNames.sl, dataLocalNames.sl)
        assertEquals(remoteLocalNames.es, dataLocalNames.es)
        assertEquals(remoteLocalNames.sr, dataLocalNames.sr)
        assertEquals(remoteLocalNames.th, dataLocalNames.th)
        assertEquals(remoteLocalNames.tr, dataLocalNames.tr)
        assertEquals(remoteLocalNames.uk, dataLocalNames.uk)
        assertEquals(remoteLocalNames.vi, dataLocalNames.vi)
        assertEquals(remoteLocalNames.zh, dataLocalNames.zh)
        assertEquals(remoteLocalNames.zu, dataLocalNames.zu)

        assertEquals(remoteLocation.lat, dataLocation.latitude)
        assertEquals(remoteLocation.lon, dataLocation.longitude)
        assertEquals(remoteLocation.name, dataLocation.name)
        assertEquals(remoteLocation.country, dataLocation.country)
        assertEquals(remoteLocation.state, dataLocation.state)
        assertNull(dataLocation.lastVisitedTimestampEpochSeconds)
        assertNull(dataLocation.order)
    }

    @Test
    fun remoteToLocal_shouldConvertCorrectly() {
        // Arrange
        val remoteLocation = createTestLocationRemote(
            lat = 1.0,
            lon = 2.0
        )
        val remoteLocalNames = remoteLocation.localNames

        // Act
        val localLocation = LocationModelData.remoteToLocal(
            remote = remoteLocation,
            latitude = -1.0,
            longitude = -2.0,
            lastVisitedTimestampEpochSeconds = -3,
            order = -4
        )
        val localLocalNames = localLocation?.localNames

        // Assert
        assertNotNull(localLocation)

        assertNotNull(remoteLocalNames)
        assertNotNull(localLocalNames)
        assertEquals(remoteLocalNames.af, localLocalNames.af)
        assertEquals(remoteLocalNames.sq, localLocalNames.sq)
        assertEquals(remoteLocalNames.ar, localLocalNames.ar)
        assertEquals(remoteLocalNames.az, localLocalNames.az)
        assertEquals(remoteLocalNames.bg, localLocalNames.bg)
        assertEquals(remoteLocalNames.ca, localLocalNames.ca)
        assertEquals(remoteLocalNames.cs, localLocalNames.cs)
        assertEquals(remoteLocalNames.da, localLocalNames.da)
        assertEquals(remoteLocalNames.de, localLocalNames.de)
        assertEquals(remoteLocalNames.el, localLocalNames.el)
        assertEquals(remoteLocalNames.en, localLocalNames.en)
        assertEquals(remoteLocalNames.eu, localLocalNames.eu)
        assertEquals(remoteLocalNames.fa, localLocalNames.fa)
        assertEquals(remoteLocalNames.fi, localLocalNames.fi)
        assertEquals(remoteLocalNames.fr, localLocalNames.fr)
        assertEquals(remoteLocalNames.gl, localLocalNames.gl)
        assertEquals(remoteLocalNames.he, localLocalNames.he)
        assertEquals(remoteLocalNames.hi, localLocalNames.hi)
        assertEquals(remoteLocalNames.hr, localLocalNames.hr)
        assertEquals(remoteLocalNames.hu, localLocalNames.hu)
        assertEquals(remoteLocalNames.id, localLocalNames.id)
        assertEquals(remoteLocalNames.it, localLocalNames.it)
        assertEquals(remoteLocalNames.ja, localLocalNames.ja)
        assertEquals(remoteLocalNames.ko, localLocalNames.ko)
        assertEquals(remoteLocalNames.lv, localLocalNames.lv)
        assertEquals(remoteLocalNames.lt, localLocalNames.lt)
        assertEquals(remoteLocalNames.mk, localLocalNames.mk)
        assertEquals(remoteLocalNames.no, localLocalNames.no)
        assertEquals(remoteLocalNames.nl, localLocalNames.nl)
        assertEquals(remoteLocalNames.pl, localLocalNames.pl)
        assertEquals(remoteLocalNames.pt, localLocalNames.pt)
        assertEquals(remoteLocalNames.ro, localLocalNames.ro)
        assertEquals(remoteLocalNames.ru, localLocalNames.ru)
        assertEquals(remoteLocalNames.sv, localLocalNames.sv)
        assertEquals(remoteLocalNames.sk, localLocalNames.sk)
        assertEquals(remoteLocalNames.sl, localLocalNames.sl)
        assertEquals(remoteLocalNames.es, localLocalNames.es)
        assertEquals(remoteLocalNames.sr, localLocalNames.sr)
        assertEquals(remoteLocalNames.th, localLocalNames.th)
        assertEquals(remoteLocalNames.tr, localLocalNames.tr)
        assertEquals(remoteLocalNames.uk, localLocalNames.uk)
        assertEquals(remoteLocalNames.vi, localLocalNames.vi)
        assertEquals(remoteLocalNames.zh, localLocalNames.zh)
        assertEquals(remoteLocalNames.zu, localLocalNames.zu)

        assertEquals(-1.0, localLocation.latitude)
        assertNotEquals(remoteLocation.lat, localLocation.latitude)
        assertEquals(-2.0, localLocation.longitude)
        assertNotEquals(remoteLocation.lon, localLocation.longitude)
        assertEquals(remoteLocation.name, localLocation.name)
        assertEquals(remoteLocation.country, localLocation.country)
        assertEquals(remoteLocation.state, localLocation.state)
        assertEquals(-3, localLocation.lastVisitedTimestampEpochSeconds)
        assertEquals(-4, localLocation.order)
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
        val state = "state"

        val localName = when (languageType) {
            NBLanguageType.ENGLISH -> localNamesEn
            NBLanguageType.GERMAN -> localNamesDe
        }
        val localNameAndCountry = "$localName, $country"
        val nameAndCountry = "$name, $country"
        val stateAndCountry = "$state, $country"

        // Act
        val locationNull = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = null,
            state = null
        )
        val locationState = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = null,
            state = state
        )
        val locationCountry = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = country,
            state = null
        )
        val locationCountryAndState = createTestLocationData(
            name = null,
            localNamesDe = null,
            localNamesEn = null,
            country = country,
            state = state
        )
        val locationLocalNames = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null,
            state = null
        )
        val locationLocalNamesAndState = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null,
            state = state
        )
        val locationLocalNamesAndCountry = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country,
            state = null
        )
        val locationLocalNamesCountryAndState = createTestLocationData(
            name = null,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country,
            state = state
        )
        val locationName = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = null,
            state = null
        )
        val locationNameAndState = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = null,
            state = state
        )
        val locationNameAndCountry = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = country,
            state = null
        )
        val locationNameCountryAndState = createTestLocationData(
            name = name,
            localNamesDe = null,
            localNamesEn = null,
            country = country,
            state = state
        )
        val locationNameAndLocalNames = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null,
            state = null
        )
        val locationNameLocalNamesAndState = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = null,
            state = state
        )
        val locationNameLocalNamesAndCountry = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country,
            state = null
        )
        val locationAll = createTestLocationData(
            name = name,
            localNamesDe = localNamesDe,
            localNamesEn = localNamesEn,
            country = country,
            state = state
        )

        // Assert
        assertNullOrEmpty(locationNull.localizedName.asString(context))
        assertNullOrEmpty(locationState.localizedName.asString(context))
        assertNullOrEmpty(locationCountry.localizedName.asString(context))
        assertNullOrEmpty(locationCountryAndState.localizedName.asString(context))
        assertEquals(
            localName,
            locationLocalNames.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationLocalNamesAndState.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationLocalNamesAndCountry.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationLocalNamesCountryAndState.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationName.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationNameAndState.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationNameAndCountry.localizedName.asString(context)
        )
        assertEquals(
            name,
            locationNameCountryAndState.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationNameAndLocalNames.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationNameLocalNamesAndState.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationNameLocalNamesAndCountry.localizedName.asString(context)
        )
        assertEquals(
            localName,
            locationAll.localizedName.asString(context)
        )

        assertNullOrEmpty(locationNull.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(locationState.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(locationCountry.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(locationCountryAndState.localizedNameAndCountry.asString(context))
        assertEquals(
            localName,
            locationLocalNames.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localName,
            locationLocalNamesAndState.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationLocalNamesAndCountry.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationLocalNamesCountryAndState.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            name,
            locationName.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            name,
            locationNameAndState.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            nameAndCountry,
            locationNameAndCountry.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            nameAndCountry,
            locationNameCountryAndState.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localName,
            locationNameAndLocalNames.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localName,
            locationNameLocalNamesAndState.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationNameLocalNamesAndCountry.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            localNameAndCountry,
            locationAll.localizedNameAndCountry.asString(context)
        )

        assertNullOrEmpty(locationNull.stateAndCountry.asString(context))
        assertNullOrEmpty(locationState.stateAndCountry.asString(context))
        assertEquals(
            country,
            locationCountry.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountry,
            locationCountryAndState.stateAndCountry.asString(context)
        )
        assertNullOrEmpty(locationLocalNames.stateAndCountry.asString(context))
        assertNullOrEmpty(locationLocalNamesAndState.stateAndCountry.asString(context))
        assertEquals(
            country,
            locationLocalNamesAndCountry.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountry,
            locationLocalNamesCountryAndState.stateAndCountry.asString(context)
        )
        assertNullOrEmpty(locationName.stateAndCountry.asString(context))
        assertNullOrEmpty(locationNameAndState.stateAndCountry.asString(context))
        assertEquals(
            country,
            locationNameAndCountry.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountry,
            locationNameCountryAndState.stateAndCountry.asString(context)
        )
        assertNullOrEmpty(locationNameAndLocalNames.stateAndCountry.asString(context))
        assertNullOrEmpty(locationNameLocalNamesAndState.stateAndCountry.asString(context))
        assertEquals(
            country,
            locationNameLocalNamesAndCountry.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountry,
            locationAll.stateAndCountry.asString(context)
        )
    }

    private fun createTestLocationData(
        name: String? = "name",
        localNamesDe: String? = "de",
        localNamesEn: String? = "en",
        country: String? = "country",
        state: String? = "state"
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
            state = state,
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

    private fun createTestLocationRemote(
        lat: Double = 1.0,
        lon: Double = 2.0
    ): LocationModelRemote {
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
            lat = lat,
            lon = lon,
            country = "country",
            state = "state"
        )
    }

}