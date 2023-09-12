package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class NBTopAppBarActionModel(
    val icon: NBIconModel,
    val onClick: () -> Unit
)