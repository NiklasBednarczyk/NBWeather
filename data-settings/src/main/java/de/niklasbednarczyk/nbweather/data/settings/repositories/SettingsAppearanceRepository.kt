package de.niklasbednarczyk.nbweather.data.settings.repositories

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.ColorSchemeMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.SettingsAppearanceMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.appearance.ThemeMapperData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
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

    suspend fun updateColorScheme(colorScheme: ColorSchemeTypeData) {
        diskStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setColorScheme(ColorSchemeMapperData.diskToProto(colorScheme))
                .build()
        }
    }

}