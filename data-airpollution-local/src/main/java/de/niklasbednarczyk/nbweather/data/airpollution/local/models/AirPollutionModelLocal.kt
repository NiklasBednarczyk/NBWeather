package de.niklasbednarczyk.nbweather.data.airpollution.local.models

import androidx.room.Embedded
import androidx.room.Relation
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal

data class AirPollutionModelLocal(
    @Embedded val metadata: AirPollutionMetadataEntityLocal,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val airPollutionItems: List<AirPollutionItemEntityLocal>? = null
)