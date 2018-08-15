package com.awareframework.android.sensor.calls.model

import com.awareframework.android.core.model.AwareObject

/**
 * Contains the calls sensor information.
 *
 * @author  sercant
 * @date 15/08/2018
 */
data class CallData(
        var eventTimestamp: Long = 0L,
        var type: Int = -1,
        var duration: Int = 0,
        var trace: String? = null
) : AwareObject(jsonVersion = 1) {

    companion object {
        const val TABLE_NAME = "callData"
    }

    override fun toString(): String = toJson()
}