package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCurrentWeatherDao : NBCurrentWeatherDao, NBFakeDao<CurrentWeatherEntityLocal, Long?> {

    override val stateFlow = MutableStateFlow<List<CurrentWeatherEntityLocal>>(emptyList())

    override fun getKey(item: CurrentWeatherEntityLocal): Long? {
        return item.metadataId
    }

    override fun getCurrentWeather(metadataId: Long?): Flow<CurrentWeatherEntityLocal?> {
        return getItem(metadataId)
    }

    override fun insertCurrentWeather(currentWeather: CurrentWeatherEntityLocal) {
        insertOrUpdate(currentWeather)
    }

    override fun deleteCurrentWeather(metadataId: Long?) {
        deleteItemWithKey(metadataId)
    }


}