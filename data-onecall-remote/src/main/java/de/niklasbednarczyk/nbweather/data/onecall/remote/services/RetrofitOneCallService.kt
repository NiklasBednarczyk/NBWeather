package de.niklasbednarczyk.nbweather.data.onecall.remote.services

import de.niklasbednarczyk.nbweather.core.data.localremote.remote.constants.ConstantsCoreRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.constants.ConstantsOneCallRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitOneCallService : NBOneCallService {

    @GET(ConstantsOneCallRemote.Endpoint.NAME)
    override suspend fun getOneCall(
        @Query(ConstantsCoreRemote.Query.Latitude.NAME) latitude: Double,
        @Query(ConstantsCoreRemote.Query.Longitude.NAME) longitude: Double,
        @Query(ConstantsCoreRemote.Query.Exclude.NAME) exclude: String,
        @Query(ConstantsCoreRemote.Query.Units.NAME) units: String,
        @Query(ConstantsCoreRemote.Query.Language.NAME) language: String
    ): OneCallModelRemote

}