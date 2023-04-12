package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import kotlinx.coroutines.flow.Flow

class RoomNationalWeatherAlertDaoTest :
    NBRoomOneCallDaoTest<RoomNationalWeatherAlertDao, List<NationalWeatherAlertEntityLocal>>() {

    override fun getDao(): RoomNationalWeatherAlertDao = db.nationalWeatherAlertDao()

    override fun createArrange(metadataId: Long): List<NationalWeatherAlertEntityLocal> =
        createNationalWeatherAlert(metadataId)

    override fun insert(entity: List<NationalWeatherAlertEntityLocal>) =
        subject.insertNationalWeatherAlerts(entity)

    override fun delete(metadataId: Long) = subject.deleteNationalWeatherAlerts(metadataId)

    override fun createAct(metadataId: Long): Flow<List<NationalWeatherAlertEntityLocal>?> =
        subject.getNationalWeatherAlerts(metadataId)

    override fun assertAreEqual(
        arrange: List<NationalWeatherAlertEntityLocal>,
        act: List<NationalWeatherAlertEntityLocal>
    ) = assertAreEqualNationalWeatherAlerts(arrange, act)

}