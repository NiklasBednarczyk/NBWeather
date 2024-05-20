package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewLocationModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlin.test.Test
import kotlin.test.assertNotEquals

class SearchOverviewScreenTest : NBComposableTest() {

    companion object {

        private const val SEARCH_QUERY = "searchQuery"

    }

    private val visitedLocation1 = createTestLocation(
        number = 1,
        country = "FR"
    )
    private val visitedLocation2 = createTestLocation(
        number = 2,
        country = "DE"
    )

    private val searchedLocation1 = createTestLocation(
        number = 3,
        country = "IT"
    )
    private val searchedLocation2 = createTestLocation(
        number = 4,
        country = "ES"
    )

    private val placeholderString =
        NBString.ResString(R.string.screen_search_overview_bar_placeholder)

    @Test
    fun searchBar_searchQueryEmpty_shouldDisplayPlaceholder() {
        // Arrange
        val uiState = createTestUiState(
            searchQuery = ""
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(placeholderString)
                .assertIsDisplayed()
        }
    }

    @Test
    fun searchBar_searchQueryNotEmpty_shouldDisplaySearchQuery() {
        // Arrange
        val uiState = createTestUiState(
            searchQuery = SEARCH_QUERY
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertStringIsNotDisplayed(placeholderString)

            onNodeWithText(SEARCH_QUERY)
                .assertIsDisplayed()
        }
    }

    @Test
    fun searchBar_onSearchQueryChange_shouldBeCalledCorrectly() {
        // Arrange
        val uiState = createTestUiState(
            searchQuery = SEARCH_QUERY
        )
        val newSearchQuery = "newSearchQuery"
        var onSearchQueryChangeCalled: String? = null

        // Act
        setScreenContent(
            uiState = uiState,
            onSearchQueryChange = { searchQuery ->
                onSearchQueryChangeCalled = searchQuery
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(SEARCH_QUERY)
                .performTextReplacement(newSearchQuery)
        }

        assertNotNull(onSearchQueryChangeCalled)
        assertNotEquals(SEARCH_QUERY, onSearchQueryChangeCalled)
        assertEquals(newSearchQuery, onSearchQueryChangeCalled)
    }

    @Test
    fun searchBar_onSearchActiveChange_shouldBeCalledCorrectly() {
        // Arrange
        val uiState = createTestUiState(
            searchActive = false
        )
        var onSearchActiveChangeCalled = false

        // Act
        setScreenContent(
            uiState = uiState,
            onSearchActiveChange = { searchActive ->
                onSearchActiveChangeCalled = searchActive
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(placeholderString)
                .performClick()
        }

        assertTrue(onSearchActiveChangeCalled)
    }

    @Test
    fun searchBar_findLocationInProgressFalse_shouldBeEnabled() {
        // Arrange
        val uiState = createTestUiState(
            findLocationInProgress = false
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(placeholderString)
                .assertIsEnabled()
        }
    }

    @Test
    fun searchBar_findLocationInProgressTrue_shouldBeDisabled() {
        // Arrange
        val uiState = createTestUiState(
            findLocationInProgress = true
        )

        // Act
        setScreenContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(placeholderString)
                .assertIsNotEnabled()
        }
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationNull_searchActiveFalse_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = null,
            searchActive = false,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationNull_searchActiveFalse_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = null,
            searchActive = false,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationNull_searchActiveTrue_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = null,
            searchActive = true,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationNull_searchActiveTrue_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = null,
            searchActive = true,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationFalse_searchActiveFalse_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = false,
            searchActive = false,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationFalse_searchActiveFalse_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = false,
            searchActive = false,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = true,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationFalse_searchActiveTrue_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = false,
            searchActive = true,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationFalse_searchActiveTrue_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = false,
            searchActive = true,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationTrue_searchActiveFalse_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = true,
            searchActive = false,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationTrue_searchActiveFalse_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = true,
            searchActive = false,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationTrue_searchActiveTrue_visitedLocationsEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = true,
            searchActive = true,
            visitedLocationsIsSet = false,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_isStartDestinationTrue_searchActiveTrue_visitedLocationsNotEmpty_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            isStartDestination = true,
            searchActive = true,
            visitedLocationsIsSet = true,
            expectedIconBackDisplayed = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryEmpty_shouldRenderCorrectly() {
        testSearchBarTrailingIcon(
            searchQuery = "",
            expectedIconCancelDisplayed = false,
            expectedIconFindLocationDisplayed = true
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryNotEmpty_shouldRenderCorrectly() {
        testSearchBarTrailingIcon(
            searchQuery = SEARCH_QUERY,
            expectedIconCancelDisplayed = true,
            expectedIconFindLocationDisplayed = false
        )
    }

    @Test
    fun content_searchActiveFalse_findLocationInProgressFalse_shouldRenderCorrectly() {
        testContent(
            searchActive = false,
            findLocationInProgress = false,
            expectedViewVisitedDisplayed = true,
            expectedViewSearchDisplayed = false
        )
    }

    @Test
    fun content_searchActiveFalse_findLocationInProgressTrue_shouldRenderCorrectly() {
        testContent(
            searchActive = false,
            findLocationInProgress = true,
            expectedViewVisitedDisplayed = false,
            expectedViewSearchDisplayed = false
        )
    }

    @Test
    fun content_searchActiveTrue_findLocationInProgressFalse_shouldRenderCorrectly() {
        testContent(
            searchActive = true,
            findLocationInProgress = false,
            expectedViewVisitedDisplayed = false,
            expectedViewSearchDisplayed = true
        )
    }

    @Test
    fun content_searchActiveTrue_findLocationInProgressTrue_shouldRenderCorrectly() {
        testContent(
            searchActive = true,
            findLocationInProgress = true,
            expectedViewVisitedDisplayed = false,
            expectedViewSearchDisplayed = false
        )
    }

    private fun testSearchBarLeadingIcon(
        isStartDestination: Boolean?,
        searchActive: Boolean,
        visitedLocationsIsSet: Boolean,
        expectedIconBackDisplayed: Boolean,
        expectedPopBackStackCalled: Boolean,
        expectedOnSearchActiveChangeCalled: Boolean
    ) {
        // Arrange
        var popBackStackCalled = false
        var onSearchActiveChangeCalled = false

        val uiState = createTestUiState(
            isStartDestination = isStartDestination,
            searchActive = searchActive,
            visitedLocationsIsSet = visitedLocationsIsSet
        )

        // Act
        setScreenContent(
            uiState = uiState,
            popBackStack = {
                popBackStackCalled = true
            },
            onSearchActiveChange = {
                onSearchActiveChangeCalled = true
            }
        )

        // Assert
        assertCompose {
            if (expectedIconBackDisplayed) {
                onNodeWithIcon(NBIcons.Back)
                    .assertIsDisplayed()
                    .performClick()
            } else {
                assertIconIsNotDisplayed(NBIcons.Back)
            }
        }

        assertEquals(
            expectedPopBackStackCalled,
            popBackStackCalled
        )
        assertEquals(
            expectedOnSearchActiveChangeCalled,
            onSearchActiveChangeCalled
        )
    }

    private fun testSearchBarTrailingIcon(
        searchQuery: String,
        expectedIconCancelDisplayed: Boolean,
        expectedIconFindLocationDisplayed: Boolean,
    ) {
        // Arrange
        val iconCancel = NBIcons.Cancel
        val iconFindLocation = NBIcons.FindLocation

        val uiState = createTestUiState(
            searchQuery = searchQuery
        )

        var onSearchQueryChangeCalled = false

        // Act
        setScreenContent(
            uiState = uiState,
            onSearchQueryChange = {
                onSearchQueryChangeCalled = true
            }
        )

        // Assert
        assertCompose {
            if (expectedIconCancelDisplayed) {
                onNodeWithIcon(iconCancel)
                    .assertIsDisplayed()
                    .performClick()

                assertIconIsNotDisplayed(iconFindLocation)
            } else if (expectedIconFindLocationDisplayed) {
                assertIconIsNotDisplayed(iconCancel)

                onNodeWithIcon(iconFindLocation)
                    .assertIsDisplayed()
            } else {
                assertIconIsNotDisplayed(iconCancel)

                assertIconIsNotDisplayed(iconFindLocation)
            }
        }

        assertEquals(
            expectedIconCancelDisplayed,
            onSearchQueryChangeCalled
        )
    }

    private fun testContent(
        searchActive: Boolean,
        findLocationInProgress: Boolean,
        expectedViewVisitedDisplayed: Boolean,
        expectedViewSearchDisplayed: Boolean
    ) {
        // Arrange
        val uiState = createTestUiState(
            searchActive = searchActive,
            findLocationInProgress = findLocationInProgress
        )

        var selectedNavigateToForecastOverview: Pair<Double, Double>? = null
        var selectedDeleteLocation: Pair<Double, Double>? = null
        var updateOrderLocations: List<Pair<Double, Double>>? = null

        // Act
        setScreenContent(
            uiState = uiState,
            navigateToForecastOverview = { latitude, longitude ->
                selectedNavigateToForecastOverview = Pair(latitude, longitude)
            },
            deleteLocation = { latitude, longitude ->
                selectedDeleteLocation = Pair(latitude, longitude)
                null
            },
            updateOrders = { locations ->
                updateOrderLocations = locations
            }
        )

        // Assert
        assertCompose {
            if (expectedViewVisitedDisplayed) {
                onNodeWithText(visitedLocation1.localizedName)
                    .assertIsDisplayed()
                    .performLongClick()
                onNodeWithText(visitedLocation1.stateAndCountry)
                    .assertIsDisplayed()
                assertImageIsNotDisplayed(visitedLocation1.flag!!)

                onNodeWithText(visitedLocation2.localizedName)
                    .assertIsDisplayed()
                onNodeWithText(visitedLocation2.stateAndCountry)
                    .assertIsDisplayed()
                assertImageIsNotDisplayed(visitedLocation2.flag!!)

                onAllNodesWithIcon(NBIcons.Delete)
                    .onLast()
                    .performClick()

                assertNotNull(selectedNavigateToForecastOverview)
                assertEquals(visitedLocation1.toPair(), selectedNavigateToForecastOverview)
                assertNotEquals(visitedLocation2.toPair(), selectedNavigateToForecastOverview)

                assertNotNull(selectedDeleteLocation)
                assertEquals(visitedLocation2.toPair(), selectedDeleteLocation)
                assertNotEquals(visitedLocation1.toPair(), selectedDeleteLocation)

                assertNotNull(updateOrderLocations)
            } else {
                assertStringIsNotDisplayed(visitedLocation1.localizedName)
                assertStringIsNotDisplayed(visitedLocation2.localizedName)
            }

            if (expectedViewSearchDisplayed) {
                onNodeWithText(searchedLocation1.localizedName)
                    .assertIsDisplayed()
                    .performClick()
                onNodeWithText(searchedLocation1.stateAndCountry)
                    .assertIsDisplayed()
                onNodeWithImage(searchedLocation1.flag!!)
                    .assertIsDisplayed()

                onNodeWithText(searchedLocation2.localizedName)
                    .assertIsDisplayed()
                onNodeWithText(searchedLocation1.stateAndCountry)
                    .assertIsDisplayed()
                onNodeWithImage(searchedLocation1.flag!!)
                    .assertIsDisplayed()

                assertNotNull(selectedNavigateToForecastOverview)
                assertEquals(searchedLocation1.toPair(), selectedNavigateToForecastOverview)
                assertNotEquals(searchedLocation2.toPair(), selectedNavigateToForecastOverview)
            } else {
                assertStringIsNotDisplayed(searchedLocation1.localizedName)
                assertStringIsNotDisplayed(searchedLocation2.localizedName)
            }
        }
    }

    private fun createTestUiState(
        isStartDestination: Boolean? = null,
        searchQuery: String = "",
        searchActive: Boolean = false,
        findLocationInProgress: Boolean = false,
        visitedLocationsIsSet: Boolean = true
    ): SearchOverviewUiState {
        val visitedLocations = if (visitedLocationsIsSet) {
            listOf(
                visitedLocation1,
                visitedLocation2
            )
        } else {
            emptyList()
        }

        return SearchOverviewUiState(
            isStartDestination = isStartDestination,
            searchQuery = searchQuery,
            searchActive = searchActive,
            findLocationInProgress = findLocationInProgress,
            visitedLocationsResource = createNBResource(visitedLocations),
            searchedLocationsResource = createNBResource(
                listOf(
                    searchedLocation1,
                    searchedLocation2
                )
            )
        )
    }

    private fun setScreenContent(
        uiState: SearchOverviewUiState = createTestUiState(),
        popBackStack: () -> Unit = {},
        navigateToForecastOverview: (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        onSearchQueryChange: (searchQuery: String) -> Unit = {},
        onSearchActiveChange: (searchActive: Boolean) -> Unit = {},
        setFindLocationInProgress: (findLocationInProgress: Boolean) -> Unit = {},
        updateOrders: (pairs: List<Pair<Double, Double>>) -> Unit = {},
        insertLocation: (location: LocationModelData) -> Unit = {},
        getAndInsertLocation: (latitude: Double, longitude: Double) -> LocationModelData? = { _, _ -> null },
        deleteLocation: (latitude: Double, longitude: Double) -> LocationModelData? = { _, _ -> null }
    ) {
        setContent {
            SearchOverviewScreen(
                uiState = uiState,
                popBackStack = popBackStack,
                navigateToForecastOverview = navigateToForecastOverview,
                onSearchQueryChange = onSearchQueryChange,
                onSearchActiveChange = onSearchActiveChange,
                setFindLocationInProgress = setFindLocationInProgress,
                updateOrders = updateOrders,
                insertLocation = insertLocation,
                getAndInsertLocation = getAndInsertLocation,
                deleteLocation = deleteLocation
            )
        }
    }

    private fun createTestLocation(
        number: Int,
        country: String
    ): SearchOverviewLocationModel {
        val id = number.toString()
        val latLong = number.toDouble()
        return SearchOverviewLocationModel(
            latitude = latLong,
            longitude = latLong,
            localizedName = createNBString("localizedName $id"),
            country = country,
            state = "state $id"
        )
    }

    private fun SearchOverviewLocationModel.toPair() = Pair(latitude, longitude)

}