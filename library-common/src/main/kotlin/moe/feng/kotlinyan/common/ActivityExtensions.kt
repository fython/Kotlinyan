package moe.feng.kotlinyan.common

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.View

@SuppressLint("NewApi")
interface ActivityExtensions {

	/**
	 * If function is supported, call it safely.
	 */
	fun ifSupportSDK(minSDK: Int, block: () -> Unit) : (() -> Unit)? {
		if (Build.VERSION.SDK_INT >= minSDK) {
			block.invoke()
			return {}
		} else return null
	}

	/**
	 * Initialize transparent/translucent status bar in current activity.
	 */
	fun Activity.initTransparentStatusBar() {
		ifSupportSDK(Build.VERSION_CODES.KITKAT) {
			window.decorView.systemUiVisibility =
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
			ifSupportSDK(Build.VERSION_CODES.LOLLIPOP) {
				window.statusBarColor = Color.TRANSPARENT
			}
		}
	}

	/**
	 * Tint color for a menu item
	 */
	fun MenuItem.tintColor(color : Int) {
		this.icon?.setTint(color)
	}

	/**
	 * Tint color for all menu items
	 */
	fun Menu.tintItemsColor(color : Int) {
		for (i in 0..size() - 1) getItem(i).tintColor(color)
	}

}