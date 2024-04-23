package de.niklasbednarczyk.nbweather.core.ui.resource

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBErrorType
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons

val NBErrorType.text: NBString
    get() {
        val resId = when (this) {
            NBErrorType.NO_INTERNET -> R.string.error_text_no_internet
            NBErrorType.UNKNOWN -> R.string.error_text_unknown
        }
        return NBString.ResString(resId)
    }

val NBErrorType.icon: NBIconItem
    get() = when (this) {
        NBErrorType.NO_INTERNET -> NBIcons.ErrorNoInternet
        NBErrorType.UNKNOWN -> NBIcons.ErrorUnknown
    }