package de.niklasbednarczyk.nbweather.data.onecall.remote.services

import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote

interface NBOneCallService {

    suspend fun getOneCall(
        latitude: Double,
        longitude: Double,
        exclude: String,
        language: String,
        units: String
    ): OneCallModelRemote

}