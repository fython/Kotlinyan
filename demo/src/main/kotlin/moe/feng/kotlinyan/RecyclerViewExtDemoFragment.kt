package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import moe.feng.kotlinyan.common.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.fragmentUiThread
import org.jetbrains.anko.support.v4.onRefresh

class RecyclerViewExtDemoFragment : Fragment() {

	lateinit var refreshLayout : SwipeRefreshLayout
	lateinit var recyclerView : RecyclerView

	private val adapter = SimpleAdapter(30)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_recyclerview_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		refreshLayout = view[R.id.refresh_layout] as SwipeRefreshLayout
		recyclerView = view[R.id.recycler_view] as RecyclerView

		refreshLayout.setColorSchemeColors(resources.color[R.color.colorAccent])
		refreshLayout.onRefresh {
			doAsync {
				Thread.sleep(1000)
				adapter.count = 15
				fragmentUiThread {
					adapter.notifyDataSetChanged()
					refreshLayout.isRefreshing = false
				}
			}
		}
		recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3, GridLayoutManager.VERTICAL, false)
		recyclerView.adapter = adapter

		// Grid SpanSizeLookUp
		recyclerView.applySpanSize { it % 2 + 1 }

		// Load more callback
		recyclerView.onLoadMore {
			if (refreshLayout.isRefreshing) return@onLoadMore
			refreshLayout.isRefreshing = true
			doAsync {
				Thread.sleep(1000)
				adapter.count += 20
				fragmentUiThread {
					adapter.notifyDataSetChanged()
					refreshLayout.isRefreshing = false
				}
			}
		}
	}

	private class SimpleAdapter(var count : Int) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

		override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
			holder?.textView?.text = "Item %d".format(position)
		}

		override fun getItemCount() = count

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
			= ViewHolder(parent.inflateView(R.layout.simple_list_item_1))

		class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

			val textView by lazy { itemView[R.id.text] as TextView }

		}

	}

}