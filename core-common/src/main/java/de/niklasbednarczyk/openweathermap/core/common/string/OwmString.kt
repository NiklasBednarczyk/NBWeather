package de.niklasbednarczyk.openweathermap.core.common.string

sealed interface OwmString {

    @JvmInline
    value class Resource(val resId: Int) : OwmString

    @JvmInline
    value class Value(val value: String) : OwmString {

        companion object {

            fun from(value: String?): Value? {
                return if (value != null) Value(value) else null
            }

        }

    }

}