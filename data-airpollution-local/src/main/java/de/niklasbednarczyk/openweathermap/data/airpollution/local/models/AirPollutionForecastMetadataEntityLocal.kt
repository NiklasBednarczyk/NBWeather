package de.niklasbednarczyk.openweathermap.data.airpollution.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.models.MetadataEntityLocal

@Entity
data class AirPollutionForecastMetadataEntityLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT) val id: Long? = null,
    val latitude: Double,
    val longitude: Double
) : MetadataEntityLocal()