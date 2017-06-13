package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.feng.kotlinyan.common.AndroidExtensions
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class ActivityExtDemoFragment : Fragment(), AndroidExtensions {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_activity_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view[R.id.btn_build_and_show].onClick {
			buildAlertDialog {
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
			}.show()
		}
	}

}