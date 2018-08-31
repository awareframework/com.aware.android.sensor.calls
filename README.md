# AWARE Calls

[![jitpack-badge](https://jitpack.io/v/awareframework/com.aware.android.sensor.calls.svg)](https://jitpack.io/#awareframework/com.aware.android.sensor.calls)

The Calls sensor logs call events performed by or received by the user. It also provides higher level context on the users’ calling availability and actions.

<!-- This sensor does not record personal information, such as phone numbers or contact information. Instead, an unique ID is assigned that is irreversible (SHA-1 encryption) but it is always the same for the same source.  -->

## Public functions

### CallsSensor

+ `start(context: Context, config: CallsSensor.Config?)`: Starts the calls sensor with the optional configuration.
+ `stop(context: Context)`: Stops the service.

### CallsConfig

Class to hold the configuration of the sensor.

#### Fields

+ `sensorObserver: CallsSensor.Observer`: Callback for live data updates.
+ `enabled: Boolean` Sensor is enabled or not. (default = `false`)
+ `debug: Boolean` enable/disable logging to `Logcat`. (default = `false`)
+ `label: String` Label for the data. (default = "")
+ `deviceId: String` Id of the device that will be associated with the events and the sensor. (default = "")
+ `dbEncryptionKey` Encryption key for the database. (default = `null`)
+ `dbType: Engine` Which db engine to use for saving data. (default = `Engine.DatabaseType.NONE`)
+ `dbPath: String` Path of the database. (default = "aware_calls")
+ `dbHost: String` Host for syncing the database. (default = `null`)

## Broadcasts

### Fired Broadcasts

+ `CallsSensor.ACTION_AWARE_CALL_ACCEPTED`: fired when the user accepts an incoming call.
+ `CallsSensor.ACTION_AWARE_CALL_RINGING`: fired when the phone is ringing.
+ `CallsSensor.ACTION_AWARE_CALL_MISSED`: fired when the user lost a call.
+ `CallsSensor.ACTION_AWARE_CALL_MADE`: fired when the user is making a call.
+ `CallsSensor.ACTION_AWARE_USER_IN_CALL`: fired when the user is currently in a call.
+ `CallsSensor.ACTION_AWARE_USER_NOT_IN_CALL`: fired when the user is not in a call.
+ `CallsSensor.ACTION_AWARE_CALL_BLOCKED`: fired when call got blocked. (Available after android 24)
+ `CallsSensor.ACTION_AWARE_CALL_REJECTED`: fired when call got rejected by the callee. (Available after android 24)
+ `CallsSensor.ACTION_AWARE_CALL_VOICE_MAILED`: fired when call got voice mailed. (Available after android 24)

### Received Broadcasts

+ `CallsSensor.ACTION_AWARE_CALLS_START`: received broadcast to start the sensor.
+ `CallsSensor.ACTION_AWARE_CALLS_STOP`: received broadcast to stop the sensor.
+ `CallsSensor.ACTION_AWARE_CALLS_SYNC`: received broadcast to send sync attempt to the host.
+ `CallsSensor.ACTION_AWARE_CALLS_SET_LABEL`: received broadcast to set the data label. Label is expected in the `CallsSensor.EXTRA_LABEL` field of the intent extras.

## Data Representations

### Call Data

Contains the calls sensor information.

| Field          | Type   | Description                                                     |
| -------------- | ------ | --------------------------------------------------------------- |
| eventTimestamp | Long   | unixtime miliseconds of the actual event                        |
| type           | String | one of the [Android’s call types][2]                           |
| duration       | Int    | length of the call session                                      |
| trace          | String | source/target of the call                                       |
| deviceId       | String | AWARE device UUID                                               |
| label          | String | Customizable label. Useful for data calibration or traceability |
| timestamp      | Long   | unixtime milliseconds since 1970                                |
| timezone       | Int    | [Raw timezone offset][1] of the device                          |
| os             | String | Operating system of the device (ex. android)                    |

## Example usage

```kotlin
// To start the service.
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

// To stop the service
CallsSensor.stop(appContext)
```

## License

Copyright (c) 2018 AWARE Mobile Context Instrumentation Middleware/Framework (http://www.awareframework.com)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

[1]: https://developer.android.com/reference/java/util/TimeZone#getRawOffset()
[2]: http://developer.android.com/reference/android/provider/CallLog.Calls.html#TYPE
