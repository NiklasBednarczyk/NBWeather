package de.niklasbednarczyk.openweathermap.core.common.language

import java.util.*

enum class LanguageType {
    EN,
    DE;

    companion object {

        fun fromLocale(): LanguageType {
            return when (Locale.getDefault().language) {
                Locale("de").language -> DE
                else -> EN
            }
        }

    }

}