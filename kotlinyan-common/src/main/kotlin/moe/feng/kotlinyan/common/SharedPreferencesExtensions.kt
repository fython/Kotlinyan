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

interface ISharedPreferencesProvider {

	val sharedPref: SharedPreferences

	fun stringValue(key: String? = null, defValue: String? = null) = StringSharedPreferenceGetter(key, defValue)
	fun intValue(key: String? = null, defValue: Int = 0) = IntSharedPreferenceGetter(key, defValue)
	fun booleanValue(key: String? = null, defValue: Boolean = false) = BooleanSharedPreferenceGetter(key, defValue)
	fun longValue(key: String? = null, defValue: Long = 0) = LongSharedPreferenceGetter(key, defValue)
	fun floatValue(key: String? = null, defValue: Float = 0F) = FloatSharedPreferenceGetter(key, defValue)

}

class StringSharedPreferenceGetter internal constructor(private val key: String? = null,
                                                        private val defValue: String? = null)
	: ReadWriteProperty<ISharedPreferencesProvider, String?> {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): String?
			= thisRef.sharedPref[key ?: property.name].asString(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: String?) {
		thisRef.sharedPref[key ?: property.name] = value
	}

}

class IntSharedPreferenceGetter internal constructor(private val key: String? = null,
                                                     private val defValue: Int = 0)
	: ReadWriteProperty<ISharedPreferencesProvider, Int> {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Int
			= thisRef.sharedPref[key ?: property.name].asInt(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Int) {
		thisRef.sharedPref[key ?: property.name] = value
	}

}

class BooleanSharedPreferenceGetter internal constructor(private val key: String? = null,
                                                     private val defValue: Boolean = false)
	: ReadWriteProperty<ISharedPreferencesProvider, Boolean> {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Boolean
			= thisRef.sharedPref[key ?: property.name].asBoolean(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Boolean) {
		thisRef.sharedPref[key ?: property.name] = value
	}

}

class LongSharedPreferenceGetter internal constructor(private val key: String? = null,
                                                         private val defValue: Long = 0)
	: ReadWriteProperty<ISharedPreferencesProvider, Long> {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Long
			= thisRef.sharedPref[key ?: property.name].asLong(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Long) {
		thisRef.sharedPref[key ?: property.name] = value
	}

}

class FloatSharedPreferenceGetter internal constructor(private val key: String? = null,
                                                       private val defValue: Float = 0F)
	: ReadWriteProperty<ISharedPreferencesProvider, Float> {

	override fun getValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>): Float
			= thisRef.sharedPref[key ?: property.name].asFloat(defValue)

	override fun setValue(thisRef: ISharedPreferencesProvider, property: KProperty<*>, value: Float) {
		thisRef.sharedPref[key ?: property.name] = value
	}

}