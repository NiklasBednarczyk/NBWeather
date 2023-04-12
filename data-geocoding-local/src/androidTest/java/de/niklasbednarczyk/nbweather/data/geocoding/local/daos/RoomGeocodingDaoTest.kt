package de.niklasbednarczyk.nbweather.data.geocoding.local.daos

import de.niklasbednarczyk.nbweather.data.geocoding.local.database.DatabaseGeocoding
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.test.data.localremote.local.daos.NBDaoTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RoomGeocodingDaoTest : NBDaoTest<DatabaseGeocoding, RoomGeocodingDao>() {

    override val databaseClass: Class<DatabaseGeocoding> = DatabaseGeocoding::class.java

    override fun getDao(): RoomGeocodingDao = db.geocodingDao()

    @Test
    fun getLocation_shouldFindLocationByCoordinates() {
        testGetItemQuery(
            keys = Triple(1.0, 2.0, 3.0),
            createArrange = { key ->
                createLocationModel(
                    latAndLong = key,
                    lastVisitedTimestampEpochSeconds = key.toLong()
                )
            },
            insert = subject::insertLocation,
            createAct = { key ->
                subject.getLocation(key, key)
            },
            assertAreEqual = { arrange, act ->
                assertEquals(arrange.latitude, act.latitude)
                assertEquals(arrange.longitude, act.longitude)
                assertEquals(
                    arrange.lastVisitedTimestampEpochSeconds,
                    act.lastVisitedTimestampEpochSeconds
                )
            }
        )
    }

    @Test
    fun getVisitedLocations_shouldOnlyContainLocationsWithTimestampInOrder() =
        runTest {
            // Arrange
            val location1 = createLocationModel(
                latAndLong = 1.0,
                lastVisitedTimestampEpochSeconds = 2L
            )
            val location2 = createLocationModel(
                latAndLong = 2.0,
                lastVisitedTimestampEpochSeconds = 1L
            )
            val location3 = createLocationModel(
                latAndLong = 3.0,
                lastVisitedTimestampEpochSeconds = 4L
            )
            val location4 = createLocationModel(
                latAndLong = 4.0,
                lastVisitedTimestampEpochSeconds = 3L
            )
            val location5 = createLocationModel(
                latAndLong = 5.0,
                lastVisitedTimestampEpochSeconds = null
            )

            subject.insertLocations(
                listOf(
                    location1,
                    location2,
                    location3,
                    location4,
                    location5
                )
            )

            // Act
            val locations = subject.getVisitedLocations().firstOrNull()

            // Assert
            assertNotNull(locations)
            assertListIsNotEmpty(locations)
            assertListHasSize(locations, 4)
            assertListsContainsItemInOrder(locations, location2, location1, location4, location3)
            assertListDoesNotContain(locations, location5)
        }

    @Test
    fun getCurrentLocation_shouldBeLocationWithHighestTimestamp() = runTest {
        // Arrange
        val location1 = createLocationModel(
            latAndLong = 1.0,
            lastVisitedTimestampEpochSeconds = 2L
        )
        val location2 = createLocationModel(
            latAndLong = 2.0,
            lastVisitedTimestampEpochSeconds = null
        )
        val location3 = createLocationModel(
            latAndLong = 3.0,
            lastVisitedTimestampEpochSeconds = 1L
        )

        subject.insertLocations(listOf(location1, location2, location3))

        // Act
        val currentLocation = subject.getCurrentLocation().firstOrNull()

        // Assert
        assertNotNull(currentLocation)
        assertEquals(currentLocation, location1)
    }

    @Test
    fun getCurrentLocation_shouldBeNullWhenOnlyLocationsWithoutTimestamps() = runTest {
        // Arrange
        val location = createLocationModel(
            latAndLong = 1.0,
            lastVisitedTimestampEpochSeconds = null
        )

        subject.insertLocation(location)

        // Act
        val currentLocation = subject.getCurrentLocation().firstOrNull()

        // Assert
        assertNull(currentLocation)
    }

    private fun createLocationModel(
        latAndLong: Double,
        lastVisitedTimestampEpochSeconds: Long?
    ) = LocationModelLocal(
        name = "",
        localNames = null,
        country = null,
        state = null,
        latitude = latAndLong,
        longitude = latAndLong,
        lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds
    )

}