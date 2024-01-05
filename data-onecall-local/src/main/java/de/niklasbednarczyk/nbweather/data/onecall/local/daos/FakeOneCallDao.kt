package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.*

class FakeOneCallDao(
    private val currentWeatherDao: NBCurrentWeatherDao,
    private val dailyForecastDao: NBDailyForecastDao,
    private val hourlyForecastDao: NBHourlyForecastDao,
    private val minutelyForecastDao: NBMinutelyForecastDao,
    private val nationalWeatherAlertDao: NBNationalWeatherAlertDao
) : NBOneCallDao, NBFakeDao<OneCallMetadataEntityLocal, Pair<Double?, Double?>> {

    private var metadataIdCounter = 1L

    override val stateFlow = MutableStateFlow<List<OneCallMetadataEntityLocal>>(emptyList())

    override fun getKey(item: OneCallMetadataEntityLocal): Pair<Double, Double> {
        return Pair(item.latitude, item.longitude)
    }

    override fun getOneCall(latitude: Double?, longitude: Double?): Flow<OneCallModelLocal?> {
        return getItem(Pair(latitude, longitude)).map { metadata ->
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

    override fun insertOneCall(oneCall: OneCallMetadataEntityLocal): Long {
        val metadataId = oneCall.id ?: metadataIdCounter++
        val oneCallWithId = oneCall.copy(id = metadataId)
        insertOrUpdate(oneCallWithId)
        return metadataId
    }

    override fun deleteOneCall(latitude: Double?, longitude: Double?) {
        deleteItemWithKey(Pair(latitude, longitude))
    }


}