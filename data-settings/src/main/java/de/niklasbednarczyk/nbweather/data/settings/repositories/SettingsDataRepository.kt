package de.niklasbednarczyk.nbweather.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.LanguageMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.SettingsDataMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.TimeFormatMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.UnitsMapperData
import de.niklasbednarczyk.nbweather.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsDataSerializer
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


    suspend fun updateLanguage(language: NBLanguageType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setLanguage(LanguageMapperData.diskToProto(language))
                .build()
        }
    }

    suspend fun updateUnits(units: NBUnitsType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUnits(UnitsMapperData.diskToProto(units))
                .build()
        }
    }

    suspend fun updateTimeFormat(timeFormat: NBTimeFormatType) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTimeFormat(TimeFormatMapperData.diskToProto(timeFormat))
                .build()
        }
    }

}