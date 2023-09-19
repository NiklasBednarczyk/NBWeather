package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListFragment

@AndroidEntryPoint
class SettingsOverviewFragment : SettingsListFragment() {

    override val viewModel: SettingsOverviewViewModel by viewModels()

    override val topAppBarItem = NBTopAppBarItem.Material.Small(
        title = NBString.ResString(R.string.screen_settings_overview_title)
    )

}