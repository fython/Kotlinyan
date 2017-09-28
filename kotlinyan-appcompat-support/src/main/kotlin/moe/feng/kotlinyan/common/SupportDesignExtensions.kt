package moe.feng.kotlinyan.common

import android.content.res.ColorStateList
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Support Design Library Extensions
 *
 * Help developers to use Android Support Design Library's widgets
 *
 * @see <a href="https://github.com/fython/Kotlinyan/wiki/SupportDesignExtensions">SupportDesignExtensions Wiki</a>
 */

/**
 * Make snackbar on parent view
 *
 * @param process the process of building snackbar
 */
fun View.snackbar(process: SnackbarBuilder.() -> Unit) : Snackbar {
	val builder = SnackbarBuilder(this)
	builder.process()
	return builder.build()
}

/**
 * Snackbar Builder
 */
class SnackbarBuilder internal constructor(view: View) {

	private var snackbar : Snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)

	var message : String? = null
	var messageRes : Int = 0
	var duration : Int = Snackbar.LENGTH_SHORT
	var actionTextColor : Int = -1
	var actionTextColorStateList : ColorStateList? = null

	fun action(textRes: Int, callback: (View) -> Unit) {
		snackbar.setAction(textRes, callback)
	}

	fun action(text: String, callback: (View) -> Unit) {
		snackbar.setAction(text, callback)
	}

	internal fun build() : Snackbar {
		if (messageRes != 0) {
			snackbar.setText(messageRes)
		} else if (message != null) {
			snackbar.setText(message!!)
		}
		if (actionTextColor != -1) {
			snackbar.setActionTextColor(actionTextColor)
		} else if (actionTextColorStateList != null) {
			snackbar.setActionTextColor(actionTextColorStateList!!)
		}

		snackbar.duration = duration
		return snackbar
	}

}