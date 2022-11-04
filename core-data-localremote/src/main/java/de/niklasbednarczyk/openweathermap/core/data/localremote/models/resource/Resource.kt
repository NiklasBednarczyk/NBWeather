package de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val type: ErrorType? = null) : Resource<Nothing>
    object Loading : Resource<Nothing>

    fun <R> map(
        mapData: (oldData: T) -> R?
    ): Resource<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(type)
            is Success -> {
                val newData = mapData(data)
                if (newData != null) {
                    Success(newData)
                } else {
                    Error()
                }
            }
        }
    }

    companion object {

        fun <T1, T2, T3> merge(
            resource1: Resource<T1>?,
            resource2: Resource<T2>?,
            transformData: (data1: T1, data2: T2) -> T3
        ): Resource<T3>? {
            return when {
                resource1 is Success && resource2 is Success -> {
                    val newData = transformData(resource1.data, resource2.data)
                    Success(newData)
                }
                resource1 is Error -> Error(resource1.type)
                resource2 is Error -> Error(resource2.type)
                resource1 is Loading || resource2 is Loading -> Loading
                else -> null
            }
        }

        fun <T1, T2> combine(
            resource1: Resource<T1>?,
            resource2: Resource<T2>?
        ): Resource<Pair<T1, T2>>? {
            return merge(
                resource1,
                resource2
            ) { data1, data2 ->
                Pair(data1, data2)
            }
        }

    }

}