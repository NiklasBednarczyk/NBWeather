package de.niklasbednarczyk.nbweather.data.onecall.remote.services

import android.content.Context
import com.squareup.moshi.JsonAdapter
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.services.NBFakeService
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemoteJsonAdapter

class FakeOneCallService(
    override val context: Context
) : NBOneCallService, NBFakeService<OneCallModelRemote> {

    override val fileName: String = "onecall.json"

    override val adapter: JsonAdapter<OneCallModelRemote> = OneCallModelRemoteJsonAdapter(moshi)

    override val defaultModel: OneCallModelRemote = OneCallModelRemote(
        lat = null,
        lon = null,
        timezone = null,
        timezoneOffset = null,
        current = null,
        minutely = listOf(),
        hourly = listOf(),
        daily = listOf(),
        alerts = listOf()
    )

    override suspend fun getOneCall(
        latitude: Double,
        longitude: Double,
        exclude: String,
        language: String,
        units: String
    ): OneCallModelRemote {
        return model
    }


}