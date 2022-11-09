package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.LanguageMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.SettingsDataMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.TimeFormatMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.UnitsMapperData
import de.niklasbednarczyk.openweathermap.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.openweathermap.data.settings.serializers.SettingsDataSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataRepository @Inject internal constructor(
    @ApplicationContext context: Context,
    serializer: SettingsDataSerializer
) : RepositoryDisk<SettingsDataProto, SettingsDataModelData>(
    context = context,
    dataStoreFileName = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME,
    mapper = SettingsDataMapperData,
    serializer = serializer
) {


    suspend fun updateLanguage(language: OwmLanguageType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setLanguage(LanguageMapperData.diskToProto(language))
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