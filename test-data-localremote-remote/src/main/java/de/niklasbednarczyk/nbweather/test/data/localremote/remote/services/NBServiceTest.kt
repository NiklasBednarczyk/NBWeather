package de.niklasbednarczyk.nbweather.test.data.localremote.remote.services

import dagger.hilt.android.testing.HiltAndroidRule
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

interface NBServiceTest : NBTest {

    @get:Rule
    val hiltRule
        get() = HiltAndroidRule(this)

    @Before
    override fun setUp() {
        hiltRule.inject()
    }

}