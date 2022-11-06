package de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString

@Composable
inline fun <reified T> OwmNavigationBar(
    selectedNavigationBarItem: T,
    crossinline selectNavigationBarItem: (T) -> Unit
) where T : Enum<T>, T : OwmNavigationBarItem {

    NavigationBar {
        enumValues<T>().forEach { navigationBarItem ->
            val isSelected = navigationBarItem == selectedNavigationBarItem
            NavigationBarItem(
                icon = {
                    OwmIcon(
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