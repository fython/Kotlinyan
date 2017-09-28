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
import moe.feng.kotlinyan.common.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ViewExtDemoFragment : Fragment() {

	private val keyboardTestEdit: EditText by findNonNullView(R.id.keyboard_test_edit)

	private val pxToDpFormat by stringRes(R.string.px_to_dp_text)
	private val dpToPxFormat by stringRes(R.string.dp_to_px_text)
	private val pxToSpFormat by stringRes(R.string.px_to_sp_text)
	private val spToPxFormat by stringRes(R.string.sp_to_px_text)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_view_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view[R.id.show_keyboard].onClick { keyboardTestEdit.showKeyboard() }
		view[R.id.hide_keyboard].onClick { keyboardTestEdit.hideKeyboard() }

		(view[R.id.edit_px_to_dp] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_px_to_dp] as TextView).text = pxToDpFormat.format(from, from.pxToDp(activity))
		}

		(view[R.id.edit_dp_to_px] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_dp_to_px] as TextView).text = dpToPxFormat.format(from, from.dpToPx(activity))
		}

		(view[R.id.edit_px_to_sp] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_px_to_sp] as TextView).text = pxToSpFormat.format(from, from.pxToSp(activity))
		}

		(view[R.id.edit_sp_to_px] as EditText).onTextChanged {
			val from = it.toString().toFloatOrNull() ?: 0F
			(view[R.id.result_sp_to_px] as TextView).text = spToPxFormat.format(from, from.spToPx(activity))
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