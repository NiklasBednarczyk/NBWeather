package de.niklasbednarczyk.nbweather.data.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.data.settings.constants.ConstantsDataSettings
import de.niklasbednarczyk.nbweather.data.settings.proto.units.SettingsUnitsProto
import de.niklasbednarczyk.nbweather.data.settings.serializers.SettingsUnitsSerializer
import de.niklasbednarczyk.nbweather.test.data.disk.repositories.NBDiskRepositoryTest
import org.junit.Test

class SettingsUnitsRepositoryTest :
    NBDiskRepositoryTest<SettingsUnitsProto, NBUnitsModel, SettingsUnitsRepository>() {

    override val fileName: String
        get() = ConstantsDataSettings.DataStore.SETTINGS_UNITS_FILE_NAME

    override val updateStartingProto: SettingsUnitsProto
        get() = SettingsUnitsProto.newBuilder()
            .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.CELSIUS)
            .setPrecipitationUnit(SettingsUnitsProto.PrecipitationUnitProto.MILLIMETER)
            .setDistanceUnit(SettingsUnitsProto.DistanceUnitProto.KILOMETER)
            .setPressureUnit(SettingsUnitsProto.PressureUnitProto.HECTOPASCAL)
            .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.METER_PER_SECOND)
            .build()

    override fun createSerializer(): Serializer<SettingsUnitsProto> {
        return SettingsUnitsSerializer()
    }

    override fun createRepository(dataStore: DataStore<SettingsUnitsProto>): SettingsUnitsRepository {
        return SettingsUnitsRepository(dataStore)
    }

    @Test
    fun getData_temperatureUnit_celsius_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.CELSIUS)
                .build(),
            assertValue = { actual ->
                assertValue(NBTemperatureUnitType.CELSIUS, actual.temperatureUnit)
            }
        )
    }

    @Test
    fun getData_temperatureUnit_fahrenheit_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT)
                .build(),
            assertValue = { actual ->
                assertValue(NBTemperatureUnitType.FAHRENHEIT, actual.temperatureUnit)
            }
        )
    }

    @Test
    fun getData_temperatureUnit_kelvin_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setTemperatureUnit(SettingsUnitsProto.TemperatureUnitProto.KELVIN)
                .build(),
            assertValue = { actual ->
                assertValue(NBTemperatureUnitType.KELVIN, actual.temperatureUnit)
            }
        )
    }

    @Test
    fun getData_precipitationUnit_inch_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setPrecipitationUnit(SettingsUnitsProto.PrecipitationUnitProto.INCH)
                .build(),
            assertValue = { actual ->
                assertValue(NBPrecipitationUnitType.INCH, actual.precipitationUnit)
            }
        )
    }

    @Test
    fun getData_precipitationUnit_millimeter_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setPrecipitationUnit(SettingsUnitsProto.PrecipitationUnitProto.MILLIMETER)
                .build(),
            assertValue = { actual ->
                assertValue(NBPrecipitationUnitType.MILLIMETER, actual.precipitationUnit)
            }
        )
    }

    @Test
    fun getData_distanceUnit_kilometer_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setDistanceUnit(SettingsUnitsProto.DistanceUnitProto.KILOMETER)
                .build(),
            assertValue = { actual ->
                assertValue(NBDistanceUnitType.KILOMETER, actual.distanceUnit)
            }
        )
    }

    @Test
    fun getData_distanceUnit_mile_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setDistanceUnit(SettingsUnitsProto.DistanceUnitProto.MILE)
                .build(),
            assertValue = { actual ->
                assertValue(NBDistanceUnitType.MILE, actual.distanceUnit)
            }
        )
    }

    @Test
    fun getData_pressureUnit_hectopascal_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setPressureUnit(SettingsUnitsProto.PressureUnitProto.HECTOPASCAL)
                .build(),
            assertValue = { actual ->
                assertValue(NBPressureUnitType.HECTOPASCAL, actual.pressureUnit)
            }
        )
    }

    @Test
    fun getData_pressureUnit_inchHg_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setPressureUnit(SettingsUnitsProto.PressureUnitProto.INCH_HG)
                .build(),
            assertValue = { actual ->
                assertValue(NBPressureUnitType.INCH_HG, actual.pressureUnit)
            }
        )
    }

    @Test
    fun getData_pressureUnit_millimeter_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setPressureUnit(SettingsUnitsProto.PressureUnitProto.MILLIMETER_OF_MERCURY)
                .build(),
            assertValue = { actual ->
                assertValue(NBPressureUnitType.MILLIMETER_OF_MERCURY, actual.pressureUnit)
            }
        )
    }

    @Test
    fun getData_windSpeedUnit_kilometerPerHour_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.KILOMETER_PER_HOUR)
                .build(),
            assertValue = { actual ->
                assertValue(NBWindSpeedUnitType.KILOMETER_PER_HOUR, actual.windSpeedUnit)
            }
        )
    }

    @Test
    fun getData_windSpeedUnit_meterPerSecond_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.METER_PER_SECOND)
                .build(),
            assertValue = { actual ->
                assertValue(NBWindSpeedUnitType.METER_PER_SECOND, actual.windSpeedUnit)
            }
        )
    }

    @Test
    fun getData_windSpeedUnit_milePerHour_shouldBeMappedCorrectly() {
        testGetData(
            proto = SettingsUnitsProto
                .newBuilder()
                .setWindSpeedUnit(SettingsUnitsProto.WindSpeedUnitProto.MILE_PER_HOUR)
                .build(),
            assertValue = { actual ->
                assertValue(NBWindSpeedUnitType.MILE_PER_HOUR, actual.windSpeedUnit)
            }
        )
    }

    @Test
    fun updateTemperatureUnit_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateTemperatureUnit(NBTemperatureUnitType.FAHRENHEIT)
            },
            assertValue = { actual ->
                assertValue(SettingsUnitsProto.TemperatureUnitProto.FAHRENHEIT, actual.temperatureUnit)
            }
        )
    }

    @Test
    fun updatePrecipitationUnit_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updatePrecipitationUnit(NBPrecipitationUnitType.INCH)
            },
            assertValue = { actual ->
                assertValue(SettingsUnitsProto.PrecipitationUnitProto.INCH, actual.precipitationUnit)
            }
        )
    }

    @Test
    fun updateDistanceUnit_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateDistanceUnit(NBDistanceUnitType.MILE)
            },
            assertValue = { actual ->
                assertValue(SettingsUnitsProto.DistanceUnitProto.MILE, actual.distanceUnit)
            }
        )
    }

    @Test
    fun updatePressureUnit_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updatePressureUnit(NBPressureUnitType.INCH_HG)
            },
            assertValue = { actual ->
                assertValue(SettingsUnitsProto.PressureUnitProto.INCH_HG, actual.pressureUnit)
            }
        )
    }

    @Test
    fun updateWindSpeedUnit_shouldUpdateCorrectly() {
        testUpdate(
            update = {
                subject.updateWindSpeedUnit(NBWindSpeedUnitType.MILE_PER_HOUR)
            },
            assertValue = { actual ->
                assertValue(SettingsUnitsProto.WindSpeedUnitProto.MILE_PER_HOUR, actual.windSpeedUnit)
            }
        )
    }

}