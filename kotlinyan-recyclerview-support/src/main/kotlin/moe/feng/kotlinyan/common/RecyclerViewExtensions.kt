package moe.feng.kotlinyan.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * RecyclerView Extensions
 *
 * @see android.support.v7.widget.RecyclerView
 * @see <a href="https://github.com/fython/Kotlinyan/wiki/RecyclerViewExtensions">RecyclerViewExtensions Wiki</a>
 */
interface RecyclerViewExtensions {

	/**
	 * When RecyclerView should load more contents, action will be done.
	 *
	 * @param action What should be done when it need to load more
	 */
	fun RecyclerView.onLoadMore(action: () -> Unit) {
		this.addOnScrollListener(OnLoadMoreListener(object: OnLoadMoreListener.Callback {
			override fun onLoadMore() {
				action.invoke()
			}
		}))
	}

	/**
	 * Set GirdLayoutManager.SpanSizeLookup
	 *
	 * @param transform Transform position index to item width
	 */
	fun RecyclerView.applySpanSize(transform: (position: Int) -> Int) {
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