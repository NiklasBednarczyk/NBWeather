package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar.NBSnackbarViewModel
import de.niklasbednarczyk.nbweather.core.ui.viewmodel.NBViewModel
import javax.inject.Inject

@HiltViewModel
class AboutOverviewViewModel @Inject constructor() :
    NBViewModel<AboutOverviewUiState>(AboutOverviewUiState()), NBSnackbarViewModel {

    fun onIntentFailed() {
        val snackbar = NBSnackbarModel(
            message = NBString.Resource(R.string.snackbar_about_intent_failed)
        )
        sendSnackbar(snackbar)
    }

}