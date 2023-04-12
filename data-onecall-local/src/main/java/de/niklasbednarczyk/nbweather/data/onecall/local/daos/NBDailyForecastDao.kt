package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

interface NBDailyForecastDao {

    fun getDailyForecasts(metadataId: Long?): Flow<List<DailyForecastEntityLocal>?>

    fun insertDailyForecasts(dailyForecasts: List<DailyForecastEntityLocal>)

    fun deleteDailyForecasts(metadataId: Long?)

}