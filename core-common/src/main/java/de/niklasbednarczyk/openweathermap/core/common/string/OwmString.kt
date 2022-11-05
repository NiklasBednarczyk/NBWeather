package de.niklasbednarczyk.openweathermap.core.common.string

sealed interface OwmString {

    class Resource(val resId: Int, vararg val args: Any) : OwmString

    @JvmInline
    value class Value private constructor(val value: String) : OwmString {

        companion object {

            fun from(value: String?): Value? {
                return if (value != null) Value(value) else null
            }

        }

    }

}