package de.niklasbednarczyk.nbweather

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import androidx.test.rule.GrantPermissionRule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.FORECAST_OVERVIEW_CONTENT_LAZY_COLUMN_TAG
import de.niklasbednarczyk.nbweather.test.common.utils.createHiltAndroidRule
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposeTest
import org.junit.Rule
import org.junit.Test
import java.util.Locale

@HiltAndroidTest
class MainActivityTest : NBComposeTest {

    companion object {
        private const val SEARCH_OVERVIEW_SEARCH_QUERY_1 = "Syd"
        private const val SEARCH_OVERVIEW_SEARCH_QUERY_2 = "New"

        private const val LOCATION_1_NAME = "Sydney"
        private const val LOCATION_2_NAME = "New York"

        private const val FORECAST_DAILY_DATE_1 = "Mon, Apr 29"
        private const val FORECAST_DAILY_DATE_2 = "Tue, Apr 30"

        private const val FORECAST_OVERVIEW_DAY_TITLE = "Apr 30"
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
        setLocale(Locale.US)

        assertCompose {
            // Start on empty SearchOverview
            assertIsOnSearchOverview()

            // SearchOverview -> ForecastOverview via search 1
            searchOverviewToForecastOverviewViaSearch(
                searchQuery = SEARCH_OVERVIEW_SEARCH_QUERY_1,
                locationName = LOCATION_1_NAME
            )

            forecastOverviewToSearchOverview()

            // SearchOverview -> ForecastOverview via search 2
            searchOverviewToForecastOverviewViaSearch(
                searchQuery = SEARCH_OVERVIEW_SEARCH_QUERY_2,
                locationName = LOCATION_2_NAME
            )

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
                itemTitle = getString(R.string.screen_forecast_overview_hourly_title),
                detailViewTitle = getString(R.string.screen_forecast_common_forecast_name_temperature)
            )

            // ForecastOverview -> ForecastDaily via daily item
            assertForecastItemWithDetailView(
                itemTitle = getString(R.string.screen_forecast_overview_daily_title),
                detailViewTitle = FORECAST_DAILY_DATE_1
            )

            // ForecastOverview -> ForecastDaily via specific day
            assertForecastItemWithDetailView(
                itemTitle = FORECAST_OVERVIEW_DAY_TITLE,
                detailViewTitle = FORECAST_DAILY_DATE_2
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
        searchQuery: String,
        locationName: String
    ) {
        onNodeWithText(R.string.screen_search_overview_bar_placeholder)
            .performTextReplacement(searchQuery)

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
    }

    private fun ComposeContentTestRule.assertForecastItemWithDetailView(
        itemTitle: String,
        detailViewTitle: String
    ) {
        onNodeWithTag(FORECAST_OVERVIEW_CONTENT_LAZY_COLUMN_TAG)
            .performScrollToNode(hasText(itemTitle))

        onNodeWithText(itemTitle)
            .performClickTopCenter()

        waitUntilAtLeastOneExistsWithText(detailViewTitle, substring = true)

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