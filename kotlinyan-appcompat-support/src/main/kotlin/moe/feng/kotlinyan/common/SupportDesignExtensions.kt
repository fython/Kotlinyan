package moe.feng.kotlinyan.common

import android.content.res.ColorStateList
import android.support.design.widget.Snackbar
import android.view.View

interface SupportDesignExtensions {

	fun View.snackbar(process: SnackbarBuilder.() -> Unit) : Snackbar {
		val builder = SnackbarBuilder(this)
		builder.process()
		return builder.build()
	}

	class SnackbarBuilder(view: View) {

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

}