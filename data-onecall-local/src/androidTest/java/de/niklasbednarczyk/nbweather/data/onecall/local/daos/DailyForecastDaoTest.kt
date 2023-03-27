package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class DailyForecastDaoTest : NBOneCallDaoTest<DailyForecastDao, List<DailyForecastEntityLocal>>() {

    override fun initiateDao(): DailyForecastDao = db.dailyForecastDao()

    override fun createArrange(metadataId: Long): List<DailyForecastEntityLocal> =
        createDailyForecasts(metadataId)

    override fun insert(entity: List<DailyForecastEntityLocal>) = dao.insertDailyForecasts(entity)

    override fun delete(metadataId: Long) = dao.deleteDailyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<DailyForecastEntityLocal>?> =
        dao.getDailyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<DailyForecastEntityLocal>,
        act: List<DailyForecastEntityLocal>
    ) = assertAreEqualDailyForecasts(arrange, act)

}