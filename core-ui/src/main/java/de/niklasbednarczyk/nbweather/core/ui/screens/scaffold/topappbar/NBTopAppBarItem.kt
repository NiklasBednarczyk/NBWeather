package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBTopAppBarItem {

    val title: NBString?

    data class CenterAligned(
        override val title: NBString?,
        val openDrawer: () -> Unit,
        val action: NBTopAppBarActionModel
    ) : NBTopAppBarItem


    data class Small(
        override val title: NBString?,
        val popBackStack: () -> Unit,
        val actions: List<NBTopAppBarActionModel> = emptyList(),
    ) : NBTopAppBarItem

}