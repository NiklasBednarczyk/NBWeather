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
    fun getVisitedLocations_shouldOnlyContainLocationsWithTimestampInOrder() = testScope.runTest {
            // Arrange
            val location1 = createLocationModel(
                latAndLong = 1.0,
                lastVisitedTimestampEpochSeconds = 2L,
                order = 1
            )
            val location2 = createLocationModel(
                latAndLong = 2.0,
                lastVisitedTimestampEpochSeconds = 1L,
                order = 2
            )
            val location3 = createLocationModel(
                latAndLong = 3.0,
                lastVisitedTimestampEpochSeconds = 4L,
                order = 3
            )
            val location4 = createLocationModel(
                latAndLong = 4.0,
                lastVisitedTimestampEpochSeconds = 3L,
                order = 4
            )
            val location5 = createLocationModel(
                latAndLong = 5.0,
                lastVisitedTimestampEpochSeconds = null,
                order = 5
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
            assertListsContainsItemInOrder(locations, location1, location2, location3, location4)
            assertListDoesNotContain(locations, location5)
        }

    @Test
    fun getCurrentLocation_shouldBeLocationWithHighestTimestamp() = testScope.runTest {
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
    fun getCurrentLocation_shouldBeNullWhenOnlyLocationsWithoutTimestamps() = testScope.runTest {
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
    
    @Test
    fun updateOrder_shouldUpdateOrder() = testScope.runTest { 
        // Arrange
        val location1Arrange = createLocationModel(
            latAndLong = 1.0,
            order = 1
        )
        val location2Arrange = createLocationModel(
            latAndLong = 2.0,
            order = null
        )

        subject.insertLocation(location1Arrange)
        subject.insertLocation(location2Arrange)

        // Act
        subject.updateOrder(1.0, 1.0, 2)
        subject.updateOrder(2.0, 2.0, 3)

        val location1Act = subject.getLocation(1.0, 1.0).firstOrNull()
        val location2Act = subject.getLocation(2.0, 2.0).firstOrNull()

        // Assert
        assertNotNull(location1Act)
        assertEquals(2L, location1Act.order)

        assertNotNull(location2Act)
        assertNotNull(location2Act.order)
        assertEquals(3L, location2Act.order)
    }

    private fun createLocationModel(
        latAndLong: Double,
        lastVisitedTimestampEpochSeconds: Long? = null,
        order: Long? = null,
    ) = LocationModelLocal(
        name = "",
        localNames = null,
        country = null,
        state = null,
        latitude = latAndLong,
        longitude = latAndLong,
        lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds,
        order = order
    )

}