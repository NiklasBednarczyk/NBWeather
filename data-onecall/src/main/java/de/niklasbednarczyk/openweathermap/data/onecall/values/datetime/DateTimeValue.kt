package de.niklasbednarczyk.openweathermap.data.onecall.values.datetime

@JvmInline
value class DateTimeValue private constructor(val value: Long) {

    companion object {

        internal fun from(value: Long?): DateTimeValue? {
            return if (value != null) DateTimeValue(value) else null
        }

    }

}