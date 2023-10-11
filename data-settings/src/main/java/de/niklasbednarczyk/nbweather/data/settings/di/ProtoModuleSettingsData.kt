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
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto
import de.niklasbednarczyk.nbweather.data.settings.proto.order.SettingsOrderProto
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsAppearanceSerializer
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsFontSerializer
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsOrderSerializer
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsUnitsSerializer
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
    internal fun provideDataStoreSettingsFont(
        @ApplicationContext context: Context,
        serializer: SettingsFontSerializer,
    ): DataStore<SettingsFontProto> =
        createProtoDataStore(
            context = context,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_FONT_FILE_NAME
        )

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsOrder(
        @ApplicationContext context: Context,
        serializer: SettingsOrderSerializer,
    ): DataStore<SettingsOrderProto> =
        createProtoDataStore(
            context = context,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_ORDER_FILE_NAME
        )

    @Provides
    @Singleton
    internal fun provideDataStoreSettingsUnits(
        @ApplicationContext context: Context,
        serializer: SettingsUnitsSerializer,
    ): DataStore<SettingsUnitsProto> =
        createProtoDataStore(
            context = context,
            serializer = serializer,
            fileName = ConstantsDataSettings.DataStore.SETTINGS_UNITS_FILE_NAME
        )

}