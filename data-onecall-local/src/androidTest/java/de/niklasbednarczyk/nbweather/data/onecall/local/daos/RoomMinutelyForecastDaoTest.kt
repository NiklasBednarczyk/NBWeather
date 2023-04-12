package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import kotlinx.coroutines.flow.Flow

class RoomMinutelyForecastDaoTest :
    NBRoomOneCallDaoTest<RoomMinutelyForecastDao, List<MinutelyForecastEntityLocal>>() {

    override fun getDao(): RoomMinutelyForecastDao = db.minutelyForecastDao()

    override fun createArrange(metadataId: Long): List<MinutelyForecastEntityLocal> =
        createMinutelyForecast(metadataId)

    override fun insert(entity: List<MinutelyForecastEntityLocal>) =
        subject.insertMinutelyForecasts(entity)

    override fun delete(metadataId: Long) = subject.deleteMinutelyForecasts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<MinutelyForecastEntityLocal>?> =
        subject.getMinutelyForecasts(metadataId)

    override fun assertAreEqual(
        arrange: List<MinutelyForecastEntityLocal>,
        act: List<MinutelyForecastEntityLocal>
    ) = assertAreEqualMinutelyForecasts(arrange, act)

}