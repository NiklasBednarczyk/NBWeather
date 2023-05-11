package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SettingsTimeFormatContentTest : NBContentTest() {

    @Test
    fun radioGroup_shouldRenderCorrectly() {
        // Arrange
        val oldSelectedKey = NBTimeFormatType.HOUR_24
        val newSelectedKey = NBTimeFormatType.HOUR_12
        var currentlySelectedKey: NBTimeFormatType? = null

        val uiState = SettingsTimeFormatUiState(
            radioGroup = NBRadioGroupModel(
                options = NBTimeFormatType.values()
                    .map { type ->
                        NBRadioOptionModel(type, type.displayText)
                    },
                selectedKey = oldSelectedKey
            )
        )

        // Act
        setContent {
            SettingsTimeFormatContent(
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