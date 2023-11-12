package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsAlertModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models.ForecastAlertsViewData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals

class ForecastAlertsContentTest : NBComposableTest() {

    @Test
    fun viewDataResource_shouldRenderCorrectly() {
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
        setContent {
            ForecastAlertsContent(
                uiState = uiState,
                startIntent = {}
            )
        }

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

    @Test
    fun startIntent_shouldBeCalledCorrectly() {
        // Arrange
        var clickCounterEmail = 0
        var clickCounterURL = 0
        var clickCounterUnknown = 0

        val emailDescription = createNBString("john.doe@example.com")
        val emailSenderName = createNBString("jane.doe@example.com")

        val urlDescription = createNBString("www.example.com")
        val urlSenderName = createNBString("https://www.example.com")

        val infoItems = listOf(
            ForecastAlertsAlertInfoItem.Description(
                text = emailDescription
            ),
            ForecastAlertsAlertInfoItem.Description(
                text = urlDescription
            ),
            ForecastAlertsAlertInfoItem.SenderName(
                text = emailSenderName
            ),
            ForecastAlertsAlertInfoItem.SenderName(
                text = urlSenderName
            )
        )
        val uiState = createTestUiState(infoItems)

        // Act
        setContent {
            ForecastAlertsContent(
                uiState = uiState,
                startIntent = { intent ->
                    val dataString = intent?.dataString
                    if (dataString != null) {
                        if (dataString.startsWith("mailto")) {
                            clickCounterEmail++
                        }
                        else if (dataString.startsWith("http")) {
                            clickCounterURL++
                        } else {
                            clickCounterUnknown++
                        }
                    } else {
                        clickCounterUnknown++
                    }
                }
            )
        }

        // Assert
        assertCompose {
            onNodeWithText(emailDescription)
                .performClick()
            onNodeWithText(emailSenderName)
                .performClick()
            onNodeWithText(urlDescription)
                .performClick()
            onNodeWithText(urlSenderName)
                .performClick()
        }

        assertEquals(2, clickCounterEmail)
        assertEquals(2, clickCounterURL)
        assertEquals(0, clickCounterUnknown)
    }

    private fun createTestUiState(
        infoItems: List<ForecastAlertsAlertInfoItem>
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

}

