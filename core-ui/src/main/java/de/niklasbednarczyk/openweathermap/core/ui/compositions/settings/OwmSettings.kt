package de.niklasbednarczyk.openweathermap.core.ui.compositions.settings

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData

object OwmSettings {
    private val settings: OwmSettingsModel
        @Composable
        get() = OwmLocalSettings.current
    val units: SettingsUnitsModelData
        @Composable
        get() = settings.units
}