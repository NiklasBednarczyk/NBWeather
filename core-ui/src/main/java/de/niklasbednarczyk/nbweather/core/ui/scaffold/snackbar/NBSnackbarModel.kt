package de.niklasbednarczyk.nbweather.core.ui.scaffold.snackbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class NBSnackbarModel(
    val message: NBString?,
    val action: NBSnackbarActionModel? = null
)