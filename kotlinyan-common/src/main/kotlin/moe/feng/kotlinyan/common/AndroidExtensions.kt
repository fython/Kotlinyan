package moe.feng.kotlinyan.common

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import java.io.Serializable

interface AndroidExtensions : ActivityExtensions, ViewExtensions, ResourcesExtensions, FragmentExtensions {

	operator fun Intent.set(key: String, value: Any?) {
		when (value) {
			is Int -> putExtra(key, value)
			is String -> putExtra(key, value)
			is Short -> putExtra(key, value)
			is Long -> putExtra(key, value)
			is Byte -> putExtra(key, value)
			is Char -> putExtra(key, value)
			is CharSequence -> putExtra(key, value)
			is Boolean -> putExtra(key, value)
			is Float -> putExtra(key, value)
			is Double -> putExtra(key, value)
			is Bundle -> putExtra(key, value)
			is Parcelable -> putExtra(key, value)
			is IntArray -> putExtra(key, value)
			is FloatArray -> putExtra(key, value)
			is CharArray -> putExtra(key, value)
			is LongArray -> putExtra(key, value)
			is ByteArray -> putExtra(key, value)
			is ShortArray -> putExtra(key, value)
			is DoubleArray -> putExtra(key, value)
			is Serializable -> putExtra(key, value)
			is Array<*> -> putExtra(key, value)
			is ArrayList<*> -> when (value.getOrNull(0)) {
				is Int -> putIntegerArrayListExtra(key, value as java.util.ArrayList<Int>)
				is Integer -> putIntegerArrayListExtra(key, value as java.util.ArrayList<Int>)
				is CharSequence -> putCharSequenceArrayListExtra(key, value as java.util.ArrayList<CharSequence>)
				is String -> putStringArrayListExtra(key, value as java.util.ArrayList<String>)
				is Parcelable -> putParcelableArrayListExtra(key, value as java.util.ArrayList<Parcelable>)
			}
			null -> {} // Ignore null pointer
			else -> throw UnsupportedOperationException("Unsupported type ${value.javaClass.name} in params")
		}
	}

	operator fun Intent.get(key: String) : IntentExtraGetter?
			= if (hasExtra(key)) IntentExtraGetter(this, key) else null

	class IntentExtraGetter internal constructor(private val intent: Intent, val key: String) {

		fun asString(): String = intent.getStringExtra(key)

		fun asCharSequence(): CharSequence = intent.getCharSequenceExtra(key)

		fun asInt(): Int = intent.getIntExtra(key, 0)

		fun asBoolean(): Boolean = intent.getBooleanExtra(key, false)

		fun asChar(): Char = intent.getCharExtra(key, ' ')

		fun asByte(): Byte = intent.getByteExtra(key, 0)

		fun asByteArray(): ByteArray = intent.getByteArrayExtra(key)

		fun asBundle(): Bundle = intent.getBundleExtra(key)

		fun asDouble(): Double = intent.getDoubleExtra(key, .0)

		fun asFloat(): Float = intent.getFloatExtra(key, 0F)

		fun asLong(): Long = intent.getLongExtra(key, 0)

		fun asShort(): Short = intent.getShortExtra(key, 0)

		fun asBooleanArray(): BooleanArray = intent.getBooleanArrayExtra(key)

		fun asCharArray(): CharArray = intent.getCharArrayExtra(key)

		fun asDoubleArray(): DoubleArray = intent.getDoubleArrayExtra(key)

		fun asIntArray(): IntArray = intent.getIntArrayExtra(key)

		fun asFloatArray(): FloatArray = intent.getFloatArrayExtra(key)

		fun asLongArray(): LongArray = intent.getLongArrayExtra(key)

		fun asShortArray(): ShortArray = intent.getShortArrayExtra(key)

		fun asCharSequenceArray(): Array<out CharSequence> = intent.getCharSequenceArrayExtra(key)

		fun <T : Parcelable> asParcelable(): T = intent.getParcelableExtra<T>(key)

		fun asSerializable(): Serializable = intent.getSerializableExtra(key)

		fun asParacelableArray(): Array<out Parcelable> = intent.getParcelableArrayExtra(key)

		fun <T : Parcelable> asParacelableList(): java.util.ArrayList<T> = intent.getParcelableArrayListExtra<T>(key)

		fun asCharSequenceList(): java.util.ArrayList<CharSequence> = intent.getCharSequenceArrayListExtra(key)

		fun asIntList(): java.util.ArrayList<Int> = intent.getIntegerArrayListExtra(key)

		fun asStringList(): java.util.ArrayList<String> = intent.getStringArrayListExtra(key)

	}

	@SuppressLint("NewApi")
	operator fun Bundle.set(key: String, value: Any) {
		when (value) {
			is Boolean -> putBoolean(key, value)
			is Byte -> putByte(key, value)
			is ByteArray -> putByteArray(key, value)
			is IBinder -> putBinder(key, value)
			is Char -> putChar(key, value)
			is CharArray -> putCharArray(key, value)
			is CharSequence -> putCharSequence(key, value)
			is Double -> putDouble(key, value)
			is DoubleArray -> putDoubleArray(key, value)
			is Float -> putFloat(key, value)
			is FloatArray -> putFloatArray(key, value)
			is Int -> putInt(key, value)
			is IntArray -> putIntArray(key, value)
			is Long -> putLong(key, value)
			is Short -> putShort(key, value)
			is ShortArray -> putShortArray(key, value)
			is Parcelable -> putParcelable(key, value)
			is Serializable -> putSerializable(key, value)
			is String -> putString(key, value)
			is Array<*> -> when (value.getOrNull(0)) {
				is CharSequence -> putCharSequenceArray(key, value as Array<out CharSequence>)
				is String -> putStringArray(key, value as Array<out String>)
				is Parcelable -> putParcelableArray(key, value as Array<out Parcelable>)
			}
			is ArrayList<*> -> when (value.getOrNull(0)) {
				is Int -> putIntegerArrayList(key, value as java.util.ArrayList<Int>)
				is Integer -> putIntegerArrayList(key, value as java.util.ArrayList<Int>)
				is CharSequence -> putCharSequenceArrayList(key, value as java.util.ArrayList<CharSequence>)
				is String -> putStringArrayList(key, value as java.util.ArrayList<String>)
				is Parcelable -> putParcelableArrayList(key, value as java.util.ArrayList<Parcelable>)
			}
			Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> when (value) {
				is Size -> putSize(key, value)
				is SizeF -> putSizeF(key, value)
			}
			else -> throw UnsupportedOperationException("Unsupported type ${value.javaClass.name} in params")
		}
	}

	operator fun SharedPreferences.get(key: String): SharedPreferencesGetter?
			= if (contains(key)) SharedPreferencesGetter(this, key) else null

	class SharedPreferencesGetter internal constructor(private val sp: SharedPreferences, val key: String) {
		fun asString(defValue: String? = null): String = sp.getString(key, defValue)
		fun asInt(defValue: Int = 0): Int = sp.getInt(key, defValue)
		fun asBoolean(defValue: Boolean = false): Boolean = sp.getBoolean(key, defValue)
		fun asLong(defValue: Long = 0): Long = sp.getLong(key, defValue)
		fun asFloat(defValue: Float = 0F): Float = sp.getFloat(key, defValue)
	}

	operator fun SharedPreferences.set(key: String, value: Any) {
		when (value) {
			is String -> edit().putString(key, value).apply()
			is Int -> edit().putInt(key, value).apply()
			is Boolean -> edit().putBoolean(key, value).apply()
			is Long -> edit().putLong(key, value).apply()
			is Float -> edit().putFloat(key, value).apply()
		}
	}

}