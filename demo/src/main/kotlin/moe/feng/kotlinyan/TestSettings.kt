package moe.feng.kotlinyan

import android.content.Context
import moe.feng.kotlinyan.common.SharedPreferencesProvider

class TestSettings private constructor(context: Context): SharedPreferencesProvider(context, "TestSettings") {

	var a by stringValue("a", "Test")
	var b by intValue("b", 888)
	var c by booleanValue("c", true)
	var d by floatValue("d", .666F)
	var e by longValue("e", 666L)

}