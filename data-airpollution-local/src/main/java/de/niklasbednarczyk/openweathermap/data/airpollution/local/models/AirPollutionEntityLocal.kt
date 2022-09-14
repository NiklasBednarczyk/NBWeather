package de.niklasbednarczyk.openweathermap.data.airpollution.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.data.airpollution.local.models.airpollution.ComponentsModelLocal

@Entity
data class AirPollutionEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY) val metadataId: Long?,
    val dt: Long?,
    val aqi: Long?,
    @Embedded(prefix = "components_") val components: ComponentsModelLocal?
)