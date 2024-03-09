package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeMetadataDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHourlyForecastDao : NBHourlyForecastDao,
    NBFakeMetadataDao<HourlyForecastEntityLocal, Long?, Long?> {

    override val stateFlow = MutableStateFlow<List<HourlyForecastEntityLocal>>(emptyList())

    override fun getKey(item: HourlyForecastEntityLocal): Pair<Long?, Long?> {
        return Pair(item.dt, item.metadataId)
    }

    override fun getMetadataKey(item: HourlyForecastEntityLocal): Long? {
        return item.metadataId
    }

    override fun getHourlyForecasts(metadataId: Long?): Flow<List<HourlyForecastEntityLocal>?> {
        return getItemsByMetadataKey(metadataId)
    }

    override fun insertHourlyForecasts(hourlyForecasts: List<HourlyForecastEntityLocal>) {
        insertOrUpdate(hourlyForecasts)
    }

    override fun deleteHourlyForecasts(metadataId: Long?) {
        deleteItemWithMetadataKey(metadataId)
    }

}