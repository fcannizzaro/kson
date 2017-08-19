import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by Francesco Cannizzaro (fcannizzaro)
 */

fun checkType(value: Any?): Boolean {
    return value == null ||
            value is Int ||
            value is Double ||
            value is Float ||
            value is String ||
            value is JSONArray ||
            value is JSONObject ||
            value is Map<*, *>
}


class KsonObject(private var json: JSONObject) {

    fun entry(key: String, obtain: () -> Any?) {

        var value = obtain()

        if (key.isEmpty()) {
            throw IllegalArgumentException("Entry key cannot be empty.")
        }

        if (value is Iterable<*>) {
            value = array(value)
        }

        if (!checkType(value)) {
            throw IllegalArgumentException("Entry value can only be Int, Double, Float, String, JSONArray, JSONObject or Map.")
        }

        json.put(key, value)

    }

    fun entry(key: String, value: Any?) = entry(key, { value })

}

fun json(kson: KsonObject.() -> Unit): JSONObject {
    val obj = JSONObject()
    kson.invoke(KsonObject(obj))
    return obj
}

fun array(vararg items: Any?): JSONArray {
    val array = JSONArray()
    items.forEach { array.put(it) }
    return array
}

fun array(items: () -> Iterable<Any?>) = JSONArray().put(items.invoke())
