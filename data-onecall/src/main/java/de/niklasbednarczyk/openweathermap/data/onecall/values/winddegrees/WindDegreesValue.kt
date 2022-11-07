package de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees

@JvmInline
value class WindDegreesValue private constructor(val type: WindDegreesType) {

    companion object {

        internal fun from(value: Long?): WindDegreesValue? {
            val type = WindDegreesType.from(value)
            return if (type != null) WindDegreesValue(type) else null
        }

    }

}