package de.niklasbednarczyk.nbweather.data.geocoding.remote.services

import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.GeoRetrofit
import de.niklasbednarczyk.nbweather.data.geocoding.remote.models.LocationModelRemote
import de.niklasbednarczyk.nbweather.test.data.localremote.remote.services.NBServiceLatLongTest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.test.assertNotNull

@HiltAndroidTest
class RetrofitGeocodingServiceTest : NBServiceLatLongTest {

    companion object {
        private const val LIMIT = 5
    }

    @GeoRetrofit
    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var subject: RetrofitGeocodingService

    override fun createSubject() {
        subject = retrofit.create(RetrofitGeocodingService::class.java)
    }

    @Test
    fun getLocationsByLocationName_shouldGetLocations() = testScope.runTest {
        // Arrange
        val locationNameCorrect = "London"
        val locationNameEmpty = " "
        val locationNameWrong = "abcdefghijklmnopqrstuvwxyz"

        // Act
        val locationsCorrect = subject.getLocationsByLocationName(
            locationName = locationNameCorrect,
            limit = LIMIT
        )
        val locationsEmpty = subject.getLocationsByLocationName(
            locationName = locationNameEmpty,
            limit = LIMIT
        )
        val locationsWrong = subject.getLocationsByLocationName(
            locationName = locationNameWrong,
            limit = LIMIT
        )

        // Assert
        assertLocations(locationsCorrect)

        assertNullOrEmpty(locationsEmpty)

        assertNullOrEmpty(locationsWrong)
    }

    @Test(expected = HttpException::class)
    fun getLocationsByLocationName_shouldThrowExceptionOnBlankName() = testScope.runTest {
        // Arrange
        val locationNameBlank = ""

        // Act + Assert
        subject.getLocationsByLocationName(
            locationName = locationNameBlank,
            limit = LIMIT
        )
    }

    @Test
    fun getLocationsByCoordinates_shouldGetLocations() = testScope.runTest {
        // Arrange + Act
        val locations = subject.getLocationsByCoordinates(
            latitude = latitude,
            longitude = longitude,
            limit = LIMIT
        )

        // Assert
        assertLocations(locations)
    }

    override suspend fun createActLatLongBounds(latitude: Double, longitude: Double) {
        subject.getLocationsByCoordinates(
            latitude = latitude,
            longitude = longitude,
            limit = LIMIT
        )
    }

    private fun assertLocations(
        locations: List<LocationModelRemote>
    ) {
        assertListIsNotEmpty(locations)

        locations.forEach { location ->
            assertNotNull(location.name)
            assertNotNull(location.lat)
            assertNotNull(location.lon)
            assertNotNull(location.country)
        }
    }


}