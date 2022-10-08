package de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val type: ErrorType? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>
    
    fun <R> map(
        mapData: (oldData: T) -> R
    ): Resource<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(type)
            is Success -> Success(mapData(data))
        }
    }

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

        fun <T1, T2, T3> combine(
            resource1: Resource<T1>?,
            resource2: Resource<T2>?,
            resource3: Resource<T3>?,
        ): Resource<Triple<T1, T2, T3>>? {
            return when {
                resource1 is Success && resource2 is Success && resource3 is Success -> Success(
                    Triple(resource1.data, resource2.data, resource3.data)
                )
                resource1 is Error -> Error(resource1.type)
                resource2 is Error -> Error(resource2.type)
                resource3 is Error -> Error(resource3.type)
                resource1 is Loading || resource2 is Loading || resource3 is Loading -> Loading
                else -> null
            }
        }
    }

}