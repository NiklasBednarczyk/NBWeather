package de.niklasbednarczyk.nbweather.feature.settings.screens.units.models

import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel

data class SettingsUnitsItemModel(
    val headerText: NBString,
    val segmentedControl: NBSegmentedControlModel<*>
) {

    companion object {

        fun from(
            units: NBUnitsModel,
            updateTemperatureUnit: (temperatureUnit: NBTemperatureUnitType) -> Unit,
            updatePrecipitationUnit: (precipitationUnit: NBPrecipitationUnitType) -> Unit,
            updateDistanceUnit: (distanceUnit: NBDistanceUnitType) -> Unit,
            updatePressureUnit: (pressureUnit: NBPressureUnitType) -> Unit,
            updateWindSpeedUnit: (windSpeedUnit: NBWindSpeedUnitType) -> Unit
        ): List<SettingsUnitsItemModel> {
            val items = mutableListOf<SettingsUnitsItemModel>()

            items.add(
                SettingsUnitsItemModel(
                    headerText = NBString.ResString(R.string.screen_settings_units_header_temperature),
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.temperatureUnit,
                        buttons = NBTemperatureUnitType.entries.map { temperature ->
                            NBSegmentedButtonModel(
                                key = temperature,
                                text = temperature.symbol
                            )
                        },
                        onItemSelected = updateTemperatureUnit,
                        sortAlphabetically = false
                    )
                )
            )

            items.add(
                SettingsUnitsItemModel(
                    headerText = NBString.ResString(R.string.screen_settings_units_header_precipitation),
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.precipitationUnit,
                        buttons = NBPrecipitationUnitType.entries.map { precipitation ->
                            NBSegmentedButtonModel(
                                key = precipitation,
                                text = precipitation.symbol
                            )
                        },
                        onItemSelected = updatePrecipitationUnit,
                    )
                )
            )

            items.add(
                SettingsUnitsItemModel(
                    headerText = NBString.ResString(R.string.screen_settings_units_header_distance),
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.distanceUnit,
                        buttons = NBDistanceUnitType.entries.map { distance ->
                            NBSegmentedButtonModel(
                                key = distance,
                                text = distance.symbol
                            )
                        },
                        onItemSelected = updateDistanceUnit,
                    )
                )
            )

            items.add(
                SettingsUnitsItemModel(
                    headerText = NBString.ResString(R.string.screen_settings_units_header_pressure),
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.pressureUnit,
                        buttons = NBPressureUnitType.entries.map { pressure ->
                            NBSegmentedButtonModel(
                                key = pressure,
                                text = pressure.symbol
                            )
                        },
                        onItemSelected = updatePressureUnit,
                    )
                )
            )

            items.add(
                SettingsUnitsItemModel(
                    headerText = NBString.ResString(R.string.screen_settings_units_header_wind_speed),
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.windSpeedUnit,
                        buttons = NBWindSpeedUnitType.entries.map { windSpeed ->
                            NBSegmentedButtonModel(
                                key = windSpeed,
                                text = windSpeed.symbol
                            )
                        },
                        onItemSelected = updateWindSpeedUnit,
                    )
                )
            )

            return items
        }

    }

}