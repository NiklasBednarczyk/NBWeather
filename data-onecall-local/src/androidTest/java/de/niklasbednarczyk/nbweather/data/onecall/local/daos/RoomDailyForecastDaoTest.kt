package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class RoomDailyForecastDaoTest : NBRoomOneCallDaoTest<RoomDailyForecastDao, List<DailyForecastEntityLocal>>() {

    override fun getDao(): RoomDailyForecastDao = db.dailyForecastDao()

    override fun createArrange(metadataId: Long): List<DailyForecastEntityLocal> =
        createDailyForecasts(metadataId)

    override fun insert(entity: List<DailyForecastEntityLocal>) = subject.insertDailyForecasts(entity)

    override fun delete(metadataId: Long) = subject.deleteDailyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<DailyForecastEntityLocal>?> =
        subject.getDailyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<DailyForecastEntityLocal>,
        act: List<DailyForecastEntityLocal>
    ) = assertAreEqualDailyForecasts(arrange, act)

}