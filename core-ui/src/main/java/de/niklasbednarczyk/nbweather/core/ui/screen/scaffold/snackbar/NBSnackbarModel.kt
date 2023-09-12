package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.snackbar

import androidx.compose.material3.SnackbarDuration
import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSnackbarModel(
    val message: NBString?,
    val action: NBSnackbarActionModel? = null,
    val duration: SnackbarDuration = if (action == null) SnackbarDuration.Short else SnackbarDuration.Long,
    val onDismissed: () -> Unit = {}
)