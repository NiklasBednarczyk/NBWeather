package de.niklasbednarczyk.nbweather.test.data.localremote.remote.services

import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.HttpException

interface NBServiceLatLongTest : NBServiceTest {

    companion object {
        const val LONDON_LATITUDE = 51.5073219
        const val LONDON_LONGITUDE = -0.1276474
    }

    private enum class LatLongBoundType {
        LOWER,
        UPPER,
        CENTRAL;

        val latitude: Double
            get() = when (this) {
                LOWER -> -100.0
                UPPER -> 100.0
                CENTRAL -> 0.0
            }

        val longitude: Double
            get() = when (this) {
                LOWER -> -200.0
                UPPER -> 200.0
                CENTRAL -> 0.0
            }

    }

    suspend fun createActLatLongBounds(
        latitude: Double,
        longitude: Double
    )

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerLowerLat_shouldThrowException() = runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.LOWER,
            longitudeType = LatLongBoundType.CENTRAL,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerUpperLat_shouldThrowException() = runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.UPPER,
            longitudeType = LatLongBoundType.CENTRAL,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerLowerLong_shouldThrowException() = runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.CENTRAL,
            longitudeType = LatLongBoundType.LOWER,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerUpperLong_shouldThrowException() = runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.CENTRAL,
            longitudeType = LatLongBoundType.UPPER,
        )
    }

    private suspend fun testCreateActLatLongBounds(
        latitudeType: LatLongBoundType,
        longitudeType: LatLongBoundType
    ) {
        // Arrange
        val latitude = latitudeType.latitude
        val longitude = longitudeType.longitude

        // Act + Assert
        createActLatLongBounds(latitude, longitude)
    }

}