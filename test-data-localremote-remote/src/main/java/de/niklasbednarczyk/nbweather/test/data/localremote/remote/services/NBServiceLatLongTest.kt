package de.niklasbednarczyk.nbweather.test.data.localremote.remote.services

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.HttpException

interface NBServiceLatLongTest : NBServiceTest {

    companion object {

        private val LONDON_COORDINATES = NBCoordinatesModel(
            latitude = 51.5073219,
            longitude = -0.1276474
        )

    }

    val latitude: Double
        get() = LONDON_COORDINATES.latitude

    val longitude: Double
        get() = LONDON_COORDINATES.longitude

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
    fun createActLatLongBounds_outerLowerLat_shouldThrowException() = testScope.runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.LOWER,
            longitudeType = LatLongBoundType.CENTRAL,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerUpperLat_shouldThrowException() = testScope.runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.UPPER,
            longitudeType = LatLongBoundType.CENTRAL,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerLowerLong_shouldThrowException() = testScope.runTest {
        testCreateActLatLongBounds(
            latitudeType = LatLongBoundType.CENTRAL,
            longitudeType = LatLongBoundType.LOWER,
        )
    }

    @Test(expected = HttpException::class)
    fun createActLatLongBounds_outerUpperLong_shouldThrowException() = testScope.runTest {
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