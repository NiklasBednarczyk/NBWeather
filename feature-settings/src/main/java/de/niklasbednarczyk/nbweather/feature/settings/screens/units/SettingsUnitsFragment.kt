package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListFragment

@AndroidEntryPoint
class SettingsUnitsFragment : SettingsListFragment() {

    override val title = NBString.Resource(R.string.screen_settings_units_title)

    override val viewModel: SettingsUnitsViewModel by viewModels()

}