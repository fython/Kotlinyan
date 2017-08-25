package moe.feng.kotlinyan.common

import android.content.Context

interface ContextExtensions {

	fun Context.sharedPreferences(prefName: String, mode: Int = Context.MODE_PRIVATE)
			= GetSharedPreferences(this, prefName, mode)

}