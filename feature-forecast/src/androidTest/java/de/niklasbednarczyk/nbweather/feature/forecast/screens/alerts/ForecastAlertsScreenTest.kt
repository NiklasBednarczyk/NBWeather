package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertTrue

class ForecastAlertsScreenTest : NBComposableTest() {

    @Test
    fun topAppBar_shouldRenderCorrectly() {
        // Arrange
        var popBackStackCalled = false

        // Act
        setScreenContent(
            popBackStack = {
                popBackStackCalled = true
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(R.string.screen_forecast_alerts_title)
                .assertIsDisplayed()

            onNodeWithIcon(NBIcons.Back)
                .assertIsDisplayed()
                .performClick()

            assertTrue(popBackStackCalled)
        }
    }

    @Test
    fun content_viewData_shouldRenderCorrectly() {
        // Arrange
        val dates = ForecastAlertsAlertInfoItem.Dates(
            startDate = createNBDateTimeModel(
                timezoneOffsetHours = 1L
            ),
            endDate = createNBDateTimeModel(
                timezoneOffsetHours = 2L
            )
        )
        val description = ForecastAlertsAlertInfoItem.Description(
            text = createNBString("Description")
        )
        val eventName = ForecastAlertsAlertInfoItem.EventName(
            text = createNBString("EventName")
        )
        val senderName = ForecastAlertsAlertInfoItem.SenderName(
            text = createNBString("SenderName")
        )
        val tags = ForecastAlertsAlertInfoItem.Tags(
            tags = listOf(
                createNBString("Tag 1"),
                createNBString("Tag 2")
            )
        )

        val infoItems = listOf(
            dates,
            description,
            eventName,
            senderName,
            tags
        )
        val uiState = createTestUiState(infoItems)

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(dates.startDate.getTime(context))
                .assertIsDisplayed()
            onNodeWithText(dates.endDate.getTime(context))
                .assertIsDisplayed()

            onNodeWithText(description.text)
                .assertIsDisplayed()

            onNodeWithText(eventName.text)
                .assertIsDisplayed()

            onNodeWithText(senderName.text)
                .assertIsDisplayed()

            tags.tags.forEach { tag ->
                onNodeWithText(tag)
                    .assertIsDisplayed()
            }
        }
    }

    private fun createTestUiState(
        infoItems: List<ForecastAlertsAlertInfoItem> = emptyList()
    ): ForecastAlertsUiState {
        val alert = ForecastAlertsAlertModel(
            infoItems = infoItems
        )
        val viewData = ForecastAlertsViewData(
            items = listOf(alert)
        )
        return ForecastAlertsUiState(
            viewDataResource = createNBResource(viewData)
        )
    }

    private fun setScreenContent(
        uiState: ForecastAlertsUiState = createTestUiState(),
        popBackStack: () -> Unit = {}
    ) {
        setContent {
            ForecastAlertsScreen(
                uiState = uiState,
                popBackStack = popBackStack
            )
        }
    }

}

