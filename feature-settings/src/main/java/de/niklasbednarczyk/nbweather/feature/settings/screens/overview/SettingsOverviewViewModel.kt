package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.feature.settings.navigation.DestinationsSettings
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    settingsAppearanceRepository: SettingsAppearanceRepository,
    settingsDataRepository: SettingsDataRepository
) : NBViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    private val headerAppearance =
        SettingsOverviewItemModel.Header(NBString.Resource(R.string.screen_settings_overview_header_appearance))

    private val headerData =
        SettingsOverviewItemModel.Header(NBString.Resource(R.string.screen_settings_overview_header_data))

    private val itemFlow: Flow<List<SettingsOverviewItemModel>> = combine(
        settingsAppearanceRepository.getData(), settingsDataRepository.getData()
    ) { appearance, data ->
        val items = mutableListOf<SettingsOverviewItemModel>()

        items.add(headerAppearance)
        items.add(
            SettingsOverviewItemModel.Item(
                icon = NBIcons.Theme,
                title = NBString.Resource(R.string.screen_settings_theme_title),
                value = appearance.theme.displayText,
                destination = DestinationsSettings.Theme
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = NBIcons.ColorScheme,
                title = NBString.Resource(R.string.screen_settings_color_scheme_title),
                value = appearance.colorScheme.displayText,
                destination = DestinationsSettings.ColorScheme
            )
        )

        items.add(SettingsOverviewItemModel.Divider)

        items.add(headerData)
        items.add(
            SettingsOverviewItemModel.Item(
                icon = NBIcons.Language,
                title = NBString.Resource(R.string.screen_settings_language_title),
                value = data.language.displayText,
                destination = DestinationsSettings.Language
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = NBIcons.Units,
                title = NBString.Resource(R.string.screen_settings_units_title),
                value = data.units.displayText,
                destination = DestinationsSettings.Units
            )
        )
        items.add(
            SettingsOverviewItemModel.Item(
                icon = NBIcons.TimeFormat,
                title = NBString.Resource(R.string.screen_settings_time_format_title),
                value = data.timeFormat.displayText,
                destination = DestinationsSettings.TimeFormat
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