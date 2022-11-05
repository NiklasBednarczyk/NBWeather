package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.display.*
import de.niklasbednarczyk.openweathermap.data.settings.models.display.*
import de.niklasbednarczyk.openweathermap.data.settings.proto.display.SettingsDisplayProto
import de.niklasbednarczyk.openweathermap.data.settings.serializers.SettingsDisplaySerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDisplayRepository @Inject constructor(
    @ApplicationContext context: Context
) : RepositoryDisk<SettingsDisplayProto, SettingsDisplayModelData>(
    context = context,
    dataStoreFileName = ConstantsDataSettings.DataStore.SETTINGS_DISPLAY_FILE_NAME,
    mapper = SettingsDisplayMapperData,
    serializer = SettingsDisplaySerializer
) {

    suspend fun updateTemperatureUnit(temperatureUnit: TemperatureUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTemperatureUnit(TemperatureUnitMapperData.diskToProto(temperatureUnit))
                .build()
        }
    }

    suspend fun updateWindSpeedUnit(windSpeedUnit: WindSpeedUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setWindSpeedUnit(WindSpeedUnitMapperData.diskToProto(windSpeedUnit))
                .build()
        }
    }

    suspend fun updatePressureUnit(pressureUnit: PressureUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setPressureUnit(PressureUnitMapperData.diskToProto(pressureUnit))
                .build()
        }
    }

    suspend fun updateDistanceUnit(distanceUnit: DistanceUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setDistanceUnit(DistanceUnitMapperData.diskToProto(distanceUnit))
                .build()
        }
    }

    suspend fun updatePrecipitationUnit(precipitationUnit: PrecipitationUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setPrecipitationUnit(PrecipitationUnitMapperData.diskToProto(precipitationUnit))
                .build()
        }
    }

    suspend fun updateTimeFormat(timeFormat: TimeFormatTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTimeFormat(TimeFormatMapperData.diskToProto(timeFormat))
                .build()
        }
    }

}