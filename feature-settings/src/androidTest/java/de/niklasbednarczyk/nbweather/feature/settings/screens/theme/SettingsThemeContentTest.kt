package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SettingsThemeContentTest : NBContentTest() {

    @Test
    fun radioGroup_shouldRenderCorrectly() {
        // Arrange
        val oldSelectedKey = ThemeTypeData.LIGHT
        val newSelectedKey = ThemeTypeData.DARK
        var currentlySelectedKey: ThemeTypeData? = null

        val uiState = SettingsThemeUiState(
            radioGroup = NBRadioGroupModel(
                options = ThemeTypeData.values()
                    .map { type ->
                        NBRadioOptionModel(type, type.displayText)
                    },
                selectedKey = oldSelectedKey
            )
        )

        // Act
        setContent {
            SettingsThemeContent(
                uiState = uiState,
                onItemSelected = { item ->
                    currentlySelectedKey = item
                }
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(newSelectedKey.displayText)
                .performClick()
        }
        assertNotNull(currentlySelectedKey)
        assertEquals(newSelectedKey, currentlySelectedKey)
        assertNotEquals(oldSelectedKey, currentlySelectedKey)
    }

}