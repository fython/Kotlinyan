package moe.feng.kotlinyan.common

import android.app.AlertDialog
import android.app.Fragment

interface FragmentExtensions: ActivityExtensions {

	fun Fragment.buildAlertDialog(process: AlertDialog.Builder.() -> Unit) = activity.buildAlertDialog(process)

}