package com.rtx.framework

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.rtx.framework.util.FwUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
private const val FAKE_STRING = "FW Hello"

@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Test fun readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        val myObjectUnderTest = FwUtil(context)

        // ...when the string is returned from the object under test...
        val result: String = myObjectUnderTest.getHelloWorldString()

        // ...then the result should be the expected one.
        assertThat(result).isEqualTo(FAKE_STRING)
    }
}
