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
        val dataArrange = LocationModelData.remoteListToData(remoteArrange)

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
    fun getVisitedLocationsInfo_shouldGetValueWhenTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithTimestamps()

        // Act + Assert
        subject.getVisitedLocationsInfo().collectUntilResource { dataAct ->
            assertListIsNotEmpty(dataAct.visitedLocations)
            assertNotNull(dataAct.currentLocation)
            assertTrue(dataAct.isInitialCurrentLocationSet)
        }
    }

    @Test
    fun getVisitedLocationsInfo_shouldNotGetValuesWhenNoTimestampSet() = testScope.runTest {
        // Arrange
        arrangeLocationsWithoutTimestamps()

        // Act + Assert
        subject.getVisitedLocationsInfo().collectUntilResource { dataAct ->
            assertListIsEmpty(dataAct.visitedLocations)
            assertNull(dataAct.currentLocation)
            assertFalse(dataAct.isInitialCurrentLocationSet)
        }
    }

    @Test
    fun insertOrUpdateCurrentLocation_shouldInsertOrUpdateLocation() = testScope.runTest {
        // Arrange
        val location1Arrange = insertLocation(1, null) ?: return@runTest
        val location2Arrange = insertLocation(2, 1L) ?: return@runTest
        val latLong3 = Pair(33.7367948, -82.7393089)

        // Act
        subject.insertOrUpdateCurrentLocation(location1Arrange.latitude, location1Arrange.longitude)
        subject.insertOrUpdateCurrentLocation(location2Arrange.latitude, location2Arrange.longitude)
        subject.insertOrUpdateCurrentLocation(latLong3.first, latLong3.second)
        val location1Act =
            geocodingDao.getLocation(location1Arrange.latitude, location1Arrange.longitude)
                .firstOrNull()
        val location2Act =
            geocodingDao.getLocation(location2Arrange.latitude, location2Arrange.longitude)
                .firstOrNull()
        val location3Act = geocodingDao.getLocation(latLong3.first, latLong3.second).firstOrNull()

        // Assert
        assertNotNull(location1Act)
        assertNotNull(location1Act.lastVisitedTimestampEpochSeconds)

        assertNotNull(location2Act)
        assertNotNull(location2Act.lastVisitedTimestampEpochSeconds)
        assertNotEquals(1L, location2Act.lastVisitedTimestampEpochSeconds)

        assertNotNull(location3Act)
        assertNotNull(location3Act.lastVisitedTimestampEpochSeconds)
    }

    @Test
    fun removeVisitedLocation_shouldRemoveTimestamp() = testScope.runTest {
        // Arrange
        insertLocation(1, 1L)
        insertLocation(2, 2L)

        // Act
        subject.removeVisitedLocation(1.0, 1.0)
        val location1Act = geocodingDao.getLocation(1.0, 1.0).firstOrNull()
        val location2Act = geocodingDao.getLocation(2.0, 2.0).firstOrNull()

        // Assert
        assertNull(location1Act?.lastVisitedTimestampEpochSeconds)

        assertNotNull(location2Act?.lastVisitedTimestampEpochSeconds)
        assertEquals(2L, location2Act?.lastVisitedTimestampEpochSeconds)
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
        lastVisitedTimestampEpochSeconds: Long?
    ): LocationModelLocal? {
        val latLong = index.toDouble()
        val locationsRemote = geocodingService.getLocationsByLocationName("", 5)
        val locationRemote = locationsRemote.getOrNull(index)
        val locationLocal = LocationModelData.remoteToLocal(
            locationRemote,
            latLong,
            latLong,
            lastVisitedTimestampEpochSeconds
        )
        locationLocal?.let { locLocal ->
            geocodingDao.insertLocation(locLocal)
        }
        return locationLocal
    }

}