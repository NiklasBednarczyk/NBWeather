package de.niklasbednarczyk.openweathermap.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.openweathermap.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.openweathermap.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.openweathermap.data.settings.mappers.appearance.ColorSchemeMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.appearance.SettingsAppearanceMapperData
import de.niklasbednarczyk.openweathermap.data.settings.mappers.appearance.ThemeMapperData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.openweathermap.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.openweathermap.data.settings.serializers.SettingsAppearanceSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsAppearanceRepository @Inject constructor(
    @ApplicationContext context: Context
) : RepositoryDisk<SettingsAppearanceProto, SettingsAppearanceModelData>(
    context = context,
    dataStoreFileName = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME,
    mapper = SettingsAppearanceMapperData,
    serializer = SettingsAppearanceSerializer
) {

    suspend fun updateTheme(theme: ThemeTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setTheme(ThemeMapperData.diskToProto(theme))
                .build()
        }
    }

    suspend fun updateUseDynamicColorScheme(useDynamicColorScheme: Boolean) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUseDynamicColorScheme(useDynamicColorScheme)
                .build()
        }
    }

    suspend fun updateColorScheme(colorScheme: ColorSchemeTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setColorScheme(ColorSchemeMapperData.diskToProto(colorScheme))
                .build()
        }
    }

}