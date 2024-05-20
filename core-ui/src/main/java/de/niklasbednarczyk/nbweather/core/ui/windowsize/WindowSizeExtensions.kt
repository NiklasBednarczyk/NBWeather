package de.niklasbednarczyk.nbweather.core.ui.windowsize

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.ui.context.getActivity

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
