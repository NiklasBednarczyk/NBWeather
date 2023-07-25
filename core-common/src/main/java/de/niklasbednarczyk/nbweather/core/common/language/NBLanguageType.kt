package de.niklasbednarczyk.nbweather.core.common.language

import java.util.Locale

enum class NBLanguageType {

    ENGLISH,
    GERMAN;

    val language: String
        get() = when (this) {
            ENGLISH -> "en"
            GERMAN -> "de"
        }

    companion object {

        fun from(): NBLanguageType {
            val localeDefaultLanguage = Locale.getDefault().language
            val languageType = NBLanguageType.values().find { languageType ->
                languageType.language == localeDefaultLanguage
            }
            return languageType ?: ENGLISH
        }

    }


}