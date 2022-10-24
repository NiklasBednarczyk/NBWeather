package de.niklasbednarczyk.openweathermap.core.data.localremote.remote.extensions

import de.niklasbednarczyk.openweathermap.core.common.language.OwmLanguageType

val OwmLanguageType.remoteName: String
    get() = when (this) {
        OwmLanguageType.DE -> "de"
        OwmLanguageType.EN -> "en"
    }

