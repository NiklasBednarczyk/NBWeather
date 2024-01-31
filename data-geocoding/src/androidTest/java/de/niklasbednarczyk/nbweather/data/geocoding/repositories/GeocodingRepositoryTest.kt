package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.collectUntilResource
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.FakeGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.daos.NBGeocodingDao
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.FakeGeocodingService
import de.niklasbednarczyk.nbweather.data.geocoding.remote.services.NBGeocodingService
import de.niklasbednarczyk.nbweather.test.data.localremote.repositories.NBLocalRemoteRepositoryTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GeocodingRepositoryTest : NBLocalRemoteRepositoryTest {

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
        val locationName = "Test"
        val remoteArrange =
            geocodingService.getLocationsByLocationName(locationName, 5)
        val dataArrange = remoteArrange.mapNotNull(LocationModelData::remoteToData)

        // Act + Assert
        subject.getLocationsByLocationName(locationName)
            .collectUntilResource { dataAct ->
                assertListsContainSameItems(dataArrange.mapToLatLong(), dataAct.mapToLatLong())
            }
    }

    @Test
    fun getVisitedLocations_shouldOnlyGetVisitedLocations() = testScope.runTest {
        // Arrange
        arrangeLocationsWithTimestamps()

        // Act + Assert
        subject.getVisitedLocations().collectUntilResource { dataAct ->
            assertListDoesContain(dataAct?.mapToLatLong(), 1.toLatLong(), 3.toLatLong())
            assertListDoesNotContain(dataAct?.mapToLatLong(), 2.toLatLong())
        }
    }

    @Test
    fun getCurrentLocation_shouldGetCurrentLocationWhenTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithTimestamps()

        // Act + Assert
        subject.getCurrentLocation().collectUntilResource { dataAct ->
            assertEquals(1.toLatLong(), dataAct?.toLatLong())
        }
    }

    @Test
    fun getCurrentLocation_shouldNotGetCurrentLocationWhenNoTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithoutTimestamps()

        // Act + Assert
        subject.getCurrentLocation().collectUntilResource { dataAct ->
            assertNull(dataAct)
        }
    }

    @Test
    fun getIsInitialCurrentLocationSet_shouldGetValueWhenTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithTimestamps()

        // Act + Assert
        subject.getIsInitialCurrentLocationSet().collectUntilResource { dataAct ->
            assertTrue(dataAct)
        }
    }

    @Test
    fun getIsInitialCurrentLocationSet_shouldNotGetValueWhenNoTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithoutTimestamps()

        // Act + Assert
        subject.getIsInitialCurrentLocationSet().collectUntilResource { dataAct ->
            assertFalse(dataAct)
        }
    }

    @Test
    fun setCurrentLocation_shouldSetCurrentLocation() = testScope.runTest {
        // Arrange
        val location1Arrange = insertLocation(1, null)!!
        val location2Arrange = insertLocation(2, 1L)!!
        val latLong3 = Pair(33.7367948, -82.7393089)
        val latLong4 = Pair(Double.MAX_VALUE, Double.MAX_VALUE)

        // Act
        val isSuccessful1 = subject.setCurrentLocation(location1Arrange.latitude, location1Arrange.longitude)
        val isSuccessful2 = subject.setCurrentLocation(location2Arrange.latitude, location2Arrange.longitude)
        val isSuccessful3 = subject.setCurrentLocation(latLong3.first, latLong3.second)
        val isSuccessful4 = subject.setCurrentLocation(latLong4.first, latLong4.second)

        val location1Act =
            geocodingDao.getLocation(location1Arrange.latitude, location1Arrange.longitude)
                .firstOrNull()
        val location2Act =
            geocodingDao.getLocation(location2Arrange.latitude, location2Arrange.longitude)
                .firstOrNull()
        val location3Act = geocodingDao.getLocation(latLong3.first, latLong3.second).firstOrNull()

        // Assert
        assertTrue(isSuccessful1)
        assertTrue(isSuccessful2)
        assertTrue(isSuccessful3)
        assertFalse(isSuccessful4)

        assertNotNull(location1Act)
        assertNotNull(location1Act.lastVisitedTimestampEpochSeconds)

        assertNotNull(location2Act)
        assertNotNull(location2Act.lastVisitedTimestampEpochSeconds)
        assertNotEquals(1L, location2Act.lastVisitedTimestampEpochSeconds)

        assertNotNull(location3Act)
        assertNotNull(location3Act.lastVisitedTimestampEpochSeconds)
    }

    @Test
    fun insertLocation_shouldInsertLocation() = testScope.runTest {
        // Arrange
        val locationArrange = createTestLocation()

        // Act
        subject.insertLocation(locationArrange)
        val locationAct = geocodingDao.getLocation(
            latitude = locationArrange.latitude,
            longitude = locationArrange.longitude
        ).firstOrNull()

        // Assert
        assertNotNull(locationAct)
    }

    @Test
    fun updateOrders_shouldUpdateOrders() = testScope.runTest {
        // Arrange
        val location1LocalArrange = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = null,
            order = -1
        )!!
        val location2LocalArrange = insertLocation(
            index = 2,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )!!

        val location1DataArrange = LocationModelData.localToData(location1LocalArrange)!!
        val location2DataArrange = LocationModelData.localToData(location2LocalArrange)!!

        // Act
        subject.updateOrders(
            listOf(
                location2DataArrange.toLatLong(),
                location1DataArrange.toLatLong()
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
        val location1Arrange = insertLocation(1)
        insertLocation(2)

        // Act
        val deletedLocation = subject.deleteLocation(1.0, 1.0)
        val location1Act = geocodingDao.getLocation(1.0, 1.0).firstOrNull()
        val location2Act = geocodingDao.getLocation(2.0, 2.0).firstOrNull()

        // Assert
        assertNotNull(location1Arrange)
        assertNotNull(deletedLocation)
        assertEquals(
            location1Arrange.latitude,
            deletedLocation.latitude
        )
        assertEquals(
            location1Arrange.longitude,
            deletedLocation.longitude
        )

        assertNull(location1Act)

        assertNotNull(location2Act)
    }

    private fun Number.toLatLong(): Pair<Double, Double> {
        return Pair(this.toDouble(), this.toDouble())
    }

    private fun LocationModelData.toLatLong(): Pair<Double, Double> {
        return Pair(latitude, longitude)
    }

    private fun List<LocationModelData>.mapToLatLong(): List<Pair<Double, Double>> {
        return map { location -> location.toLatLong() }
    }

    private suspend fun arrangeLocationsWithTimestamps() {
        insertLocation(1, 2L)
        insertLocation(2, null)
        insertLocation(3, 1L)
    }

    private suspend fun arrangeLocationsWithoutTimestamps() {
        insertLocation(1, null)
        insertLocation(2, null)
    }

    private suspend fun insertLocation(
        index: Int,
        lastVisitedTimestampEpochSeconds: Long? = null,
        order: Long? = null
    ): LocationModelLocal? {
        val latLong = index.toDouble()
        val locationsRemote = geocodingService.getLocationsByLocationName("", 5)
        val locationRemote = locationsRemote.getOrNull(index)
        val locationLocal = LocationModelData.remoteToLocal(
            remote = locationRemote,
            latitude = latLong,
            longitude = latLong,
            lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds,
            order = order
        )
        locationLocal?.let { locLocal ->
            geocodingDao.insertLocation(locLocal)
        }
        return locationLocal
    }

    private fun createTestLocation(): LocationModelData {
        return LocationModelData(
            latitude = 1.0,
            longitude = 2.0,
            name = null,
            localNames = null,
            country = null,
            state = null,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )
    }

}