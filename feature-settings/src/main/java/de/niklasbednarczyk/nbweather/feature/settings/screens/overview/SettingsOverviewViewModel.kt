package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.isVariableFontAvailable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor() : SettingsListViewModel() {

    override val itemsFlow: Flow<List<SettingsListItemModel>>
        get() {
            val items = mutableListOf<SettingsListItemModel>()

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Appearance,
                    title = NBString.ResString(R.string.screen_settings_appearance_title),
                    description = NBString.ResString(R.string.screen_settings_appearance_description),
                    destination = DestinationsSettings.Appearance
                )
            )

            if (isVariableFontAvailable) {
                items.add(
                    SettingsListItemModel.ItemDestination(
                        icon = NBIcons.Font,
                        title = NBString.ResString(R.string.screen_settings_font_title),
                        description = NBString.ResString(R.string.screen_settings_font_description),
                        destination = DestinationsSettings.Font
                    )
                )
            }

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Units,
                    title = NBString.ResString(R.string.screen_settings_units_title),
                    description = NBString.ResString(R.string.screen_settings_units_description),
                    destination = DestinationsSettings.Units
                )
            )

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Order,
                    title = NBString.ResString(R.string.screen_settings_order_title),
                    description = NBString.ResString(R.string.screen_settings_order_description),
                    destination = DestinationsSettings.Order
                )
            )

            return flowOf(items)
        }

    init {

        collectFlow(
            { itemsFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

}