package de.niklasbednarczyk.nbweather.core.ui.dimens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

const val alphaContentDisabled = 0.38f

val elevationLevel2 = 3.dp

val filledTonalButtonIconSize = 18.dp
val filledTonalButtonPaddingBetweenElements = 8.dp

val listContentPaddingHorizontal = 8.dp
val listContentPaddingValuesHorizontal = PaddingValues(
    horizontal = listContentPaddingHorizontal
)
val listContentPaddingVertical = 8.dp
val listContentPaddingValuesVertical = PaddingValues(
    vertical = listContentPaddingVertical
)

val listItemPaddingValuesVerticalOneLine = PaddingValues(vertical = 8.dp)
val listItemPaddingValuesHorizontal = PaddingValues(
    start = 16.dp,
    end = 24.dp
)

val navigationDrawerDividerPadding = PaddingValues(
    horizontal = 28.dp,
    vertical = dividerPaddingVertical
)
val navigationDrawerHeadlineColor
    @Composable
    get() = MaterialTheme.colorScheme.onSurfaceVariant
val navigationDrawerHeadlineHeight = 56.dp
val navigationDrawerHeadlinePadding = PaddingValues(
    horizontal = 28.dp
)
val navigationDrawerHeadlineTextStyle
    @Composable
    get() = MaterialTheme.typography.titleSmall

val segmentedButtonColorBorder
    @Composable
    get() = MaterialTheme.colorScheme.outline
val segmentedButtonColorContainerSelected
    @Composable
    get() = MaterialTheme.colorScheme.secondaryContainer
val segmentedButtonColorContainerUnselected
    @Composable
    get() = MaterialTheme.colorScheme.surface
val segmentedButtonColorContentSelected
    @Composable
    get() = MaterialTheme.colorScheme.onSecondaryContainer
val segmentedButtonColorContentUnselected
    @Composable
    get() = MaterialTheme.colorScheme.onSurface
const val segmentedButtonColorDisabledOpacityBorder = 0.12f
const val segmentedButtonColorDisabledOpacityContent = alphaContentDisabled
const val segmentedButtonCornerPercent = 50
val segmentedButtonHeight = 40.dp
val segmentedButtonPaddingHorizontal = 12.dp
val segmentedButtonShape = CircleShape
val segmentedButtonTextStyle
    @Composable
    get() = MaterialTheme.typography.labelLarge

val sliderHandleSize = 20.dp
val sliderLabelContainerWidth = 28.dp
val sliderLabelContainerColor
    @Composable
    get() = MaterialTheme.colorScheme.primary
val sliderLabelTextColor
    @Composable
    get() = MaterialTheme.colorScheme.onPrimary
val sliderLabelTextStyle
    @Composable
    get() = MaterialTheme.typography.labelMedium

val topAppBarElevation = elevationLevel2