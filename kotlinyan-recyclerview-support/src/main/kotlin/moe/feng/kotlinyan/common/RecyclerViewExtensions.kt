package moe.feng.kotlinyan.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

interface RecyclerViewExtensions {

	fun RecyclerView.onLoadMore(action: () -> Unit) {
		this.addOnScrollListener(OnLoadMoreListener(object: OnLoadMoreListener.Callback {
			override fun onLoadMore() {
				action.invoke()
			}
		}))
	}

	fun RecyclerView.applySpanSize(transform: (Int) -> Int) {
		val layoutManager = this.layoutManager
		if (layoutManager is GridLayoutManager) {
			layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
				override fun getSpanSize(position: Int): Int = transform(position)
			}
		} else {
			throw IllegalAccessException("Unsupported layout manager. Please use GridLayoutManager instead.")
		}
	}

}