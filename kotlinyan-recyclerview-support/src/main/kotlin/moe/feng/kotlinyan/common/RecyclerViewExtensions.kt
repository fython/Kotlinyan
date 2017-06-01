package moe.feng.kotlinyan.common

import android.support.v7.widget.RecyclerView

interface RecyclerViewExtensions {

	fun RecyclerView.onLoadMore(action: () -> Unit) {
		this.addOnScrollListener(OnLoadMoreListener(object: OnLoadMoreListener.Callback {
			override fun onLoadMore() {
				action.invoke()
			}
		}))
	}

}