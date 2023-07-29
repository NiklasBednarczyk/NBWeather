package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBDistanceUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPrecipitationUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBPressureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBTemperatureUnitType
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBWindSpeedUnitType
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedButtonModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsUnitsViewModel @Inject constructor(
    private val settingsUnitsRepository: SettingsUnitsRepository,
) : SettingsListViewModel() {

    override val itemsFlow: Flow<List<SettingsListItemModel>> =
        settingsUnitsRepository.getData().map { units ->
            val items = mutableListOf<SettingsListItemModel>()

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_units_header_temperature)))

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.temperatureUnit,
                        buttons = NBTemperatureUnitType.values().map { temperature ->
                            NBSegmentedButtonModel(
                                key = temperature,
                                text = temperature.symbol
                            )
                        },
                        onItemSelected = ::updateTemperatureUnit,
                        sortAlphabetically = false
                    )
                )
            )

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_units_header_precipitation)))

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.precipitationUnit,
                        buttons = NBPrecipitationUnitType.values().map { precipitation ->
                            NBSegmentedButtonModel(
                                key = precipitation,
                                text = precipitation.symbol
                            )
                        },
                        onItemSelected = ::updatePrecipitationUnit,
                    )
                )
            )

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_units_header_distance)))

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.distanceUnit,
                        buttons = NBDistanceUnitType.values().map { distance ->
                            NBSegmentedButtonModel(
                                key = distance,
                                text = distance.symbol
                            )
                        },
                        onItemSelected = ::updateDistanceUnit,
                    )
                )
            )

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_units_header_pressure)))

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.pressureUnit,
                        buttons = NBPressureUnitType.values().map { pressure ->
                            NBSegmentedButtonModel(
                                key = pressure,
                                text = pressure.symbol
                            )
                        },
                        onItemSelected = ::updatePressureUnit,
                    )
                )
            )

            items.add(SettingsListItemModel.Header(NBString.Resource(R.string.screen_settings_units_header_wind_speed)))

            items.add(
                SettingsListItemModel.ItemButtons(
                    segmentedControl = NBSegmentedControlModel(
                        selectedKey = units.windSpeedUnit,
                        buttons = NBWindSpeedUnitType.values().map { windSpeed ->
                            NBSegmentedButtonModel(
                                key = windSpeed,
                                text = windSpeed.symbol
                            )
                        },
                        onItemSelected = ::updateWindSpeedUnit,
                    )
                )
            )

            items
        }

    init {

        collectFlow(
            { itemsFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun updateTemperatureUnit(temperatureUnit: NBTemperatureUnitType) {
        launchSuspend {
            settingsUnitsRepository.updateTemperatureUnit(temperatureUnit)
        }
    }

    private fun updatePrecipitationUnit(precipitationUnit: NBPrecipitationUnitType) {
        launchSuspend {
            settingsUnitsRepository.updatePrecipitationUnit(precipitationUnit)
        }
    }

    private fun updateDistanceUnit(distanceUnit: NBDistanceUnitType) {
        launchSuspend {
            settingsUnitsRepository.updateDistanceUnit(distanceUnit)
        }
    }

    private fun updatePressureUnit(pressureUnit: NBPressureUnitType) {
        launchSuspend {
            settingsUnitsRepository.updatePressureUnit(pressureUnit)
        }
    }

    private fun updateWindSpeedUnit(windSpeedUnit: NBWindSpeedUnitType) {
        launchSuspend {
            settingsUnitsRepository.updateWindSpeedUnit(windSpeedUnit)
        }
    }

}