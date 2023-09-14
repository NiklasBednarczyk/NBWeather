package de.niklasbednarczyk.nbweather.data.airpollution.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.airpollution.local.models.AirPollutionModelLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomAirPollutionDao : NBAirPollutionDao {

    @Transaction
    @Query("SELECT * FROM airpollutionmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    override fun getAirPollution(latitude: Double, longitude: Double?): Flow<AirPollutionModelLocal?>

    @Insert(onConflict = ConstantsCoreLocal.Dao.DEFAULT_ON_CONFLICT)
    override fun insertAirPollution(airPollution: AirPollutionMetadataEntityLocal): Long

    @Query("DELETE FROM airpollutionmetadataentitylocal WHERE latitude = :latitude AND longitude = :longitude")
    override fun deleteAirPollution(latitude: Double?, longitude: Double?)

}