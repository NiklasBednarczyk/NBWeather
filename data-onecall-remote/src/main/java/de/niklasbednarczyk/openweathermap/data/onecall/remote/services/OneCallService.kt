package de.niklasbednarczyk.openweathermap.data.onecall.remote.services

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallService {

    @GET("onecall")
    suspend fun getOneCall(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double,
        @Query(ConstantsCoreRemote.Query.Units.NAME) units: String,
        @Query(ConstantsCoreRemote.Query.Language.NAME) language: String
    ): OneCallRemote

}