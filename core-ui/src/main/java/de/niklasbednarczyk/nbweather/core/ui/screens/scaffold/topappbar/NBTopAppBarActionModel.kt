package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem

data class NBTopAppBarActionModel(
    val icon: NBIconItem,
    val onClick: () -> Unit
)