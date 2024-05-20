package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import androidx.compose.ui.text.font.FontFamily
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.models.SettingsFontItemModel

data class SettingsFontUiState(
    val stickyHeader: NBStickyHeaderModel? = null,
    val items: List<SettingsFontItemModel> = emptyList(),
    val fontFamily: FontFamily? = null
)