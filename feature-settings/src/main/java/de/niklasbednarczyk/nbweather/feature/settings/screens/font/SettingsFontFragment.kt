package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListFragment

@AndroidEntryPoint
class SettingsFontFragment : SettingsListFragment() {

    override val viewModel: SettingsFontViewModel by viewModels()

    override val topAppBarItem = NBTopAppBarItem.Material.Small(
        title = NBString.ResString(R.string.screen_settings_font_title),
        actions = listOf(
            NBTopAppBarActionModel(
                icon = NBIcons.Reset,
                onClick = ::resetToDefault
            )
        )
    )

    override val keepInitialFontFamily: Boolean
        get() = true

    private fun resetToDefault() {
        viewModel.resetToDefault()
    }

}