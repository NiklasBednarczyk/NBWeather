package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsDataRepository
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsUnitsViewModel @Inject constructor(
    private val settingsDataRepository: SettingsDataRepository
) : NBViewModel<SettingsUnitsUiState>(SettingsUnitsUiState()) {

    private val radioOptions = NBUnitsType.values().map { units ->
        NBRadioOptionModel(
            key = units,
            text = units.displayText
        )
    }

    private val radioGroupFlow: Flow<NBRadioGroupModel<NBUnitsType>> =
        settingsDataRepository.getData().map { data ->
            NBRadioGroupModel(
                selectedKey = data.units,
                options = radioOptions
            )
        }

    init {

        collectFlow(
            { radioGroupFlow },
            { oldUiState, output -> oldUiState.copy(radioGroup = output) }
        )

    }

    fun updateUnits(units: NBUnitsType) {
        launchSuspend {
            settingsDataRepository.updateUnits(units)
        }
    }

}