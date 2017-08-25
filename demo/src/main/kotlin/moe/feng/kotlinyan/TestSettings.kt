package moe.feng.kotlinyan

import android.content.Context
import moe.feng.kotlinyan.common.SharedPreferencesProvider

class TestSettings private constructor(context: Context): SharedPreferencesProvider(context, "TestSettings") {

	var a by stringValue(defValue = "Test")
	var b by intValue(defValue = 888)
	var c by booleanValue(defValue = true)
	var d by floatValue(defValue = .666F)
	var e by longValue(defValue = 666L)

}