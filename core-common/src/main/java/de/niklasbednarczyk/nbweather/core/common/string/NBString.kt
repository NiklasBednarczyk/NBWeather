package de.niklasbednarczyk.nbweather.core.common.string

import android.content.Context
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

sealed interface NBString {

    companion object {
        fun NBString?.asString(context: Context): String {
            return asStringNullable(context).orEmpty()
        }

        private fun NBString?.asStringNullable(context: Context): String? {
            return when (this) {
                is Resource -> {
                    val arguments = args.toList().map { arg ->
                        if (arg is NBString?) arg.asString(context) else arg
                    }
                    context.getString(resId, *arguments.toTypedArray())
                }

                is Value -> value
                null -> null
            }
        }
    }

    class Resource(val resId: Int, vararg val args: Any?) : NBString

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