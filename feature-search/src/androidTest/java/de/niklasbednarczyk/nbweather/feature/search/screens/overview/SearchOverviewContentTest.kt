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
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocalNamesModelData
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.models.SearchOverviewVisitedLocationsInfoModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBComposableTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SearchOverviewContentTest : NBComposableTest() {

    companion object {

        private const val SEARCH_QUERY = "searchQuery"

    }

    private val visitedLocation1 = createTestLocation(1)
    private val visitedLocation2 = createTestLocation(2)

    private val searchedLocation1 = createTestLocation(3)
    private val searchedLocation2 = createTestLocation(4)

    private val placeholderString =
        NBString.ResString(R.string.screen_search_overview_bar_placeholder)

    @Test
    fun backHandler_isInitialCurrentLocationSetFalse_isCurrentLocationSetFalse_findLocationInProgressFalse_shouldBeDisabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = false,
            uiStateIsCurrentLocationSet = false,
            uiStateFindLocationInProgress = false,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = false
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetFalse_isCurrentLocationSetFalse_findLocationInProgressTrue_shouldBeEnabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = false,
            uiStateIsCurrentLocationSet = false,
            uiStateFindLocationInProgress = true,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = true
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetFalse_isCurrentLocationSetTrue_findLocationInProgressFalse_shouldBeDisabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = false,
            uiStateIsCurrentLocationSet = true,
            uiStateFindLocationInProgress = false,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = false
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetFalse_isCurrentLocationSetTrue_findLocationInProgressTrue_shouldBeEnabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = false,
            uiStateIsCurrentLocationSet = true,
            uiStateFindLocationInProgress = true,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = true
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetTrue_isCurrentLocationSetFalse_findLocationInProgressFalse_shouldBeEnabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = true,
            uiStateIsCurrentLocationSet = false,
            uiStateFindLocationInProgress = false,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = true,
            expectedOnBackPressedWhenFindLocationInProgressCalled = false
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetTrue_isCurrentLocationSetFalse_findLocationInProgressTrue_shouldBeEnabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = true,
            uiStateIsCurrentLocationSet = false,
            uiStateFindLocationInProgress = true,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = true
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetTrue_isCurrentLocationSetTrue_findLocationInProgressFalse_shouldBeDisabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = true,
            uiStateIsCurrentLocationSet = true,
            uiStateFindLocationInProgress = false,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = false
        )
    }

    @Test
    fun backHandler_isInitialCurrentLocationSetTrue_isCurrentLocationSetTrue_findLocationInProgressTrue_shouldBeEnabled() {
        testBackHandler(
            uiStateIsInitialCurrentLocationSet = true,
            uiStateIsCurrentLocationSet = true,
            uiStateFindLocationInProgress = true,
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false,
            expectedOnBackPressedWhenFindLocationInProgressCalled = true
        )
    }

    @Test
    fun searchBar_searchQueryEmpty_shouldDisplayPlaceholder() {
        // Arrange
        val uiState = createTestUiState(
            searchQuery = ""
        )

        // Act
        setSearchOverviewContent(
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
        setSearchOverviewContent(
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
        setSearchOverviewContent(
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
        var onSearchActiveChangeCalled: Boolean? = null

        // Act
        setSearchOverviewContent(
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

        assertNotNull(onSearchActiveChangeCalled)
        assertEquals(true, onSearchActiveChangeCalled)
    }

    @Test
    fun searchBar_findLocationInProgressFalse_shouldBeEnabled() {
        // Arrange
        val uiState = createTestUiState(
            findLocationInProgress = false
        )

        // Act
        setSearchOverviewContent(
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
        setSearchOverviewContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(placeholderString)
                .assertIsNotEnabled()
        }
    }

    @Test
    fun searchBar_leadingIcon_searchActiveFalse_isCurrentLocationSetFalse_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            icon = NBIcons.Search,
            uiStateSearchActive = false,
            uiStateIsCurrentLocationSet = false,
            shouldClickIcon = false,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_searchActiveFalse_isCurrentLocationSetTrue_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            icon = NBIcons.Back,
            uiStateSearchActive = false,
            uiStateIsCurrentLocationSet = true,
            shouldClickIcon = true,
            expectedPopBackStackCalled = true,
            expectedOnSearchActiveChangeCalled = false
        )
    }

    @Test
    fun searchBar_leadingIcon_searchActiveTrue_isCurrentLocationSetFalse_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            icon = NBIcons.Back,
            uiStateSearchActive = true,
            uiStateIsCurrentLocationSet = false,
            shouldClickIcon = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_leadingIcon_searchActiveTrue_isCurrentLocationSetTrue_shouldRenderCorrectly() {
        testSearchBarLeadingIcon(
            icon = NBIcons.Back,
            uiStateSearchActive = true,
            uiStateIsCurrentLocationSet = true,
            shouldClickIcon = true,
            expectedPopBackStackCalled = false,
            expectedOnSearchActiveChangeCalled = true
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryIsNotEmpty_isFindLocationAvailableFalse_shouldRenderCorrectly() {
        testSearchBarTrailingIcon(
            uiStateSearchQueryIsEmpty = false,
            isFindLocationAvailable = false,
            expectedIconCancelDisplayed = true,
            expectedIconFindLocationDisplayed = false
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryIsNotEmpty_isFindLocationAvailableTrue_shouldRenderCorrectly() {
        testSearchBarTrailingIcon(
            uiStateSearchQueryIsEmpty = false,
            isFindLocationAvailable = true,
            expectedIconCancelDisplayed = true,
            expectedIconFindLocationDisplayed = false
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryIsEmpty_isFindLocationAvailableFalse_shouldNotBeDisplayed() {
        testSearchBarTrailingIcon(
            uiStateSearchQueryIsEmpty = true,
            isFindLocationAvailable = false,
            expectedIconCancelDisplayed = false,
            expectedIconFindLocationDisplayed = false
        )
    }

    @Test
    fun searchBar_trailingIcon_searchQueryIsEmpty_isFindLocationAvailableTrue_shouldRenderCorrectly() {
        testSearchBarTrailingIcon(
            uiStateSearchQueryIsEmpty = true,
            isFindLocationAvailable = true,
            expectedIconCancelDisplayed = false,
            expectedIconFindLocationDisplayed = true
        )
    }

    @Test
    fun view_searchActiveFalse_findLocationInProgressFalse_shouldRenderCorrectly() {
        testView(
            uiStateSearchActive = false,
            uiStateFindLocationInProgress = false,
            expectedViewManageDisplayed = true,
            expectedViewSearchDisplayed = false
        )
    }

    @Test
    fun view_searchActiveFalse_findLocationInProgressTrue_shouldRenderCorrectly() {
        testView(
            uiStateSearchActive = false,
            uiStateFindLocationInProgress = true,
            expectedViewManageDisplayed = false,
            expectedViewSearchDisplayed = false
        )
    }

    @Test
    fun view_searchActiveTrue_findLocationInProgressFalse_shouldRenderCorrectly() {
        testView(
            uiStateSearchActive = true,
            uiStateFindLocationInProgress = false,
            expectedViewManageDisplayed = false,
            expectedViewSearchDisplayed = true
        )
    }

    @Test
    fun view_searchActiveTrue_findLocationInProgressTrue_shouldRenderCorrectly() {
        testView(
            uiStateSearchActive = true,
            uiStateFindLocationInProgress = true,
            expectedViewManageDisplayed = false,
            expectedViewSearchDisplayed = false
        )
    }

    private fun testBackHandler(
        uiStateIsInitialCurrentLocationSet: Boolean,
        uiStateIsCurrentLocationSet: Boolean,
        uiStateFindLocationInProgress: Boolean,
        expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled: Boolean,
        expectedOnBackPressedWhenFindLocationInProgressCalled: Boolean
    ) {
        // Arrange
        val uiState = createTestUiState(
            isInitialCurrentLocationSet = uiStateIsInitialCurrentLocationSet,
            isCurrentLocationSet = uiStateIsCurrentLocationSet,
            findLocationInProgress = uiStateFindLocationInProgress
        )

        var onBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = false
        var onBackPressedWhenFindLocationInProgressCalled = false

        // Act
        setSearchOverviewContent(
            uiState = uiState,
            onBackPressedWhenNoCurrentLocationAndNotStartDestination = {
                onBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled = true
            },
            onBackPressedWhenFindLocationInProgress = {
                onBackPressedWhenFindLocationInProgressCalled = true
            }
        )
        pressBack()

        // Assert
        assertEquals(
            expectedOnBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled,
            onBackPressedWhenNoCurrentLocationAndNotStartDestinationCalled
        )
        assertEquals(
            expectedOnBackPressedWhenFindLocationInProgressCalled,
            onBackPressedWhenFindLocationInProgressCalled
        )
    }

    private fun testSearchBarLeadingIcon(
        icon: NBIconModel,
        uiStateSearchActive: Boolean,
        uiStateIsCurrentLocationSet: Boolean,
        shouldClickIcon: Boolean,
        expectedPopBackStackCalled: Boolean,
        expectedOnSearchActiveChangeCalled: Boolean
    ) {
        // Arrange
        val uiState = createTestUiState(
            searchActive = uiStateSearchActive,
            isCurrentLocationSet = uiStateIsCurrentLocationSet
        )

        var popBackStackCalled = false
        var onSearchActiveChangeCalled = false

        // Act
        setSearchOverviewContent(
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
            if (shouldClickIcon) {
                onNodeWithIcon(icon)
                    .assertIsDisplayed()
                    .performClick()
            } else {
                onNodeWithIcon(icon)
                    .assertIsDisplayed()
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
        uiStateSearchQueryIsEmpty: Boolean,
        isFindLocationAvailable: Boolean,
        expectedIconCancelDisplayed: Boolean,
        expectedIconFindLocationDisplayed: Boolean,
    ) {
        // Arrange
        val iconCancel = NBIcons.Cancel
        val iconFindLocation = NBIcons.FindLocation

        val uiState = createTestUiState(
            searchQuery = if (uiStateSearchQueryIsEmpty) "" else SEARCH_QUERY,
        )

        var onSearchQueryChangeCalled = false
        var onFindLocationClickedCalled = false

        // Act
        setSearchOverviewContent(
            uiState = uiState,
            isFindLocationAvailable = isFindLocationAvailable,
            onSearchQueryChange = {
                onSearchQueryChangeCalled = true
            },
            onFindLocationClicked = {
                onFindLocationClickedCalled = true
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
                    .performClick()
            } else {
                assertIconIsNotDisplayed(iconCancel)

                assertIconIsNotDisplayed(iconFindLocation)
            }
        }

        assertEquals(
            expectedIconCancelDisplayed,
            onSearchQueryChangeCalled
        )
        assertEquals(
            expectedIconFindLocationDisplayed,
            onFindLocationClickedCalled
        )
    }

    private fun testView(
        uiStateSearchActive: Boolean,
        uiStateFindLocationInProgress: Boolean,
        expectedViewManageDisplayed: Boolean,
        expectedViewSearchDisplayed: Boolean
    ) {
        // Arrange
        val uiState = createTestUiState(
            searchActive = uiStateSearchActive,
            findLocationInProgress = uiStateFindLocationInProgress
        )

        var selectedNavigateToForecast: Pair<Double, Double>? = null
        var selectedDeleteLocation: Pair<Double, Double>? = null
        var updateOrderLocations: List<LocationModelData>? = null

        // Act
        setSearchOverviewContent(
            uiState = uiState,
            navigateToForecast = { latitude, longitude ->
                selectedNavigateToForecast = Pair(latitude, longitude)
            },
            deleteLocation = { latitude, longitude ->
                selectedDeleteLocation = Pair(latitude, longitude)
            },
            updateOrders = { locations ->
                updateOrderLocations = locations
            }
        )

        // Assert
        assertCompose {
            if (expectedViewManageDisplayed) {
                onNodeWithText(visitedLocation1.localizedNameAndCountry)
                    .assertIsDisplayed()
                    .performLongClick()

                onNodeWithText(visitedLocation2.localizedNameAndCountry)
                    .assertIsDisplayed()

                onAllNodesWithIcon(NBIcons.Delete)
                    .onLast()
                    .performClick()

                assertNotNull(selectedNavigateToForecast)
                assertEquals(visitedLocation1.toPair(), selectedNavigateToForecast)
                assertNotEquals(visitedLocation2.toPair(), selectedNavigateToForecast)

                assertNotNull(selectedDeleteLocation)
                assertEquals(visitedLocation2.toPair(), selectedDeleteLocation)
                assertNotEquals(visitedLocation1.toPair(), selectedDeleteLocation)

                assertNotNull(updateOrderLocations)
            } else {
                assertStringIsNotDisplayed(visitedLocation1.localizedNameAndCountry)
                assertStringIsNotDisplayed(visitedLocation2.localizedNameAndCountry)
            }

            if (expectedViewSearchDisplayed) {
                onNodeWithText(searchedLocation1.localizedName)
                    .assertIsDisplayed()
                    .performClick()

                onNodeWithText(searchedLocation2.localizedName)
                    .assertIsDisplayed()

                assertNotNull(selectedNavigateToForecast)
                assertEquals(searchedLocation1.toPair(), selectedNavigateToForecast)
                assertNotEquals(searchedLocation2.toPair(), selectedNavigateToForecast)
            } else {
                assertStringIsNotDisplayed(searchedLocation1.localizedName)
                assertStringIsNotDisplayed(searchedLocation2.localizedName)
            }
        }
    }

    private fun createTestUiState(
        searchQuery: String = "",
        searchActive: Boolean = false,
        findLocationInProgress: Boolean = false,
        isInitialCurrentLocationSet: Boolean = true,
        isCurrentLocationSet: Boolean = true
    ): SearchOverviewUiState {
        val currentLocation = if (isCurrentLocationSet) createTestLocation(-1) else null
        return SearchOverviewUiState(
            searchQuery = searchQuery,
            searchActive = searchActive,
            findLocationInProgress = findLocationInProgress,
            visitedLocationsInfoResource = createNBResource(
                SearchOverviewVisitedLocationsInfoModel(
                    currentLocation = currentLocation,
                    isInitialCurrentLocationSet = isInitialCurrentLocationSet,
                    visitedLocations = listOf(
                        visitedLocation1,
                        visitedLocation2
                    )
                )
            ),
            searchedLocationsResource = createNBResource(
                listOf(
                    searchedLocation1,
                    searchedLocation2
                )
            )
        )
    }

    private fun setSearchOverviewContent(
        uiState: SearchOverviewUiState,
        isFindLocationAvailable: Boolean = true,
        popBackStack: () -> Unit = {},
        onBackPressedWhenNoCurrentLocationAndNotStartDestination: () -> Unit = {},
        onBackPressedWhenFindLocationInProgress: () -> Unit = {},
        onSearchQueryChange: (String) -> Unit = {},
        onSearchActiveChange: (Boolean) -> Unit = {},
        onFindLocationClicked: () -> Unit = {},
        navigateToForecast: (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        deleteLocation: (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        updateOrders: (locations: List<LocationModelData>) -> Unit = {}
    ) {
        setContent {
            SearchOverviewContent(
                uiState = uiState,
                isFindLocationAvailable = isFindLocationAvailable,
                popBackStack = popBackStack,
                onBackPressedWhenNoCurrentLocationAndNotStartDestination = onBackPressedWhenNoCurrentLocationAndNotStartDestination,
                onBackPressedWhenFindLocationInProgress = onBackPressedWhenFindLocationInProgress,
                onSearchQueryChange = onSearchQueryChange,
                onSearchActiveChange = onSearchActiveChange,
                onFindLocationClicked = onFindLocationClicked,
                navigateToForecast = navigateToForecast,
                deleteLocation = deleteLocation,
                updateOrders = updateOrders
            )
        }
    }

    private fun createTestLocation(
        number: Int
    ): LocationModelData {
        val id = number.toString()
        val latLong = number.toDouble()
        return LocationModelData(
            localNames = LocalNamesModelData(
                de = "de $id",
                en = "en $id"
            ),
            name = "name $id",
            state = "state $id",
            country = "country $id",
            latitude = latLong,
            longitude = latLong
        )
    }

    private fun LocationModelData.toPair() = Pair(latitude, longitude)

}