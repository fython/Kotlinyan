package moe.feng.kotlinyan.common

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class SharedPreferencesProvider constructor(context: Context, prefName: String, mode: Int = Context.MODE_PRIVATE)
	: ISharedPreferencesProvider {

	override val sharedPref: SharedPreferences by GetSharedPreferences(context, prefName, mode)

}

private var __sharedPreferencesProviders: MutableMap<String, SharedPreferencesProvider> = mutableMapOf()

fun <T: SharedPreferencesProvider> Context.getSharedPreferencesProvider(clazz: Class<T>): T {
	val providerName = clazz.canonicalName
	if (!__sharedPreferencesProviders.contains(providerName)) {
		__sharedPreferencesProviders[providerName] = clazz
				.getDeclaredConstructor(Context::class.java).apply { isAccessible = true }.newInstance(this)
	}
	return __sharedPreferencesProviders[providerName] as T
}

inline fun <reified T: SharedPreferencesProvider> Context.getSharedPreferencesProvider(): T {
	return getSharedPreferencesProvider(T::class.java)
}

interface ISharedPreferencesProvider: ContextExtensions {

	val sharedPref: SharedPreferences

	fun stringValue(key: String, defValue: String? = null) = StringSharedPreferenceGetter(key, defValue)
	fun intValue(key: String, defValue: Int = 0) = IntSharedPreferenceGetter(key, defValue)
	fun booleanValue(key: String, defValue: Boolean = false) = BooleanSharedPreferenceGetter(key, defValue)
	fun longValue(key: String, defValue: Long = 0) = LongSharedPreferenceGetter(key, defValue)
	fun floatValue(key: String, defValue: Float = 0F) = FloatSharedPreferenceGetter(key, defValue)

}

class StringSharedPreferenceGetter internal constructor(private val key: String,
                                                        private val defValue: String? = null)
	: ReadWriteProperty<ISharedPreferencesProvider, String?>, AndroidExtensions {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): String?
			= thisRef.sharedPref[key].asString(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: String?) {
		thisRef.sharedPref[key] = value
	}

}

class IntSharedPreferenceGetter internal constructor(private val key: String,
                                                     private val defValue: Int = 0)
	: ReadWriteProperty<ISharedPreferencesProvider, Int>, AndroidExtensions {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Int
			= thisRef.sharedPref[key].asInt(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Int) {
		thisRef.sharedPref[key] = value
	}

}

class BooleanSharedPreferenceGetter internal constructor(private val key: String,
                                                     private val defValue: Boolean = false)
	: ReadWriteProperty<ISharedPreferencesProvider, Boolean>, AndroidExtensions {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Boolean
			= thisRef.sharedPref[key].asBoolean(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Boolean) {
		thisRef.sharedPref[key] = value
	}

}

class LongSharedPreferenceGetter internal constructor(private val key: String,
                                                         private val defValue: Long = 0)
	: ReadWriteProperty<ISharedPreferencesProvider, Long>, AndroidExtensions {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Long
			= thisRef.sharedPref[key].asLong(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Long) {
		thisRef.sharedPref[key] = value
	}

}

class FloatSharedPreferenceGetter internal constructor(private val key: String,
                                                       private val defValue: Float = 0F)
	: ReadWriteProperty<ISharedPreferencesProvider, Float>, AndroidExtensions {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Float
			= thisRef.sharedPref[key].asFloat(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Float) {
		thisRef.sharedPref[key] = value
	}

}