package de.niklasbednarczyk.openweathermap.core.common.language

import java.util.*

enum class OwmLanguageType {
    EN,
    DE;

    companion object {

        fun fromLocale(): OwmLanguageType {
            return when (Locale.getDefault().language) {
                Locale("de").language -> DE
                else -> EN
            }
        }

    }

}