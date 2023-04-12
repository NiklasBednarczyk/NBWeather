package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow

class RoomCurrentWeatherDaoTest : NBRoomOneCallDaoTest<RoomCurrentWeatherDao, CurrentWeatherEntityLocal>() {

    override fun getDao(): RoomCurrentWeatherDao = db.currentWeatherDao()

    override fun createArrange(metadataId: Long): CurrentWeatherEntityLocal =
        createCurrentWeatherModel(metadataId)

    override fun insert(entity: CurrentWeatherEntityLocal) = subject.insertCurrentWeather(entity)

    override fun delete(metadataId: Long) = subject.deleteCurrentWeather(metadataId)

    override fun createAct(metadataId: Long): Flow<CurrentWeatherEntityLocal?> =
        subject.getCurrentWeather(metadataId)

    override fun assertAreEqual(
        arrange: CurrentWeatherEntityLocal,
        act: CurrentWeatherEntityLocal
    ) = assertAreEqualCurrentWeather(arrange, act)

}