package de.niklasbednarczyk.openweathermap.core.ui.list

sealed interface OwmListItem<out T> {
    data class Full<T>(val data: T) : OwmListItem<T>
    object Empty : OwmListItem<Nothing>

    companion object {
        fun <T> owmEmptyList(
            itemCount: Int
        ): List<OwmListItem<T>> {
            return (1..itemCount).toList().map { Empty }
        }


    }
}