package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.ColorSchemeMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.SettingsAppearanceMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.ThemeMapperData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsAppearanceRepository @Inject constructor(
    override val dataStore: DataStore<SettingsAppearanceProto>
) : RepositoryDisk<SettingsAppearanceProto, SettingsAppearanceModelData>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
        ): SettingsAppearanceRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsAppearanceSerializer(),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME
            )
            return SettingsAppearanceRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: OneWayMapperDisk<SettingsAppearanceProto, SettingsAppearanceModelData>
        get() = SettingsAppearanceMapperData

    suspend fun updateTheme(theme: ThemeTypeData) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTheme(ThemeMapperData.dataToProto(theme))
                .build()
        }
    }

    suspend fun updateColorScheme(colorScheme: ColorSchemeTypeData) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setColorScheme(ColorSchemeMapperData.dataToProto(colorScheme))
                .build()
        }
    }

}