package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.OwmResource
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.info.OwmInfoView

@Composable
fun <T> OwmResourceWithLoadingView(
    resource: OwmResource<T>?,
    content: @Composable (data: T) -> Unit
) {
    AnimatedContent(resource) { r ->
        when (r) {
            is OwmResource.Error -> {
                ErrorView(r.errorType)
            }
            is OwmResource.Success -> {
                content(r.data)
            }
            else -> {
                OwmLoadingView()
            }
        }
    }
}

@Composable
fun <T> OwmResourceWithoutLoadingView(
    resource: OwmResource<T>?,
    content: @Composable (data: T) -> Unit
) {
    when (resource) {
        is OwmResource.Error -> {
            ErrorView(resource.errorType)
        }
        is OwmResource.Success -> {
            content(resource.data)
        }
        else -> {}
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

@Composable
fun OwmLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
