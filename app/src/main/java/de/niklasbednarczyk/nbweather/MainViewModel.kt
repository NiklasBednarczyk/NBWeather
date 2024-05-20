package de.niklasbednarczyk.nbweather

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.geocoding.repositories.GeocodingRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsAppearanceRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsFontRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsUnitsRepository
import de.niklasbednarczyk.nbweather.models.MainViewData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingRepository: GeocodingRepository,
    private val settingsAppearanceRepository: SettingsAppearanceRepository,
    private val settingsFontRepository: SettingsFontRepository,
    private val settingsOrderRepository: SettingsOrderRepository,
    private val settingsUnitsRepository: SettingsUnitsRepository,
) : NBViewModel<MainUiState>(MainUiState()) {

    init {

        collectFlow(
            { settingsAppearanceRepository.getData() },
            { oldUiState, output -> oldUiState.copy(appearance = output) }
        )

        collectFlow(
            { settingsFontRepository.getData() },
            { oldUiState, output -> oldUiState.copy(font = output) }
        )

        collectFlow(
            { settingsOrderRepository.getData() },
            { oldUiState, output -> oldUiState.copy(order = output) }
        )

        collectFlow(
            { settingsUnitsRepository.getData() },
            { oldUiState, output -> oldUiState.copy(units = output) }
        )

        collectFlow(
            { getViewDataResourceFlow() },
            { oldUiState, output -> oldUiState.copy(viewDataResource = output) }
        )

    }

    private suspend fun getViewDataResourceFlow(): Flow<NBResource<MainViewData>> {
        return NBResource.nbCombineResourceFlows(
            geocodingRepository.getVisitedLocations(),
            geocodingRepository.getCurrentLocation(),
            geocodingRepository.getInitialCurrentLocation(),
            MainViewData::from
        )
    }

}