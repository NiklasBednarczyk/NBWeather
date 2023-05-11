package de.niklasbednarczyk.nbweather.feature.location.screens.alerts

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertExpandableItem
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models.LocationAlertModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test

class LocationAlertsContentTest : NBContentTest() {

    @Test
    fun alerts_shouldRenderCorrectly() {
        // Arrange
        val alert1 = testAlert("1")
        val alert2 = testAlert("2")

        val uiState = LocationAlertsUiState(
            alertsResource = createNBResource(
                listOf(
                    alert1,
                    alert2
                )
            )
        )

        // Act
        setContent {
            LocationAlertsContent(
                uiState = uiState
            )
        }

        // Assert
        assertCompose {
            assertNotExpanded(alert1)
            assertNotExpanded(alert2)

            toggleExpanded(alert1)

            assertExpanded(alert1)
            assertNotExpanded(alert2)

            toggleExpanded(alert1)

            assertNotExpanded(alert1)
            assertNotExpanded(alert2)
        }


    }

    private fun SemanticsNodeInteractionsProvider.assertNotExpanded(alert: LocationAlertModel) {
        onNodeWithText(alert.eventName)
            .assertIsDisplayed()
        onNodeWithText(alert.startDate, substring = true)
            .assertIsDisplayed()
        onNodeWithText(alert.endDate, substring = true)
            .assertIsDisplayed()
        alert.expandableItems.forEach { expandableItem ->
            when (expandableItem) {
                is LocationAlertExpandableItem.Description -> {
                    assertStringIsNotDisplayed(expandableItem.text)
                }
                is LocationAlertExpandableItem.Sender -> {
                    assertStringIsNotDisplayed(expandableItem.text)
                }
                is LocationAlertExpandableItem.Tags -> {
                    expandableItem.tags.forEach { tag ->
                        assertStringIsNotDisplayed(tag)
                    }
                }
            }
        }
    }

    private fun SemanticsNodeInteractionsProvider.assertExpanded(alert: LocationAlertModel) {
        onNodeWithText(alert.eventName)
            .assertIsDisplayed()
        onNodeWithText(alert.startDate, substring = true)
            .assertIsDisplayed()
        onNodeWithText(alert.endDate, substring = true)
            .assertIsDisplayed()
        alert.expandableItems.forEach { expandableItem ->
            when (expandableItem) {
                is LocationAlertExpandableItem.Description -> {
                    onNodeWithText(expandableItem.text)
                        .assertIsDisplayed()
                }
                is LocationAlertExpandableItem.Sender -> {
                    onNodeWithText(expandableItem.text)
                        .assertIsDisplayed()
                }
                is LocationAlertExpandableItem.Tags -> {
                    expandableItem.tags.forEach { tag ->
                        onNodeWithText(tag)
                            .assertIsDisplayed()
                    }
                }
            }
        }
    }

    private fun SemanticsNodeInteractionsProvider.toggleExpanded(alert: LocationAlertModel) {
        onNodeWithText(alert.eventName)
            .performClick()
    }

    private fun testAlert(name: String): LocationAlertModel = LocationAlertModel(
        eventName = createNBString("eventName $name"),
        startDate = createNBString("startDate $name"),
        endDate = createNBString("endDate $name"),
        expandableItems = listOf(
            LocationAlertExpandableItem.Description(
                text = createNBString("Description $name")
            ),
            LocationAlertExpandableItem.Sender(
                text = createNBString("Sender $name")
            ),
            LocationAlertExpandableItem.Tags(
                tags = listOf(
                    createNBString("Tag1 $name"),
                    createNBString("Tag2 $name")
                )
            )
        )
    )

}