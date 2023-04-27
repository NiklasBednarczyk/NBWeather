package de.niklasbednarczyk.nbweather.data.settings.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.LanguageMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.SettingsDataMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.TimeFormatMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.data.UnitsMapperData
import de.niklasbednarczyk.nbweather.data.settings.models.data.SettingsDataModelData
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsDataSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataRepository @Inject internal constructor(
    override val dataStore: DataStore<SettingsDataProto>
) : RepositoryDisk<SettingsDataProto, SettingsDataModelData>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
            context: Context
        ): SettingsDataRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsDataSerializer(context),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME
            )
            return SettingsDataRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: OneWayMapperDisk<SettingsDataProto, SettingsDataModelData>
        get() = SettingsDataMapperData

    suspend fun updateLanguage(language: NBLanguageType) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setLanguage(LanguageMapperData.dataToProto(language))
                .build()
        }
    }

    suspend fun updateUnits(units: NBUnitsType) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUnits(UnitsMapperData.dataToProto(units))
                .build()
        }
    }

    suspend fun updateTimeFormat(timeFormat: NBTimeFormatType) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTimeFormat(TimeFormatMapperData.dataToProto(timeFormat))
                .build()
        }
    }

}