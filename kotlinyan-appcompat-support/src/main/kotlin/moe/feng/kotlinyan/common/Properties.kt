package moe.feng.kotlinyan.common

import android.annotation.TargetApi
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.view.View

fun <T: View> Fragment.findView(viewId: Int): LazyGetView<Fragment, T?> {
	return LazyGetView(viewId, { fragment, id -> fragment.view?.findViewById(id) }, dontLazy = true)
}

fun <T: View> Fragment.findNonNullView(viewId: Int): LazyGetNonNullView<Fragment, T> {
	return LazyGetNonNullView(viewId, { fragment, id -> fragment.view?.findViewById(id)!! }, dontLazy = true)
}

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