package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.data.onecall.local.database.DatabaseOneCall
import de.niklasbednarczyk.nbweather.data.onecall.local.models.*
import de.niklasbednarczyk.nbweather.test.data.localremote.local.daos.NBDaoTest
import kotlinx.coroutines.flow.Flow
import org.junit.Test
import kotlin.test.assertEquals

abstract class NBRoomOneCallDaoTest<Dao : Any, Entity> : NBDaoTest<DatabaseOneCall, Dao>() {

    override val databaseClass: Class<DatabaseOneCall> = DatabaseOneCall::class.java

    private fun <T> createList(
        transform: (id: Long) -> T
    ) = (10L..13L).map(transform)

    protected abstract fun createArrange(metadataId: Long): Entity

    protected abstract fun insert(entity: Entity)

    protected abstract fun delete(metadataId: Long)

    protected abstract fun createAct(metadataId: Long): Flow<Entity?>

    protected abstract fun assertAreEqual(arrange: Entity, act: Entity)

    @Test
    fun getItem_shouldFindEntityByMetadataId() {
        testGetItemQuery(
            keys = Triple(1L, 2L, 3L),
            createArrange = ::createArrange,
            insert = ::insert,
            createAct = ::createAct,
            assertAreEqual = ::assertAreEqual
        )
    }

    @Test
    fun deleteItem_shouldDeleteCorrectEntity() {
        testDeleteItemQuery(
            keys = Pair(1L, 2L),
            createArrange = ::createArrange,
            insert = ::insert,
            delete = ::delete,
            createAct = ::createAct,
            assertAreEqual = ::assertAreEqual
        )
    }

    protected fun createCurrentWeatherModel(
        metadataId: Long
    ) = CurrentWeatherEntityLocal(
        id = metadataId,
        metadataId = metadataId,
        dt = null,
        sunrise = null,
        sunset = null,
        temp = null,
        feelsLike = null,
        pressure = null,
        humidity = null,
        dewPoint = null,
        clouds = null,
        uvi = null,
        visibility = null,
        windSpeed = null,
        windGust = null,
        windDeg = null,
        rain1h = null,
        snow1h = null,
        weather = null
    )

    protected fun createDailyForecasts(
        metadataId: Long
    ) = createList { id ->
        DailyForecastEntityLocal(
            id = id,
            metadataId = metadataId,
            dt = null,
            sunrise = null,
            sunset = null,
            moonrise = null,
            moonset = null,
            moonPhase = null,
            temp = null,
            feelsLike = null,
            pressure = null,
            humidity = null,
            dewPoint = null,
            windSpeed = null,
            windGust = null,
            windDeg = null,
            clouds = null,
            uvi = null,
            pop = null,
            rain = null,
            snow = null,
            weather = null
        )
    }

    protected fun createHourlyForecasts(
        metadataId: Long
    ) = createList { id ->
        HourlyForecastEntityLocal(
            id = id,
            metadataId = metadataId,
            dt = null,
            temp = null,
            feelsLike = null,
            pressure = null,
            humidity = null,
            dewPoint = null,
            uvi = null,
            clouds = null,
            visibility = null,
            windSpeed = null,
            windGust = null,
            windDeg = null,
            pop = null,
            rain1h = null,
            snow1h = null,
            weather = null
        )
    }

    protected fun createMinutelyForecast(
        metadataId: Long
    ) = createList { id ->
        MinutelyForecastEntityLocal(
            id = id,
            metadataId = metadataId,
            dt = null,
            precipitation = null
        )
    }

    protected fun createNationalWeatherAlert(
        metadataId: Long
    ) = createList {
        NationalWeatherAlertEntityLocal(
            id = metadataId,
            metadataId = metadataId,
            senderName = null,
            event = null,
            start = null,
            end = null,
            description = null,
            tags = null
        )
    }

    protected fun assertAreEqualCurrentWeather(
        arrange: CurrentWeatherEntityLocal,
        act: CurrentWeatherEntityLocal
    ) {
        assertEquals(arrange.metadataId, act.metadataId)
        assertEquals(arrange.id, act.id)
    }

    protected fun assertAreEqualDailyForecasts(
        arrange: List<DailyForecastEntityLocal>,
        act: List<DailyForecastEntityLocal>
    ) {
        assertListsContainSameItems(arrange, act)
    }

    protected fun assertAreEqualHourlyForecasts(
        arrange: List<HourlyForecastEntityLocal>,
        act: List<HourlyForecastEntityLocal>
    ) {
        assertListsContainSameItems(arrange, act)
    }

    protected fun assertAreEqualMinutelyForecasts(
        arrange: List<MinutelyForecastEntityLocal>,
        act: List<MinutelyForecastEntityLocal>
    ) {
        assertListsContainSameItems(arrange, act)
    }

    protected fun assertAreEqualNationalWeatherAlerts(
        arrange: List<NationalWeatherAlertEntityLocal>,
        act: List<NationalWeatherAlertEntityLocal>
    ) {
        assertListsContainSameItems(arrange, act)
    }

}