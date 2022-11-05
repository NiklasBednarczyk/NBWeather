package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView

@Composable
fun <T> OwmResourceView(
    resource: OwmResource<T>?,
    nullContent: @Composable () -> Unit = {},
    errorContent: @Composable (type: OwmErrorType?) -> Unit = { type -> ErrorView(type) },
    loadingContent: @Composable () -> Unit = {},
    successContent: @Composable (data: T) -> Unit
) {
    when (resource) {
        is OwmResource.Error -> {
            errorContent(resource.type)
        }
        is OwmResource.Loading -> {
            loadingContent()
        }
        is OwmResource.Success -> {
            successContent(resource.data)
        }
        null -> {
            nullContent()
        }
    }
}

@Composable
private fun ErrorView(type: OwmErrorType?) {
    val stringResId = when (type) {
        OwmErrorType.NO_INTERNET -> R.string.error_text_no_internet
        null -> R.string.error_text_unknown
    }

    val icon = when (type) {
        OwmErrorType.NO_INTERNET -> OwmIcons.ErrorNoInternet
        null -> OwmIcons.ErrorUnknown
    }

    OwmInfoView(
        icon = icon,
        text = OwmString.Resource(stringResId)
    )
}
