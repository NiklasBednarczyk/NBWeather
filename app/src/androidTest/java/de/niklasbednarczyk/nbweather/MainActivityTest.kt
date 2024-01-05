package de.niklasbednarczyk.nbweather

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.test.rule.GrantPermissionRule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.test.common.utils.createHiltAndroidRule
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposeTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest : NBComposeTest {

    companion object {
        private const val SEARCH_OVERVIEW_SEARCH_QUERY = "Washington"

        private const val LOCATION_1_NAME = "Washington 1"
        private const val LOCATION_2_NAME = "Washington 2"

        private const val FORECAST_DAILY_DATE = "Sun, Apr 09"
    }

    @get:Rule(order = 0)
    val hiltAndroidRule = createHiltAndroidRule()

    @BindValue
    @get:Rule(order = 1)
    val temporaryFolder = createTemporaryFolderRule()

    @get:Rule(order = 2)
    override val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 3)
    val grantLocationPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Test
    fun app_shouldNavigateCorrectly() {
        assertCompose {
            // Start on empty SearchOverview
            assertIsOnSearchOverview()

            // SearchOverview -> ForecastOverview via search 1
            searchOverviewToForecastOverviewViaSearch(LOCATION_1_NAME)

            forecastOverviewToSearchOverview()

            // SearchOverview -> ForecastOverview via search 2
            searchOverviewToForecastOverviewViaSearch(LOCATION_2_NAME)

            forecastOverviewToSearchOverview()

            // SearchOverview -> ForecastOverview via visited location
            onNodeWithLocationName(LOCATION_1_NAME)
                .performClick()
            assertIsOnForecastOverview(LOCATION_1_NAME)

            // ForecastOverview -> ForecastAlerts
            onNodeWithIcon(NBIcons.Warning)
                .performClick()
            onNodeWithText(R.string.screen_forecast_alerts_title)
                .assertIsDisplayed()

            pressBack()

            // ForecastOverview -> ForecastHourly
            assertForecastItemWithDetailView(
                itemTitleResId = R.string.screen_forecast_overview_hourly_title,
                detailViewString = getString(R.string.screen_forecast_common_forecast_name_temperature)
            )

            // ForecastOverview -> ForecastDaily
            assertForecastItemWithDetailView(
                itemTitleResId = R.string.screen_forecast_overview_daily_title,
                detailViewString = FORECAST_DAILY_DATE
            )

            // Location 1 -> Location 2 via drawer
            clickDrawerItem(LOCATION_2_NAME)
            assertIsOnForecastOverview(LOCATION_2_NAME)

            // ForecastOverview -> SettingsOverview
            clickDrawerItem(getString(R.string.screen_settings_overview_title))
            onNodeWithText(R.string.screen_settings_appearance_title)
                .assertIsDisplayed()

            // SettingsOverview -> SettingsAppearance
            assertSettingsItemWithDetailView(
                itemTitleResId = R.string.screen_settings_appearance_title,
                detailViewResId = R.string.screen_settings_appearance_header_theme
            )

            // SettingsOverview -> SettingsFont
            assertSettingsItemWithDetailView(
                itemTitleResId = R.string.screen_settings_font_title,
                detailViewResId = R.string.screen_settings_font_axis_slant
            )

            // SettingsOverview -> SettingsOrder
            assertSettingsItemWithDetailView(
                itemTitleResId = R.string.screen_settings_order_title,
                detailViewResId = R.string.screen_forecast_overview_current_weather_title
            )

            // SettingsOverview -> SettingsUnits
            assertSettingsItemWithDetailView(
                itemTitleResId = R.string.screen_settings_units_title,
                detailViewResId = R.string.screen_settings_units_header_temperature
            )

            pressBack()

            // ForecastOverview -> AboutOverview
            clickDrawerItem(getString(R.string.screen_about_overview_title))
            onNodeWithText(R.string.screen_about_overview_text_open_weather)
                .assertIsDisplayed()
        }
    }

    private fun ComposeContentTestRule.searchOverviewToForecastOverviewViaSearch(
        locationName: String
    ) {
        onNodeWithText(R.string.screen_search_overview_bar_placeholder)
            .performTextReplacement(SEARCH_OVERVIEW_SEARCH_QUERY)

        waitUntilAtLeastOneExistsWithText(locationName)

        onNodeWithText(locationName)
            .performClick()

        assertIsOnForecastOverview(locationName)
    }

    private fun ComposeContentTestRule.forecastOverviewToSearchOverview() {
        onNodeWithIcon(NBIcons.Search)
            .performClick()
    }

    private fun ComposeContentTestRule.clickDrawerItem(text: String) {
        onNodeWithIcon(NBIcons.Drawer)
            .performClick()
        waitUntilAtLeastOneExistsWithText(de.niklasbednarczyk.nbweather.R.string.app_name)
        onNodeWithText(text, substring = true)
            .performClick()
    }

    private fun ComposeContentTestRule.onNodeWithLocationName(
        locationName: String
    ): SemanticsNodeInteraction {
        return onAllNodesWithText(locationName, substring = true)
            .onLast()
    }

    private fun ComposeContentTestRule.assertIsOnSearchOverview() {
        onNodeWithText(R.string.screen_search_overview_bar_placeholder)
            .assertIsDisplayed()
    }

    private fun ComposeContentTestRule.assertIsOnForecastOverview(
        locationName: String
    ) {
        waitUntilAtLeastOneExistsWithText(locationName, substring = true)
        onNodeWithIcon(NBIcons.MaxTemperature)
            .assertIsDisplayed()
        onNodeWithIcon(NBIcons.MinTemperature)
            .assertIsDisplayed()
        onNodeWithText(R.string.screen_forecast_overview_current_weather_title)
            .performScrollTo()
            .assertExists()
    }

    private fun ComposeContentTestRule.assertForecastItemWithDetailView(
        @StringRes itemTitleResId: Int,
        detailViewString: String
    ) {
        onAllNodes((hasScrollAction()))
            .getNodeWithMostChildren()
            .performScrollToNode(hasText(getString(itemTitleResId)))

        onNodeWithText(itemTitleResId)
            .performClick()

        waitUntilAtLeastOneExistsWithText(detailViewString, substring = true)

        pressBack()
    }

    private fun ComposeContentTestRule.assertSettingsItemWithDetailView(
        @StringRes itemTitleResId: Int,
        @StringRes detailViewResId: Int
    ) {
        onNodeWithText(itemTitleResId)
            .performClick()
        onNodeWithText(detailViewResId)
            .assertIsDisplayed()

        pressBack()
    }

}