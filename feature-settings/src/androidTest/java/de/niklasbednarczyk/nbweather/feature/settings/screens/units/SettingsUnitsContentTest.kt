package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioOptionModel
import de.niklasbednarczyk.nbweather.feature.settings.extensions.displayText
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SettingsUnitsContentTest : NBContentTest() {

    @Test
    fun radioGroup_shouldRenderCorrectly() {
        // Arrange
        val oldSelectedKey = NBUnitsType.METRIC
        val newSelectedKey = NBUnitsType.IMPERIAL
        var currentlySelectedKey: NBUnitsType? = null

        val uiState = SettingsUnitsUiState(
            radioGroup = NBRadioGroupModel(
                options = NBUnitsType.values()
                    .map { type ->
                        NBRadioOptionModel(type, type.displayText)
                    },
                selectedKey = oldSelectedKey
            )
        )

        // Act
        setContent {
            SettingsUnitsContent(
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