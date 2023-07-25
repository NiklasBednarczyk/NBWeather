package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListFragment

@AndroidEntryPoint
class SettingsAppearanceFragment : SettingsListFragment() {

    override val title = NBString.Resource(R.string.screen_settings_appearance_title)

    override val viewModel: SettingsAppearanceViewModel by viewModels()

}