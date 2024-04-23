package de.niklasbednarczyk.nbweather.data.geocoding.repositories

import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource.Companion.nbCollectUntilResource
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

    companion object {

        private const val LOCATION_1_LATITUDE = 40.7127281
        private const val LOCATION_1_LONGITUDE = -74.0060152

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
        val locationName = "Test"
        val remoteArrange =
            geocodingService.getLocationsByLocationName(locationName, 5)
        val dataArrange = remoteArrange.mapNotNull(LocationModelData::remoteToData)

        // Act + Assert
        subject.getLocationsByLocationName(locationName)
            .nbCollectUntilResource { dataAct ->
                assertListsContainSameItems(dataArrange.mapToLatLong(), dataAct.mapToLatLong())
            }
    }

    @Test
    fun getVisitedLocations_shouldOnlyGetVisitedLocations() = testScope.runTest {
        // Arrange
        val location1 = insertLocation(0, 2L)
        val location2 = insertLocation(1, null)
        val location3 = insertLocation(2, 1L)

        // Act + Assert
        subject.getVisitedLocations().nbCollectUntilResource { dataAct ->
            assertListDoesContain(
                dataAct?.mapToLatLong(),
                location1.toLatLong(),
                location3.toLatLong()
            )
            assertListDoesNotContain(dataAct?.mapToLatLong(), location2.toLatLong())
        }
    }

    @Test
    fun getCurrentLocation_withTimestamp_shouldGetCurrentLocation() = testScope.runTest {
        // Arrange
        insertLocation(0, 1L)
        val location = insertLocation(1, 3L)
        insertLocation(2, 2L)

        // Act + Assert
        subject.getCurrentLocation().nbCollectUntilResource { dataAct ->
            assertEquals(location.toLatLong(), dataAct?.toLatLong())
        }
    }

    @Test
    fun getCurrentLocation_withoutTimestamp_shouldBeNull() = testScope.runTest {
        // Arrange
        insertLocation(0, null)

        // Act + Assert
        subject.getCurrentLocation().nbCollectUntilResource { dataAct ->
            assertNull(dataAct)
        }
    }

    @Test
    fun getIsInitialCurrentLocationSet_withTimestamp_shouldBeTrue() = testScope.runTest {
        // Arrange
        insertLocation(0, 1L)

        // Act + Assert
        subject.getIsInitialCurrentLocationSet().nbCollectUntilResource { dataAct ->
            assertTrue(dataAct)
        }
    }

    @Test
    fun getIsInitialCurrentLocationSet_withoutTimestamp_shouldBeFalse() = testScope.runTest {
        // Arrange
        insertLocation(0, null)

        // Act + Assert
        subject.getIsInitialCurrentLocationSet().nbCollectUntilResource { dataAct ->
            assertFalse(dataAct)
        }
    }

    @Test
    fun setCurrentLocation_localExistsWithGivenCoordinates_withOrder_shouldSetCurrentLocation() =
        testScope.runTest {
            val location = insertLocation(0, 1L, 2L)
            testSetCurrentLocation(
                latitude = location.latitude,
                longitude = location.longitude,
                expectedIsSuccessful = true,
                illegalLastVisitedTimestampEpochSeconds = location.lastVisitedTimestampEpochSeconds,
                expectedOrder = location.order
            )
        }

    @Test
    fun setCurrentLocation_localExistsWithGivenCoordinates_withoutOrder_shouldSetCurrentLocation() =
        testScope.runTest {
            val location = insertLocation(0, 1L, null)
            testSetCurrentLocation(
                latitude = location.latitude,
                longitude = location.longitude,
                expectedIsSuccessful = true,
                illegalLastVisitedTimestampEpochSeconds = location.lastVisitedTimestampEpochSeconds
            )
        }

    @Test
    fun setCurrentLocation_localExistsWithRemoteCoordinates_withOrder_shouldSetCurrentLocation() =
        testScope.runTest {
            val location = insertLocation(0, 1L, 2L)
            testSetCurrentLocation(
                latitude = location.latitude.toInt().toDouble(),
                longitude = location.longitude.toInt().toDouble(),
                locationLatitude = location.latitude,
                locationLongitude = location.longitude,
                expectedIsSuccessful = true,
                illegalLastVisitedTimestampEpochSeconds = location.lastVisitedTimestampEpochSeconds,
                expectedOrder = location.order
            )
        }

    @Test
    fun setCurrentLocation_localExistsWithRemoteCoordinates_withoutOrder_shouldSetCurrentLocation() =
        testScope.runTest {
            val location = insertLocation(0, 1L, null)
            testSetCurrentLocation(
                latitude = location.latitude.toInt().toDouble(),
                longitude = location.longitude.toInt().toDouble(),
                locationLatitude = location.latitude,
                locationLongitude = location.longitude,
                expectedIsSuccessful = true,
                illegalLastVisitedTimestampEpochSeconds = location.lastVisitedTimestampEpochSeconds
            )
        }

    @Test
    fun setCurrentLocation_localDoesNotExistsButRemoteDoes_shouldSetCurrentLocation() =
        testScope.runTest {
            testSetCurrentLocation(
                latitude = LOCATION_1_LATITUDE,
                longitude = LOCATION_1_LONGITUDE,
                expectedIsSuccessful = true
            )
        }

    @Test
    fun setCurrentLocation_localAndRemoteDoNotExists_shouldNotBeSuccessful() =
        testScope.runTest {
            testSetCurrentLocation(
                latitude = Double.MAX_VALUE,
                longitude = Double.MAX_VALUE,
                expectedIsSuccessful = false
            )
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
            index = 0,
            lastVisitedTimestampEpochSeconds = null,
            order = -1
        )
        val location2LocalArrange = insertLocation(
            index = 1,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )

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
        val location1Arrange = insertLocation(0)
        val location2Arrange = insertLocation(1)

        // Act
        val deletedLocation = subject.deleteLocation(
            latitude = location1Arrange.latitude,
            longitude = location1Arrange.longitude
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

    private suspend fun testSetCurrentLocation(
        latitude: Double,
        longitude: Double,
        locationLatitude: Double = latitude,
        locationLongitude: Double = longitude,
        expectedIsSuccessful: Boolean,
        illegalLastVisitedTimestampEpochSeconds: Long? = null,
        expectedOrder: Long? = null
    ) {
        // Arrange + Act
        val isSuccessful = subject.setCurrentLocation(latitude, longitude)
        val location = geocodingDao.getLocation(locationLatitude, locationLongitude).firstOrNull()

        // Assert
        if (expectedIsSuccessful) {
            assertTrue(isSuccessful)

            assertNotNull(location)

            assertNotNull(location.lastVisitedTimestampEpochSeconds)
            if (illegalLastVisitedTimestampEpochSeconds != null) {
                assertNotEquals(
                    illegalLastVisitedTimestampEpochSeconds,
                    location.lastVisitedTimestampEpochSeconds
                )
            }

            assertNotNull(location.order)
            if (expectedOrder != null) {
                assertEquals(expectedOrder, location.order)
            } else {
                assertEquals(location.lastVisitedTimestampEpochSeconds, location.order)
            }
        } else {
            assertFalse(isSuccessful)

            assertNull(location)
        }
    }

    private fun LocationModelLocal.toLatLong(): Pair<Double, Double> {
        return Pair(latitude, longitude)
    }

    private fun LocationModelData.toLatLong(): Pair<Double, Double> {
        return Pair(latitude, longitude)
    }

    private fun List<LocationModelData>.mapToLatLong(): List<Pair<Double, Double>> {
        return map { location -> location.toLatLong() }
    }

    private suspend fun insertLocation(
        index: Int,
        lastVisitedTimestampEpochSeconds: Long? = null,
        order: Long? = null
    ): LocationModelLocal {
        val locationsRemote = geocodingService.getLocationsByLocationName("", 5)
        val locationRemote = locationsRemote[index]
        val locationLocal = LocationModelData.remoteToLocal(
            remote = locationRemote,
            lastVisitedTimestampEpochSeconds = lastVisitedTimestampEpochSeconds,
            order = order
        )
        geocodingDao.insertLocation(locationLocal)
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