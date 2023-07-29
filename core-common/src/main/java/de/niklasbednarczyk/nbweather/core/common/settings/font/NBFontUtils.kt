package de.niklasbednarczyk.nbweather.core.common.settings.font

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
val isVariableFontAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O