package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView

@Composable
fun <T> OwmResourceView(
    resource: Resource<T>?,
    nullContent: @Composable () -> Unit = {},
    errorContent: @Composable (type: ErrorType?) -> Unit = { type -> ErrorView(type) },
    loadingContent: @Composable () -> Unit = {},
    successContent: @Composable (data: T) -> Unit
) {
    when (resource) {
        is Resource.Error -> {
            errorContent(resource.type)
        }
        is Resource.Loading -> {
            loadingContent()
        }
        is Resource.Success -> {
            successContent(resource.data)
        }
        null -> {
            nullContent()
        }
    }
}

@Composable
private fun ErrorView(type: ErrorType?) {
    val stringResId = when (type) {
        ErrorType.NO_INTERNET -> R.string.error_text_no_internet
        null -> R.string.error_text_unknown
    }

    val icon = when (type) {
        ErrorType.NO_INTERNET -> OwmIcons.ErrorNoInternet
        null -> OwmIcons.ErrorUnknown
    }

    OwmInfoView(
        icon = icon,
        text = OwmString.Resource(stringResId)
    )
}
