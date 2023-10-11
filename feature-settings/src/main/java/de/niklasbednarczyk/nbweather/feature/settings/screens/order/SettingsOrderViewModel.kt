package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsOrderRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType.Companion.toOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsOrderViewModel @Inject constructor(
    private val settingsOrderRepository: SettingsOrderRepository
) : NBViewModel<SettingsOrderUiState>(SettingsOrderUiState()) {

    init {

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getItemsFlow(): Flow<List<SettingsOrderItemType>> {
        return settingsOrderRepository.getData().map { order ->
            SettingsOrderItemType.from(order)
        }
    }

    fun updateOrder(items: List<SettingsOrderItemType>) {
        launchSuspend {
            nbNullSafe(items.toOrder()) { order ->
                settingsOrderRepository.updateOrder(order)
            }
        }
    }

    fun resetToDefault() {
        launchSuspend {
            settingsOrderRepository.resetToDefault()
        }
    }

}