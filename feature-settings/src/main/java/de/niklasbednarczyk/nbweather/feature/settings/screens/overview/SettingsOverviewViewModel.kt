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
                    title = NBString.Resource(R.string.screen_settings_appearance_title),
                    value = NBString.Resource(
                        R.string.format_bullet_2_items,
                        NBString.Resource(R.string.screen_settings_appearance_header_theme),
                        NBString.Resource(R.string.screen_settings_appearance_header_color_scheme)
                    ),
                    destination = DestinationsSettings.Appearance
                )
            )

            if (isVariableFontAvailable) {
                items.add(
                    SettingsListItemModel.ItemDestination(
                        icon = NBIcons.Font,
                        title = NBString.Resource(R.string.screen_settings_font_title),
                        value = NBString.Resource(
                            R.string.format_bullet_11_items,
                            NBString.Resource(R.string.screen_settings_font_axis_slant),
                            NBString.Resource(R.string.screen_settings_font_axis_width),
                            NBString.Resource(R.string.screen_settings_font_axis_weight),
                            NBString.Resource(R.string.screen_settings_font_axis_grade),
                            NBString.Resource(R.string.screen_settings_font_axis_counter_width),
                            NBString.Resource(R.string.screen_settings_font_axis_thin_stroke),
                            NBString.Resource(R.string.screen_settings_font_axis_ascender_height),
                            NBString.Resource(R.string.screen_settings_font_axis_descender_depth),
                            NBString.Resource(R.string.screen_settings_font_axis_figure_height),
                            NBString.Resource(R.string.screen_settings_font_axis_lowercase_height),
                            NBString.Resource(R.string.screen_settings_font_axis_uppercase_height)
                        ),
                        destination = DestinationsSettings.Font
                    )
                )
            }

            items.add(
                SettingsListItemModel.ItemDestination(
                    icon = NBIcons.Units,
                    title = NBString.Resource(R.string.screen_settings_units_title),
                    value = NBString.Resource(
                        R.string.format_bullet_5_items,
                        NBString.Resource(R.string.screen_settings_units_header_temperature),
                        NBString.Resource(R.string.screen_settings_units_header_precipitation),
                        NBString.Resource(R.string.screen_settings_units_header_distance),
                        NBString.Resource(R.string.screen_settings_units_header_pressure),
                        NBString.Resource(R.string.screen_settings_units_header_wind_speed)
                    ),
                    destination = DestinationsSettings.Units
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