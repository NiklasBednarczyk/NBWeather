package de.niklasbednarczyk.nbweather.feature.about.screens.overview

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.context.startIntent
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.filledTonalButtonIconSize
import de.niklasbednarczyk.nbweather.core.ui.dimens.filledTonalButtonPaddingBetweenElements
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.rowHorizontalArrangementSmall
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageItem
import de.niklasbednarczyk.nbweather.core.ui.image.NBImageView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarController.Companion.rememberNBSnackbarController
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewButtonModel
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.models.AboutOverviewItem

@Composable
fun AboutOverviewRoute(
    viewModel: AboutOverviewViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AboutOverviewScreen(
        uiState = uiState,
        popBackStack = popBackStack
    )
}

@Composable
internal fun AboutOverviewScreen(
    uiState: AboutOverviewUiState,
    popBackStack: () -> Unit
) {
    val context = LocalContext.current

    val snackbarController = rememberNBSnackbarController()

    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_about_overview_title),
            popBackStack = popBackStack
        )
    ) {
        LazyColumn(
            verticalArrangement = columnVerticalArrangementBig,
            contentPadding = listContentPaddingValuesVertical
        ) {
            items(uiState.items) { item ->
                Item(
                    item = item,
                    startIntent = { intent ->
                        context.startIntent(
                            intent = intent,
                            showSnackbar = snackbarController::showSnackbar
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun Banner(
    banner: NBImageItem?
) {
    nbNullSafe(banner) { b ->
        NBImageView(
            modifier = Modifier.fillMaxWidth(),
            image = b
        )
    }
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
                NBIconView(
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
private fun Credit(
    item: AboutOverviewItem.Credit,
    startIntent: (intent: Intent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = columnVerticalArrangementBig
    ) {
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
private fun Divider() {
    HorizontalDivider(
        modifier = Modifier.padding(
            vertical = dividerPaddingVertical
        )
    )
}

@Composable
private fun Item(
    item: AboutOverviewItem,
    startIntent: (intent: Intent) -> Unit
) {
    when (item) {
        AboutOverviewItem.Divider -> {
            Divider()
        }

        is AboutOverviewItem.Credit -> {
            Credit(
                item = item,
                startIntent = startIntent
            )
        }
    }
}
