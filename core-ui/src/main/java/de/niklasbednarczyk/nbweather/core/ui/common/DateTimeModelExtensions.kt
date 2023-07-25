package de.niklasbednarczyk.nbweather.core.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString

val NBDateTimeModel?.dateTimeDayOfWeekShortWithTime: NBString?
    @Composable
    get() = this?.getDateTimeDayOfWeekShortWithTime(LocalContext.current)

val NBDateTimeModel?.time: NBString?
    @Composable
    get() = this?.getTime(LocalContext.current)