package de.niklasbednarczyk.nbweather.data.settings.di

import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.proto.appearance.SettingsAppearanceProto
import de.niklasbednarczyk.nbweather.data.settings.proto.data.SettingsDataProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsDataSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ProtoModuleSettingsData::class],
)
object FakeModuleSettingsData {

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsAppearance(
        temporaryFolder: TemporaryFolder,
        serializer: SettingsAppearanceSerializer,
    ): DataStore<SettingsAppearanceProto> =
        createFakeDataStore(
            temporaryFolder = temporaryFolder,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_APPEARANCE_FILE_NAME
        )

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsData(
        temporaryFolder: TemporaryFolder,
        serializer: SettingsDataSerializer,
    ): DataStore<SettingsDataProto> =
        createFakeDataStore(
            temporaryFolder = temporaryFolder,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_DATA_FILE_NAME
        )

}