package de.niklasbednarczyk.nbweather.core.ui.resource

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBErrorType
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.info.NBInfoView

@Composable
fun <T> NBResourceWithLoadingView(
    resource: NBResource<T>,
    errorView: @Composable (type: NBErrorType) -> Unit = { type -> ErrorView(type) },
    loadingView: @Composable () -> Unit = { NBLoadingView() },
    successView: @Composable (data: T) -> Unit
) {
    AnimatedContent(
        targetState = resource,
        label = "resource"
    ) { r ->
        when (r) {
            is NBResource.Error -> {
                errorView(r.errorType)
            }

            is NBResource.Loading -> {
                loadingView()
            }

            is NBResource.Success -> {
                successView(r.data)
            }
        }
    }
}

@Composable
fun <T> NBResourceWithoutLoadingView(
    resource: NBResource<T>,
    errorView: @Composable (type: NBErrorType) -> Unit = { type -> ErrorView(type) },
    loadingView: @Composable () -> Unit = {},
    successView: @Composable (data: T) -> Unit
) {
    when (resource) {
        is NBResource.Error -> {
            errorView(resource.errorType)
        }

        is NBResource.Loading -> {
            loadingView()
        }

        is NBResource.Success -> {
            successView(resource.data)
        }
    }
}

@Composable
private fun ErrorView(type: NBErrorType) {
    val stringResId = when (type) {
        NBErrorType.NO_INTERNET -> R.string.error_text_no_internet
        NBErrorType.UNKNOWN -> R.string.error_text_unknown
    }

    val icon = when (type) {
        NBErrorType.NO_INTERNET -> NBIcons.ErrorNoInternet
        NBErrorType.UNKNOWN -> NBIcons.ErrorUnknown
    }

    NBInfoView(
        icon = icon,
        text = NBString.ResString(stringResId)
    )
}

@Composable
fun NBLoadingView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
