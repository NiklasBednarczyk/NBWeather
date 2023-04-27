package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.test.data.localremote.NBLocalRemoteModelTest
import org.junit.Test
import kotlin.test.assertEquals

class GeocodingModelTest : NBLocalRemoteModelTest {

    @Test
    fun localToData_shouldLocalizeBasedOnLanguageType() {
        toDataTest(
            testLocation = ::testLocationLocal,
            toAct = LocationModelData::localToData
        )
    }

    @Test
    fun remoteToData_shouldLocalizeBasedOnLanguageType() {
        toDataTest(
            testLocation = ::testLocationRemote,
            toAct = LocationModelData::remoteToData
        )
    }

    private fun <T> toDataTest(
        testLocation: (name: String?, country: String?, state: String?, de: String?, en: String?) -> T,
        toAct: (arrangeLocation: T?, language: NBLanguageType) -> LocationModelData?
    ) {
        // Arrange
        val toActPair: (T) -> Pair<LocationModelData, LocationModelData> = { arrangeLocation ->
            val defaultLocation = LocationModelData(
                localizedName = null,
                stateAndCountry = null,
                localizedNameAndCountry = null,
                latitude = -1.0,
                longitude = -1.0
            )
            val locationDe = toAct(arrangeLocation, NBLanguageType.GERMAN) ?: defaultLocation
            val locationEn = toAct(arrangeLocation, NBLanguageType.ENGLISH) ?: defaultLocation
            Pair(locationDe, locationEn)
        }
        val arrangeNull = testLocation(null, null, null, null, null)

        val arrangeLocalizedName1 = testLocation("name", null, null, null, null)
        val arrangeLocalizedName2 = testLocation("name", null, null, "de", "en")

        val arrangeStateAndCountry1 = testLocation(null, "country", "state", null, null)
        val arrangeStateAndCountry2 = testLocation(null, "country", null, null, null)
        val arrangeStateAndCountry3 = testLocation(null, null, "state", null, null)

        val arrangeLocalizedNameAndCountry1 = testLocation(null, "country", null, "de", "en")
        val arrangeLocalizedNameAndCountry2 = testLocation(null, null, null, "de", "en")
        val arrangeLocalizedNameAndCountry3 = testLocation(null, "country", null, null, null)

        // Act
        val actNull = toActPair(arrangeNull)

        val actLocalizedName1 = toActPair(arrangeLocalizedName1)
        val actLocalizedName2 = toActPair(arrangeLocalizedName2)

        val actStateAndCountry1 = toActPair(arrangeStateAndCountry1)
        val actStateAndCountry2 = toActPair(arrangeStateAndCountry2)
        val actStateAndCountry3 = toActPair(arrangeStateAndCountry3)

        val actLocalizedNameAndCountry1 = toActPair(arrangeLocalizedNameAndCountry1)
        val actLocalizedNameAndCountry2 = toActPair(arrangeLocalizedNameAndCountry2)
        val actLocalizedNameAndCountry3 = toActPair(arrangeLocalizedNameAndCountry3)


        // Assert
        //      Localized name
        assertNullOrEmpty(actNull.first.localizedName.asString(context))
        assertNullOrEmpty(actNull.second.localizedName.asString(context))

        assertEquals("name", actLocalizedName1.first.localizedName.asString(context))
        assertEquals("name", actLocalizedName1.second.localizedName.asString(context))

        assertEquals("de", actLocalizedName2.first.localizedName.asString(context))
        assertEquals("en", actLocalizedName2.second.localizedName.asString(context))

        //      State and country
        assertNullOrEmpty(actNull.first.stateAndCountry.asString(context))
        assertNullOrEmpty(actNull.second.stateAndCountry.asString(context))

        assertEquals("state, country", actStateAndCountry1.first.stateAndCountry.asString(context))
        assertEquals("state, country", actStateAndCountry1.second.stateAndCountry.asString(context))

        assertEquals("country", actStateAndCountry2.first.stateAndCountry.asString(context))
        assertEquals("country", actStateAndCountry2.second.stateAndCountry.asString(context))

        assertNullOrEmpty(actStateAndCountry3.first.stateAndCountry.asString(context))
        assertNullOrEmpty(actStateAndCountry3.second.stateAndCountry.asString(context))

        //      Localized name and country
        assertNullOrEmpty(actNull.first.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(actNull.second.localizedNameAndCountry.asString(context))

        assertEquals(
            "de, country",
            actLocalizedNameAndCountry1.first.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            "en, country",
            actLocalizedNameAndCountry1.second.localizedNameAndCountry.asString(context)
        )

        assertEquals(
            "de",
            actLocalizedNameAndCountry2.first.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            "en",
            actLocalizedNameAndCountry2.second.localizedNameAndCountry.asString(context)
        )

        assertNullOrEmpty(actLocalizedNameAndCountry3.first.localizedNameAndCountry.asString(context))
        assertNullOrEmpty(
            actLocalizedNameAndCountry3.second.localizedNameAndCountry.asString(
                context
            )
        )
    }

    private fun testLocationLocal(
        name: String?,
        country: String?,
        state: String?,
        de: String?,
        en: String?
    ): LocationModelLocal = LocationModelLocal(
        name = name,
        localNames = LocalNamesModelLocal(
            af = null,
            sq = null,
            ar = null,
            az = null,
            bg = null,
            ca = null,
            cs = null,
            da = null,
            de = de,
            el = null,
            en = en,
            eu = null,
            fa = null,
            fi = null,
            fr = null,
            gl = null,
            he = null,
            hi = null,
            hr = null,
            hu = null,
            id = null,
            it = null,
            ja = null,
            ko = null,
            lv = null,
            lt = null,
            mk = null,
            no = null,
            nl = null,
            pl = null,
            pt = null,
            ro = null,
            ru = null,
            sv = null,
            sk = null,
            sl = null,
            es = null,
            sr = null,
            th = null,
            tr = null,
            uk = null,
            vi = null,
            zh = null,
            zu = null
        ),
        country = country,
        state = state,
        latitude = 0.0,
        longitude = 0.0,
        lastVisitedTimestampEpochSeconds = null
    )


    private fun testLocationRemote(
        name: String?,
        country: String?,
        state: String?,
        de: String?,
        en: String?
    ): LocationModelRemote = LocationModelRemote(
        lat = 0.0,
        lon = 0.0,
        name = name,
        country = country,
        state = state,
        localNames = LocalNamesModelRemote(
            af = null,
            sq = null,
            ar = null,
            az = null,
            bg = null,
            ca = null,
            cs = null,
            da = null,
            de = de,
            el = null,
            en = en,
            eu = null,
            fa = null,
            fi = null,
            fr = null,
            gl = null,
            he = null,
            hi = null,
            hr = null,
            hu = null,
            id = null,
            it = null,
            ja = null,
            ko = null,
            lv = null,
            lt = null,
            mk = null,
            no = null,
            nl = null,
            pl = null,
            pt = null,
            ro = null,
            ru = null,
            sv = null,
            sk = null,
            sl = null,
            es = null,
            sr = null,
            th = null,
            tr = null,
            uk = null,
            vi = null,
            zh = null,
            zu = null,
            ascii = null,
            featureName = null
        )
    )

}