package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeMetadataDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMinutelyForecastDao : NBMinutelyForecastDao,
    NBFakeMetadataDao<MinutelyForecastEntityLocal, Long?, Long?> {

    override val stateFlow = MutableStateFlow<List<MinutelyForecastEntityLocal>>(emptyList())

    override fun getKey(item: MinutelyForecastEntityLocal): Pair<Long?, Long?> {
        return Pair(item.dt, item.metadataId)
    }

    override fun getMetadataKey(item: MinutelyForecastEntityLocal): Long? {
        return item.metadataId
    }

    override fun getMinutelyForecasts(metadataId: Long?): Flow<List<MinutelyForecastEntityLocal>?> {
        return getItemsByMetadataKey(metadataId)
    }

    override fun insertMinutelyForecasts(minutelyForecasts: List<MinutelyForecastEntityLocal>) {
        insertOrUpdate(minutelyForecasts)
    }

    override fun deleteMinutelyForecasts(metadataId: Long?) {
        deleteItemWithMetadataKey(metadataId)
    }


}