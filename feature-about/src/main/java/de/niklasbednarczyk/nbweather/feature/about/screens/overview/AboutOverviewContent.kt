package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.filledTonalButtonIconSize
import de.niklasbednarczyk.nbweather.core.ui.dimens.filledTonalButtonPaddingBetweenElements
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.image.NBImage
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageModel
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewButtonModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem

@Composable
fun AboutOverviewContent(
    uiState: AboutOverviewUiState,
    startIntent: (intent: Intent) -> Unit
) {
    LazyColumn(
        verticalArrangement = columnVerticalArrangementBig,
        contentPadding = listContentPaddingValuesVertical
    ) {
        items(uiState.items) { item ->
            when (item) {
                AboutOverviewItem.Divider -> {
                    ItemDivider()
                }

                is AboutOverviewItem.WithBanner -> {
                    WithBanner(
                        item = item,
                        startIntent = startIntent
                    )
                }

                is AboutOverviewItem.WithoutBanner -> {
                    WithoutBanner(
                        item = item,
                        startIntent = startIntent
                    )
                }
            }
        }
    }
}

@Composable
private fun Banner(
    banner: NBImageModel
) {
    NBImage(
        modifier = Modifier.fillMaxWidth(),
        image = banner
    )
}

@Composable
private fun Buttons(
    buttons: List<AboutOverviewButtonModel>,
    startIntent: (intent: Intent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding),
        horizontalArrangement = rowHorizontalArrangementSmall
    ) {
        buttons.forEach { button ->
            FilledTonalButton(
                modifier = Modifier.weight(1f),
                onClick = { startIntent(button.intent) },
            ) {
                NBIcon(
                    modifier = Modifier.size(filledTonalButtonIconSize),
                    icon = button.icon
                )
                Spacer(
                    modifier = Modifier.width(filledTonalButtonPaddingBetweenElements)
                )
                Text(
                    text = button.label.asString()
                )
            }
        }
    }
}

@Composable
private fun Description(
    text: NBString?
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding),
        text = text.asString(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun ItemColumn(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementBig,
        content = content
    )
}

@Composable
private fun ItemDivider() {
    Divider(
        modifier = Modifier.padding(
            vertical = dividerPaddingVertical
        )
    )
}

@Composable
private fun WithBanner(
    item: AboutOverviewItem.WithBanner,
    startIntent: (intent: Intent) -> Unit
) {
    ItemColumn {
        Banner(
            banner = item.banner
        )
        Description(
            text = item.text
        )
        Buttons(
            buttons = item.buttons,
            startIntent = startIntent
        )
    }
}

@Composable
private fun WithoutBanner(
    item: AboutOverviewItem.WithoutBanner,
    startIntent: (intent: Intent) -> Unit
) {
    ItemColumn {
        Description(
            text = item.text
        )
        Buttons(
            buttons = item.buttons,
            startIntent = startIntent
        )
    }
}