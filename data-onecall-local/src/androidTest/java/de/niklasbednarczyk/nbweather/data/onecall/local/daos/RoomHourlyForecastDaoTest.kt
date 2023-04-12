package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class RoomHourlyForecastDaoTest :
    NBRoomOneCallDaoTest<RoomHourlyForecastDao, List<HourlyForecastEntityLocal>>() {

    override fun getDao(): RoomHourlyForecastDao = db.hourlyForecastDao()

    override fun createArrange(metadataId: Long): List<HourlyForecastEntityLocal> =
        createHourlyForecasts(metadataId)

    override fun insert(entity: List<HourlyForecastEntityLocal>) = subject.insertHourlyForecasts(entity)

    override fun delete(metadataId: Long) = subject.deleteHourlyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<HourlyForecastEntityLocal>?> =
        subject.getHourlyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<HourlyForecastEntityLocal>,
        act: List<HourlyForecastEntityLocal>
    ) = assertAreEqualHourlyForecasts(arrange, act)

}