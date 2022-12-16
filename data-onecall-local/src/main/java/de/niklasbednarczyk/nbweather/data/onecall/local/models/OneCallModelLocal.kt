package de.niklasbednarczyk.nbweather.data.onecall.local.models

import androidx.room.Embedded
import androidx.room.Relation
import de.niklasbednarczyk.nbweather.core.data.localremote.local.constants.ConstantsCoreLocal

data class OneCallModelLocal(
    @Embedded val metadata: OneCallMetadataEntityLocal,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val currentWeather: CurrentWeatherEntityLocal? = null,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val minutelyForecasts: List<MinutelyForecastEntityLocal>? = null,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val hourlyForecasts: List<HourlyForecastEntityLocal>? = null,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val dailyForecasts: List<DailyForecastEntityLocal>? = null,
    @Relation(
        parentColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_PARENT,
        entityColumn = ConstantsCoreLocal.ColumnName.METADATA_ID_ENTITY
    )
    val nationalWeatherAlerts: List<NationalWeatherAlertEntityLocal>? = null
)