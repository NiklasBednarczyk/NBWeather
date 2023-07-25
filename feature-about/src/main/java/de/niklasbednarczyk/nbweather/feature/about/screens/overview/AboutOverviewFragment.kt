package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarModel

@AndroidEntryPoint
class AboutOverviewFragment : NBFragmentUiState<AboutOverviewUiState>() {

    override val viewModel: AboutOverviewViewModel by viewModels()

    @Composable
    override fun createTopAppBarItem(viewData: AboutOverviewUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.Resource(R.string.screen_about_overview_title)
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: AboutOverviewUiState) {
        AboutOverviewContent(
            uiState = viewData,
            onIntent = ::onIntent
        )
    }

    private fun onIntent(intent: Intent) {
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            val snackbar = NBSnackbarModel(
                message = NBString.Resource(R.string.snackbar_about_intent_failed_message)
            )
            sendSnackbar(snackbar)
        }
    }

}