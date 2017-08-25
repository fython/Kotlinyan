package moe.feng.kotlinyan.common

import android.annotation.TargetApi
import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.preference.Preference
import android.preference.PreferenceManager
import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// View Properties

fun <T: View> Activity.lazyFindView(viewId: Int): LazyGetView<Activity, T?> {
	return LazyGetView(viewId, { activity, id -> activity.findViewById(id) })
}

fun <T: View> Activity.lazyFindNonNullView(viewId: Int): LazyGetNonNullView<Activity, T> {
	return LazyGetNonNullView(viewId, { activity, id -> activity.findViewById(id) })
}

fun <T: View> Fragment.findView(viewId: Int): LazyGetView<Fragment, T?> {
	return LazyGetView(viewId, { fragment, id -> fragment.view.findViewById(id) }, dontLazy = true)
}

fun <T: View> Fragment.findNonNullView(viewId: Int): LazyGetNonNullView<Fragment, T> {
	return LazyGetNonNullView(viewId, { fragment, id -> fragment.view.findViewById(id) }, dontLazy = true)
}

fun <T: View> View.lazyFindView(viewId: Int): LazyGetView<View, T?> {
	return LazyGetView(viewId, { view, id -> view.findViewById(id) })
}

fun <T: View> View.lazyFindNonNullView(viewId: Int): LazyGetNonNullView<View, T> {
	return LazyGetNonNullView(viewId, { view, id -> view.findViewById(id) })
}

class LazyGetView<in R, out T: View?> (
		private val viewId: Int,
		private val getter: (R, Int) -> T?,
		private val dontLazy: Boolean = false
): ReadOnlyProperty<R, T?> {

	private var _value: T? = null

	override fun getValue(thisRef: R, property: KProperty<*>): T? {
		if (_value == null || !dontLazy) _value = getter(thisRef, viewId)
		return _value
	}

}

class LazyGetNonNullView<in R, out T: View> (
		private val viewId: Int,
		private val getter: (R, Int) -> T,
		private val dontLazy: Boolean = false
): ReadOnlyProperty<R, T> {

	private var _value: T? = null

	override fun getValue(thisRef: R, property: KProperty<*>): T {
		if (_value == null || dontLazy) _value = getter(thisRef, viewId)
		return _value!!
	}

}

// Preference Properties

class GetPreference<out T: Preference>(private val keyName: String)
	: ReadOnlyProperty<GetPreference.PreferenceObserver, T> {

	override fun getValue(thisRef: PreferenceObserver, property: KProperty<*>): T {
		return thisRef.getPreferenceManager().findPreference(keyName) as T
	}

	interface PreferenceObserver {
		fun getPreferenceManager(): PreferenceManager
	}

}

// Shared Preferences Properties

class GetSharedPreferences internal constructor(
		private val context: Context,
		private val prefName: String,
		private val mode: Int = Context.MODE_PRIVATE
): ReadOnlyProperty<Any, SharedPreferences> {

	override fun getValue(thisRef: Any, property: KProperty<*>): SharedPreferences {
		return context.getSharedPreferences(prefName, mode)
	}

}

// Resources Properties

fun Context.dimenRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Float>
		= ResourcesProperty(id, resources::getDimension, loadOnlyOnce)
fun Context.stringRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<String>
		= ResourcesProperty<String>(id, resources::getString, loadOnlyOnce)
fun Context.integerRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Int>
		= ResourcesProperty(id, resources::getInteger, loadOnlyOnce)
fun Context.boolRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Boolean>
		= ResourcesProperty(id, resources::getBoolean, loadOnlyOnce)
fun Context.drawableRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Drawable>
		= ResourcesProperty<Drawable>(id, resources::getDrawable, loadOnlyOnce)
fun Context.colorRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Int>
		= ResourcesProperty<Int>(id, resources::getColor, loadOnlyOnce)
fun Context.stringArrayRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Array<String>>
		= ResourcesProperty(id, resources::getStringArray, loadOnlyOnce)
fun Context.intArrayRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<IntArray>
		= ResourcesProperty(id, resources::getIntArray, loadOnlyOnce)
@TargetApi(26) fun Context.fontRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Typeface>
		= ResourcesProperty(id, resources::getFont, loadOnlyOnce)

fun Fragment.dimenRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Float>
		= ResourcesProperty(id, { resources.getDimension(it) }, loadOnlyOnce)
fun Fragment.stringRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<String>
		= ResourcesProperty(id, { resources.getString(it) }, loadOnlyOnce)
fun Fragment.integerRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Int>
		= ResourcesProperty(id, { resources.getInteger(it) }, loadOnlyOnce)
fun Fragment.boolRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Boolean>
		= ResourcesProperty(id, { resources.getBoolean(it) }, loadOnlyOnce)
fun Fragment.drawableRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Drawable>
		= ResourcesProperty(id, { resources.getDrawable(it) }, loadOnlyOnce)
fun Fragment.colorRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Int>
		= ResourcesProperty(id, { resources.getColor(it) }, loadOnlyOnce)
fun Fragment.stringArrayRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Array<String>>
		= ResourcesProperty(id, { resources.getStringArray(it) }, loadOnlyOnce)
fun Fragment.intArrayRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<IntArray>
		= ResourcesProperty(id, { resources.getIntArray(it) }, loadOnlyOnce)
@TargetApi(26) fun Fragment.fontRes(id: Int, loadOnlyOnce: Boolean = true): ResourcesProperty<Typeface>
		= ResourcesProperty(id, { resources.getFont(it) }, loadOnlyOnce)

class ResourcesProperty<out T> (
		private val resourceId: Int,
		private val func: (resourceId: Int) -> T,
		private val loadOnlyOnce: Boolean = true): ReadOnlyProperty<Any, T> {

	private var _value: T? = null

	override fun getValue(thisRef: Any, property: KProperty<*>): T {
		if (_value == null || !loadOnlyOnce) _value = func(resourceId)
		return _value!!
	}

}