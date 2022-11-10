package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.openweathermap.feature.settings.extensions.displayText
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.SettingsDestinations
import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.models.SettingsOverviewItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    private val headerAppearance =
        SettingsOverviewItemModel.Header(OwmString.Resource(R.string.screen_settings_overview_header_appearance))

    private val headerData =
        SettingsOverviewItemModel.Header(OwmString.Resource(R.string.screen_settings_overview_header_data))

    private val itemFlow: Flow<List<SettingsOverviewItemModel>> = combine(
        settingsAppearanceRepository.getData(), settingsDataRepository.getData()
    ) { appearance, data ->
        val items = mutableListOf<SettingsOverviewItemModel>()

        items.add(headerAppearance)
        items.add(
            SettingsOverviewItemModel.Item(
                icon = OwmIcons.Theme,
                title = OwmString.Resource(R.string.screen_settings_theme_title),
                value = appearance.theme.displayText,
                destination = SettingsDestinations.Theme
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = OwmIcons.ColorScheme,
                title = OwmString.Resource(R.string.screen_settings_color_scheme_title),
                value = appearance.colorScheme.displayText,
                destination = SettingsDestinations.ColorScheme
            )
        )

        items.add(SettingsOverviewItemModel.Divider)

        items.add(headerData)
        items.add(
            SettingsOverviewItemModel.Item(
                icon = OwmIcons.Language,
                title = OwmString.Resource(R.string.screen_settings_language_title),
                value = data.language.displayText,
                destination = SettingsDestinations.Language
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = OwmIcons.Units,
                title = OwmString.Resource(R.string.screen_settings_units_title),
                value = data.units.displayText,
                destination = SettingsDestinations.Units
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = OwmIcons.TimeFormat,
                title = OwmString.Resource(R.string.screen_settings_time_format_title),
                value = data.timeFormat.displayText,
                destination = SettingsDestinations.TimeFormat
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