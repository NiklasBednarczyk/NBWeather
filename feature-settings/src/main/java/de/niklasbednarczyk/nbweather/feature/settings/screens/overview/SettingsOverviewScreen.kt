package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemType

@Composable
fun SettingsOverviewRoute(
    viewModel: SettingsOverviewViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToSettingsAppearance: () -> Unit,
    navigateToSettingsFont: () -> Unit,
    navigateToSettingsOrder: () -> Unit,
    navigateToSettingsUnits: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsOverviewScreen(
        uiState = uiState,
        popBackStack = popBackStack,
        navigateToSettingsAppearance = navigateToSettingsAppearance,
        navigateToSettingsFont = navigateToSettingsFont,
        navigateToSettingsOrder = navigateToSettingsOrder,
        navigateToSettingsUnits = navigateToSettingsUnits
    )
}

@Composable
internal fun SettingsOverviewScreen(
    uiState: SettingsOverviewUiState,
    popBackStack: () -> Unit,
    navigateToSettingsAppearance: () -> Unit,
    navigateToSettingsFont: () -> Unit,
    navigateToSettingsOrder: () -> Unit,
    navigateToSettingsUnits: () -> Unit
) {
    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_settings_overview_title),
            popBackStack = popBackStack
        )
    ) {
        LazyColumn(
            contentPadding = listContentPaddingValuesVertical
        ) {
            items(uiState.items) { item ->
                val navigate = when (item) {
                    SettingsOverviewItemType.APPEARANCE -> navigateToSettingsAppearance
                    SettingsOverviewItemType.FONT -> navigateToSettingsFont
                    SettingsOverviewItemType.ORDER -> navigateToSettingsOrder
                    SettingsOverviewItemType.UNITS -> navigateToSettingsUnits
                }
                Item(
                    item = item,
                    navigate = navigate
                )
            }
        }
    }
}

@Composable
private fun Item(
    item: SettingsOverviewItemType,
    navigate: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = navigate),
        headlineContent = {
            Text(
                text = item.title.asString()
            )
        },
        supportingContent = {
            NBTextSingleLine(
                text = item.description.asString()
            )
        },
        leadingContent = {
            NBIconView(
                icon = item.icon
            )
        }
    )
}