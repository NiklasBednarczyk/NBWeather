package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.units.SettingsUnitsMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.units.TemperatureUnitMapperData
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.units.TemperatureUnitTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.units.SettingsUnitsProto
import de.niklasbednarczyk.openweathermap.data.settings.serializers.SettingsUnitsSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUnitsRepository @Inject constructor(
    @ApplicationContext context: Context
) : RepositoryDisk<SettingsUnitsProto, SettingsUnitsModelData>(
    context = context,
    dataStoreFileName = ConstantsDataSettings.DataStore.SETTINGS_UNITS_FILE_NAME,
    mapper = SettingsUnitsMapperData,
    serializer = SettingsUnitsSerializer
) {

    suspend fun updateTemperatureUnit(temperatureUnit: TemperatureUnitTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTemperatureUnit(TemperatureUnitMapperData.diskToProto(temperatureUnit))
                .build()
        }
    }

}