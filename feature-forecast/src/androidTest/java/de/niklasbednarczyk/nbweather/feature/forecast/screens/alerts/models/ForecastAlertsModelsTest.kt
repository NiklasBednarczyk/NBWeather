package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.NBForecastModelsTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ForecastAlertsModelsTest : NBForecastModelsTest {

    @Test
    fun alertInfoItem_dates_shouldConvertCorrectly() {
        val klass = ForecastAlertsAlertInfoItem.Dates::class.java

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                startDateValue = null,
                endDateValue = null
            )
        )

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                startDateValue = testStartDateValue,
                endDateValue = null
            )
        )

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                startDateValue = null,
                endDateValue = testEndDateValue
            )
        )

        testAlertInfoItemExpectedNotNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                startDateValue = testStartDateValue,
                endDateValue = testEndDateValue
            ),
            assertInfoItem = { nationalWeatherAlert, infoItem ->
                assertEquals(
                    nationalWeatherAlert.startDate?.value,
                    infoItem.startDate.dt.value
                )
                assertEquals(
                    nationalWeatherAlert.endDate?.value,
                    infoItem.endDate.dt.value
                )
            }
        )
    }

    @Test
    fun alertInfoItem_description_shouldConvertCorrectly() {
        val klass = ForecastAlertsAlertInfoItem.Description::class.java

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                descriptionText = null
            )
        )

        testAlertInfoItemExpectedNotNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                descriptionText = "Description"
            ),
            assertInfoItem = { nationalWeatherAlert, infoItem ->
                assertEquals(nationalWeatherAlert.description, infoItem.text)
            }
        )
    }

    @Test
    fun alertInfoItem_eventName_shouldConvertCorrectly() {
        val klass = ForecastAlertsAlertInfoItem.EventName::class.java

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                eventNameText = null
            )
        )

        testAlertInfoItemExpectedNotNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                eventNameText = "EventName"
            ),
            assertInfoItem = { nationalWeatherAlert, infoItem ->
                assertEquals(nationalWeatherAlert.eventName, infoItem.text)
            }
        )
    }

    @Test
    fun alertInfoItem_senderName_shouldConvertCorrectly() {
        val klass = ForecastAlertsAlertInfoItem.SenderName::class.java

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                senderNameText = null
            )
        )

        testAlertInfoItemExpectedNotNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                senderNameText = "SenderName"
            ),
            assertInfoItem = { nationalWeatherAlert, infoItem ->
                assertEquals(nationalWeatherAlert.senderName, infoItem.text)
            }
        )
    }

    @Test
    fun alertInfoItem_tags_shouldConvertCorrectly() {
        val klass = ForecastAlertsAlertInfoItem.Tags::class.java

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                tagTexts = null
            )
        )

        testAlertInfoItemExpectedNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                tagTexts = emptyList()
            )
        )

        testAlertInfoItemExpectedNotNull(
            klass = klass,
            nationalWeatherAlert = createTestNationalWeatherAlert(
                tagTexts = listOf("Tag 1", "Tag 2", "Tag 3")
            ),
            assertInfoItem = { nationalWeatherAlert, infoItem ->
                assertListsContainSameItems(nationalWeatherAlert.tags, infoItem.tags)
            }
        )
    }

    @Test
    fun alert_emptyInfoItems_shouldBeNull() {
        // Arrange
        val nationalWeatherAlert = createTestNationalWeatherAlert(
            senderNameText = null,
            eventNameText = null,
            startDateValue = null,
            endDateValue = null,
            descriptionText = null,
            tagTexts = null
        )

        // Act
        val alert = ForecastAlertsAlertModel.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlert = nationalWeatherAlert
        )

        // Assert
        assertNull(alert)
    }

    @Test
    fun alert_fullInfoItems_shouldNotBeNull() {
        // Arrange
        val nationalWeatherAlert = createTestNationalWeatherAlert()

        // Act
        val alert = ForecastAlertsAlertModel.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlert = nationalWeatherAlert
        )

        // Assert
        assertNotNull(alert)
        assertListHasSize(alert.infoItems, 5)
    }

    @Test
    fun viewData_emptyAlerts_shouldBeNull() {
        // Arrange
        val nationalWeatherAlerts = createTestNationalWeatherAlerts(0)

        // Act
        val viewData = ForecastAlertsViewData.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlerts = nationalWeatherAlerts
        )

        // Assert
        assertNull(viewData)
    }

    @Test
    fun viewData_fullAlerts_shouldNotBeNull() {
        // Arrange
        val nationalWeatherAlerts = createTestNationalWeatherAlerts(3)

        // Act
        val viewData = ForecastAlertsViewData.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlerts = nationalWeatherAlerts
        )

        // Assert
        assertNotNull(viewData)
        assertNull(viewData.initialKey)
        assertListHasSize(viewData.items, 3)
    }

    private fun <T : Any> testAlertInfoItemExpectedNull(
        klass: Class<T>,
        nationalWeatherAlert: NationalWeatherAlertModelData
    ) {
        // Arrange + Act
        val alert = ForecastAlertsAlertModel.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlert = nationalWeatherAlert
        )!!
        val infoItems = alert.infoItems

        // Assert
        assertListDoesNotContainClass(
            actual = infoItems,
            klass = klass
        )
    }

    private fun <T : Any> testAlertInfoItemExpectedNotNull(
        klass: Class<T>,
        nationalWeatherAlert: NationalWeatherAlertModelData,
        assertInfoItem: (nationalWeatherAlert: NationalWeatherAlertModelData, infoItem: T) -> Unit
    ) {
        // Arrange + Act
        val alert = ForecastAlertsAlertModel.from(
            timezoneOffset = testTimezoneOffset,
            nationalWeatherAlert = nationalWeatherAlert
        )!!
        val infoItems = alert.infoItems

        // Assert
        assertListDoesContainClassOnce(
            actual = infoItems,
            klass = klass
        )

        assertInfoItem(nationalWeatherAlert, infoItems.getFirstItemFromList(klass))
    }

}