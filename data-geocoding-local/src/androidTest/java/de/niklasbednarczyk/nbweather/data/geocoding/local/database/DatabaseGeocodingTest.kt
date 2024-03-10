package de.niklasbednarczyk.nbweather.data.geocoding.local.database

import androidx.core.database.getLongOrNull
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocalNamesModelLocal
import de.niklasbednarczyk.nbweather.data.geocoding.local.models.LocationModelLocal
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DatabaseGeocodingTest : NBTest {

    companion object {

        private const val DB_NAME = "DatabaseGeocodingTest"

        private const val ENTITY_LOCATION = "locationmodellocal"

    }

    private val allMigrations = arrayOf(
        DatabaseGeocoding_AutoMigration_1_2_Impl()
    )

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        DatabaseGeocoding::class.java
    )

    @Test
    fun migration1To2_shouldMigrateCorrectly() {
        // Arrange
        fun getColumns(
            location: LocationModelLocal? = null
        ): Map<String, Any?> {
            return mapOf(
                "name" to location?.name,
                "country" to location?.country,
                "state" to location?.state,
                "latitude" to location?.latitude,
                "longitude" to location?.longitude,
                "lastVisitedTimestampEpochSeconds" to location?.lastVisitedTimestampEpochSeconds,
                "localNames_af" to location?.localNames?.af,
                "localNames_sq" to location?.localNames?.sq,
                "localNames_ar" to location?.localNames?.ar,
                "localNames_az" to location?.localNames?.az,
                "localNames_bg" to location?.localNames?.bg,
                "localNames_ca" to location?.localNames?.ca,
                "localNames_cs" to location?.localNames?.cs,
                "localNames_da" to location?.localNames?.da,
                "localNames_de" to location?.localNames?.de,
                "localNames_el" to location?.localNames?.el,
                "localNames_en" to location?.localNames?.en,
                "localNames_eu" to location?.localNames?.eu,
                "localNames_fa" to location?.localNames?.fa,
                "localNames_fi" to location?.localNames?.fi,
                "localNames_fr" to location?.localNames?.fr,
                "localNames_gl" to location?.localNames?.gl,
                "localNames_he" to location?.localNames?.he,
                "localNames_hi" to location?.localNames?.hi,
                "localNames_hr" to location?.localNames?.hr,
                "localNames_hu" to location?.localNames?.hu,
                "localNames_id" to location?.localNames?.id,
                "localNames_it" to location?.localNames?.it,
                "localNames_ja" to location?.localNames?.ja,
                "localNames_ko" to location?.localNames?.ko,
                "localNames_lv" to location?.localNames?.lv,
                "localNames_lt" to location?.localNames?.lt,
                "localNames_mk" to location?.localNames?.mk,
                "localNames_no" to location?.localNames?.no,
                "localNames_nl" to location?.localNames?.nl,
                "localNames_pl" to location?.localNames?.pl,
                "localNames_pt" to location?.localNames?.pt,
                "localNames_ro" to location?.localNames?.ro,
                "localNames_ru" to location?.localNames?.ru,
                "localNames_sv" to location?.localNames?.sv,
                "localNames_sk" to location?.localNames?.sk,
                "localNames_sl" to location?.localNames?.sl,
                "localNames_es" to location?.localNames?.es,
                "localNames_sr" to location?.localNames?.sr,
                "localNames_th" to location?.localNames?.th,
                "localNames_tr" to location?.localNames?.tr,
                "localNames_uk" to location?.localNames?.uk,
                "localNames_vi" to location?.localNames?.vi,
                "localNames_zh" to location?.localNames?.zh,
                "localNames_zu" to location?.localNames?.zu
            )
        }

        val location1 = createTestLocation(1, false)
        val location2 = createTestLocation(2, true)
        val location3 = createTestLocation(3, false)

        val locations = listOf(location1, location2, location3)

        helper.createDatabase(DB_NAME, 1).apply {
            execSQL(
                """
                INSERT INTO $ENTITY_LOCATION ${getColumnNames(getColumns())}
                VALUES 
                    ${getValueString(getColumns(location1))},
                    ${getValueString(getColumns(location2))},
                    ${getValueString(getColumns(location3))}
                """.trimIndent()
            )
            close()
        }

        // Act
        val database = helper.runMigrationsAndValidate(DB_NAME, 2, true)
        val cursor = database.query("SELECT * from $ENTITY_LOCATION")

        // Assert
        locations.forEachIndexed { index, location ->
            cursor.moveToPosition(index)

            getColumns(location).forEach { column ->
                val columnIndex = cursor.getColumnIndex(column.key)
                val value = when (column.value) {
                    is Double -> cursor.getDouble(columnIndex)
                    is Long -> cursor.getLong(columnIndex)
                    is String -> cursor.getString(columnIndex)
                    null -> null
                    else -> throw RuntimeException("Unexpected type of value: ${column.value?.javaClass?.simpleName}")
                }
                assertEquals(column.value, value)
            }

            val orderColumnIndex = cursor.getColumnIndex("order")
            val order = cursor.getLongOrNull(orderColumnIndex)
            assertNull(order)
        }

    }

    @Test
    fun allMigrations_shouldMigrateCorrectly() {
        // Arrange
        helper.createDatabase(DB_NAME, 1).apply {
            close()
        }

        // Act + Assert
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            DatabaseGeocoding::class.java,
            DB_NAME
        ).addMigrations(*allMigrations).build().apply {
            openHelper.writableDatabase.close()
        }
    }

    private fun createTestLocation(
        index: Long,
        nullableAllNull: Boolean
    ): LocationModelLocal {
        val latitude = index.toDouble()
        val longitude = -index.toDouble()
        return if (nullableAllNull) {
            LocationModelLocal(
                latitude = latitude,
                longitude = longitude,
                name = null,
                localNames = null,
                country = null,
                state = null,
                lastVisitedTimestampEpochSeconds = null,
                order = null
            )
        } else {
            LocationModelLocal(
                latitude = latitude,
                longitude = longitude,
                name = "name $index",
                localNames = LocalNamesModelLocal(
                    af = "af $index",
                    sq = "sq $index",
                    ar = "ar $index",
                    az = "az $index",
                    bg = "bg $index",
                    ca = "ca $index",
                    cs = "cs $index",
                    da = "da $index",
                    de = "de $index",
                    el = "el $index",
                    en = "en $index",
                    eu = "eu $index",
                    fa = "fa $index",
                    fi = "fi $index",
                    fr = "fr $index",
                    gl = "gl $index",
                    he = "he $index",
                    hi = "hi $index",
                    hr = "hr $index",
                    hu = "hu $index",
                    id = "id $index",
                    it = "it $index",
                    ja = "ja $index",
                    ko = "ko $index",
                    lv = "lv $index",
                    lt = "lt $index",
                    mk = "mk $index",
                    no = "no $index",
                    nl = "nl $index",
                    pl = "pl $index",
                    pt = "pt $index",
                    ro = "ro $index",
                    ru = "ru $index",
                    sv = "sv $index",
                    sk = "sk $index",
                    sl = "sl $index",
                    es = "es $index",
                    sr = "sr $index",
                    th = "th $index",
                    tr = "tr $index",
                    uk = "uk $index",
                    vi = "vi $index",
                    zh = "zh $index",
                    zu = "zu $index"
                ),
                country = "country $index",
                state = "state $index",
                lastVisitedTimestampEpochSeconds = index + 1,
                order = index + 2
            )
        }


    }

    private fun getColumnNames(
        columns: Map<String, Any?>
    ): String {
        return columns.keys.joinToString(
            separator = ", ",
            prefix = "(",
            postfix = ")"
        )
    }

    private fun getValueString(
        columns: Map<String, Any?>
    ): String {
        return columns.values.joinToString(
            separator = ", ",
            prefix = "(",
            postfix = ")",
            transform = ::toSQLString
        )
    }

    private fun toSQLString(
        value: Any?
    ): String {
        return if (value is String) {
            "'$value'"
        } else {
            value.toString()
        }
    }

}