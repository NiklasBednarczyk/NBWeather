package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource

@Composable
fun <T> ResourceView(
    resource: Resource<T>?,
    nullContent: @Composable () -> Unit = {},
    errorContent: @Composable (type: ErrorType?) -> Unit = { type -> ErrorView(type) },
    loadingContent: @Composable () -> Unit = { LoadingView() },
    successContent: @Composable (data: T) -> Unit
) {

    //TODO (#9) Animate

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
    //TODO (#9) Do correctly
}

@Composable
private fun LoadingView() {
    //TODO (#9) Do correctly
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
