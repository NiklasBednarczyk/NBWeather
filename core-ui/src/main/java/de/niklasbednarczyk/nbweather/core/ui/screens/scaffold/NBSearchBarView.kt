package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButtonView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NBSearchBarView(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    searchActive: Boolean,
    onSearchActiveChange: (Boolean) -> Unit,
    enabled: Boolean,
    popBackStackEnabled: Boolean,
    popBackStack: () -> Unit,
    trailingIcon: @Composable () -> Unit,
    contentOnSearchActive: @Composable ColumnScope.() -> Unit,
    contentOnSearchInactive: @Composable ColumnScope.() -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .semantics { traversalIndex = -1f },
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = {
                focusManager.clearFocus()
            },
            active = searchActive,
            onActiveChange = onSearchActiveChange,
            enabled = enabled,
            placeholder = {
                Text(
                    text = NBString.ResString(R.string.screen_search_overview_bar_placeholder)
                        .asString()
                )
            },
            leadingIcon = {
                if (searchActive) {
                    NBIconButtonView(
                        icon = NBIcons.Back,
                        onClick = {
                            onSearchActiveChange(false)
                        },
                        enabled = enabled
                    )
                } else if (popBackStackEnabled) {
                    NBIconButtonView(
                        icon = NBIcons.Back,
                        onClick = popBackStack,
                        enabled = enabled
                    )
                } else {
                    NBIconView(
                        icon = NBIcons.Search
                    )
                }
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    NBIconButtonView(
                        icon = NBIcons.Cancel,
                        onClick = {
                            onSearchQueryChange("")
                        },
                        enabled = enabled
                    )
                } else {
                    trailingIcon()
                }
            },
            content = {
                if (searchActive) {
                    contentOnSearchActive()
                }
            }
        )

        if (!searchActive) {
            contentOnSearchInactive()
        }
    }
}