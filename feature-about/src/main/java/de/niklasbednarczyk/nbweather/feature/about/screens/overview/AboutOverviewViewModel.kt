package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import javax.inject.Inject

@HiltViewModel
class AboutOverviewViewModel @Inject constructor() :
    NBViewModel<AboutOverviewUiState>(AboutOverviewUiState())