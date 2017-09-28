package moe.feng.kotlinyan.common

import android.content.Context

fun Context.sharedPreferences(prefName: String, mode: Int = Context.MODE_PRIVATE)
		= GetSharedPreferences(this, prefName, mode)