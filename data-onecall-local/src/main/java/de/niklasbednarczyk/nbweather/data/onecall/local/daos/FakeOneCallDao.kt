package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class FakeOneCallDao(
    private val currentWeatherDao: NBCurrentWeatherDao,
    private val dailyForecastDao: NBDailyForecastDao,
    private val hourlyForecastDao: NBHourlyForecastDao,
    private val minutelyForecastDao: NBMinutelyForecastDao,
    private val nationalWeatherAlertDao: NBNationalWeatherAlertDao
) : NBOneCallDao, NBFakeDao<OneCallMetadataEntityLocal, Long?> {

    private var metadataIdCounter = 1L

    override val stateFlow = MutableStateFlow<List<OneCallMetadataEntityLocal>>(emptyList())

    override fun getKey(item: OneCallMetadataEntityLocal): Long? {
        return item.id
    }

    override fun getOneCall(latitude: Double?, longitude: Double?): Flow<OneCallModelLocal?> {
        return getItem { metadata ->
            metadata.latitude == latitude && metadata.longitude == longitude
        }.map { metadata ->
            if (metadata == null) return@map null
            val metadataId = metadata.id
            combine(
                currentWeatherDao.getCurrentWeather(metadataId),
                dailyForecastDao.getDailyForecasts(metadataId),
                hourlyForecastDao.getHourlyForecasts(metadataId),
                minutelyForecastDao.getMinutelyForecasts(metadataId),
                nationalWeatherAlertDao.getNationalWeatherAlerts(metadataId)
            ) { currentWeather, dailyForecasts, hourlyForecasts, minutelyForecasts, nationalWeatherAlerts ->
                OneCallModelLocal(
                    metadata = metadata,
                    currentWeather = currentWeather,
                    minutelyForecasts = minutelyForecasts,
                    hourlyForecasts = hourlyForecasts,
                    dailyForecasts = dailyForecasts,
                    nationalWeatherAlerts = nationalWeatherAlerts
                )
            }.firstOrNull()
        }
    }

    override fun insertOneCallMetadata(oneCallMetadata: OneCallMetadataEntityLocal): Long {
        val metadataId = oneCallMetadata.id ?: metadataIdCounter++
        val oneCallWithId = oneCallMetadata.copy(id = metadataId)
        insertOrUpdate(oneCallWithId)
        return metadataId
    }

    override fun deleteOneCallMetadata(id: Long?) {
        deleteItemWithKey(id)
    }


}