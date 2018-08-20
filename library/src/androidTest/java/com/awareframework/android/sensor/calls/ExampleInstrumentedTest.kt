package com.awareframework.android.sensor.calls

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.awareframework.android.core.db.Engine
import com.awareframework.android.sensor.calls.model.CallData
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * <p>
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        CallsSensor.start(appContext, CallsSensor.Config().apply {
            sensorObserver = object : CallsSensor.Observer {
                override fun onCall(data: CallData) {
                    // your code here...
                }

                override fun onRinging(number: String?) {
                    // your code here...
                }

                override fun onBusy(number: String?) {
                    // your code here...
                }

                override fun onFree(number: String?) {
                    // your code here...
                }
            }
            dbType = Engine.DatabaseType.ROOM
            debug = true
            // more configuration...
        })
    }
}
