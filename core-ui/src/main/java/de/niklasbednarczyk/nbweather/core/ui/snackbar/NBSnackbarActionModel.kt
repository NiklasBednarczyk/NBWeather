package de.niklasbednarczyk.nbweather.core.ui.snackbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSnackbarActionModel(
    val label: NBString?,
    val onActionPerformed: () -> Unit
)