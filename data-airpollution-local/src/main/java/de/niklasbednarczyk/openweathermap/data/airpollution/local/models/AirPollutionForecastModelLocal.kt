package de.niklasbednarczyk.openweathermap.data.airpollution.local.models

import androidx.room.Embedded
import androidx.room.Relation
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal

data class AirPollutionForecastModelLocal(
    @Embedded val metadata: AirPollutionForecastMetadataEntityLocal,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val airPollutions: List<AirPollutionEntityLocal>? = null,
)