package de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val type: ErrorType? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>

    companion object {
        fun <T1, T2> combine(
            resource1: Resource<T1>?,
            resource2: Resource<T2>?
        ): Resource<Pair<T1, T2>>? {
            return when {
                resource1 is Success && resource2 is Success -> Success(
                    Pair(resource1.data, resource2.data)
                )
                resource1 is Error -> Error(resource1.type)
                resource2 is Error -> Error(resource2.type)
                resource1 is Loading || resource2 is Loading -> Loading
                else -> null
            }
        }
    }

}