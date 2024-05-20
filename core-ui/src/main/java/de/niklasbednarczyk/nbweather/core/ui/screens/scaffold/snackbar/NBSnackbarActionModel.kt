package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSnackbarActionModel(
    val label: NBString?,
    val onActionPerformed: () -> Unit
)