package de.niklasbednarczyk.openweathermap.data.onecall.repositories

import de.niklasbednarczyk.openweathermap.data.onecall.remote.services.OneCallService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OneCallRepository @Inject constructor(
    private val oneCallService: OneCallService
) {

    suspend fun getOneCall(
        latitude: Double,
        longitude: Double
    ): Flow<String> {
        //TODO (#9) Replace with disk models
        val units = "standard"
        val language = "de"

        val remote = oneCallService.getOneCall(latitude, longitude, units, language)

        return flowOf(remote.toString())
    }

}