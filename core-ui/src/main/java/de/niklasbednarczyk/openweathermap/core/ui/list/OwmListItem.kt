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

        fun <T> List<T?>.toOwmList(): List<OwmListItem<T>> {
            return this.map { item ->
                if (item != null) {
                    Full(item)
                } else {
                    Empty
                }
            }
        }

    }
}