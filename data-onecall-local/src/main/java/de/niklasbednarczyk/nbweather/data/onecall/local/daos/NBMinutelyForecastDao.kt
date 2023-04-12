package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

interface NBMinutelyForecastDao {

    fun getMinutelyForecasts(metadataId: Long?): Flow<List<MinutelyForecastEntityLocal>?>

    fun insertMinutelyForecasts(minutelyForecasts: List<MinutelyForecastEntityLocal>)

    fun deleteMinutelyForecasts(metadataId: Long?)

}