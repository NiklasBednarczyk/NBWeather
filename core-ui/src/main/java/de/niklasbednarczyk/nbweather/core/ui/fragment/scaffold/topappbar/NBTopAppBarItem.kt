package de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.emptyIcon

sealed interface NBTopAppBarItem {

    sealed interface Material : NBTopAppBarItem {

        val title: NBString?
        val actions: List<NBTopAppBarActionModel>

        data class CenterAligned(
            override val title: NBString?,
            private val action: NBTopAppBarActionModel
        ) : Material {

            override val actions: List<NBTopAppBarActionModel>
                get() = listOf(action)

        }

        data class Small(
            override val title: NBString?,
            override val actions: List<NBTopAppBarActionModel> = emptyList()
        ) : Material

    }

    data class Search(
        val searchTerm: String,
        val enabled: Boolean = true,
        val showNavigationIcon: Boolean = true,
        val trailingIconWhenEmpty: @Composable () -> Unit = emptyIcon,
        val onSearchTermChanged: (String) -> Unit,
    ) : NBTopAppBarItem


}