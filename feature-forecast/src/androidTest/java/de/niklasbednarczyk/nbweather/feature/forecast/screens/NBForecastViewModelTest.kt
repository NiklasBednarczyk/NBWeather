package de.niklasbednarczyk.nbweather.feature.forecast.screens

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest

interface NBForecastViewModelTest : NBViewModelTest {

    val forecastTime: Long
        get() = 1

    val coordinates: NBCoordinatesModel
        get() = NBCoordinatesModel(
            latitude = -33.8698439,
            longitude = 151.2082848
        )

}