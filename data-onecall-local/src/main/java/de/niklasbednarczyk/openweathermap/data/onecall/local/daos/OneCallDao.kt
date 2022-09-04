package de.niklasbednarczyk.openweathermap.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface OneCallDao {

    @Transaction
    @Query("SELECT * FROM onecallentitylocal WHERE lat = :lat AND lon = :lon LIMIT 1")
    fun getOneCall(lat: Double?, lon: Double?): Flow<OneCallModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    fun insertOneCall(oneCall: OneCallEntityLocal): Long

    @Query("DELETE FROM onecallentitylocal WHERE lat = :lat AND lon = :lon")
    fun deleteOneCall(lat: Double?, lon: Double?)


}