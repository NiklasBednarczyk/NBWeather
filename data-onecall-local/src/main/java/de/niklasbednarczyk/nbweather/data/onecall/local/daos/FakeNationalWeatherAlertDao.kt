package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.data.localremote.local.daos.NBFakeDao
import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeNationalWeatherAlertDao : NBNationalWeatherAlertDao,
    NBFakeDao<NationalWeatherAlertEntityLocal, Long?> {

    override val stateFlow = MutableStateFlow<List<NationalWeatherAlertEntityLocal>>(emptyList())

    override fun getKey(item: NationalWeatherAlertEntityLocal): Long? {
        return item.metadataId
    }

    override fun getNationalWeatherAlerts(metadataId: Long?): Flow<List<NationalWeatherAlertEntityLocal>?> {
        return getItems(metadataId)
    }

    override fun insertNationalWeatherAlerts(nationalWeatherAlerts: List<NationalWeatherAlertEntityLocal>) {
        insertOrUpdate(nationalWeatherAlerts)
    }

    override fun deleteNationalWeatherAlerts(metadataId: Long?) {
        deleteItemWithKey(metadataId)
    }


}