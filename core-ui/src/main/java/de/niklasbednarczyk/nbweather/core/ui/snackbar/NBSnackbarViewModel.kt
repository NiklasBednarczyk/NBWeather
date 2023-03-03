package de.niklasbednarczyk.nbweather.core.ui.snackbar

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModelChannel

@HiltViewModel
class NBSnackbarViewModel : NBViewModelChannel<NBSnackbarModel>()