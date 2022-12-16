package de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSnackbarActionModel(
    val label: NBString?,
    val onPerformed: () -> Unit
)