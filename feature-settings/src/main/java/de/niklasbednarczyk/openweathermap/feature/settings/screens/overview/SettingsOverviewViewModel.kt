package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    init {

        collectFlow(
            { settingsAppearanceRepository.getData() },
            { oldUiState, output -> oldUiState.copy(appearance = output) }
        )

        collectFlow(
            { settingsDisplayRepository.getData() },
            { oldUiState, output -> oldUiState.copy(display = output) }
        )

    }

}