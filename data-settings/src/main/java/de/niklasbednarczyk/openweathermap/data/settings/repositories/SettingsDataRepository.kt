package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.UnitsType
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.DataLanguageMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.SettingsDataMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.data.UnitsMapperData
import de.niklasbednarczyk.openweathermap.data.settings.models.SettingsDataModelData
import de.niklasbednarczyk.openweathermap.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.openweathermap.data.settings.serializers.SettingsDataSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataRepository @Inject constructor(
    @ApplicationContext context: Context
) : RepositoryDisk<SettingsDataProto, SettingsDataModelData>(
    context = context,
    dataStoreFileName = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME,
    mapper = SettingsDataMapperData,
    serializer = SettingsDataSerializer
) {

    suspend fun updateUnits(units: UnitsType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUnits(UnitsMapperData.diskToProto(units))
                .build()
        }
    }

    suspend fun updateDataLanguage(dataLanguage: DataLanguageType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setDataLanguage(DataLanguageMapperData.diskToProto(dataLanguage))
                .build()
        }
    }

}