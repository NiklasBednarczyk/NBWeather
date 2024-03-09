package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeMetadataDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCurrentWeatherDao : NBCurrentWeatherDao,
    NBFakeMetadataDao<CurrentWeatherEntityLocal, Long?, Long?> {

    override val stateFlow = MutableStateFlow<List<CurrentWeatherEntityLocal>>(emptyList())

    override fun getKey(item: CurrentWeatherEntityLocal): Pair<Long?, Long?> {
        return Pair(item.dt, item.metadataId)
    }

    override fun getMetadataKey(item: CurrentWeatherEntityLocal): Long? {
        return item.metadataId
    }

    override fun getCurrentWeather(metadataId: Long?): Flow<CurrentWeatherEntityLocal?> {
        return getItemByMetadataKey(metadataId)
    }

    override fun insertCurrentWeather(currentWeather: CurrentWeatherEntityLocal) {
        insertOrUpdate(currentWeather)
    }

    override fun deleteCurrentWeather(metadataId: Long?) {
        deleteItemWithMetadataKey(metadataId)
    }


}