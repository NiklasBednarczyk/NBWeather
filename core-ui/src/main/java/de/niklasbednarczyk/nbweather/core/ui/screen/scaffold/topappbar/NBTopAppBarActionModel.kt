package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem

data class NBTopAppBarActionModel(
    val icon: NBIconItem,
    val onClick: () -> Unit
)