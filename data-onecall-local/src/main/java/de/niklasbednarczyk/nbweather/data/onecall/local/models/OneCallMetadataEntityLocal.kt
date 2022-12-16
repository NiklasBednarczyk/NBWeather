package de.niklasbednarczyk.nbweather.data.onecall.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.nbweather.core.data.localremote.local.models.MetadataEntityLocal

@Entity
data class OneCallMetadataEntityLocal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT) val id: Long? = null,
    val language: NBLanguageType,
    val units: NBUnitsType,
    val latitude: Double,
    val longitude: Double,
    val timezone: String?,
    val timezoneOffset: Long?,
) : MetadataEntityLocal()