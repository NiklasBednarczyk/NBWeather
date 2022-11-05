package de.niklasbednarczyk.openweathermap.core.common.constants

import android.annotation.SuppressLint
import android.os.Build

object ConstantsCoreCommon {

    object DynamicColor {
        @SuppressLint("AnnotateVersionCheck")
        val isAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

}
