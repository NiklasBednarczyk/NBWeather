package de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class NBTopAppBarActionModel(
    val icon: NBIconModel,
    val onClick: () -> Unit
)