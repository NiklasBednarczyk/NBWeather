package de.niklasbednarczyk.nbweather.test.data.localremote.local.daos

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import de.niklasbednarczyk.nbweather.test.common.utils.assertNullOrEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import java.io.IOException
import kotlin.test.assertNotNull

abstract class NBDaoTest<Database : RoomDatabase, Dao : Any> {

    protected lateinit var db: Database
    protected lateinit var dao: Dao

    abstract val databaseClass: Class<Database>

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            databaseClass
        ).build()
        dao = initiateDao()
        initiateOtherDaos()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    protected abstract fun initiateDao(): Dao

    protected open fun initiateOtherDaos() {}

    protected fun <Subject, Key> testGetItemQuery(
        keys: Triple<Key, Key, Key>,
        createArrange: (Key) -> Subject,
        insert: (Subject) -> Unit,
        createAct: (Key) -> Flow<Subject?>,
        assertAreEqual: (arrange: Subject, act: Subject) -> Unit
    ) = runTest {
        // Arrange
        val arrange1 = createArrange(keys.first)
        val arrange2 = createArrange(keys.second)

        insert(arrange1)
        insert(arrange2)

        // Act
        val act1 = createAct(keys.first).firstOrNull()
        val act2 = createAct(keys.second).firstOrNull()
        val act3 = createAct(keys.third).firstOrNull()

        // Assert
        assertNotNull(act1)
        assertAreEqual(arrange1, act1)

        assertNotNull(act2)
        assertAreEqual(arrange2, act2)

        assertNullOrEmpty(act3)
    }

    protected fun <Subject, Key> testDeleteItemQuery(
        keys: Pair<Key, Key>,
        createArrange: (Key) -> Subject,
        insert: (Subject) -> Unit,
        delete: (Key) -> Unit,
        createAct: (Key) -> Flow<Subject?>,
        assertAreEqual: (arrange: Subject, act: Subject) -> Unit
    ) = runTest {
        // Arrange
        val arrange1 = createArrange(keys.first)
        val arrange2 = createArrange(keys.second)

        insert(arrange1)
        insert(arrange2)

        // Act
        delete(keys.first)

        val act1 = createAct(keys.first).firstOrNull()
        val act2 = createAct(keys.second).firstOrNull()

        // Assert
        assertNullOrEmpty(act1)

        assertNotNull(act2)
        assertAreEqual(arrange2, act2)
    }

}