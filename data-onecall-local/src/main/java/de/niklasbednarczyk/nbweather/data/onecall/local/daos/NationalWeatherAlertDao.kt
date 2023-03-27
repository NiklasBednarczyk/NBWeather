package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface NationalWeatherAlertDao {

    @Query("SELECT * FROM nationalweatheralertentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun getNationalWeatherAlerts(metadataId: Long?): Flow<List<NationalWeatherAlertEntityLocal>?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertNationalWeatherAlerts(nationalWeatherAlerts: List<NationalWeatherAlertEntityLocal>)

    @Query("DELETE FROM nationalweatheralertentitylocal WHERE ${ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY} = :metadataId")
    fun deleteNationalWeatherAlerts(metadataId: Long?)

}