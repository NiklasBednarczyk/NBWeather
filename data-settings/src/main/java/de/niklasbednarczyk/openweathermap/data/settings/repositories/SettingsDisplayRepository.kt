package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.display.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.display.DataLanguageMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.display.SettingsDisplayMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.display.TimeFormatMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.display.UnitsMapperData
import de.niklasbednarczyk.openweathermap.data.settings.models.display.SettingsDisplayModelData
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


    suspend fun updateDataLanguage(dataLanguage: OwmDataLanguageType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setDataLanguage(DataLanguageMapperData.diskToProto(dataLanguage))
                .build()
        }
    }

    suspend fun updateUnits(units: OwmUnitsType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUnits(UnitsMapperData.diskToProto(units))
                .build()
        }
    }

    suspend fun updateTimeFormat(timeFormat: OwmTimeFormatType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTimeFormat(TimeFormatMapperData.diskToProto(timeFormat))
                .build()
        }
    }

}