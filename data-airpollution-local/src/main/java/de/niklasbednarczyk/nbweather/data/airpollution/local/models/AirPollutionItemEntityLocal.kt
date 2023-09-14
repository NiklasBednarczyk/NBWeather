package de.niklasbednarczyk.nbweather.data.airpollution.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal

@Entity
data class AirPollutionItemEntityLocal(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY) val metadataId: Long?,
    val dt: Long?,
    @Embedded(prefix = "main_") val main: MainModelLocal?,
    @Embedded(prefix = "components_") val components: ComponentsModelLocal?
)