package de.niklasbednarczyk.openweathermap.data.onecall.values.moon

@JvmInline
value class MoonPhaseValue private constructor(val type: MoonPhaseType) {

    companion object {

        internal fun from(value: Double?): MoonPhaseValue? {
            val type = MoonPhaseType.from(value)
            return if (type != null) MoonPhaseValue(type) else null
        }

    }

}