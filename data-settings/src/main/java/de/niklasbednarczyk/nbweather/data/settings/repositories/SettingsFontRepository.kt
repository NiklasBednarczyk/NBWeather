package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.font.NBFontMapperData
import de.niklasbednarczyk.nbweather.data.settings.proto.font.SettingsFontProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsFontSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsFontRepository @Inject internal constructor(
    override val dataStore: DataStore<SettingsFontProto>
) : RepositoryDisk<SettingsFontProto, NBFontModel>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
        ): SettingsFontRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsFontSerializer(),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_FONT_FILE_NAME
            )
            return SettingsFontRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: OneWayMapperDisk<SettingsFontProto, NBFontModel>
        get() = NBFontMapperData

    suspend fun resetToDefault() {
        dataStore.updateData {
            SettingsFontSerializer.createDefaultValue()
        }
    }

    suspend fun updateSlant(slant: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setSlant(slant)
                .build()
        }
    }

    suspend fun updateWidth(width: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setWidth(width)
                .build()
        }
    }

    suspend fun updateWeight(weight: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setWeight(weight)
                .build()
        }
    }

    suspend fun updateGrade(grade: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setGrade(grade)
                .build()
        }
    }

    suspend fun updateCounterWidth(counterWidth: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setCounterWidth(counterWidth)
                .build()
        }
    }

    suspend fun updateThinStroke(thinStroke: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setThinStroke(thinStroke)
                .build()
        }
    }

    suspend fun updateAscenderHeight(ascenderHeight: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setAscenderHeight(ascenderHeight)
                .build()
        }
    }

    suspend fun updateDescenderDepth(descenderDepth: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setDescenderDepth(descenderDepth)
                .build()
        }
    }

    suspend fun updateFigureHeight(figureHeight: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setFigureHeight(figureHeight)
                .build()
        }
    }

    suspend fun updateLowercaseHeight(lowercaseHeight: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setLowercaseHeight(lowercaseHeight)
                .build()
        }
    }

    suspend fun updateUppercaseHeight(uppercaseHeight: Float) {
        dataStore.updateData { currentProto ->
            currentProto
                .toBuilder()
                .setUppercaseHeight(uppercaseHeight)
                .build()
        }
    }

}