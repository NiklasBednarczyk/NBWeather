package de.niklasbednarczyk.nbweather.test.data.disk.utils

import androidx.datastore.core.Serializer
import java.io.ByteArrayOutputStream

suspend fun <Proto> Serializer<Proto>.writeTo(proto: Proto): ByteArrayOutputStream {
    val outputStream = ByteArrayOutputStream()
    writeTo(proto, outputStream)
    return outputStream
}