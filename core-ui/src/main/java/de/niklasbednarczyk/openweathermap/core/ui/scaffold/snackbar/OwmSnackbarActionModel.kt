package de.niklasbednarczyk.openweathermap.core.ui.scaffold.snackbar

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

data class OwmSnackbarActionModel(
    val label: OwmString,
    val onPerformed: () -> Unit
)