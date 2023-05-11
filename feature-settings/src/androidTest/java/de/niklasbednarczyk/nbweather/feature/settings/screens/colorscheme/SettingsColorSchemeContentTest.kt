package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SettingsColorSchemeContentTest : NBContentTest() {

    @Test
    fun radioGroup_shouldRenderCorrectly() {
        // Arrange
        val oldSelectedKey = ColorSchemeTypeData.CYAN
        val newSelectedKey = ColorSchemeTypeData.BLUE
        var currentlySelectedKey: ColorSchemeTypeData? = null

        val uiState = SettingsColorSchemeUiState(
            radioGroup = NBRadioGroupModel(
                options = ColorSchemeTypeData.values()
                    .map { type ->
                        NBRadioOptionModel(type, type.displayText)
                    },
                selectedKey = oldSelectedKey
            )
        )

        // Act
        setContent {
            SettingsColorSchemeContent(
                uiState = uiState,
                onItemSelected = { item ->
                    currentlySelectedKey = item
                }
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(oldSelectedKey.displayText)
                .assertIsSelected()
            onNodeWithText(newSelectedKey.displayText)
                .assertIsNotSelected()
                .performClick()
        }
        assertNotNull(currentlySelectedKey)
        assertEquals(newSelectedKey, currentlySelectedKey)
        assertNotEquals(oldSelectedKey, currentlySelectedKey)
    }

}