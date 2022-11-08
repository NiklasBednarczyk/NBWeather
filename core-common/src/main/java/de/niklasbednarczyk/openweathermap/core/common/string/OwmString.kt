package de.niklasbednarczyk.openweathermap.core.common.string

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe

sealed interface OwmString {

    class Resource(val resId: Int, vararg val args: Any) : OwmString

    @JvmInline
    value class Value private constructor(val value: String) : OwmString {

        companion object {

            fun from(value: String): Value {
                return Value(value)
            }

            fun from(value: String?): Value? {
                return owmNullSafe(value) { from(it) }
            }

        }

    }

}