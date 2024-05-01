package de.niklasbednarczyk.nbweather.data.onecall.remote.services

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.coordinates.areCoordinatesApproximatelyTheSame
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.services.NBFakeService
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote

class FakeOneCallService(
    override val context: Context
) : NBOneCallService, NBFakeService<OneCallModelRemote> {

    override val klass = OneCallModelRemote::class.java

    override val fileName = "onecalls.json"

    override suspend fun getOneCall(
        latitude: Double,
        longitude: Double,
        exclude: String,
        units: String,
        language: String
    ): OneCallModelRemote {
        return items.first { oneCall ->
            areCoordinatesApproximatelyTheSame(
                latitude1 = oneCall.lat,
                longitude1 = oneCall.lon,
                latitude2 = latitude,
                longitude2 = longitude
            )
        }
    }

}