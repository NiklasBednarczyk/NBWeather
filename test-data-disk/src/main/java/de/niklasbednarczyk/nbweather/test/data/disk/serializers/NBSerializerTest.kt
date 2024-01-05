package de.niklasbednarczyk.nbweather.test.data.disk.serializers

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import kotlin.test.assertEquals
import de.niklasbednarczyk.nbweather.test.data.disk.extentions.writeTo

abstract class NBSerializerTest<Proto>: NBTest {

    private lateinit var subject: Serializer<Proto>

    @Before
    override fun setUp() {
        subject = createSerializer()
    }

    protected abstract fun createSerializer(): Serializer<Proto>

    protected abstract val expectedDefaultValue: Proto

    protected abstract val expectedNewValue: Proto

    @Test
    fun defaultValue_isCorrect() {
        // Arrange
        val expectedValue = expectedDefaultValue

        // Act
        val actualValue = subject.defaultValue

        // Assert
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun writeToAndReadFrom_outputsCorrectValue() = testScope.runTest {
        // Arrange
        val expectedValue = expectedNewValue

        // Act
        val outputStream = subject.writeTo(expectedValue)
        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val actualValue = subject.readFrom(inputStream)

        // Assert
        assertEquals(expectedValue, actualValue)
    }

    @Test(expected = CorruptionException::class)
    fun readFrom_throwsExceptionOnIncorectValue () = testScope.runTest {
        // Arrange + Act + Assert
        subject.readFrom(ByteArrayInputStream(byteArrayOf(0)))
    }



}