package de.niklasbednarczyk.openweathermap.data.onecall.local.models

import androidx.room.Embedded
import androidx.room.Relation
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.constants.ConstantsCoreLocal
import de.niklasbednarczyk.openweathermap.core.data.localremote.local.models.ModelLocal

data class OneCallModelLocal(
    @Embedded override val metadata: OneCallMetadataEntityLocal,
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
) : ModelLocal