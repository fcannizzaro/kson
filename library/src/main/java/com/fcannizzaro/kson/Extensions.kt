package com.fcannizzaro.kson

import org.json.JSONObject

operator fun Any.get(key: String): Any {
    if (this is JSONObject) {
        return get(key)
    }
    return this
}