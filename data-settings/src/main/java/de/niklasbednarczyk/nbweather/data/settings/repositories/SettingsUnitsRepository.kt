package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.data.disk.mappers.OneWayMapperDisk
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.core.data.disk.utils.createFakeDataStore
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBDistanceUnitMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBPrecipitationUnitMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBPressureUnitMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBTemperatureUnitMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBUnitsMapperData
import de.niklasbednarczyk.nbweather.data.settings.mappers.units.NBWindSpeedUnitMapperData
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsUnitsSerializer
import org.junit.rules.TemporaryFolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUnitsRepository @Inject internal constructor(
    override val dataStore: DataStore<SettingsUnitsProto>
) : RepositoryDisk<SettingsUnitsProto, NBUnitsModel>() {

    companion object {

        fun createFake(
            temporaryFolder: TemporaryFolder,
        ): SettingsUnitsRepository {
            val dataStore = createFakeDataStore(
                temporaryFolder = temporaryFolder,
                serializer = SettingsUnitsSerializer(),
                fileName = ConstantsDataSettings.DataStore.SETTINGS_UNITS_FILE_NAME
            )
            return SettingsUnitsRepository(
                dataStore = dataStore
            )
        }

    }

    override val mapper: OneWayMapperDisk<SettingsUnitsProto, NBUnitsModel>
        get() = NBUnitsMapperData

    suspend fun updateTemperatureUnit(temperatureUnit: NBTemperatureUnitType) {
        updateData { currentProto ->
            currentProto.toBuilder()
                .setTemperatureUnit(NBTemperatureUnitMapperData.dataToProto(temperatureUnit))
                .build()
        }
    }

    suspend fun updatePrecipitationUnit(precipitationUnit: NBPrecipitationUnitType) {
        updateData { currentProto ->
            currentProto.toBuilder()
                .setPrecipitationUnit(NBPrecipitationUnitMapperData.dataToProto(precipitationUnit))
                .build()
        }
    }

    suspend fun updateDistanceUnit(distanceUnit: NBDistanceUnitType) {
        updateData { currentProto ->
            currentProto.toBuilder()
                .setDistanceUnit(NBDistanceUnitMapperData.dataToProto(distanceUnit))
                .build()
        }
    }

    suspend fun updatePressureUnit(pressureUnit: NBPressureUnitType) {
        updateData { currentProto ->
            currentProto.toBuilder()
                .setPressureUnit(NBPressureUnitMapperData.dataToProto(pressureUnit))
                .build()
        }
    }

    suspend fun updateWindSpeedUnit(windSpeedUnit: NBWindSpeedUnitType) {
        updateData { currentProto ->
            currentProto.toBuilder()
                .setWindSpeedUnit(NBWindSpeedUnitMapperData.dataToProto(windSpeedUnit))
                .build()
        }
    }

}