package de.niklasbednarczyk.nbweather.data.onecall.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal

@Entity
data class NationalWeatherAlertEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY) val metadataId: Long?,
    val senderName: String?,
    val event: String?,
    val start: Long?,
    val end: Long?,
    val description: String?,
    val tags: List<String>?
)