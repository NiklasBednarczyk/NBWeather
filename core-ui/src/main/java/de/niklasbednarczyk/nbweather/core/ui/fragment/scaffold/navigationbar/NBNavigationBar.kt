package de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.navigationbar

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
inline fun <reified T> NBNavigationBar(
    selectedNavigationBarItem: T,
    crossinline selectNavigationBarItem: (T) -> Unit
) where T : Enum<T>, T : NBNavigationBarItem {

    NavigationBar {
        enumValues<T>().forEach { navigationBarItem ->
            val isSelected = navigationBarItem == selectedNavigationBarItem
            NavigationBarItem(
                icon = {
                    NBIcon(
                        icon = navigationBarItem.icon,
                        isFilled = isSelected
                    )
                },
                label = { Text(navigationBarItem.label.asString()) },
                selected = isSelected,
                onClick = { selectNavigationBarItem(navigationBarItem) }
            )
        }
    }


}