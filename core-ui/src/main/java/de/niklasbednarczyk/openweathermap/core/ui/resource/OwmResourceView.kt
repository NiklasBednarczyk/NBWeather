package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView

@Composable
fun OwmResourceView(
    uiState: OwmResourceUiState,
    content: @Composable () -> Unit
) {
    val errorType = uiState.errorType
    if (errorType != null) {
        ErrorView(errorType)
    } else {
        content()
    }
}

@Composable
private fun ErrorView(type: OwmErrorType) {
    val stringResId = when (type) {
        OwmErrorType.NO_INTERNET -> R.string.error_text_no_internet
        OwmErrorType.UNKNOWN -> R.string.error_text_unknown
    }

    val icon = when (type) {
        OwmErrorType.NO_INTERNET -> OwmIcons.ErrorNoInternet
        OwmErrorType.UNKNOWN -> OwmIcons.ErrorUnknown
    }

    OwmInfoView(
        icon = icon,
        text = OwmString.Resource(stringResId)
    )
}
