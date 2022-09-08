package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface OneCallDao {

    @Transaction
    @Query("SELECT * FROM onecallmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    fun getOneCall(latitude: Double?, longitude: Double?): Flow<OneCallModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertOneCall(oneCall: OneCallMetadataEntityLocal): Long

    @Query("DELETE FROM onecallmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude")
    fun deleteOneCall(latitude: Double?, longitude: Double?)


}