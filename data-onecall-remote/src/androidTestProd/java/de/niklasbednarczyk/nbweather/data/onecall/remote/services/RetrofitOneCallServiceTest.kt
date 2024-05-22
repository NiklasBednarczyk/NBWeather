package de.niklasbednarczyk.nbweather.data.onecall.remote.services

import dagger.hilt.android.testing.HiltAndroidTest
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.data.localremote.remote.qualifiers.retrofit.DataRetrofit
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.OneCallModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.test.data.localremote.remote.services.NBServiceLatLongTest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

@HiltAndroidTest
class RetrofitOneCallServiceTest : NBServiceLatLongTest {

    companion object {
        private const val LANGUAGE = "en"
        private const val UNITS = "standard"
        private const val EXCLUDE = ""
    }

    @DataRetrofit
    @Inject
    lateinit var retrofit: Retrofit

    private lateinit var subject: RetrofitOneCallService

    override fun createSubject() {
        subject = retrofit.create(RetrofitOneCallService::class.java)
    }

    @Test
    fun getOneCall_shouldGetOneCallBasedOnLanguageAndUnits() = testScope.runTest {
        // Arrange + Act
        val oneCallLanguageDe = subject.getOneCall(
            latitude = latitude,
            longitude = longitude,
            exclude = EXCLUDE,
            units = UNITS,
            language = "de"
        )
        val oneCallLanguageEs = subject.getOneCall(
            latitude = latitude,
            longitude = longitude,
            exclude = EXCLUDE,
            units = UNITS,
            language = "es"
        )
        val oneCallUnitsImperial = subject.getOneCall(
            latitude = latitude,
            longitude = longitude,
            exclude = EXCLUDE,
            units = "imperial",
            language = LANGUAGE
        )
        val oneCallUnitsMetric = subject.getOneCall(
            latitude = latitude,
            longitude = longitude,
            exclude = EXCLUDE,
            units = "metric",
            language = LANGUAGE
        )

        // Assert

        //      OneCall
        assertOneCall(oneCallLanguageDe)
        assertOneCall(oneCallLanguageEs)
        assertOneCall(oneCallUnitsImperial)
        assertOneCall(oneCallUnitsMetric)

        //      Language
        assertListValues(
            oneCallLanguageDe.current?.weather,
            oneCallLanguageEs.current?.weather
        ) { de, es ->
            assertNotEquals(de.description, es.description)
        }

        assertListValues(oneCallLanguageDe.hourly, oneCallLanguageEs.hourly) { hourlyDe, hourlyEs ->
            assertListValues(hourlyDe.weather, hourlyEs.weather) { weatherDe, weatherEs ->
                assertNotEquals(weatherDe.description, weatherEs.description)
            }
        }

        assertListValues(oneCallLanguageDe.daily, oneCallLanguageEs.daily) { dailyDe, dailyEs ->
            assertListValues(dailyDe.weather, dailyEs.weather) { weatherDe, weatherEs ->
                assertNotEquals(weatherDe.description, weatherEs.description)
            }
        }

        //      Units
        assertNotEquals(
            oneCallUnitsImperial.current?.temp,
            oneCallUnitsMetric.current?.temp
        )
        assertNotEquals(
            oneCallUnitsImperial.current?.feelsLike,
            oneCallUnitsMetric.current?.feelsLike
        )
        assertNotEquals(
            oneCallUnitsImperial.current?.dewPoint,
            oneCallUnitsMetric.current?.dewPoint
        )
        assertNotEquals(
            oneCallUnitsImperial.current?.windSpeed,
            oneCallUnitsMetric.current?.windSpeed
        )
        assertUnitsWindGust(
            oneCallUnitsImperial.current?.windGust,
            oneCallUnitsMetric.current?.windGust
        )

        assertListValues(
            oneCallUnitsImperial.hourly,
            oneCallUnitsMetric.hourly
        ) { imperial, metric ->
            assertNotEquals(imperial.temp, metric.temp)
            assertNotEquals(imperial.feelsLike, metric.feelsLike)
            assertNotEquals(imperial.dewPoint, metric.dewPoint)
            assertNotEquals(imperial.windSpeed, metric.windSpeed)
            assertUnitsWindGust(imperial.windGust, metric.windGust)
        }

        assertListValues(oneCallUnitsImperial.daily, oneCallUnitsMetric.daily) { imperial, metric ->
            assertNotEquals(imperial.temp?.morn, metric.temp?.morn)
            assertNotEquals(imperial.temp?.day, metric.temp?.day)
            assertNotEquals(imperial.temp?.eve, metric.temp?.eve)
            assertNotEquals(imperial.temp?.night, metric.temp?.night)
            assertNotEquals(imperial.temp?.min, metric.temp?.min)
            assertNotEquals(imperial.temp?.max, metric.temp?.max)
            assertNotEquals(imperial.feelsLike?.morn, metric.feelsLike?.morn)
            assertNotEquals(imperial.feelsLike?.day, metric.feelsLike?.day)
            assertNotEquals(imperial.feelsLike?.eve, metric.feelsLike?.eve)
            assertNotEquals(imperial.feelsLike?.night, metric.feelsLike?.night)
            assertNotEquals(imperial.dewPoint, metric.dewPoint)
            assertNotEquals(imperial.windSpeed, metric.windSpeed)
            assertUnitsWindGust(imperial.windGust, metric.windGust)
        }
    }

    private fun assertOneCall(
        oneCall: OneCallModelRemote
    ) {
        assertNotNull(oneCall.lat)
        assertNotNull(oneCall.lon)
        assertNotNull(oneCall.timezone)
        assertNotNull(oneCall.timezoneOffset)

        val current = oneCall.current
        assertNotNull(current)
        assertNotNull(current.dt)
        assertNotNull(current.sunrise)
        assertNotNull(current.sunset)
        assertNotNull(current.temp)
        assertNotNull(current.feelsLike)
        assertNotNull(current.pressure)
        assertNotNull(current.humidity)
        assertNotNull(current.dewPoint)
        assertNotNull(current.clouds)
        assertNotNull(current.uvi)
        assertNotNull(current.visibility)
        assertNotNull(current.windSpeed)
        assertNotNull(current.windDeg)
        assertWeather(current.weather)

        assertListIsNotEmpty(oneCall.minutely)
        oneCall.minutely?.forEach { minutely ->
            assertNotNull(minutely.dt)
            assertNotNull(minutely.precipitation)
        }

        assertListIsNotEmpty(oneCall.hourly)
        oneCall.hourly?.forEach { hourly ->
            assertNotNull(hourly.dt)
            assertNotNull(hourly.temp)
            assertNotNull(hourly.feelsLike)
            assertNotNull(hourly.pressure)
            assertNotNull(hourly.humidity)
            assertNotNull(hourly.dewPoint)
            assertNotNull(hourly.uvi)
            assertNotNull(hourly.clouds)
            assertNotNull(hourly.windSpeed)
            assertNotNull(hourly.windDeg)
            assertNotNull(hourly.pop)
            assertWeather(hourly.weather)
        }

        assertListIsNotEmpty(oneCall.daily)
        oneCall.daily?.forEach { daily ->
            assertNotNull(daily.dt)
            assertNotNull(daily.sunrise)
            assertNotNull(daily.sunset)
            assertNotNull(daily.moonrise)
            assertNotNull(daily.moonset)
            assertNotNull(daily.moonPhase)
            assertNotNull(daily.summary)

            val temp = daily.temp
            assertNotNull(temp)
            assertNotNull(temp.morn)
            assertNotNull(temp.day)
            assertNotNull(temp.eve)
            assertNotNull(temp.night)
            assertNotNull(temp.min)
            assertNotNull(temp.max)

            val feelsLike = daily.feelsLike
            assertNotNull(feelsLike)
            assertNotNull(feelsLike.morn)
            assertNotNull(feelsLike.day)
            assertNotNull(feelsLike.eve)
            assertNotNull(feelsLike.night)

            assertNotNull(daily.pressure)
            assertNotNull(daily.humidity)
            assertNotNull(daily.dewPoint)
            assertNotNull(daily.windSpeed)
            assertNotNull(daily.windDeg)
            assertNotNull(daily.clouds)
            assertNotNull(daily.uvi)
            assertNotNull(daily.pop)
            assertWeather(daily.weather)
        }

        oneCall.alerts?.forEach { alert ->
            assertNotNull(alert.senderName)
            assertNotNull(alert.event)
            assertNotNull(alert.start)
            assertNotNull(alert.end)
            assertNotNull(alert.description)
            assertListIsNotEmpty(alert.tags)
        }
    }

    private fun assertWeather(weather: List<WeatherModelRemote>?) {
        assertNotNull(weather)
        assertListIsNotEmpty(weather)
        weather.forEach { w ->
            assertNotNull(w.id)
        }
    }

    private fun assertUnitsWindGust(imperial: Double?, metric: Double?) {
        nbNullSafe(imperial, metric) { i, m ->
            assertNotEquals(i, m)
        }
    }

    override suspend fun createActLatLongBounds(latitude: Double, longitude: Double) {
        subject.getOneCall(
            latitude = latitude,
            longitude = longitude,
            language = LANGUAGE,
            units = UNITS,
            exclude = EXCLUDE
        )
    }

}