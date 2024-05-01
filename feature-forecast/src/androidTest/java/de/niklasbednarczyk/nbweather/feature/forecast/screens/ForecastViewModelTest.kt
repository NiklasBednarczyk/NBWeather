package de.niklasbednarczyk.nbweather.feature.forecast.screens

import de.niklasbednarczyk.nbweather.test.ui.screens.NBViewModelTest

interface ForecastViewModelTest : NBViewModelTest {

    val forecastTime: Long
        get() = 1

    val latitude: Double
        get() = -33.8698439

    val longitude: Double
        get() = 151.2082848

}