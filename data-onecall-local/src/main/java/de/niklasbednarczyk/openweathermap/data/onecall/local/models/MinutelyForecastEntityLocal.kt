package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal

@Entity
data class MinutelyForecastEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY) val metadataId: Long?,
    val dt: Long?,
    val precipitation: Double?
)