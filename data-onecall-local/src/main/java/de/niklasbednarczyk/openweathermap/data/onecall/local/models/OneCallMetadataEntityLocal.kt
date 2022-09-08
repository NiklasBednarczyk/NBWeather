package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.common.display.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.UnitsType
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.models.MetadataEntityLocal

@Entity
data class OneCallMetadataEntityLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT) val id: Long? = null,
    val units: UnitsType,
    val language: DataLanguageType,
    val latitude: Double,
    val longitude: Double,
    val timezone: String?,
    val timezoneOffset: Long?,
) : MetadataEntityLocal()