package de.niklasbednarczyk.openweathermap.data.onecall.local.typeconverters

import androidx.room.TypeConverter

class TypeConvertersLocal {

    companion object {
        private const val SEPARATOR = " || "
    }

    @TypeConverter
    fun stringListToString(value: List<String>?): String? {
        return value?.joinToString(SEPARATOR)
    }

    @TypeConverter
    fun stringToStringList(value: String?): List<String>? {
        return value?.split(SEPARATOR)
    }

}