package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import moe.feng.kotlinyan.common.AndroidExtensions
import moe.feng.kotlinyan.common.ViewExtensions
import org.jetbrains.anko.sdk25.coroutines.onClick

class ViewExtDemoFragment : Fragment(), ViewExtensions, AndroidExtensions {

	lateinit var keyboardTestEdit : EditText

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_view_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		keyboardTestEdit = view[R.id.keyboard_test_edit] as EditText
		view[R.id.show_keyboard].onClick { keyboardTestEdit.showKeyboard() }
		view[R.id.hide_keyboard].onClick { keyboardTestEdit.hideKeyboard() }

		(view[R.id.edit_px_to_dp] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_px_to_dp] as TextView).text =
					resources.string[R.string.px_to_dp_text]!!.format(from, from.pxToDp(activity))
		}

		(view[R.id.edit_dp_to_px] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_dp_to_px] as TextView).text =
					resources.string[R.string.dp_to_px_text]!!.format(from, from.dpToPx(activity))
		}

		(view[R.id.edit_px_to_sp] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_px_to_sp] as TextView).text =
					resources.string[R.string.px_to_sp_text]!!.format(from, from.pxToSp(activity))
		}

		(view[R.id.edit_sp_to_px] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_sp_to_px] as TextView).text =
					resources.string[R.string.sp_to_px_text]!!.format(from, from.spToPx(activity))
		}
	}

	private fun EditText.onTextChanged(method: (CharSequence) -> Unit) {
		this.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {}
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { method(s) }
		})
	}

}