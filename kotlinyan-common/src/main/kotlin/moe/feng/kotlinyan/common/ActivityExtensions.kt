package moe.feng.kotlinyan.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Process
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button

@SuppressLint("NewApi")
interface ActivityExtensions: ServiceExtensions {

	/**
	 * If function is supported, call it safely.
	 *
	 * @param minSDK The minimum supported SDK
	 * @param block Methods
	 * @return Only if not supported, return null
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
	 *
	 * @param color Color value
	 */
	fun MenuItem.tintColor(color : Int) {
		this.icon?.setTint(color)
	}

	/**
	 * Tint color for all menu items
	 *
	 * @param color Color value
	 */
	fun Menu.tintItemsColor(color : Int) {
		for (i in 0..size() - 1) getItem(i).tintColor(color)
	}

	// Permissions Utils

	/**
	 * Run codes with permission safely.
	 * @param permission Manifest.permission.XXX
	 * @param callback If permission is granted, then it will be called
	 * @return If app should show request permission rationale (M+)/ it is denied (Lower than M), it will return null
	 */
	fun Activity.runWithPermission(permission: String, callback: () -> Unit) : (() -> Unit)? {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			if (checkPermission(permission, android.os.Process.myPid(), Process.myUid())
					== PackageManager.PERMISSION_GRANTED) {
				callback()
				return {}
			} else {
				return null
			}
		}
		if (checkPermission(permission, android.os.Process.myPid(), Process.myUid())
				== PackageManager.PERMISSION_GRANTED) {
			callback()
			return {}
		} else if (shouldShowRequestPermissionRationale(permission)) {
			activityPermissionsCallbacks["$localClassName#$permission"] = callback
			return null
		} else {
			activityPermissionsCallbacks["$localClassName#$permission"] = callback
			requestPermissions(arrayOf(permission), REQUEST_PERMISSION_CODE)
			return {}
		}
	}

	/**
	 * Run codes with permission safely. (No rationale)
	 * @param permission Manifest.permission.XXX
	 * @param callback If permission is granted, then it will be called
	 * @return If it is denied (Lower than M), return false
	 */
	fun Activity.runWithPermissionNoRationale(permission: String, callback: () -> Unit) : Boolean {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			if (checkPermission(permission, android.os.Process.myPid(), Process.myUid())
					== PackageManager.PERMISSION_GRANTED) {
				callback()
			} else {
				return false
			}
		}
		if (checkPermission(permission, android.os.Process.myPid(), Process.myUid())
				== PackageManager.PERMISSION_GRANTED) {
			callback()
		} else {
			activityPermissionsCallbacks["$localClassName#$permission"] = callback
			requestPermissions(arrayOf(permission), REQUEST_PERMISSION_CODE)
		}
		return true
	}

	/**
	 * If user accept permission rationale, you can call this continue to ask for permission.
	 * @param permission Manifest.permission.XXX
	 */
	fun Activity.continueRunWithPermission(permission: String) {
		if (checkPermission(permission, android.os.Process.myPid(), Process.myUid())
				== PackageManager.PERMISSION_GRANTED) {
			activityPermissionsCallbacks["$localClassName#$permission"]?.invoke()
		} else {
			requestPermissions(arrayOf(permission), REQUEST_PERMISSION_CODE)
		}
	}

	/**
	 * If you use Activity.runWithPermissions to request permission,
	 * please call this method in onRequestPermissionsResult method of your activity
	 *
	 * @param requestCode The result of request code
	 * @param permissions Permissions string array
	 * @param grantResults Permissions result array
	 * @param deniedCallback Denied permission callback
	 */
	fun Activity.handleOnRequestPermissionsResult(requestCode: Int,
	                                              permissions: Array<out String>,
	                                              grantResults: IntArray,
	                                              deniedCallback: ((deniedPermission: String) -> Unit)?) {
		permissions.forEachIndexed { index, permission ->
			if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
				activityPermissionsCallbacks["$localClassName#$permission"]?.invoke()
			} else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
				deniedCallback?.invoke(permission)
			}
		}
	}

	// Kotlin-style builders

	/**
	 * Build AlertDialog in Activity
	 *
	 * @param process The process of building AlertDialog
	 * @see android.app.AlertDialog
	 */
	fun Activity.buildAlertDialog(process: AlertDialog.Builder.() -> Unit) : AlertDialog {
		val builder = AlertDialog.Builder(this)
		builder.process()
		return builder.create()
	}

	var AlertDialog.Builder.title : String
		get() { throw NoSuchMethodException("Title getter is not supported")}
		set(value) { this.setTitle(value) }

	var AlertDialog.Builder.titleRes : Int
		get() { throw NoSuchMethodException("Title res id getter is not supported")}
		set(value) { this.setTitle(value) }

	var AlertDialog.Builder.message : String
		get() { throw NoSuchMethodException("Message getter is not supported")}
		set(value) { this.setMessage(value) }

	var AlertDialog.Builder.messageRes : Int
		get() { throw NoSuchMethodException("Message res id getter is not supported")}
		set(value) { this.setMessage(value) }

	var AlertDialog.Builder.isCancelable : Boolean
		get() { throw NoSuchMethodException("isCancelable getter is not supported")}
		set(value) { this.setCancelable(value) }

	var AlertDialog.Builder.customTitle : View
		get() { throw NoSuchMethodException("Custom title getter is not supported")}
		set(value) { this.setCustomTitle(value) }

	var AlertDialog.Builder.icon : Drawable
		get() { throw NoSuchMethodException("Icon getter is not supported")}
		set(value) { this.setIcon(value) }

	var AlertDialog.Builder.iconRes : Int
		get() { throw NoSuchMethodException("Icon res id getter is not supported")}
		set(value) { this.setIcon(value) }

	var AlertDialog.Builder.iconAttribute : Int
		get() { throw NoSuchMethodException("Icon attribute getter is not supported")}
		set(value) { this.setIconAttribute(value) }

	var AlertDialog.Builder.onCancel : (DialogInterface) -> Unit
		get() { throw NoSuchMethodException("OnCancelListener getter is not supported")}
		set(value) { this.setOnCancelListener(value) }

	var AlertDialog.Builder.onDismiss : (DialogInterface) -> Unit
		get() { throw NoSuchMethodException("OnDismissListener getter is not supported")}
		set(value) { this.setOnDismissListener(value) }

	var AlertDialog.Builder.onKey : DialogInterface.OnKeyListener
		get() { throw NoSuchMethodException("OnKeyListener getter is not supported")}
		set(value) { this.setOnKeyListener(value) }

	var AlertDialog.Builder.onItemSelected : AdapterView.OnItemSelectedListener
		get() { throw NoSuchMethodException("OnItemSelectedListener getter is not supported")}
		set(value) { this.setOnItemSelectedListener(value) }

	var AlertDialog.Builder.view : View
		get() { throw NoSuchMethodException("View getter is not supported")}
		set(value) { this.setView(value) }

	var AlertDialog.Builder.viewRes : Int
		get() { throw NoSuchMethodException("View res id getter is not supported")}
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
	fun AlertDialog.Builder.positiveButton(textId: Int, onClick: (DialogInterface, Int) -> Unit) {
		setPositiveButton(textId, onClick)
	}

	/**
	 * Set positive button for AlertDialog
	 *
	 * @param text Text string
	 * @param onClick onClick callback
	 */
	fun AlertDialog.Builder.positiveButton(text: String, onClick: (DialogInterface, Int) -> Unit) {
		setPositiveButton(text, onClick)
	}

	/**
	 * Set negative button for AlertDialog
	 *
	 * @param textId Text resource id
	 * @param onClick onClick callback
	 */
	fun AlertDialog.Builder.negativeButton(textId: Int, onClick: (DialogInterface, Int) -> Unit) {
		setNegativeButton(textId, onClick)
	}

	/**
	 * Set negative button for AlertDialog
	 *
	 * @param text Text string
	 * @param onClick onClick callback
	 */
	fun AlertDialog.Builder.negativeButton(text: String, onClick: (DialogInterface, Int) -> Unit) {
		setNegativeButton(text, onClick)
	}

	/**
	 * Set neutral button for AlertDialog
	 *
	 * @param textId Text resource id
	 * @param onClick onClick callback
	 */
	fun AlertDialog.Builder.neutralButton(textId: Int, onClick: (DialogInterface, Int) -> Unit) {
		setNeutralButton(textId, onClick)
	}

	/**
	 * Set neutral button for AlertDialog
	 *
	 * @param text Text string
	 * @param onClick onClick callback
	 */
	fun AlertDialog.Builder.neutralButton(text: String, onClick: (DialogInterface, Int) -> Unit) {
		setNeutralButton(text, onClick)
	}

	fun <T : Dialog> T.afterViewCreated(block: (T) -> Unit) : T {
		this.setOnShowListener { block(this) }
		return this
	}

	val AlertDialog.positiveButton : Button
		get() = this.getButton(DialogInterface.BUTTON_POSITIVE)

	val AlertDialog.negativeButton : Button
		get() = this.getButton(DialogInterface.BUTTON_NEGATIVE)

	val AlertDialog.neutralButton : Button
		get() = this.getButton(DialogInterface.BUTTON_NEUTRAL)

	companion object {

		private val activityPermissionsCallbacks = hashMapOf<String, () -> Unit>()
		private const val REQUEST_PERMISSION_CODE = 60003

	}

}