package de.niklasbednarczyk.nbweather.test.common.utils

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.rules.TemporaryFolder
import java.util.Objects

fun Any.createHiltAndroidRule(): HiltAndroidRule = HiltAndroidRule(this)

fun createTemporaryFolderRule(): TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()