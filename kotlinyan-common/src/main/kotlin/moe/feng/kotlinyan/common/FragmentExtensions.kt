package moe.feng.kotlinyan.common

import android.app.AlertDialog
import android.app.Fragment

/**
 * Build AlertDialog in Fragment
 *
 * @param process The process of building AlertDialog
 * @see android.app.AlertDialog
 */
fun Fragment.buildAlertDialog(process: AlertDialog.Builder.() -> Unit) = activity.buildAlertDialog(process)