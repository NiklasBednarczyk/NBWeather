package de.niklasbednarczyk.nbweather.core.common.locale

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
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

        fun from(
            language: String?
        ): NBLanguageType? {
            return nbNullSafe(language) { l ->
                entries.find { languageType ->
                    languageType.language == l
                }
            }
        }

        fun fromLocale(): NBLanguageType {
            val language = Locale.getDefault().language
            return from(language) ?: ENGLISH
        }

    }


}