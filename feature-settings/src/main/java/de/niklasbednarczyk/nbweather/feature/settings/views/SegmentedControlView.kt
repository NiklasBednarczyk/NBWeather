package de.niklasbednarczyk.nbweather.feature.settings.views

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlView

@Composable
fun <T> SegmentedControlView(
    segmentedControl: NBSegmentedControlModel<T>
) {
    NBSegmentedControlView(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding,
            vertical = screenVerticalPadding
        ),
        segmentedControl = segmentedControl
    )
}