package de.niklasbednarczyk.nbweather.core.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString

val NBDateTimeDisplayModel?.time: NBString?
    @Composable
    get() = this?.getTime(LocalContext.current)