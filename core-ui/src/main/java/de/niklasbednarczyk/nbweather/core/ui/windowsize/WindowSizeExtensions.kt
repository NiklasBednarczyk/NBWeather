package de.niklasbednarczyk.nbweather.core.ui.windowsize

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
private fun getWindowSizeClass(): WindowSizeClass? {
    val context = LocalContext.current
    val activity = context.getActivity() ?: return null
    return calculateWindowSizeClass(activity)
}

@Composable
fun getWidthWindowSizeType(): NBWindowSizeType {
    return when (getWindowSizeClass()?.widthSizeClass) {
        WindowWidthSizeClass.Compact -> NBWindowSizeType.COMPACT
        WindowWidthSizeClass.Medium -> NBWindowSizeType.MEDIUM
        WindowWidthSizeClass.Expanded -> NBWindowSizeType.EXPANDED
        else -> NBWindowSizeType.COMPACT
    }
}
