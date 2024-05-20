package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding
import de.niklasbednarczyk.nbweather.core.ui.font.changeFontFamily
import de.niklasbednarczyk.nbweather.core.ui.font.fontFamily
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.list.nBStickyHeader
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderView
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderView
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.models.SettingsFontItemModel

@Composable
fun SettingsFontRoute(
    viewModel: SettingsFontViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsFontScreen(
        uiState = uiState,
        popBackStack = popBackStack,
        resetToDefault = viewModel::resetToDefault,
        updateFontFamily = viewModel::updateFontFamily
    )
}

@Composable
internal fun SettingsFontScreen(
    uiState: SettingsFontUiState,
    popBackStack: () -> Unit,
    resetToDefault: () -> Unit,
    updateFontFamily: (fontFamily: FontFamily?) -> Unit
) {
    val context = LocalContext.current
    val typography = MaterialTheme.typography
    LaunchedEffect(context) {
        updateFontFamily(typography.fontFamily)
    }

    MaterialTheme(
        typography = typography.changeFontFamily(uiState.fontFamily)
    ) {
        NBScaffoldView(
            topAppBarItem = NBTopAppBarItem.Small(
                title = NBString.ResString(R.string.screen_settings_font_title),
                popBackStack = popBackStack,
                actions = listOf(
                    NBTopAppBarActionModel(
                        icon = NBIcons.Reset,
                        onClick = resetToDefault
                    )
                )
            )
        ) {
            LazyColumn(
                contentPadding = listContentPaddingValuesVertical
            ) {
                nbNullSafe(uiState.stickyHeader) { stickyHeader ->
                    nBStickyHeader {
                        NBStickyHeaderView(
                            model = stickyHeader
                        )
                    }
                }
                items(uiState.items) { item ->
                    Item(
                        item = item
                    )
                }
            }
        }
    }
}

@Composable
private fun Item(
    item: SettingsFontItemModel
) {
    NBSliderView(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding,
            vertical = screenVerticalPadding
        ),
        model = item.slider
    )
}