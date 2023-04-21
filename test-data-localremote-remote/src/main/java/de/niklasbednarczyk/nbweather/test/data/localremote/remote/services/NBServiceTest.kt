package de.niklasbednarczyk.nbweather.test.data.localremote.remote.services

import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import de.niklasbednarczyk.nbweather.test.common.utils.createHiltAndroidRule
import org.junit.Before
import org.junit.Rule

interface NBServiceTest : NBTest {

    @get:Rule
    val hiltAndroidRule
        get() = createHiltAndroidRule()

    @Before
    override fun setUp() {
        hiltAndroidRule.inject()
        createSubject()
    }

    fun createSubject()

}