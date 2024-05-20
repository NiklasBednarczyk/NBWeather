package de.niklasbednarczyk.nbweather.feature.search.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.image.NBImages
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SearchOverviewModelsTest : NBTest {

    @Test
    fun location_flag_shouldConvertCorrectly() {
        // Arrange
        val expectedFlagCountryDE = NBImages.CountryFlagGermany

        // Act
        val locationCountryNull = createTestLocation(
            country = null
        )
        val locationCountryDE = createTestLocation(
            country = "DE"
        )

        // Assert
        assertNull(locationCountryNull.flag)

        assertEquals(expectedFlagCountryDE, locationCountryDE.flag)
    }

    @Test
    fun location_stateAndCountry_shouldConvertCorrectly() {
        // Arrange
        val countryWithoutFullName = "country"
        val countryWithFullName = "DE"
        val state = "state"

        val fullName = NBString.ResString(R.string.common_country_germany).asString(context)

        val stateAndCountryWithoutFullName = "$state, $countryWithoutFullName"
        val stateAndCountryWithFullName = "$state, $fullName"

        // Act
        val locationNull = createTestLocation(
            country = null,
            state = null
        )
        val locationState = createTestLocation(
            country = null,
            state = state
        )
        val locationCountryWithoutFullName = createTestLocation(
            country = countryWithoutFullName,
            state = null
        )
        val locationStateAndCountryWithoutFullName = createTestLocation(
            country = countryWithoutFullName,
            state = state
        )
        val locationCountryWithFullName = createTestLocation(
            country = countryWithFullName,
            state = null
        )
        val locationStateAndCountryWithFullName = createTestLocation(
            country = countryWithFullName,
            state = state
        )

        // Assert
        assertNullOrEmpty(locationNull.stateAndCountry.asString(context))
        assertNullOrEmpty(locationState.stateAndCountry.asString(context))
        assertEquals(
            countryWithoutFullName,
            locationCountryWithoutFullName.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountryWithoutFullName,
            locationStateAndCountryWithoutFullName.stateAndCountry.asString(context)
        )
        assertEquals(
            fullName,
            locationCountryWithFullName.stateAndCountry.asString(context)
        )
        assertEquals(
            stateAndCountryWithFullName,
            locationStateAndCountryWithFullName.stateAndCountry.asString(context)
        )
    }

    private fun createTestLocation(
        country: String?,
        state: String? = "state"
    ): SearchOverviewLocationModel {
        return SearchOverviewLocationModel(
            latitude = 1.0,
            longitude = 2.0,
            localizedName = NBString.Value.from("localizedName"),
            country = country,
            state = state
        )
    }

}