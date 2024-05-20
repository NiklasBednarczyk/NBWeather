package de.niklasbednarczyk.nbweather.core.ui.navigation

sealed interface NBArgumentKeyItem<T> {

    val key: String

    fun toValue(
        string: String?
    ): T?

    companion object {

        fun <T> toString(
            value: T?
        ): String {
            return value?.toString().orEmpty()
        }

    }



}