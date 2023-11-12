package de.niklasbednarczyk.nbweather.data.settings.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.NBColorSchemeMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.NBAppearanceMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.NBThemeMapperData
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBColorSchemeType
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsAppearanceRepository @Inject internal constructor(
    override val dataStore: DataStore<SettingsAppearanceProto>
) : RepositoryDisk<SettingsAppearanceProto, NBAppearanceModel>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
            context: Context
        ): SettingsAppearanceRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsAppearanceSerializer(context),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME
            )
            return SettingsAppearanceRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: OneWayMapperDisk<SettingsAppearanceProto, NBAppearanceModel>
        get() = NBAppearanceMapperData

    suspend fun updateUseDeviceTheme(useDeviceTheme: Boolean) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUseDeviceTheme(useDeviceTheme)
                .build()
        }
    }

    suspend fun updateTheme(theme: NBThemeType) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTheme(NBThemeMapperData.dataToProto(theme))
                .build()
        }
    }

    suspend fun updateUseDynamicColorScheme(useDynamicColorScheme: Boolean) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUseDynamicColorScheme(useDynamicColorScheme)
                .build()
        }
    }

    suspend fun updateColorScheme(colorScheme: NBColorSchemeType) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setColorScheme(NBColorSchemeMapperData.dataToProto(colorScheme))
                .build()
        }
    }

}