package de.niklasbednarczyk.nbweather.feature.settings.screens.font

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.font.FontFamily
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.font.changeFontFamily
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.models.SettingsFontItemModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SettingsFontScreenTest : NBComposableTest() {

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        var popBackStackCalled = false
        var resetToDefaultCalled = false

        // Act
        setScreenContent(
            popBackStack = {
                popBackStackCalled = true
                resetToDefaultCalled = false
            },
            resetToDefault = {
                popBackStackCalled = false
                resetToDefaultCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_settings_font_title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Back)
                .assertIsDisplayed()
                .performClick()

            assertTrue(popBackStackCalled)
            assertFalse(resetToDefaultCalled)

            onNodeWithIcon(NBIcons.Reset)
                .assertIsDisplayed()
                .performClick()

            assertFalse(popBackStackCalled)
            assertTrue(resetToDefaultCalled)
        }
    }

    @Test
    fun content_stickyHeader_shouldRenderCorrectly() {
        // Arrange
        val stickyHeader = NBStickyHeaderModel(
            text = createNBString("StickyHeader"),
            fontFamily = FontFamily.Cursive
        )

        val uiState = createTestUiState(
            stickyHeader = stickyHeader
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(stickyHeader.text)
                .assertIsDisplayed()
                .assertIsOfFontFamily(stickyHeader.fontFamily)
        }
    }

    @Test
    fun content_items_shouldRenderCorrectly() {
        // Arrange
        var selectedSliderValue = 0f

        val item = SettingsFontItemModel(
            slider = NBSliderModel(
                title = createNBString("ItemSlider Title"),
                value = selectedSliderValue,
                minValue = 0f,
                maxValue = 1f,
                fractionDigits = 0,
                onValueChange = { value -> selectedSliderValue = value },
                onValueChangeFinished = {},
            )
        )

        val uiState = createTestUiState(
            item = item
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithContentDescription(item.slider.title)
                .assertIsDisplayed()
                .swipeRight()
        }

        assertEquals(item.slider.maxValue, selectedSliderValue)
        assertNotEquals(item.slider.minValue, selectedSliderValue)
    }

    @Test
    fun content_fontFamily_shouldBeChangedOnStart() {
        // Arrange
        val startingFontFamily = FontFamily.Cursive

        var updatedFontFamily: FontFamily? = null

        val uiState = createTestUiState(
            fontFamily = null
        )

        // Act
        setScreenContent(
            uiState = uiState,
            updateFontFamily = { fontFamily ->
                updatedFontFamily = fontFamily
            },
            typography = Typography().changeFontFamily(startingFontFamily)
        )

        // Assert
        assertNotNull(updatedFontFamily)
        assertEquals(startingFontFamily, updatedFontFamily)
    }

    private fun createTestUiState(
        stickyHeader: NBStickyHeaderModel? = null,
        item: SettingsFontItemModel? = null,
        fontFamily: FontFamily? = null
    ): SettingsFontUiState {
        return SettingsFontUiState(
            stickyHeader = stickyHeader,
            items = listOfNotNull(item),
            fontFamily = fontFamily
        )
    }

    private fun setScreenContent(
        uiState: SettingsFontUiState = createTestUiState(),
        popBackStack: () -> Unit = {},
        resetToDefault: () -> Unit = {},
        updateFontFamily: (fontFamily: FontFamily?) -> Unit = {},
        typography: Typography? = null
    ) {
        setContent {
            MaterialTheme(
                typography = typography ?: MaterialTheme.typography
            ) {
                SettingsFontScreen(
                    uiState = uiState,
                    popBackStack = popBackStack,
                    resetToDefault = resetToDefault,
                    updateFontFamily = updateFontFamily
                )
            }
        }
    }

}
