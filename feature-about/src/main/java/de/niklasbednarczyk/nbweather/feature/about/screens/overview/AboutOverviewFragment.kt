package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class AboutOverviewFragment : NBFragment<AboutOverviewUiState>() {

    override val viewModel: AboutOverviewViewModel by viewModels()

    override fun createTopAppBarItem(uiState: AboutOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.ResString(R.string.screen_about_overview_title)
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: AboutOverviewUiState) {
        AboutOverviewContent(
            uiState = uiState,
            startIntent = ::startIntent
        )
    }

}