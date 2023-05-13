package de.niklasbednarczyk.nbweather

import androidx.annotation.StringRes
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.rule.GrantPermissionRule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.test.common.utils.createHiltAndroidRule
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposeTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest : NBComposeTest {

    companion object {
        private const val SEARCH_OVERVIEW_SEARCH_TERM = "Washington"

        private const val LOCATION_1_NAME = "Washington 1"
        private const val LOCATION_2_NAME = "Washington 2"

        private const val LOCATION_DAILY_ITEM_NAME = "9"
        private const val LOCATION_HOURLY_ITEM_NAME = "9:00"
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

            // SearchOverview -> LocationOverview via search 1
            searchOverviewToLocationOverviewViaSearch(LOCATION_1_NAME)

            locationOverviewToSearchOverview()

            // SearchOverview -> LocationOverview via search 2
            searchOverviewToLocationOverviewViaSearch(LOCATION_2_NAME)

            locationOverviewToSearchOverview()

            // SearchOverview -> LocationOverview via visited location
            onNodeWithLocationName(LOCATION_1_NAME)
                .performClick()
            assertIsOnLocationOverview(LOCATION_1_NAME)

            // LocationOverview Tab Hourly -> LocationHourly
            switchTab(NBIcons.Hourly)
            onAllNodesWithText(LOCATION_HOURLY_ITEM_NAME, substring = true)
                .onFirst()
                .performClick()
            assertIsLocationCard()

            pressBack()

            // LocationOverview Tab Daily -> LocationDaily
            switchTab(NBIcons.Daily)
            onAllNodesWithText(LOCATION_DAILY_ITEM_NAME, substring = true)
                .onFirst()
                .performClick()
            assertIsLocationCard()

            pressBack()

            // LocationOverview Tab Today -> LocationAlerts
            switchTab(NBIcons.Today)
            onNodeWithIcon(NBIcons.Warning)
                .performClick()
            onNodeWithText(R.string.screen_location_alerts_title)
                .assertIsDisplayed()

            pressBack()

            // Location 1 -> Location 2 via drawer
            clickDrawerItem(LOCATION_2_NAME)
            assertIsOnLocationOverview(LOCATION_2_NAME)

            // LocationOverview -> SettingsOverview
            clickDrawerItem(getString(R.string.screen_settings_overview_title))
            onNodeWithText(R.string.screen_settings_overview_header_appearance)
                .assertIsDisplayed()

            // SettingsOverview -> SettingsTheme
            testSettingsOverviewNavigation(
                titleResId = R.string.screen_settings_theme_title,
                valueOldResId = R.string.screen_settings_theme_value_light,
                valueNewResId = R.string.screen_settings_theme_value_system_default
            )

            // SettingsOverview -> SettingsColorScheme
            testSettingsOverviewNavigation(
                titleResId = R.string.screen_settings_color_scheme_title,
                valueOldResId = R.string.screen_settings_color_scheme_value_red,
                valueNewResId = R.string.screen_settings_color_scheme_value_cyan
            )

            // SettingsOverview -> SettingsLanguage
            testSettingsOverviewNavigation(
                titleResId = R.string.screen_settings_language_title,
                valueOldResId = R.string.screen_settings_language_value_afrikaans,
                valueNewResId = R.string.screen_settings_language_value_german
            )

            // SettingsOverview -> SettingsUnits
            testSettingsOverviewNavigation(
                titleResId = R.string.screen_settings_units_title,
                valueOldResId = R.string.screen_settings_units_value_standard,
                valueNewResId = R.string.screen_settings_units_value_imperial
            )

            // SettingsOverview -> SettingsTimeFormat
            testSettingsOverviewNavigation(
                titleResId = R.string.screen_settings_time_format_title,
                valueOldResId = R.string.screen_settings_time_format_value_hour_12,
                valueNewResId = R.string.screen_settings_time_format_value_hour_24
            )

            pressBack()

            // LocationOverview -> AboutOverview
            clickDrawerItem(getString(R.string.screen_about_overview_title))
            onNodeWithText(R.string.screen_about_overview_text_open_weather)
                .assertIsDisplayed()
        }
    }

    private fun ComposeContentTestRule.searchOverviewToLocationOverviewViaSearch(
        locationName: String
    ) {
        onNodeWithText(R.string.top_app_bar_search_placeholder)
            .performTextInput(SEARCH_OVERVIEW_SEARCH_TERM)

        waitUntilAtLeastOneExists(hasText(locationName))

        onNodeWithText(locationName)
            .performClick()

        assertIsOnLocationOverview(locationName)
    }

    private fun ComposeContentTestRule.locationOverviewToSearchOverview() {
        onNodeWithIcon(NBIcons.Search)
            .performClick()
    }

    private fun ComposeContentTestRule.switchTab(icon: NBIconModel) {
        onNodeWithIcon(icon, useUnmergedTree = true)
            .performClick()
    }

    private fun ComposeContentTestRule.testSettingsOverviewNavigation(
        @StringRes titleResId: Int,
        @StringRes valueOldResId: Int,
        @StringRes valueNewResId: Int,
    ) {
        onNodeWithText(valueOldResId)
            .assertIsDisplayed()
        onNodeWithText(titleResId)
            .performClick()
        onNodeWithText(valueNewResId)
            .performClick()
        onNodeWithText(valueNewResId)
            .assertIsDisplayed()

    }

    private fun ComposeContentTestRule.clickDrawerItem(text: String) {
        onNodeWithIcon(NBIcons.Drawer)
            .performClick()
        waitUntilAtLeastOneExists(hasText(getString(de.niklasbednarczyk.nbweather.R.string.app_name)))
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
        onNodeWithText(R.string.top_app_bar_search_placeholder)
            .assertIsDisplayed()
    }

    private fun ComposeContentTestRule.assertIsOnLocationOverview(
        locationName: String
    ) {
        onNodeWithLocationName(locationName)
            .assertIsDisplayed()
        assertIsLocationCard()
    }

    private fun ComposeContentTestRule.assertIsLocationCard() {
        onNodeWithText(R.string.screen_location_card_overview_title)
            .assertIsDisplayed()
    }

}