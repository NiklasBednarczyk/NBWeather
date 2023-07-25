package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    settingsUnitsRepository: SettingsUnitsRepository
) : SettingsListViewModel() {

    override val itemFlow: Flow<List<SettingsListItemModel>> =
        settingsUnitsRepository.getData().map { units ->
            val items = mutableListOf<SettingsListItemModel>()

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Appearance,
                    title = NBString.Resource(R.string.screen_settings_appearance_title),
                    value = NBString.Resource(
                        R.string.format_bullet_2_items,
                        NBString.Resource(R.string.screen_settings_appearance_header_theme),
                        NBString.Resource(R.string.screen_settings_appearance_header_color_scheme)
                    ),
                    destination = DestinationsSettings.Appearance
                )
            )

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Units,
                    title = NBString.Resource(R.string.screen_settings_units_title),
                    value = NBString.Resource(
                        R.string.format_bullet_5_items,
                        units.temperatureUnit.symbol,
                        units.precipitationUnit.symbol,
                        units.distanceUnit.symbol,
                        units.pressureUnit.symbol,
                        units.windSpeedUnit.symbol
                    ),
                    destination = DestinationsSettings.Units
                )
            )

            items
        }

    init {

        collectFlow(
            { itemFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

}