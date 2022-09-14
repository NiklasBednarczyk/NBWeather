package de.niklasbednarczyk.openweathermap.data.onecall.remote.services

import de.niklasbednarczyk.openweathermap.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.constants.ConstantsOneCallRemote
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.OneCallModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallService {

    @GET(ConstantsOneCallRemote.Endpoint.NAME)
    suspend fun getOneCall(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double,
        @Query(ConstantsCoreRemote.Query.Units.NAME) units: String,
        @Query(ConstantsCoreRemote.Query.Language.NAME) language: String
    ): OneCallModelRemote

}