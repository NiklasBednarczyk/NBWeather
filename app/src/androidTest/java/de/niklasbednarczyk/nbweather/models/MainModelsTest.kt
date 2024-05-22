package de.niklasbednarczyk.nbweather.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.navigation.drawer.NBNavigationDrawerItem
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MainModelsTest : NBTest {

    @Test
    fun viewData_visitedLocationsEmpty_currentLocationNull_initialCurrentLocationNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = false,
            currentLocationIsSet = false,
            initialCurrentLocationIsSet = false,
            expectedInitialCurrentLocationIsSet = false,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = false
        )
    }

    @Test
    fun viewData_visitedLocationsEmpty_currentLocationNull_initialCurrentLocationNotNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = false,
            currentLocationIsSet = false,
            initialCurrentLocationIsSet = true,
            expectedInitialCurrentLocationIsSet = true,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = false
        )
    }

    @Test
    fun viewData_visitedLocationsEmpty_currentLocationNotNull_initialCurrentLocationNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = false,
            currentLocationIsSet = true,
            initialCurrentLocationIsSet = false,
            expectedInitialCurrentLocationIsSet = false,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = false
        )
    }

    @Test
    fun viewData_visitedLocationsEmpty_currentLocationNotNull_initialCurrentLocationNotNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = false,
            currentLocationIsSet = true,
            initialCurrentLocationIsSet = true,
            expectedInitialCurrentLocationIsSet = true,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = false
        )
    }

    @Test
    fun viewData_visitedLocationsNotEmpty_currentLocationNull_initialCurrentLocationNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = true,
            currentLocationIsSet = false,
            initialCurrentLocationIsSet = false,
            expectedInitialCurrentLocationIsSet = false,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = true
        )
    }

    @Test
    fun viewData_visitedLocationsNotEmpty_currentLocationNull_initialCurrentLocationNotNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = true,
            currentLocationIsSet = false,
            initialCurrentLocationIsSet = true,
            expectedInitialCurrentLocationIsSet = true,
            expectedOneDestinationToBeSelected = false,
            expectedOneDivider = true
        )
    }

    @Test
    fun viewData_visitedLocationsNotEmpty_currentLocationNotNull_initialCurrentLocationNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = true,
            currentLocationIsSet = true,
            initialCurrentLocationIsSet = false,
            expectedInitialCurrentLocationIsSet = false,
            expectedOneDestinationToBeSelected = true,
            expectedOneDivider = true
        )
    }

    @Test
    fun viewData_visitedLocationsNotEmpty_currentLocationNotNull_initialCurrentLocationNotNull_shouldConvertCorrectly() {
        testViewData(
            visitedLocationsIsSet = true,
            currentLocationIsSet = true,
            initialCurrentLocationIsSet = true,
            expectedInitialCurrentLocationIsSet = true,
            expectedOneDestinationToBeSelected = true,
            expectedOneDivider = true
        )
    }

    private fun testViewData(
        visitedLocationsIsSet: Boolean,
        currentLocationIsSet: Boolean,
        initialCurrentLocationIsSet: Boolean,
        expectedInitialCurrentLocationIsSet: Boolean,
        expectedOneDestinationToBeSelected: Boolean,
        expectedOneDivider: Boolean
    ) {
        // Arrange
        val location1 = createTestLocation(1)
        val location2 = createTestLocation(2)

        val visitedLocations = if (visitedLocationsIsSet) {
            listOf(
                location1,
                location2
            )
        } else {
            emptyList()
        }
        val currentLocation = if (currentLocationIsSet) {
            location2
        } else {
            null
        }
        val initialCurrentLocation = if (initialCurrentLocationIsSet) {
            location1
        } else {
            null
        }

        // Act
        val viewData = MainViewData.from(
            visitedLocations = visitedLocations,
            currentLocation = currentLocation,
            initialCurrentLocation = initialCurrentLocation
        )

        // Assert
        if (expectedInitialCurrentLocationIsSet) {
            assertNotNull(viewData.initialCurrentLocation)
            assertEquals(
                initialCurrentLocation?.coordinates,
                viewData.initialCurrentLocation?.coordinates
            )
        } else {
            assertNull(viewData.initialCurrentLocation)
        }

        val destinations =
            viewData.drawerItems.filterIsInstance<NBNavigationDrawerItem.Destination>()
        val selectedCount = destinations.count { destination -> destination.selected }
        if (expectedOneDestinationToBeSelected) {
            assertEquals(1, selectedCount)
        } else {
            assertEquals(0, selectedCount)
        }

        if (expectedOneDivider) {
            assertListDoesContainClass(
                actual = viewData.drawerItems,
                klass = NBNavigationDrawerItem.Divider::class.java,
                size = 1
            )
        } else {
            assertListDoesNotContainClass(
                actual = viewData.drawerItems,
                klass = NBNavigationDrawerItem.Divider::class.java
            )
        }
    }

    private fun createTestLocation(
        index: Long
    ): LocationModelData {
        return LocationModelData(
            coordinates = NBCoordinatesModel(
                latitude = index.toDouble(),
                longitude = index.toDouble()
            ),
            country = null,
            state = null,
            name = null,
            localNames = null,
            lastVisitedTimestampEpochSeconds = null,
            order = null
        )
    }

}