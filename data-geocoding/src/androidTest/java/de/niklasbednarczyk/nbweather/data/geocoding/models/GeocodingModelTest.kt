package de.niklasbednarczyk.nbweather.data.geocoding.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocalNamesModelRemote
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.test.data.localremote.NBLocalRemoteModelTest
import org.junit.Test
import java.util.Locale
import kotlin.test.assertEquals

class GeocodingModelTest : NBLocalRemoteModelTest {

    @Test
    fun localToData_shouldLocalizeBasedOnLanguageType() {
        testData(
            createTestLocation = ::createTestLocationLocal,
            toAct = LocationModelData::localToData
        )
    }

    @Test
    fun remoteToData_shouldLocalizeBasedOnLanguageType() {
        testData(
            createTestLocation = ::createTestLocationRemote,
            toAct = LocationModelData::remoteToData
        )
    }

    private fun <T> testData(
        createTestLocation: (name: String?, country: String?, state: String?, de: String?, en: String?) -> T,
        toAct: (arrangeLocation: T?) -> LocationModelData?,
    ) {
        testDataWithLocale(
            locale = Locale.ENGLISH,
            createTestLocation = createTestLocation,
            toAct = toAct
        )
        testDataWithLocale(
            locale = Locale.GERMAN,
            createTestLocation = createTestLocation,
            toAct = toAct
        )
    }

    private fun <T> testDataWithLocale(
        locale: Locale,
        createTestLocation: (name: String?, country: String?, state: String?, de: String?, en: String?) -> T,
        toAct: (arrangeLocation: T?) -> LocationModelData?,
    ) {
        // Arrange
        setLocale(locale)
        val localNameDe = "de"
        val localNameEn = "en"
        val expectedLocalName = when (locale.language) {
            Locale("de").language -> localNameDe
            Locale("en").language -> localNameEn
            else -> throw RuntimeException("Tested unused locale")
        }

        val arrangeNull = createTestLocation(null, null, null, null, null)

        val arrangeLocalizedName1 = createTestLocation("name", null, null, null, null)
        val arrangeLocalizedName2 = createTestLocation("name", null, null, localNameDe, localNameEn)

        val arrangeStateAndCountry1 = createTestLocation(null, "country", "state", null, null)
        val arrangeStateAndCountry2 = createTestLocation(null, "country", null, null, null)
        val arrangeStateAndCountry3 = createTestLocation(null, null, "state", null, null)

        val arrangeLocalizedNameAndCountry1 =
            createTestLocation(null, "country", null, localNameDe, localNameEn)
        val arrangeLocalizedNameAndCountry2 =
            createTestLocation(null, null, null, localNameDe, localNameEn)
        val arrangeLocalizedNameAndCountry3 = createTestLocation(null, "country", null, null, null)

        // Act
        val actNull = toAct(arrangeNull)

        val actLocalizedName1 = toAct(arrangeLocalizedName1)
        val actLocalizedName2 = toAct(arrangeLocalizedName2)

        val actStateAndCountry1 = toAct(arrangeStateAndCountry1)
        val actStateAndCountry2 = toAct(arrangeStateAndCountry2)
        val actStateAndCountry3 = toAct(arrangeStateAndCountry3)

        val actLocalizedNameAndCountry1 = toAct(arrangeLocalizedNameAndCountry1)
        val actLocalizedNameAndCountry2 = toAct(arrangeLocalizedNameAndCountry2)
        val actLocalizedNameAndCountry3 = toAct(arrangeLocalizedNameAndCountry3)


        // Assert
        assertNullOrEmpty(actNull?.localizedName.asString(context))
        assertEquals("name", actLocalizedName1?.localizedName.asString(context))
        assertEquals(expectedLocalName, actLocalizedName2?.localizedName.asString(context))

        assertNullOrEmpty(actNull?.stateAndCountry.asString(context))
        assertEquals("state, country", actStateAndCountry1?.stateAndCountry.asString(context))
        assertEquals("country", actStateAndCountry2?.stateAndCountry.asString(context))
        assertNullOrEmpty(actStateAndCountry3?.stateAndCountry.asString(context))

        assertNullOrEmpty(actNull?.localizedNameAndCountry.asString(context))
        assertEquals(
            "$expectedLocalName, country",
            actLocalizedNameAndCountry1?.localizedNameAndCountry.asString(context)
        )
        assertEquals(
            expectedLocalName,
            actLocalizedNameAndCountry2?.localizedNameAndCountry.asString(context)
        )
        assertNullOrEmpty(actLocalizedNameAndCountry3?.localizedNameAndCountry.asString(context))
    }

    private fun createTestLocationLocal(
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


    private fun createTestLocationRemote(
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