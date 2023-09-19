package de.niklasbednarczyk.nbweather.core.common.string

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

sealed interface NBString {

    companion object {
        fun NBString?.asString(context: Context): String {
            return asStringNullable(context).orEmpty()
        }

        private fun NBString?.asStringNullable(context: Context): String? {
            return when (this) {
                is ResPlurals -> {
                    val arguments = args.toArguments(context)
                    context.resources.getQuantityString(resId, size, *arguments.toTypedArray())
                }

                is ResString -> {
                    val arguments = args.toArguments(context)
                    context.getString(resId, *arguments.toTypedArray())
                }

                is Value -> value

                null -> null
            }
        }

        private fun Array<out Any?>.toArguments(context: Context) = toList().map { arg ->
            if (arg is NBString?) arg.asString(context) else arg
        }
    }

    class ResPlurals(@PluralsRes val resId: Int, val size: Int, vararg val args: Any?) : NBString

    class ResString(@StringRes val resId: Int, vararg val args: Any?) : NBString

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