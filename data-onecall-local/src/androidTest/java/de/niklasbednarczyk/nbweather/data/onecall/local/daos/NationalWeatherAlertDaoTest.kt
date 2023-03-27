package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import kotlinx.coroutines.flow.Flow

class NationalWeatherAlertDaoTest :
    NBOneCallDaoTest<NationalWeatherAlertDao, List<NationalWeatherAlertEntityLocal>>() {

    override fun initiateDao(): NationalWeatherAlertDao = db.nationalWeatherAlertDao()

    override fun createArrange(metadataId: Long): List<NationalWeatherAlertEntityLocal> =
        createNationalWeatherAlert(metadataId)

    override fun insert(entity: List<NationalWeatherAlertEntityLocal>) =
        dao.insertNationalWeatherAlerts(entity)

    override fun delete(metadataId: Long) = dao.deleteNationalWeatherAlerts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<NationalWeatherAlertEntityLocal>?> =
        dao.getNationalWeatherAlerts(metadataId)

    override fun assertAreEqual(
        arrange: List<NationalWeatherAlertEntityLocal>,
        act: List<NationalWeatherAlertEntityLocal>
    ) = assertAreEqualNationalWeatherAlerts(arrange, act)

}