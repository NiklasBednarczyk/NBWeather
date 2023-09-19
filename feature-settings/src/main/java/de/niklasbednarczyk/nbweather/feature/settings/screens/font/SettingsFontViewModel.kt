package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import dagger.hilt.android.lifecycle.HiltViewModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxes
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontAxisModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.font.fontFamily
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.data.settings.repositories.SettingsFontRepository
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.SettingsListViewModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsFontViewModel @Inject constructor(
    private val settingsFontRepository: SettingsFontRepository
) : SettingsListViewModel() {

    private val fontStateFlow = MutableStateFlow<NBFontModel?>(null)

    override val stickyHeaderFlow: Flow<NBStickyHeaderModel?> = fontStateFlow.map { font ->
        NBStickyHeaderModel(
            text = NBString.ResString(R.string.screen_settings_font_sticky_header_text),
            fontFamily = font?.fontFamily
        )
    }

    override val itemsFlow: Flow<List<SettingsListItemModel>> =
        fontStateFlow.map { font ->
            val items = mutableListOf<SettingsListItemModel>()

            if (font == null) return@map emptyList()

            items.add(
                NBFontAxes.Slant.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_slant),
                    value = font.slant,
                    updateLocally = ::updateSlantLocally,
                    updateGlobally = ::updateSlantGlobally
                )
            )

            items.add(
                NBFontAxes.Width.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_width),
                    value = font.width,
                    updateLocally = ::updateWidthLocally,
                    updateGlobally = ::updateWidthGlobally
                )
            )

            items.add(
                NBFontAxes.Weight.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_weight),
                    value = font.weight,
                    updateLocally = ::updateWeightLocally,
                    updateGlobally = ::updateWeightGlobally
                )
            )

            items.add(
                NBFontAxes.Grade.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_grade),
                    value = font.grade,
                    updateLocally = ::updateGradeLocally,
                    updateGlobally = ::updateGradeGlobally
                )
            )

            items.add(
                NBFontAxes.CounterWidth.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_counter_width),
                    value = font.counterWidth,
                    updateLocally = ::updateCounterWidthLocally,
                    updateGlobally = ::updateCounterWidthGlobally
                )
            )

            items.add(
                NBFontAxes.ThinStroke.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_thin_stroke),
                    value = font.thinStroke,
                    updateLocally = ::updateThinStrokeLocally,
                    updateGlobally = ::updateThinStrokeGlobally
                )
            )

            items.add(
                NBFontAxes.AscenderHeight.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_ascender_height),
                    value = font.ascenderHeight,
                    updateLocally = ::updateAscenderHeightLocally,
                    updateGlobally = ::updateAscenderHeightGlobally
                )
            )

            items.add(
                NBFontAxes.DescenderDepth.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_descender_depth),
                    value = font.descenderDepth,
                    updateLocally = ::updateDescenderDepthLocally,
                    updateGlobally = ::updateDescenderDepthGlobally
                )
            )

            items.add(
                NBFontAxes.FigureHeight.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_figure_height),
                    value = font.figureHeight,
                    updateLocally = ::updateFigureHeightLocally,
                    updateGlobally = ::updateFigureHeightGlobally
                )
            )

            items.add(
                NBFontAxes.LowercaseHeight.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_lowercase_height),
                    value = font.lowercaseHeight,
                    updateLocally = ::updateLowercaseHeightLocally,
                    updateGlobally = ::updateLowercaseHeightGlobally
                )
            )

            items.add(
                NBFontAxes.UppercaseHeight.toSlider(
                    title = NBString.ResString(R.string.screen_settings_font_axis_uppercase_height),
                    value = font.uppercaseHeight,
                    updateLocally = ::updateUppercaseHeightLocally,
                    updateGlobally = ::updateUppercaseHeightGlobally
                )
            )

            items
        }

    init {

        setLocalFontToGlobal()

        collectFlow(
            { stickyHeaderFlow },
            { oldUiState, output -> oldUiState.copy(stickyHeader = output) }
        )

        collectFlow(
            { itemsFlow },
            { oldUiState, output -> oldUiState.copy(items = output) }
        )

    }

    private fun setLocalFontToGlobal() {
        updateFontLocally {
            settingsFontRepository.getData().firstOrNull()
        }
    }

    fun resetToDefault() {
        launchSuspend {
            settingsFontRepository.resetToDefault()
            setLocalFontToGlobal()
        }
    }

    private fun NBFontAxisModel.toSlider(
        title: NBString?,
        value: Float,
        updateLocally: (Float) -> Unit,
        updateGlobally: () -> Unit,
    ): SettingsListItemModel.ItemSlider = SettingsListItemModel.ItemSlider(
        slider = NBSliderModel(
            title = title,
            value = value,
            minValue = minValue,
            maxValue = maxValue,
            fractionDigits = fractionDigits,
            onValueChange = updateLocally,
            onValueChangeFinished = updateGlobally
        )
    )


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

    private fun updateSlantGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateSlant(font.slant)
        }
    }

    private fun updateSlantLocally(slant: Float) {
        updateFontLocally { font ->
            font?.copy(slant = slant)
        }
    }

    private fun updateWidthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateWidth(font.width)
        }
    }

    private fun updateWidthLocally(width: Float) {
        updateFontLocally { font ->
            font?.copy(width = width)
        }
    }

    private fun updateWeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateWeight(font.weight)
        }
    }

    private fun updateWeightLocally(weight: Float) {
        updateFontLocally { font ->
            font?.copy(weight = weight)
        }
    }

    private fun updateGradeGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateGrade(font.grade)
        }
    }

    private fun updateGradeLocally(grade: Float) {
        updateFontLocally { font ->
            font?.copy(grade = grade)
        }
    }

    private fun updateCounterWidthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateCounterWidth(font.counterWidth)
        }
    }

    private fun updateCounterWidthLocally(counterWidth: Float) {
        updateFontLocally { font ->
            font?.copy(counterWidth = counterWidth)
        }
    }

    private fun updateThinStrokeGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateThinStroke(font.thinStroke)
        }
    }

    private fun updateThinStrokeLocally(thinStroke: Float) {
        updateFontLocally { font ->
            font?.copy(thinStroke = thinStroke)
        }
    }

    private fun updateAscenderHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateAscenderHeight(font.ascenderHeight)
        }
    }

    private fun updateAscenderHeightLocally(ascenderHeight: Float) {
        updateFontLocally { font ->
            font?.copy(ascenderHeight = ascenderHeight)
        }
    }

    private fun updateDescenderDepthGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateDescenderDepth(font.descenderDepth)
        }
    }

    private fun updateDescenderDepthLocally(descenderDepth: Float) {
        updateFontLocally { font ->
            font?.copy(descenderDepth = descenderDepth)
        }
    }

    private fun updateFigureHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateFigureHeight(font.figureHeight)
        }
    }

    private fun updateFigureHeightLocally(figureHeight: Float) {
        updateFontLocally { font ->
            font?.copy(figureHeight = figureHeight)
        }
    }

    private fun updateLowercaseHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateLowercaseHeight(font.lowercaseHeight)
        }
    }

    private fun updateLowercaseHeightLocally(lowercaseHeight: Float) {
        updateFontLocally { font ->
            font?.copy(lowercaseHeight = lowercaseHeight)
        }
    }

    private fun updateUppercaseHeightGlobally() {
        updateFontGlobally { font ->
            settingsFontRepository.updateUppercaseHeight(font.uppercaseHeight)
        }
    }

    private fun updateUppercaseHeightLocally(uppercaseHeight: Float) {
        updateFontLocally { font ->
            font?.copy(uppercaseHeight = uppercaseHeight)
        }
    }
}