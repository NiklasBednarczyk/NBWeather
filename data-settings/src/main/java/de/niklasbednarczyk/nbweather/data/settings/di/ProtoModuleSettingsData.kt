package de.niklasbednarczyk.nbweather.data.settings.di

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createProtoDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsDataSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtoModuleSettingsData {

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsAppearance(
        @ApplicationContext context: Context,
        serializer: SettingsAppearanceSerializer,
    ): DataStore<SettingsAppearanceProto> =
        createProtoDataStore(
            context = context,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME
        )

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsData(
        @ApplicationContext context: Context,
        serializer: SettingsDataSerializer,
    ): DataStore<SettingsDataProto> =
        createProtoDataStore(
            context = context,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME
        )

}