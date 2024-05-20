package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import androidx.compose.ui.text.font.FontFamily
import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.font.fontFamily
import de.niklasbednarczyk.nbweather.core.ui.screens.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsFontRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.models.SettingsFontItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsFontViewModel @Inject constructor(
    private val settingsFontRepository: SettingsFontRepository
) : NBViewModel<SettingsFontUiState>(SettingsFontUiState()) {

    private val fontStateFlow = MutableStateFlow<NBFontModel?>(null)

    init {

        setLocalFontToGlobal()

        collectFlow(
            { getStickyHeaderFlow() },
            { oldUiState, output -> oldUiState.copy(stickyHeader = output) }
        )

        collectFlow(
            { getItemsFlow() },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun getStickyHeaderFlow(): Flow<NBStickyHeaderModel> {
        return fontStateFlow.map { font ->
            NBStickyHeaderModel(
                text = NBString.ResString(R.string.screen_settings_font_sticky_header_text),
                fontFamily = font?.fontFamily
            )
        }
    }

    private fun getItemsFlow(): Flow<List<SettingsFontItemModel>> {
        return fontStateFlow.map { font ->
            SettingsFontItemModel.from(
                font = font,
                updateSlantGlobally = ::updateSlantGlobally,
                updateSlantLocally = ::updateSlantLocally,
                updateWidthGlobally = ::updateWidthGlobally,
                updateWidthLocally = ::updateWidthLocally,
                updateWeightGlobally = ::updateWeightGlobally,
                updateWeightLocally = ::updateWeightLocally,
                updateGradeGlobally = ::updateGradeGlobally,
                updateGradeLocally = ::updateGradeLocally,
                updateCounterWidthGlobally = ::updateCounterWidthGlobally,
                updateCounterWidthLocally = ::updateCounterWidthLocally,
                updateThinStrokeGlobally = ::updateThinStrokeGlobally,
                updateThinStrokeLocally = ::updateThinStrokeLocally,
                updateAscenderHeightGlobally = ::updateAscenderHeightGlobally,
                updateAscenderHeightLocally = ::updateAscenderHeightLocally,
                updateDescenderDepthGlobally = ::updateDescenderDepthGlobally,
                updateDescenderDepthLocally = ::updateDescenderDepthLocally,
                updateFigureHeightGlobally = ::updateFigureHeightGlobally,
                updateFigureHeightLocally = ::updateFigureHeightLocally,
                updateLowercaseHeightGlobally = ::updateLowercaseHeightGlobally,
                updateLowercaseHeightLocally = ::updateLowercaseHeightLocally,
                updateUppercaseHeightGlobally = ::updateUppercaseHeightGlobally,
                updateUppercaseHeightLocally = ::updateUppercaseHeightLocally
            )
        }
    }


    fun resetToDefault() {
        launchSuspend {
            settingsFontRepository.resetToDefault()
            setLocalFontToGlobal()
        }
    }

    fun updateFontFamily(
        fontFamily: FontFamily?
    ) {
        if (uiState.value.fontFamily == null) {
            updateUiState { oldUiState ->
                oldUiState.copy(fontFamily = fontFamily)
            }
        }
    }

    private fun updateSlantGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateSlant(font.slant)
        }
    }

    private fun updateSlantLocally(
        slant: Float
    ) {
        updateFontLocally { font ->
            font?.copy(slant = slant)
        }
    }

    private fun updateWidthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateWidth(font.width)
        }
    }

    private fun updateWidthLocally(
        width: Float
    ) {
        updateFontLocally { font ->
            font?.copy(width = width)
        }
    }

    private fun updateWeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateWeight(font.weight)
        }
    }

    private fun updateWeightLocally(
        weight: Float
    ) {
        updateFontLocally { font ->
            font?.copy(weight = weight)
        }
    }

    private fun updateGradeGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateGrade(font.grade)
        }
    }

    private fun updateGradeLocally(
        grade: Float
    ) {
        updateFontLocally { font ->
            font?.copy(grade = grade)
        }
    }

    private fun updateCounterWidthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateCounterWidth(font.counterWidth)
        }
    }

    private fun updateCounterWidthLocally(
        counterWidth: Float
    ) {
        updateFontLocally { font ->
            font?.copy(counterWidth = counterWidth)
        }
    }

    private fun updateThinStrokeGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateThinStroke(font.thinStroke)
        }
    }

    private fun updateThinStrokeLocally(
        thinStroke: Float
    ) {
        updateFontLocally { font ->
            font?.copy(thinStroke = thinStroke)
        }
    }

    private fun updateAscenderHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateAscenderHeight(font.ascenderHeight)
        }
    }

    private fun updateAscenderHeightLocally(
        ascenderHeight: Float
    ) {
        updateFontLocally { font ->
            font?.copy(ascenderHeight = ascenderHeight)
        }
    }

    private fun updateDescenderDepthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateDescenderDepth(font.descenderDepth)
        }
    }

    private fun updateDescenderDepthLocally(
        descenderDepth: Float
    ) {
        updateFontLocally { font ->
            font?.copy(descenderDepth = descenderDepth)
        }
    }

    private fun updateFigureHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateFigureHeight(font.figureHeight)
        }
    }

    private fun updateFigureHeightLocally(
        figureHeight: Float
    ) {
        updateFontLocally { font ->
            font?.copy(figureHeight = figureHeight)
        }
    }

    private fun updateLowercaseHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateLowercaseHeight(font.lowercaseHeight)
        }
    }

    private fun updateLowercaseHeightLocally(
        lowercaseHeight: Float
    ) {
        updateFontLocally { font ->
            font?.copy(lowercaseHeight = lowercaseHeight)
        }
    }

    private fun updateUppercaseHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateUppercaseHeight(font.uppercaseHeight)
        }
    }

    private fun updateUppercaseHeightLocally(
        uppercaseHeight: Float
    ) {
        updateFontLocally { font ->
            font?.copy(uppercaseHeight = uppercaseHeight)
        }
    }

    private fun setLocalFontToGlobal() {
        updateFontLocally {
            settingsFontRepository.getData().firstOrNull()
        }
    }

    private fun updateFontGlobally(
        update: suspend (font: NBFontModel) -> Unit
    ) {
        launchSuspend {
            val font = fontStateFlow.firstOrNull()
            if (font != null) {
                update(font)
            }
        }
    }

    private fun updateFontLocally(
        update: suspend (font: NBFontModel?) -> NBFontModel?
    ) {
        updateStateFlow(fontStateFlow) { font ->
            update(font)
        }
    }

}