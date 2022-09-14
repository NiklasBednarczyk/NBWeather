package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.data.DataLanguageType
import de.niklasbednarczyk.openweathermap.core.common.data.UnitsType
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    init {
        collectFlow(
            { settingsDataRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsData = output) }
        )
    }

    //TODO (#15) Remove after testing
    fun toggleUnits(units: UnitsType) {
        viewModelScope.launch {
            val newUnits = when (units) {
                UnitsType.STANDARD -> UnitsType.METRIC
                UnitsType.METRIC -> UnitsType.IMPERIAL
                UnitsType.IMPERIAL -> UnitsType.STANDARD
            }
            settingsDataRepository.updateUnits(newUnits)
        }
    }

    //TODO (#15) Remove after testing
    fun toggleDataLanguage(dataLanguage: DataLanguageType) {
        viewModelScope.launch {
            val newDataLanguage = when (dataLanguage) {
                DataLanguageType.ENGLISH -> DataLanguageType.GERMAN
                else -> DataLanguageType.ENGLISH
            }
            settingsDataRepository.updateDataLanguage(newDataLanguage)
        }
    }

}