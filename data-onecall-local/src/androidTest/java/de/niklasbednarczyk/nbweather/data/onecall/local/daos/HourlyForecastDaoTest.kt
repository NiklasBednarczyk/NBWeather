package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class HourlyForecastDaoTest :
    NBOneCallDaoTest<HourlyForecastDao, List<HourlyForecastEntityLocal>>() {

    override fun initiateDao(): HourlyForecastDao = db.hourlyForecastDao()

    override fun createArrange(metadataId: Long): List<HourlyForecastEntityLocal> =
        createHourlyForecasts(metadataId)

    override fun insert(entity: List<HourlyForecastEntityLocal>) = dao.insertHourlyForecasts(entity)

    override fun delete(metadataId: Long) = dao.deleteHourlyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<HourlyForecastEntityLocal>?> =
        dao.getHourlyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<HourlyForecastEntityLocal>,
        act: List<HourlyForecastEntityLocal>
    ) = assertAreEqualHourlyForecasts(arrange, act)

}