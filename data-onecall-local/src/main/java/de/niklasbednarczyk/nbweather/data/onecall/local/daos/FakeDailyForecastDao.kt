package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeMetadataDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeDailyForecastDao : NBDailyForecastDao,
    NBFakeMetadataDao<DailyForecastEntityLocal, Long?, Long?> {

    override val stateFlow = MutableStateFlow<List<DailyForecastEntityLocal>>(emptyList())

    override fun getKey(item: DailyForecastEntityLocal): Pair<Long?, Long?> {
        return Pair(item.dt, item.metadataId)
    }

    override fun getMetadataKey(item: DailyForecastEntityLocal): Long? {
        return item.metadataId
    }

    override fun getDailyForecasts(metadataId: Long?): Flow<List<DailyForecastEntityLocal>?> {
        return getItemsByMetadataKey(metadataId)
    }

    override fun insertDailyForecasts(dailyForecasts: List<DailyForecastEntityLocal>) {
        insertOrUpdate(dailyForecasts)
    }

    override fun deleteDailyForecasts(metadataId: Long?) {
        deleteItemWithMetadataKey(metadataId)
    }

}