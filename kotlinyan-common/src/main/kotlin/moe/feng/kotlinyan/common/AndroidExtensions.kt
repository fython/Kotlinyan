package moe.feng.kotlinyan.common

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import java.io.Serializable

interface AndroidExtensions : ActivityExtensions, ViewExtensions, ResourcesExtensions, FragmentExtensions {

	operator fun Intent.set(key: String, value: Any) {
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
			else -> throw UnsupportedOperationException("Unsupported type ${value.javaClass.name} in params")
		}
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

}