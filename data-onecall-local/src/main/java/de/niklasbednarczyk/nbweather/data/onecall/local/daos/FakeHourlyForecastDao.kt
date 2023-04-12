package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHourlyForecastDao : NBHourlyForecastDao, NBFakeDao<HourlyForecastEntityLocal, Long?> {

    override val stateFlow = MutableStateFlow<List<HourlyForecastEntityLocal>>(emptyList())

    override fun getKey(item: HourlyForecastEntityLocal): Long? {
        return item.metadataId
    }

    override fun getHourlyForecasts(metadataId: Long?): Flow<List<HourlyForecastEntityLocal>?> {
        return getItems(metadataId)
    }

    override fun insertHourlyForecasts(hourlyForecasts: List<HourlyForecastEntityLocal>) {
        insertOrUpdate(hourlyForecasts)
    }

    override fun deleteHourlyForecasts(metadataId: Long?) {
        deleteItem(metadataId)
    }

}