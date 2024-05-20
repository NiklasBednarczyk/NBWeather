package de.niklasbednarczyk.nbweather.feature.settings.screens.units.models

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test

class SettingsUnitsModelsTest : NBTest {

    @Test
    fun items_shouldConvertCorrectly() {
        // Arrange
        val units = NBUnitsModel(
            temperatureUnit = NBTemperatureUnitType.FAHRENHEIT,
            precipitationUnit = NBPrecipitationUnitType.INCH,
            distanceUnit = NBDistanceUnitType.KILOMETER,
            pressureUnit = NBPressureUnitType.MILLIMETER_OF_MERCURY,
            windSpeedUnit = NBWindSpeedUnitType.MILE_PER_HOUR
        )

        // Act
        val items = SettingsUnitsItemModel.from(
            units = units,
            updateTemperatureUnit = {},
            updatePrecipitationUnit = {},
            updateDistanceUnit = {},
            updatePressureUnit = {},
            updateWindSpeedUnit = {}
        )

        // Assert
        assertListHasSize(items, 5)
    }

}