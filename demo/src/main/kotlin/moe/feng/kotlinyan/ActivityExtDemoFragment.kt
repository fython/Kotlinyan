package moe.feng.kotlinyan

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.feng.kotlinyan.common.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class ActivityExtDemoFragment : Fragment() {

	companion object {

		val TAG = ActivityExtDemoFragment::class.simpleName

	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_activity_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view[R.id.btn_alertdialog_native].onClick {
			buildAlertDialog {
				title = "Test"
				message = "Hello nyan! Here is Kotlinyan demo."
				positiveButton(android.R.string.ok) { _, _ ->
					toast("PositiveButton Clicked!")
				}
				negativeButton(android.R.string.cancel) { _, _ ->
					toast("NegativeButton Clicked!")
				}
			}.afterViewCreated {
				it.positiveButton.filterTouchesWhenObscured = true
			}.show()
		}
		view[R.id.btn_alertdialog_v7].onClick {
			buildV7AlertDialog {
				title = "Test"
				message = "Hello nyan! Here is Kotlinyan demo."
				isCancelable = true
				positiveButton(android.R.string.ok) { _, _ ->
					toast("PositiveButton Clicked!")
				}
				negativeButton(android.R.string.cancel) { _, _ ->
					toast("NegativeButton Clicked!")
				}
				onCancel = {

				}
				onDismiss = {

				}
			}.afterViewCreated {
				it.positiveButton.filterTouchesWhenObscured = true
			}.show()
		}
		view[R.id.btn_show_snackbar].onClick {
			view[R.id.root_layout].snackbar {
				message = "Here is Snackbar"
				duration = Snackbar.LENGTH_SHORT
				action(android.R.string.ok) {
					toast("OK!")
				}
			}.show()
		}
		view[R.id.btn_test_intent].onClick {
			val intent = Intent()
			intent["intExtra"] = 15
			intent["stringExtra"] = "Hello"
			Log.i(TAG, "intExtra=" + (intent["intExtra"]?.asInt() ?: -1))
			Log.i(TAG, "stringExtra=" + (intent["stringExtra"]?.asString() ?: "defaultValue"))
			Log.i(TAG, "nonExistExtra=" + (intent["nonExistExtra"]?.asString() ?: "defaultValue"))
		}
		view[R.id.btn_test_bundle].onClick {
			val bundle = Bundle()
			bundle["int"] = 18
			bundle["string"] = "Nyanyanyanyan"
			bundle["stringArray"] = arrayOf("test", "lalala")
			Log.i(TAG, "intExtra=" + (bundle["int"] as? Int ?: -1))
			Log.i(TAG, "string=" + (bundle["string"] as? String ?: "defaultValue"))
			Log.i(TAG, "stringArray=" +
					((bundle["stringArray"] as? Array<String>)?.reduce { a, b -> "$a,$b" } ?: "defaultValue"))
			Log.i(TAG, "nonExist=" + (bundle["nonExist"] as? String ?: "defaultValue"))
		}
	}

}