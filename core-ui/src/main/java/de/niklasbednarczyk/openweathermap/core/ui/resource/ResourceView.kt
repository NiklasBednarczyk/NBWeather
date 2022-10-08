package de.niklasbednarczyk.openweathermap.core.ui.resource

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.ErrorType
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource

@Composable
fun <T> ResourceView(
    resource: Resource<T>?,
    nullContent: @Composable () -> Unit = {},
    errorContent: @Composable (type: ErrorType?) -> Unit = { type -> ErrorView(type) },
    loadingContent: @Composable () -> Unit = { },
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
