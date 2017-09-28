package moe.feng.kotlinyan.common

import android.app.Activity
import android.app.Fragment
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.AdapterView
import android.widget.Button

/**
 * AppCompat Library Extensions
 *
 * @see <a href="https://github.com/fython/Kotlinyan/wiki/AppCompatExtensions">AppCompatExtensions Wiki</a>
 */

// Kotlin-style builders

/**
 * Build AppCompat v7 AlertDialog in Fragment
 *
 * @param process The process of building AppCompat v7 AlertDialog
 * @see android.support.v7.app.AlertDialog
 */
fun Fragment.buildV7AlertDialog(process: AlertDialog.Builder.() -> Unit) = activity.buildV7AlertDialog(process)

/**
 * Build AppCompat v7 AlertDialog in Fragment
 *
 * @param process The process of building AppCompat v7 AlertDialog
 * @see android.support.v7.app.AlertDialog
 */
fun android.support.v4.app.Fragment
		.buildV7AlertDialog(process: AlertDialog.Builder.() -> Unit) = activity.buildV7AlertDialog(process)

/**
 * Build AppCompat v7 AlertDialog in Activity
 *
 * @param process The process of building AppCompat v7 AlertDialog
 * @see android.support.v7.app.AlertDialog
 */
fun Activity.buildV7AlertDialog(process: AlertDialog.Builder.() -> Unit) : AlertDialog {
	val builder = AlertDialog.Builder(this)
	builder.process()
	return builder.create()
}

var AlertDialog.Builder.title : String
	get() { throw java.lang.NoSuchMethodException("Title getter is not supported") }
	set(value) { this.setTitle(value) }

var AlertDialog.Builder.titleRes : Int
	get() { throw java.lang.NoSuchMethodException("Title res id getter is not supported") }
	set(value) { this.setTitle(value) }

var AlertDialog.Builder.message : String
	get() { throw java.lang.NoSuchMethodException("Message getter is not supported") }
	set(value) { this.setMessage(value) }

var AlertDialog.Builder.messageRes : Int
	get() { throw java.lang.NoSuchMethodException("Message res id getter is not supported") }
	set(value) { this.setMessage(value) }

var AlertDialog.Builder.isCancelable : Boolean
	get() { throw java.lang.NoSuchMethodException("isCancelable getter is not supported") }
	set(value) { this.setCancelable(value) }

var AlertDialog.Builder.customTitle : View
	get() { throw java.lang.NoSuchMethodException("Custom title getter is not supported") }
	set(value) { this.setCustomTitle(value) }

var AlertDialog.Builder.icon : Drawable
	get() { throw java.lang.NoSuchMethodException("Icon getter is not supported") }
	set(value) { this.setIcon(value) }

var AlertDialog.Builder.iconRes : Int
	get() { throw java.lang.NoSuchMethodException("Icon res id getter is not supported") }
	set(value) { this.setIcon(value) }

var AlertDialog.Builder.iconAttribute : Int
	get() { throw java.lang.NoSuchMethodException("Icon attribute getter is not supported") }
	set(value) { this.setIconAttribute(value) }

var AlertDialog.Builder.onCancel : (DialogInterface) -> Unit
	get() { throw java.lang.NoSuchMethodException("OnCancelListener getter is not supported") }
	set(value) { this.setOnCancelListener(value) }

var AlertDialog.Builder.onDismiss : (DialogInterface) -> Unit
	get() { throw java.lang.NoSuchMethodException("OnDismissListener getter is not supported") }
	set(value) { this.setOnDismissListener(value) }

var AlertDialog.Builder.onKey : DialogInterface.OnKeyListener
	get() { throw java.lang.NoSuchMethodException("OnKeyListener getter is not supported") }
	set(value) { this.setOnKeyListener(value) }

var AlertDialog.Builder.onItemSelected : AdapterView.OnItemSelectedListener
	get() { throw java.lang.NoSuchMethodException("OnItemSelectedListener getter is not supported") }
	set(value) { this.setOnItemSelectedListener(value) }

var AlertDialog.Builder.view : View
	get() { throw java.lang.NoSuchMethodException("View getter is not supported") }
	set(value) { this.setView(value) }

var AlertDialog.Builder.viewRes : Int
	get() { throw java.lang.NoSuchMethodException("View res id getter is not supported") }
	set(value) { this.setView(value) }

/**
 * Set ok button for AlertDialog
 *
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.okButton(onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setPositiveButton(android.R.string.ok, onClick)
}

/**
 * Set cancel button for AlertDialog
 *
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.cancelButton(onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setNegativeButton(android.R.string.cancel, onClick)
}

/**
 * Set positive button for AlertDialog
 *
 * @param textId Text resource id
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.positiveButton(textId: Int, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setPositiveButton(textId, onClick)
}

/**
 * Set positive button for AlertDialog
 *
 * @param text Text string
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.positiveButton(text: String, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setPositiveButton(text, onClick)
}

/**
 * Set negative button for AlertDialog
 *
 * @param textId Text resource id
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.negativeButton(textId: Int, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setNegativeButton(textId, onClick)
}

/**
 * Set negative button for AlertDialog
 *
 * @param text Text string
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.negativeButton(text: String, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setNegativeButton(text, onClick)
}

/**
 * Set neutral button for AlertDialog
 *
 * @param textId Text resource id
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.neutralButton(textId: Int, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setNeutralButton(textId, onClick)
}

/**
 * Set neutral button for AlertDialog
 *
 * @param text Text string
 * @param onClick onClick callback
 */
fun AlertDialog.Builder.neutralButton(text: String, onClick: (DialogInterface, Int) -> Unit = {_, _ ->}) {
	setNeutralButton(text, onClick)
}

val AlertDialog.positiveButton : Button
	get() = this.getButton(DialogInterface.BUTTON_POSITIVE)

val AlertDialog.negativeButton : Button
	get() = this.getButton(DialogInterface.BUTTON_NEGATIVE)

val AlertDialog.neutralButton : Button
	get() = this.getButton(DialogInterface.BUTTON_NEUTRAL)