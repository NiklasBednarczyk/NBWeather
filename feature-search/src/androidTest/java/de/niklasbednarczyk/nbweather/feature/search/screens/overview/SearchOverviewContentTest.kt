package de.niklasbednarczyk.nbweather.feature.search.screens.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performClick
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.data.geocoding.models.VisitedLocationsInfoModelData
import de.niklasbednarczyk.nbweather.test.ui.screens.NBContentTest
import org.junit.Test
import kotlin.test.*

class SearchOverviewContentTest : NBContentTest() {

    private val visitedLocation1 = testLocation(1)
    private val visitedLocation2 = testLocation(2)

    private val searchedLocation1 = testLocation(3)
    private val searchedLocation2 = testLocation(4)

    @Test
    fun backHandler_shouldNotBeOverwritten_whenCurrentLocationAndIsInitialSet() {
        // Arrange
        val uiState = createSearchUiState(
            isInitialCurrentLocationSet = true,
            setCurrentLocation = true
        )

        var backHandlerOverwritten = false

        // Act
        setSearchContent(
            uiState = uiState,
            onBackPressedWhenNoCurrentLocation = { backHandlerOverwritten = true }
        )
        pressBack()

        // Assert
        assertFalse(backHandlerOverwritten)
    }

    @Test
    fun backHandler_shouldBeOverwritten_whenCurrentLocationIsNotSet() {
        // Arrange
        val uiState = createSearchUiState(
            isInitialCurrentLocationSet = true,
            setCurrentLocation = false
        )

        var backHandlerOverwritten = false

        // Act
        setSearchContent(
            uiState = uiState,
            onBackPressedWhenNoCurrentLocation = { backHandlerOverwritten = true }
        )
        pressBack()

        // Assert
        assertTrue(backHandlerOverwritten)
    }

    @Test
    fun backHandler_shouldBeOverwritten_whenIsInitialCurrentLocationNotSet() {
        // Arrange
        val uiState = createSearchUiState(
            isInitialCurrentLocationSet = false,
            setCurrentLocation = true
        )

        var backHandlerOverwritten = false

        // Act
        setSearchContent(
            uiState = uiState,
            onBackPressedWhenNoCurrentLocation = { backHandlerOverwritten = true }
        )
        pressBack()

        // Assert
        assertTrue(backHandlerOverwritten)
    }

    @Test
    fun findingLocationInProgress_shouldBeLoading_WhenTrue() {
        // Arrange
        val uiState = createSearchUiState(
            findingLocationInProgress = true,
            searchTerm = ""
        )

        // Act
        setSearchContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            assertStringIsNotDisplayed(visitedLocation1.localizedNameAndCountry)
            assertStringIsNotDisplayed(visitedLocation2.localizedNameAndCountry)
        }
    }

    @Test
    fun findingLocationInProgress_shouldNotBeLoading_WhenFalse() {
        // Arrange
        val uiState = createSearchUiState(
            findingLocationInProgress = false,
            searchTerm = ""
        )

        // Act
        setSearchContent(
            uiState = uiState
        )

        // Assert
        assertCompose {
            onNodeWithText(visitedLocation1.localizedNameAndCountry)
                .assertIsDisplayed()
            onNodeWithText(visitedLocation2.localizedNameAndCountry)
                .assertIsDisplayed()
        }
    }

    @Test
    fun manageView_shouldRenderCorrectly() {
        // Arrange
        val uiState = createSearchUiState(
            searchTerm = ""
        )
        var selectedNavigateToLocation: Pair<Double, Double>? = null
        var selectedRemoveVisitedLocation: Pair<Double, Double>? = null

        // Act
        setSearchContent(
            uiState = uiState,
            navigateToLocation = { latitude, longitude ->
                selectedNavigateToLocation = Pair(latitude, longitude)
            },
            removeVisitedLocation = { latitude, longitude ->
                selectedRemoveVisitedLocation = Pair(latitude, longitude)
            }
        )

        // Assert
        assertCompose {
            onNodeWithText(visitedLocation1.localizedNameAndCountry)
                .assertIsDisplayed()
                .performClick()

            onNodeWithText(visitedLocation2.localizedNameAndCountry)
                .assertIsDisplayed()

            onAllNodesWithIcon(NBIcons.Delete)
                .onLast()
                .performClick()

            assertStringIsNotDisplayed(searchedLocation1.localizedName)
            assertStringIsNotDisplayed(searchedLocation2.localizedName)

        }

        assertNotNull(selectedNavigateToLocation)
        assertEquals(visitedLocation1.toPair(), selectedNavigateToLocation)
        assertNotEquals(visitedLocation2.toPair(), selectedNavigateToLocation)

        assertNotNull(selectedRemoveVisitedLocation)
        assertEquals(visitedLocation2.toPair(), selectedRemoveVisitedLocation)
        assertNotEquals(visitedLocation1.toPair(), selectedRemoveVisitedLocation)
    }

    @Test
    fun searchView_shouldRenderCorrectly() {
        // Arrange
        val uiState = createSearchUiState(
            searchTerm = "Search"
        )
        var selectedNavigateToLocation: Pair<Double, Double>? = null


        // Act
        setSearchContent(
            uiState = uiState,
            navigateToLocation = { latitude, longitude ->
                selectedNavigateToLocation = Pair(latitude, longitude)
            }
        )

        // Assert
        assertCompose {
            assertStringIsNotDisplayed(visitedLocation1.localizedNameAndCountry)
            assertStringIsNotDisplayed(visitedLocation2.localizedNameAndCountry)

            onNodeWithText(searchedLocation1.localizedName)
                .assertIsDisplayed()
                .performClick()

            onNodeWithText(searchedLocation2.localizedName)
                .assertIsDisplayed()

        }

        assertNotNull(selectedNavigateToLocation)
        assertEquals(searchedLocation1.toPair(), selectedNavigateToLocation)
        assertNotEquals(searchedLocation2.toPair(), selectedNavigateToLocation)
    }

    private fun createSearchUiState(
        searchTerm: String = "",
        isInitialCurrentLocationSet: Boolean = true,
        setCurrentLocation: Boolean = true,
        findingLocationInProgress: Boolean = false
    ): SearchOverviewUiState {
        val currentLocation = if (setCurrentLocation) testLocation(-1) else null
        return SearchOverviewUiState(
            searchTerm = searchTerm,
            findingLocationInProgress = findingLocationInProgress,
            visitedLocationsInfoResource = createNBResource(
                VisitedLocationsInfoModelData(
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

    private fun setSearchContent(
        uiState: SearchOverviewUiState,
        onBackPressedWhenNoCurrentLocation: () -> Unit = {},
        navigateToLocation: (latitude: Double, longitude: Double) -> Unit = { _, _ -> },
        removeVisitedLocation: (latitude: Double, longitude: Double) -> Unit = { _, _ -> }
    ) {
        setContent {
            SearchOverviewContent(
                uiState = uiState,
                onBackPressedWhenNoCurrentLocation = onBackPressedWhenNoCurrentLocation,
                navigateToLocation = navigateToLocation,
                removeVisitedLocation = removeVisitedLocation
            )
        }
    }

    private fun testLocation(
        number: Int
    ): LocationModelData {
        val name = number.toString()
        val latLong = number.toDouble()
        return LocationModelData(
            localizedName = createNBString("localizedName $name"),
            localizedNameAndCountry = createNBString("localizedNameAndCountry $name"),
            stateAndCountry = createNBString("stateAndCountry $name"),
            latitude = latLong,
            longitude = latLong
        )
    }

    private fun LocationModelData.toPair() = Pair(latitude, longitude)

}