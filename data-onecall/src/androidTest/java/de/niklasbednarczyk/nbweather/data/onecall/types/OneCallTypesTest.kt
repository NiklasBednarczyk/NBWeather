package de.niklasbednarczyk.nbweather.data.onecall.types

import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.types.wind.WindDegreesType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class OneCallTypesTest : NBTest {

    @Test
    fun moonPhaseType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null

        val outsideLowerLimitValue = -0.01

        val newMoonMinValue = 0.00
        val firstQuarterMoonValue = 0.25
        val fullMoonValue = 0.50
        val lastQuarterMoonValue = 0.75
        val newMoonMaxValue = 1.00

        val waxingCrescentValue = average(newMoonMinValue, firstQuarterMoonValue)
        val waxingGibbousValue = average(firstQuarterMoonValue, fullMoonValue)
        val waningGibbousValue = average(fullMoonValue, lastQuarterMoonValue)
        val waningCrescentValue = average(lastQuarterMoonValue, newMoonMaxValue)

        val outsideUpperLimitValue = 1.01

        // Act
        val nullType = MoonPhaseType.from(nullValue)

        val outsideLowerLimitType = MoonPhaseType.from(outsideLowerLimitValue)

        val newMoonMinType = MoonPhaseType.from(newMoonMinValue)
        val firstQuarterMoonType = MoonPhaseType.from(firstQuarterMoonValue)
        val fullMoonType = MoonPhaseType.from(fullMoonValue)
        val lastQuarterMoonType = MoonPhaseType.from(lastQuarterMoonValue)
        val newMoonMaxType = MoonPhaseType.from(newMoonMaxValue)

        val waxingCrescentType = MoonPhaseType.from(waxingCrescentValue)
        val waxingGibbousType = MoonPhaseType.from(waxingGibbousValue)
        val waningGibbousType = MoonPhaseType.from(waningGibbousValue)
        val waningCrescentType = MoonPhaseType.from(waningCrescentValue)

        val outsideUpperLimitType = MoonPhaseType.from(outsideUpperLimitValue)

        // Assert
        assertNull(nullType)

        assertNull(outsideLowerLimitType)

        assertEquals(MoonPhaseType.NEW_MOON, newMoonMinType)
        assertEquals(MoonPhaseType.FIRST_QUARTER_MOON, firstQuarterMoonType)
        assertEquals(MoonPhaseType.FULL_MOON, fullMoonType)
        assertEquals(MoonPhaseType.LAST_QUARTER_MOON, lastQuarterMoonType)
        assertEquals(MoonPhaseType.NEW_MOON, newMoonMaxType)

        assertListDoesContain(
            listOf(
                MoonPhaseType.WAXING_CRESCENT_1,
                MoonPhaseType.WAXING_CRESCENT_2,
                MoonPhaseType.WAXING_CRESCENT_3,
                MoonPhaseType.WAXING_CRESCENT_4,
                MoonPhaseType.WAXING_CRESCENT_5,
                MoonPhaseType.WAXING_CRESCENT_6
            ), waxingCrescentType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WAXING_GIBBOUS_1,
                MoonPhaseType.WAXING_GIBBOUS_2,
                MoonPhaseType.WAXING_GIBBOUS_3,
                MoonPhaseType.WAXING_GIBBOUS_4,
                MoonPhaseType.WAXING_GIBBOUS_5,
                MoonPhaseType.WAXING_GIBBOUS_6
            ), waxingGibbousType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WANING_GIBBOUS_1,
                MoonPhaseType.WANING_GIBBOUS_2,
                MoonPhaseType.WANING_GIBBOUS_3,
                MoonPhaseType.WANING_GIBBOUS_4,
                MoonPhaseType.WANING_GIBBOUS_5,
                MoonPhaseType.WANING_GIBBOUS_6
            ), waningGibbousType
        )
        assertListDoesContain(
            listOf(
                MoonPhaseType.WANING_CRESCENT_1,
                MoonPhaseType.WANING_CRESCENT_2,
                MoonPhaseType.WANING_CRESCENT_3,
                MoonPhaseType.WANING_CRESCENT_4,
                MoonPhaseType.WANING_CRESCENT_5,
                MoonPhaseType.WANING_CRESCENT_6
            ), waningCrescentType
        )

        assertNull(outsideUpperLimitType)
    }

    @Test
    fun weatherConditionType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null
        val wrongValue = -1L

        // Act
        val nullType = WeatherConditionType.from(nullValue)
        val wrongType = WeatherConditionType.from(wrongValue)

        val type200 = WeatherConditionType.from(200L)
        val type201 = WeatherConditionType.from(201L)
        val type202 = WeatherConditionType.from(202L)
        val type210 = WeatherConditionType.from(210L)
        val type211 = WeatherConditionType.from(211L)
        val type212 = WeatherConditionType.from(212L)
        val type221 = WeatherConditionType.from(221L)
        val type230 = WeatherConditionType.from(230L)
        val type231 = WeatherConditionType.from(231L)
        val type232 = WeatherConditionType.from(232L)
        val type300 = WeatherConditionType.from(300L)
        val type301 = WeatherConditionType.from(301L)
        val type302 = WeatherConditionType.from(302L)
        val type310 = WeatherConditionType.from(310L)
        val type311 = WeatherConditionType.from(311L)
        val type312 = WeatherConditionType.from(312L)
        val type313 = WeatherConditionType.from(313L)
        val type314 = WeatherConditionType.from(314L)
        val type321 = WeatherConditionType.from(321L)
        val type500 = WeatherConditionType.from(500L)
        val type501 = WeatherConditionType.from(501L)
        val type502 = WeatherConditionType.from(502L)
        val type503 = WeatherConditionType.from(503L)
        val type504 = WeatherConditionType.from(504L)
        val type511 = WeatherConditionType.from(511L)
        val type520 = WeatherConditionType.from(520L)
        val type521 = WeatherConditionType.from(521L)
        val type522 = WeatherConditionType.from(522L)
        val type531 = WeatherConditionType.from(531L)
        val type600 = WeatherConditionType.from(600L)
        val type601 = WeatherConditionType.from(601L)
        val type602 = WeatherConditionType.from(602L)
        val type611 = WeatherConditionType.from(611L)
        val type612 = WeatherConditionType.from(612L)
        val type613 = WeatherConditionType.from(613L)
        val type615 = WeatherConditionType.from(615L)
        val type616 = WeatherConditionType.from(616L)
        val type620 = WeatherConditionType.from(620L)
        val type621 = WeatherConditionType.from(621L)
        val type622 = WeatherConditionType.from(622L)
        val type701 = WeatherConditionType.from(701L)
        val type711 = WeatherConditionType.from(711L)
        val type721 = WeatherConditionType.from(721L)
        val type731 = WeatherConditionType.from(731L)
        val type741 = WeatherConditionType.from(741L)
        val type751 = WeatherConditionType.from(751L)
        val type761 = WeatherConditionType.from(761L)
        val type762 = WeatherConditionType.from(762L)
        val type771 = WeatherConditionType.from(771L)
        val type781 = WeatherConditionType.from(781L)
        val type800 = WeatherConditionType.from(800L)
        val type801 = WeatherConditionType.from(801L)
        val type802 = WeatherConditionType.from(802L)
        val type803 = WeatherConditionType.from(803L)
        val type804 = WeatherConditionType.from(804L)

        // Assert
        assertNull(nullType)
        assertNull(wrongType)

        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_LIGHT_RAIN, type200)
        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_RAIN, type201)
        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_HEAVY_RAIN, type202)
        assertEquals(WeatherConditionType.LIGHT_THUNDERSTORM, type210)
        assertEquals(WeatherConditionType.THUNDERSTORM, type211)
        assertEquals(WeatherConditionType.HEAVY_THUNDERSTORM, type212)
        assertEquals(WeatherConditionType.RAGGED_THUNDERSTORM, type221)
        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_LIGHT_DRIZZLE, type230)
        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_DRIZZLE, type231)
        assertEquals(WeatherConditionType.THUNDERSTORM_WITH_HEAVY_DRIZZLE, type232)
        assertEquals(WeatherConditionType.LIGHT_INTENSITY_DRIZZLE, type300)
        assertEquals(WeatherConditionType.DRIZZLE, type301)
        assertEquals(WeatherConditionType.HEAVY_INTENSITY_DRIZZLE, type302)
        assertEquals(WeatherConditionType.LIGHT_INTENSITY_DRIZZLE_RAIN, type310)
        assertEquals(WeatherConditionType.DRIZZLE_RAIN, type311)
        assertEquals(WeatherConditionType.HEAVY_INTENSITY_DRIZZLE_RAIN, type312)
        assertEquals(WeatherConditionType.SHOWER_RAIN_AND_DRIZZLE, type313)
        assertEquals(WeatherConditionType.HEAVY_SHOWER_RAIN_AND_DRIZZLE, type314)
        assertEquals(WeatherConditionType.SHOWER_DRIZZLE, type321)
        assertEquals(WeatherConditionType.LIGHT_RAIN, type500)
        assertEquals(WeatherConditionType.MODERATE_RAIN, type501)
        assertEquals(WeatherConditionType.HEAVY_INTENSITY_RAIN, type502)
        assertEquals(WeatherConditionType.VERY_HEAVY_RAIN, type503)
        assertEquals(WeatherConditionType.EXTREME_RAIN, type504)
        assertEquals(WeatherConditionType.FREEZING_RAIN, type511)
        assertEquals(WeatherConditionType.LIGHT_INTENSITY_SHOWER_RAIN, type520)
        assertEquals(WeatherConditionType.SHOWER_RAIN, type521)
        assertEquals(WeatherConditionType.HEAVY_INTENSITY_SHOWER_RAIN, type522)
        assertEquals(WeatherConditionType.RAGGED_SHOWER_RAIN, type531)
        assertEquals(WeatherConditionType.LIGHT_SNOW, type600)
        assertEquals(WeatherConditionType.SNOW, type601)
        assertEquals(WeatherConditionType.HEAVY_SNOW, type602)
        assertEquals(WeatherConditionType.SLEET, type611)
        assertEquals(WeatherConditionType.LIGHT_SHOWER_SLEET, type612)
        assertEquals(WeatherConditionType.SHOWER_SLEET, type613)
        assertEquals(WeatherConditionType.LIGHT_RAIN_AND_SNOW, type615)
        assertEquals(WeatherConditionType.RAIN_AND_SNOW, type616)
        assertEquals(WeatherConditionType.LIGHT_SHOWER_SNOW, type620)
        assertEquals(WeatherConditionType.SHOWER_SNOW, type621)
        assertEquals(WeatherConditionType.HEAVY_SHOWER_SNOW, type622)
        assertEquals(WeatherConditionType.MIST, type701)
        assertEquals(WeatherConditionType.SMOKE, type711)
        assertEquals(WeatherConditionType.HAZE, type721)
        assertEquals(WeatherConditionType.SAND_DUST_WHIRLS, type731)
        assertEquals(WeatherConditionType.FOG, type741)
        assertEquals(WeatherConditionType.SAND, type751)
        assertEquals(WeatherConditionType.DUST, type761)
        assertEquals(WeatherConditionType.VOLCANIC_ASH, type762)
        assertEquals(WeatherConditionType.SQUALLS, type771)
        assertEquals(WeatherConditionType.TORNADO, type781)
        assertEquals(WeatherConditionType.CLEAR_SKY, type800)
        assertEquals(WeatherConditionType.FEW_CLOUDS, type801)
        assertEquals(WeatherConditionType.SCATTERED_CLOUDS, type802)
        assertEquals(WeatherConditionType.BROKEN_CLOUDS, type803)
        assertEquals(WeatherConditionType.OVERCAST_CLOUDS, type804)
    }

    @Test
    fun weatherIconType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null
        val emptyValue = ""
        val wrongValue = "Test"

        // Act
        val nullType = WeatherIconType.from(nullValue)
        val emptyType = WeatherIconType.from(emptyValue)
        val wrongType = WeatherIconType.from(wrongValue)

        val d01Type = WeatherIconType.from("01d")
        val d02Type = WeatherIconType.from("02d")
        val d03Type = WeatherIconType.from("03d")
        val d04Type = WeatherIconType.from("04d")
        val d09Type = WeatherIconType.from("09d")
        val d10Type = WeatherIconType.from("10d")
        val d11Type = WeatherIconType.from("11d")
        val d13Type = WeatherIconType.from("13d")
        val d50Type = WeatherIconType.from("50d")

        val n01Type = WeatherIconType.from("01n")
        val n02Type = WeatherIconType.from("02n")
        val n03Type = WeatherIconType.from("03n")
        val n04Type = WeatherIconType.from("04n")
        val n09Type = WeatherIconType.from("09n")
        val n10Type = WeatherIconType.from("10n")
        val n11Type = WeatherIconType.from("11n")
        val n13Type = WeatherIconType.from("13n")
        val n50Type = WeatherIconType.from("50n")

        // Assert
        assertNull(nullType)
        assertNull(emptyType)
        assertNull(wrongType)

        assertEquals(WeatherIconType.D_CLEAR_SKY, d01Type)
        assertEquals(WeatherIconType.D_FEW_CLOUDS, d02Type)
        assertEquals(WeatherIconType.D_SCATTERED_CLOUDS, d03Type)
        assertEquals(WeatherIconType.D_BROKEN_CLOUDS, d04Type)
        assertEquals(WeatherIconType.D_SHOWER_RAIN, d09Type)
        assertEquals(WeatherIconType.D_RAIN, d10Type)
        assertEquals(WeatherIconType.D_THUNDERSTORM, d11Type)
        assertEquals(WeatherIconType.D_SNOW, d13Type)
        assertEquals(WeatherIconType.D_MIST, d50Type)

        assertEquals(WeatherIconType.N_CLEAR_SKY, n01Type)
        assertEquals(WeatherIconType.N_FEW_CLOUDS, n02Type)
        assertEquals(WeatherIconType.N_SCATTERED_CLOUDS, n03Type)
        assertEquals(WeatherIconType.N_BROKEN_CLOUDS, n04Type)
        assertEquals(WeatherIconType.N_SHOWER_RAIN, n09Type)
        assertEquals(WeatherIconType.N_RAIN, n10Type)
        assertEquals(WeatherIconType.N_THUNDERSTORM, n11Type)
        assertEquals(WeatherIconType.N_SNOW, n13Type)
        assertEquals(WeatherIconType.N_MIST, n50Type)
    }

    @Test
    fun windDegreesType_shouldBeCorrectType() {
        // Arrange
        val nullValue = null

        val outsideLowerLimitValue = -1L

        val nMinValue = 0L
        val nneValue = windDegreeStep(1)
        val neValue = windDegreeStep(2)
        val eneValue = windDegreeStep(3)
        val eValue = windDegreeStep(4)
        val eseValue = windDegreeStep(5)
        val seValue = windDegreeStep(6)
        val sseValue = windDegreeStep(7)
        val sValue = windDegreeStep(8)
        val sswValue = windDegreeStep(9)
        val swValue = windDegreeStep(10)
        val wswValue = windDegreeStep(11)
        val wValue = windDegreeStep(12)
        val wnwValue = windDegreeStep(13)
        val nwValue = windDegreeStep(14)
        val nnwValue = windDegreeStep(15)
        val nMaxValue = 359L

        val outsideUpperLimitValue = 360L

        // Act
        val nullType = WindDegreesType.from(nullValue)

        val outsideLowerLimitType = WindDegreesType.from(outsideLowerLimitValue)

        val nMinType = WindDegreesType.from(nMinValue)
        val nneType = WindDegreesType.from(nneValue)
        val neType = WindDegreesType.from(neValue)
        val eneType = WindDegreesType.from(eneValue)
        val eType = WindDegreesType.from(eValue)
        val eseType = WindDegreesType.from(eseValue)
        val seType = WindDegreesType.from(seValue)
        val sseType = WindDegreesType.from(sseValue)
        val sType = WindDegreesType.from(sValue)
        val sswType = WindDegreesType.from(sswValue)
        val swType = WindDegreesType.from(swValue)
        val wswType = WindDegreesType.from(wswValue)
        val wType = WindDegreesType.from(wValue)
        val wnwType = WindDegreesType.from(wnwValue)
        val nwType = WindDegreesType.from(nwValue)
        val nnwType = WindDegreesType.from(nnwValue)
        val nMaxType = WindDegreesType.from(nMaxValue)

        val outsideUpperLimitType = WindDegreesType.from(outsideUpperLimitValue)

        // Assert
        assertNull(nullType)

        assertNull(outsideLowerLimitType)

        assertEquals(WindDegreesType.N, nMinType)
        assertEquals(WindDegreesType.NNE, nneType)
        assertEquals(WindDegreesType.NE, neType)
        assertEquals(WindDegreesType.ENE, eneType)
        assertEquals(WindDegreesType.E, eType)
        assertEquals(WindDegreesType.ESE, eseType)
        assertEquals(WindDegreesType.SE, seType)
        assertEquals(WindDegreesType.SSE, sseType)
        assertEquals(WindDegreesType.S, sType)
        assertEquals(WindDegreesType.SSW, sswType)
        assertEquals(WindDegreesType.SW, swType)
        assertEquals(WindDegreesType.WSW, wswType)
        assertEquals(WindDegreesType.W, wType)
        assertEquals(WindDegreesType.WNW, wnwType)
        assertEquals(WindDegreesType.NW, nwType)
        assertEquals(WindDegreesType.NNW, nnwType)
        assertEquals(WindDegreesType.N, nMaxType)

        assertNull(outsideUpperLimitType)

    }

    private fun average(vararg values: Double): Double {
        return values.average()
    }

    private fun windDegreeStep(step: Int): Long {
        return (step * 22.5).toLong()
    }

}