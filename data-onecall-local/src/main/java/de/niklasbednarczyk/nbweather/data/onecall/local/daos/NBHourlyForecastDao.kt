package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

interface NBHourlyForecastDao {

    fun getHourlyForecasts(metadataId: Long?): Flow<List<HourlyForecastEntityLocal>?>

    fun insertHourlyForecasts(hourlyForecasts: List<HourlyForecastEntityLocal>)

    fun deleteHourlyForecasts(metadataId: Long?)

}