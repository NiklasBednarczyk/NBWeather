package de.niklasbednarczyk.nbweather.core.ui.theme.dimens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

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

val topAppBarElevation = 3.dp // Level 2