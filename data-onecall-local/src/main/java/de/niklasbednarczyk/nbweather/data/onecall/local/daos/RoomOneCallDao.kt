package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomOneCallDao : NBOneCallDao {

    @Transaction
    @Query("SELECT * FROM onecallmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    override fun getOneCall(latitude: Double?, longitude: Double?): Flow<OneCallModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    override fun insertOneCallMetadata(oneCallMetadata: OneCallMetadataEntityLocal): Long

    @Query("DELETE FROM onecallmetadataentitylocal WHERE id = :id")
    override fun deleteOneCallMetadata(id: Long?)

}