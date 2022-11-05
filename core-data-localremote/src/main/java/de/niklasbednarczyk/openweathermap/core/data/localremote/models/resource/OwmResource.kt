package de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource

sealed interface OwmResource<out T> {
    data class Success<T>(val data: T) : OwmResource<T>
    data class Error(val type: OwmErrorType? = null) : OwmResource<Nothing>
    object Loading : OwmResource<Nothing>

    fun <R> map(
        mapData: (oldData: T) -> R?
    ): OwmResource<R> {
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

        fun <T1, T2, R> combine(
            resource1: OwmResource<T1>?,
            resource2: OwmResource<T2>?,
            transformData: (data1: T1, data2: T2) -> R
        ): OwmResource<R>? {
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

        fun <T1, T2, T3, R> combine(
            resource1: OwmResource<T1>?,
            resource2: OwmResource<T2>?,
            resource3: OwmResource<T3>?,
            transformData: (data1: T1, data2: T2, data3: T3) -> R
        ): OwmResource<R>? {
            return when {
                resource1 is Success && resource2 is Success && resource3 is Success -> {
                    val newData = transformData(resource1.data, resource2.data, resource3.data)
                    Success(newData)
                }
                resource1 is Error -> Error(resource1.type)
                resource2 is Error -> Error(resource2.type)
                resource3 is Error -> Error(resource3.type)
                resource1 is Loading || resource2 is Loading || resource3 is Loading -> Loading
                else -> null
            }
        }


    }

}