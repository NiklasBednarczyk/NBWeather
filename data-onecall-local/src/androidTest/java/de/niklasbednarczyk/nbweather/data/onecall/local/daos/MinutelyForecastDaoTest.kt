package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class MinutelyForecastDaoTest :
    NBOneCallDaoTest<MinutelyForecastDao, List<MinutelyForecastEntityLocal>>() {

    override fun initiateDao(): MinutelyForecastDao = db.minutelyForecastDao()

    override fun createArrange(metadataId: Long): List<MinutelyForecastEntityLocal> =
        createMinutelyForecast(metadataId)

    override fun insert(entity: List<MinutelyForecastEntityLocal>) =
        dao.insertMinutelyForecasts(entity)

    override fun delete(metadataId: Long) = dao.deleteMinutelyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<MinutelyForecastEntityLocal>?> =
        dao.getMinutelyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<MinutelyForecastEntityLocal>,
        act: List<MinutelyForecastEntityLocal>
    ) = assertAreEqualMinutelyForecasts(arrange, act)

}