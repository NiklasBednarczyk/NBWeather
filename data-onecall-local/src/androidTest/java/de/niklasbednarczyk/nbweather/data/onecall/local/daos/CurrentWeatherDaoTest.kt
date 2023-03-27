package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import kotlinx.coroutines.flow.Flow

class CurrentWeatherDaoTest : NBOneCallDaoTest<CurrentWeatherDao, CurrentWeatherEntityLocal>() {

    override fun initiateDao(): CurrentWeatherDao = db.currentWeatherDao()

    override fun createArrange(metadataId: Long): CurrentWeatherEntityLocal =
        createCurrentWeatherModel(metadataId)

    override fun insert(entity: CurrentWeatherEntityLocal) = dao.insertCurrentWeather(entity)

    override fun delete(metadataId: Long) = dao.deleteCurrentWeather(metadataId)

    override fun createAct(metadataId: Long): Flow<CurrentWeatherEntityLocal?> =
        dao.getCurrentWeather(metadataId)

    override fun assertAreEqual(
        arrange: CurrentWeatherEntityLocal,
        act: CurrentWeatherEntityLocal
    ) = assertAreEqualCurrentWeather(arrange, act)

}