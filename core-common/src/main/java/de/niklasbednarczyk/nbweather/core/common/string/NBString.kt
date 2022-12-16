package de.niklasbednarczyk.nbweather.core.common.string

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

sealed interface NBString {

    class Resource(val resId: Int, vararg val args: Any) : NBString

    @JvmInline
    value class Value private constructor(val value: String) : NBString {

        companion object {

            fun from(value: String?): Value? {
                return nbNullSafe(value) { v ->
                    if (v.isNotBlank()) {
                        Value(v)
                    } else {
                        null
                    }
                }
            }

        }

    }

}