package de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar

import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBTopAppBarItem {

    val title: NBString?
    val actions: List<NBTopAppBarActionModel>

    data class CenterAligned(
        override val title: NBString?,
        private val action: NBTopAppBarActionModel
    ) : NBTopAppBarItem {

        override val actions: List<NBTopAppBarActionModel>
            get() = listOf(action)

    }

    data class Small(
        override val title: NBString?,
        override val actions: List<NBTopAppBarActionModel> = emptyList()
    ) : NBTopAppBarItem

}