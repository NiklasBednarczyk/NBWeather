package de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

interface OwmNavigationBarItem {

    val label: OwmString

    val icon: OwmIconModel

}