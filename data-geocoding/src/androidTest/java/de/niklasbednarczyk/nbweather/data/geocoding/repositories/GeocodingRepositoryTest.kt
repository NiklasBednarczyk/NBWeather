package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal.Companion.coordinates
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import de.niklasbednarczyk.nbweather.test.data.localremote.repositories.NBLocalRemoteRepositoryTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GeocodingRepositoryTest : NBLocalRemoteRepositoryTest {

    companion object {

        private val LOCATION_1_COORDINATES = NBCoordinatesModel(
            latitude = 40.7127281,
            longitude = -74.0060152
        )

        private const val LOCATION_2_LOCATION_NAME = "Ber"

    }

    private lateinit var subject: GeocodingRepository

    private lateinit var geocodingService: NBGeocodingService

    private lateinit var geocodingDao: NBGeocodingDao

    @Before
    override fun setUp() {
        geocodingDao = FakeGeocodingDao()
        geocodingService = FakeGeocodingService(context)
        subject = GeocodingRepository(
            geocodingService = geocodingService,
            geocodingDao = geocodingDao
        )
    }

    @Test
    fun getLocationsByLocationName_shouldGetCorrectLocations() = testScope.runTest {
        // Arrange
        val remoteArrange =
            geocodingService.getLocationsByLocationName(LOCATION_2_LOCATION_NAME, 5)
        val dataArrange = remoteArrange.mapNotNull(LocationModelData::remoteToData)

        // Act + Assert
        subject.getLocationsByLocationName(LOCATION_2_LOCATION_NAME)
            .nbCollectUntilResource { dataAct ->
                assertListHasSize(dataAct, 1)
                assertListsContainSameItems(
                    dataArrange.mapToCoordinates(),
                    dataAct.mapToCoordinates()
                )
            }
    }

    @Test
    fun getLocationByCoordinatesAndSetAsCurrent_shouldGetAndUpdateLocation() = testScope.runTest {
        // Arrange
        val location1Arrange = insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )
        val location2Arrange = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = 2L,
            order = 3L
        )

        // Act + Assert
        subject.getLocationByCoordinatesAndSetAsCurrent(
            coordinates = location1Arrange.coordinates
        ).nbCollectUntilResource { location1Act ->
            assertNotNull(location1Act)
            assertNotNull(location1Act.lastVisitedTimestampEpochSeconds)
            assertNotNull(location1Act.order)
            assertEquals(location1Act.lastVisitedTimestampEpochSeconds, location1Act.order)
        }

        subject.getLocationByCoordinatesAndSetAsCurrent(
            coordinates = location2Arrange.coordinates
        ).nbCollectUntilResource { location2Act ->
            assertNotNull(location2Act)
            assertNotNull(location2Act.lastVisitedTimestampEpochSeconds)
            assertNotEquals(
                location2Arrange.lastVisitedTimestampEpochSeconds,
                location2Act.lastVisitedTimestampEpochSeconds
            )
            assertNotNull(location2Act.order)
            assertEquals(location2Arrange.order, location2Act.order)
        }
    }

    @Test
    fun getVisitedLocations_shouldOnlyGetVisitedLocations() = testScope.runTest {
        // Arrange
        val location1 = insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = 2L
        )
        val location2 = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = null
        )
        val location3 = insertLocation(
            index = 2,
            lastVisitedTimestampEpochSeconds = 1L
        )

        // Act + Assert
        subject.getVisitedLocations().nbCollectUntilResource { dataAct ->
            assertListDoesContain(
                dataAct.mapToCoordinates(),
                location1.coordinates,
                location3.coordinates
            )
            assertListDoesNotContain(dataAct.mapToCoordinates(), location2.coordinates)
        }
    }

    @Test
    fun getCurrentLocation_withTimestamp_shouldGetCurrentLocation() = testScope.runTest {
        // Arrange
        insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = 1L
        )
        val location = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = 3L
        )
        insertLocation(
            index = 2,
            lastVisitedTimestampEpochSeconds = 2L
        )

        // Act + Assert
        subject.getCurrentLocation().nbCollectUntilResource { dataAct ->
            assertEquals(location.coordinates, dataAct?.coordinates)
        }
    }

    @Test
    fun getCurrentLocation_withoutTimestamp_shouldBeNull() = testScope.runTest {
        // Arrange
        insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = null
        )

        // Act + Assert
        subject.getCurrentLocation().nbCollectUntilResource { dataAct ->
            assertNull(dataAct)
        }
    }

    @Test
    fun getInitialCurrentLocation_withTimestamp_shouldBeNotNull() = testScope.runTest {
        // Arrange
        insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = 1L
        )

        // Act + Assert
        subject.getInitialCurrentLocation().nbCollectUntilResource { dataAct ->
            assertNotNull(dataAct)
            assertEquals(LOCATION_1_COORDINATES, dataAct.coordinates)
        }
    }

    @Test
    fun getInitialCurrentLocation_withoutTimestamp_shouldBeNull() = testScope.runTest {
        // Arrange
        insertLocation(0, null)

        // Act + Assert
        subject.getInitialCurrentLocation().nbCollectUntilResource { dataAct ->
            assertNull(dataAct)
        }
    }

    @Test
    fun getAndInsertLocation_shouldGetAndInsertLocation() = testScope.runTest {
        // Arrange
        val location2Arrange = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = 2L,
            order = 3L
        )

        // Act
        val location1Act = subject.getAndInsertLocation(
            coordinates = LOCATION_1_COORDINATES
        )
        val location2Act = subject.getAndInsertLocation(
            coordinates = location2Arrange.coordinates
        )
        val location3Act = subject.getAndInsertLocation(
            coordinates = NBCoordinatesModel(
                latitude = Double.MAX_VALUE,
                longitude = Double.MIN_VALUE
            )
        )

        // Assert
        assertNotNull(location1Act)
        assertNull(location1Act.lastVisitedTimestampEpochSeconds)
        assertNull(location1Act.order)

        assertNotNull(location2Act)
        assertNotNull(location2Act.lastVisitedTimestampEpochSeconds)
        assertEquals(
            location2Arrange.lastVisitedTimestampEpochSeconds,
            location2Act.lastVisitedTimestampEpochSeconds
        )
        assertNotNull(location2Act.order)
        assertEquals(location2Arrange.order, location2Act.order)

        assertNull(location3Act)
    }

    @Test
    fun insertLocation_shouldInsertLocation() = testScope.runTest {
        // Arrange
        val locationArrange = createTestLocation()

        // Act
        subject.insertLocation(locationArrange)
        val locationAct = geocodingDao.getLocation(
            latitude = locationArrange.coordinates.latitude,
            longitude = locationArrange.coordinates.longitude
        ).firstOrNull()

        // Assert
        assertNotNull(locationAct)
    }

    @Test
    fun updateOrders_shouldUpdateOrders() = testScope.runTest {
        // Arrange
        val location1LocalArrange = insertLocation(
            index = 0,
            lastVisitedTimestampEpochSeconds = null,
            order = -1
        )
        val location2LocalArrange = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )

        val location1DataArrange = LocationModelData.localToData(location1LocalArrange)
        val location2DataArrange = LocationModelData.localToData(location2LocalArrange)

        // Act
        subject.updateOrders(
            listOf(
                location2DataArrange.coordinates,
                location1DataArrange.coordinates
            )
        )

        val location1LocalAct = geocodingDao.getLocation(
            latitude = location1LocalArrange.latitude,
            longitude = location1LocalArrange.longitude
        ).firstOrNull()
        val location2LocalAct = geocodingDao.getLocation(
            latitude = location2LocalArrange.latitude,
            longitude = location2LocalArrange.longitude
        ).firstOrNull()

        // Assert
        assertNotNull(location1LocalAct)
        assertEquals(1L, location1LocalAct.order)

        assertNotNull(location2LocalAct)
        assertEquals(0L, location2LocalAct.order)
    }

    @Test
    fun deleteLocation_shouldDeleteLocation() = testScope.runTest {
        // Arrange
        val location1Arrange = insertLocation(
            index = 0
        )
        val location2Arrange = insertLocation(
            index = 1
        )

        // Act
        val deletedLocation = subject.deleteLocation(
            coordinates = location1Arrange.coordinates,
        )
        val location1Act = geocodingDao.getLocation(
            latitude = location1Arrange.latitude,
            longitude = location1Arrange.longitude
        ).firstOrNull()
        val location2Act = geocodingDao.getLocation(
            latitude = location2Arrange.latitude,
            longitude = location2Arrange.longitude
        ).firstOrNull()

        // Assert
        assertNotNull(location1Arrange)
        assertNotNull(deletedLocation)
        assertEquals(
            location1Arrange.coordinates,
            deletedLocation.coordinates
        )

        assertNull(location1Act)

        assertNotNull(location2Act)
    }

    private fun List<LocationModelData>.mapToCoordinates(): List<NBCoordinatesModel> {
        return map { location -> location.coordinates }
    }

    private suspend fun insertLocation(
        index: Int,
        lastVisitedTimestampEpochSeconds: Long? = null,
        order: Long? = null
    ): LocationModelLocal {
        val locationsRemote = geocodingService.getLocationsByLocationName("", 5)
        val locationRemote = locationsRemote[index]
        val locationLocal = LocationModelData.remoteToLocal(
            remote = locationRemote
        ).copy(
            lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds,
            order = order
        )
        geocodingDao.insertLocation(locationLocal)
        return locationLocal
    }

    private fun createTestLocation(): LocationModelData {
        return LocationModelData(
            coordinates = NBCoordinatesModel(
                latitude = 1.0,
                longitude = 2.0
            ),
            name = null,
            localNames = null,
            country = null,
            state = null,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )
    }

}