package de.niklasbednarczyk.openweathermap.core.common.resource

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val type: ErrorType? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>
}
