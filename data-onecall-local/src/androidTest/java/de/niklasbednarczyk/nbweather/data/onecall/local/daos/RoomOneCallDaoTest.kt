package de.niklasbednarczyk.nbweather.data.onecall.local.daos

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallMetadataEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.local.models.OneCallModelLocal
import kotlinx.coroutines.flow.Flow
import kotlin.test.assertEquals

class RoomOneCallDaoTest : NBRoomOneCallDaoTest<RoomOneCallDao, OneCallModelLocal>() {

    private lateinit var currentWeatherDao: RoomCurrentWeatherDao
    private lateinit var dailyForecastDao: RoomDailyForecastDao
    private lateinit var hourlyForecastDao: RoomHourlyForecastDao
    private lateinit var minutelyForecastDao: RoomMinutelyForecastDao
    private lateinit var nationalWeatherAlertDao: RoomNationalWeatherAlertDao

    override fun getDao(): RoomOneCallDao = db.oneCallDao()

    override fun initiateOtherDaos() {
        super.initiateOtherDaos()

        currentWeatherDao = db.currentWeatherDao()
        dailyForecastDao = db.dailyForecastDao()
        hourlyForecastDao = db.hourlyForecastDao()
        minutelyForecastDao = db.minutelyForecastDao()
        nationalWeatherAlertDao = db.nationalWeatherAlertDao()
    }

    override fun createArrange(metadataId: Long): OneCallModelLocal {
        val metadata = OneCallMetadataEntityLocal(
            id = metadataId,
            latitude = getLatLongFromMetadataId(metadataId),
            longitude = getLatLongFromMetadataId(metadataId),
            timezone = null,
            timezoneOffset = null
        )
        return OneCallModelLocal(
            metadata = metadata,
            currentWeather = createCurrentWeatherModel(metadataId),
            minutelyForecasts = createMinutelyForecast(metadataId),
            hourlyForecasts = createHourlyForecasts(metadataId),
            dailyForecasts = createDailyForecasts(metadataId),
            nationalWeatherAlerts = createNationalWeatherAlert(metadataId)
        )
    }

    override fun insert(entity: OneCallModelLocal) {
        subject.insertOneCall(entity.metadata)

        nbNullSafe(entity.currentWeather) { currentWeather ->
            currentWeatherDao.insertCurrentWeather(currentWeather)
        }

        nbNullSafe(entity.minutelyForecasts) { minutelyForecasts ->
            minutelyForecastDao.insertMinutelyForecasts(minutelyForecasts)
        }

        nbNullSafe(entity.hourlyForecasts) { hourlyForecasts ->
            hourlyForecastDao.insertHourlyForecasts(hourlyForecasts)
        }

        nbNullSafe(entity.dailyForecasts) { dailyForecasts ->
            dailyForecastDao.insertDailyForecasts(dailyForecasts)
        }

        nbNullSafe(entity.nationalWeatherAlerts) { nationalWeatherAlerts ->
            nationalWeatherAlertDao.insertNationalWeatherAlerts(nationalWeatherAlerts)
        }
    }

    override fun delete(metadataId: Long) =
        subject.deleteOneCall(
            getLatLongFromMetadataId(metadataId),
            getLatLongFromMetadataId(metadataId)
        )

    override fun createAct(metadataId: Long): Flow<OneCallModelLocal?> =
        subject.getOneCall(
            getLatLongFromMetadataId(metadataId),
            getLatLongFromMetadataId(metadataId)
        )

    override fun assertAreEqual(
        arrange: OneCallModelLocal,
        act: OneCallModelLocal
    ) {
        assertEquals(arrange.metadata, act.metadata)

        nbNullSafe(arrange.currentWeather, act.currentWeather) { arrangeCw, actCw ->
            assertAreEqualCurrentWeather(arrangeCw, actCw)
        }

        nbNullSafe(arrange.minutelyForecasts, act.minutelyForecasts) { arrangeMf, actMf ->
            assertAreEqualMinutelyForecasts(arrangeMf, actMf)
        }

        nbNullSafe(arrange.hourlyForecasts, act.hourlyForecasts) { arrangeHf, actHf ->
            assertAreEqualHourlyForecasts(arrangeHf, actHf)
        }

        nbNullSafe(arrange.dailyForecasts, act.dailyForecasts) { arrangeDf, actDf ->
            assertAreEqualDailyForecasts(arrangeDf, actDf)
        }

        nbNullSafe(arrange.nationalWeatherAlerts, act.nationalWeatherAlerts) { arrangeNwa, actNwa ->
            assertAreEqualNationalWeatherAlerts(arrangeNwa, actNwa)
        }
    }

    private fun getLatLongFromMetadataId(metadataId: Long) = metadataId + 20.0

}