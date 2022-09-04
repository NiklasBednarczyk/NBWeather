package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.viewmodel.OwmViewModel
import de.niklasbednarczyk.openweathermap.data.settings.models.display.DataLanguageTypeData
import de.niklasbednarczyk.openweathermap.data.settings.models.display.UnitsTypeData
import de.niklasbednarczyk.openweathermap.data.settings.repositories.SettingsDisplayRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsOverviewViewModel @Inject constructor(
    private val settingsDisplayRepository: SettingsDisplayRepository
) : OwmViewModel<SettingsOverviewUiState>(SettingsOverviewUiState()) {

    init {
        collectFlow(
            { settingsDisplayRepository.getData() },
            { oldUiState, output -> oldUiState.copy(settingsDisplay = output) }
        )
    }

    //TODO (#15) Remove after testing
    fun toggleUnits(units: UnitsTypeData) {
        viewModelScope.launch {
            val newUnits = when (units) {
                UnitsTypeData.STANDARD -> UnitsTypeData.METRIC
                UnitsTypeData.METRIC -> UnitsTypeData.IMPERIAL
                UnitsTypeData.IMPERIAL -> UnitsTypeData.STANDARD
            }
            settingsDisplayRepository.updateUnits(newUnits)
        }
    }

    //TODO (#15) Remove after testing
    fun toggleDataLanguage(dataLanguage: DataLanguageTypeData) {
        viewModelScope.launch {
            val newDataLanguage = when (dataLanguage) {
                DataLanguageTypeData.ENGLISH -> DataLanguageTypeData.GERMAN
                else -> DataLanguageTypeData.ENGLISH
            }
            settingsDisplayRepository.updateDataLanguage(newDataLanguage)
        }
    }

}