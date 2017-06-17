package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.feng.kotlinyan.common.AndroidExtensions
import moe.feng.kotlinyan.common.AppCompatExtensions
import moe.feng.kotlinyan.common.SupportDesignExtensions
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class ActivityExtDemoFragment : Fragment(), AndroidExtensions, AppCompatExtensions, SupportDesignExtensions {

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
	}

}