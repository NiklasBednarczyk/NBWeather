package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMinutelyForecastDao : NBMinutelyForecastDao,
    NBFakeDao<MinutelyForecastEntityLocal, Long?> {

    override val stateFlow = MutableStateFlow<List<MinutelyForecastEntityLocal>>(emptyList())

    override fun getKey(item: MinutelyForecastEntityLocal): Long? {
        return item.metadataId
    }

    override fun getMinutelyForecasts(metadataId: Long?): Flow<List<MinutelyForecastEntityLocal>?> {
        return getItems(metadataId)
    }

    override fun insertMinutelyForecasts(minutelyForecasts: List<MinutelyForecastEntityLocal>) {
        insertOrUpdate(minutelyForecasts)
    }

    override fun deleteMinutelyForecasts(metadataId: Long?) {
        deleteItemWithKey(metadataId)
    }


}