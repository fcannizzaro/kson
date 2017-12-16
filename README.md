# kson
Kotlin Json DSL

[![](https://jitpack.io/v/fcannizzaro/kson.svg)](https://jitpack.io/#fcannizzaro/kson)

## Dependecies

**Step 1**. Add the JitPack repository to your build file

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2**. Add the dependency


```gradle
dependencies {
  compile 'com.github.fcannizzaro:kson:1.0.3'
}
```

## Usage

```kotlin
val data = json {

  entry("key", "value")

  entry("array") {
    val items = (1..4).map { "item $it" }.reversed()
    items
  }

  entry("array-fast", array("fast 0", "fast 1"))

  entry("another-key", "another-value")

}

println(data.toString(4))

```

### JSON Result

```json
{
  "array-fast": ["fast 0", "fast 1"],
  "array": ["item 4", "item 3", "item 2", "item 1"],
  "another-key": "another-value",
  "key": "value"
}
```

### \[ \] Operator

```json
{
  "first": {
    "second" : {
      "key" : "value"
    }
  }
}
```

You can access "**key**" by simply doing:

```kotlin
val obj : JSONObject = /* already built */
val key = obj["first"]["second"]["key"] as String
```

## Methods

### json(kson: KsonObject.() -> Unit)
Create a JSONObject.

### entry(key: String, value: Any?)
### entry(key: String, value: () -> Any?)
Add a new entry.

### array(vararg items: Any?)
### array(items : () -> Iterable<Any?>)
Create a JSONArray.

## Exceptions

### IllegalArgumentException
- Entry key cannot be **empty.**
- Entry value can only be **Int**, **Double**, **Float**, **String**, **JSONArray**, **JSONObject** or **Map.**

## Author
Francesco Saverio Cannizzaro (**fcannizzaro**)
