package de.niklasbednarczyk.nbweather.test.data.disk.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.core.data.disk.repositories.RepositoryDisk
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import de.niklasbednarczyk.nbweather.test.common.utils.createTemporaryFolderRule
import de.niklasbednarczyk.nbweather.test.data.disk.utils.createFakeDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import kotlin.test.assertNotEquals

abstract class NBDiskRepositoryTest<Proto, Data, Repository : RepositoryDisk<Proto, Data>> :
    NBTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = createTemporaryFolderRule()

    protected abstract val fileName: String

    protected abstract val updateStartingProto: Proto

    protected lateinit var subject: Repository

    protected abstract fun createSerializer(): Serializer<Proto>

    protected abstract fun createRepository(dataStore: DataStore<Proto>): Repository


    @Before
    override fun setUp() {
        val dataStore = createFakeDataStore(
            temporaryFolder = temporaryFolder,
            serializer = createSerializer(),
            fileName = fileName,
            scope = testScope
        )
        subject = createRepository(dataStore)
    }

    protected fun testGetData(
        proto: Proto,
        assertValue: (actual: Data) -> Unit
    ) = testScope.runTest {
        // Arrange
        subject.dataStore.updateData { proto }

        // Act
        val actual = subject.getData().first()

        // Assert
        assertValue(actual)
    }

    protected fun testUpdate(
        update: suspend () -> Unit,
        assertValue: (actual: Proto) -> Unit
    ) = testScope.runTest {
        // Arrange
        subject.dataStore.updateData { updateStartingProto }

        // Act
        update()
        val actual = subject.dataStore.data.first()

        // Assert
        assertValue(actual)
        assertNotEquals(updateStartingProto, actual)
    }

}