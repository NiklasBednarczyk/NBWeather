package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.getString
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.models.SettingsOverviewItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    private val headerAppearance =
        SettingsOverviewItemModel.Header(OwmString.Resource(R.string.screen_settings_overview_header_appearance))

    private val headerDisplay =
        SettingsOverviewItemModel.Header(OwmString.Resource(R.string.screen_settings_overview_header_display))

    private val itemFlow: Flow<List<SettingsOverviewItemModel>> = combine(
        settingsAppearanceRepository.getData(), settingsDisplayRepository.getData()
    ) { appearance, display ->
        val items = mutableListOf<SettingsOverviewItemModel>()

        items.add(headerAppearance)
        items.add(SettingsOverviewItemModel.Item(
            icon = OwmIcons.Theme,
            title = OwmString.Resource(R.string.screen_settings_theme_title),
            value = appearance.theme.getString(),
            destination = SettingsDestinations.Theme
        ))
        items.add(SettingsOverviewItemModel.Item(
            icon = OwmIcons.ColorScheme,
            title = OwmString.Resource(R.string.screen_settings_color_scheme_title),
            value = appearance.colorScheme.getString(),
            destination = SettingsDestinations.ColorScheme
        ))
        
        items.add(SettingsOverviewItemModel.Divider)
        
        items.add(headerDisplay)
        items.add(SettingsOverviewItemModel.Item(
            icon = OwmIcons.DataLanguage,
            title = OwmString.Resource(R.string.screen_settings_data_language_title),
            value = display.dataLanguage.getString(),
            destination = SettingsDestinations.DataLanguage
        ))
        items.add(SettingsOverviewItemModel.Item(
            icon = OwmIcons.Units,
            title = OwmString.Resource(R.string.screen_settings_units_title),
            value = display.units.getString(),
            destination = SettingsDestinations.Units
        ))
        items.add(SettingsOverviewItemModel.Item(
            icon = OwmIcons.TimeFormat,
            title = OwmString.Resource(R.string.screen_settings_time_format_title),
            value = display.timeFormat.getString(),
            destination = SettingsDestinations.TimeFormat
        ))
        
        items
    }

    init {

        collectFlow(
            { itemFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

}