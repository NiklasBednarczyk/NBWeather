package de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

interface NBNavigationBarItem {

    val label: NBString

    val icon: NBIconModel.FilledAndOutlined

}