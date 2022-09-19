package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions

import de.niklasbednarczyk.openweathermap.core.common.language.LanguageType

val LanguageType.remoteName: String
    get() = when (this) {
        LanguageType.DE -> "de"
        LanguageType.EN -> "en"
    }

