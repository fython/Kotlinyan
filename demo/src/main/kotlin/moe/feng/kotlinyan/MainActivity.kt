package moe.feng.kotlinyan

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import moe.feng.kotlinyan.common.AndroidExtensions
import moe.feng.kotlinyan.common.ColorExtensions
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity(), AndroidExtensions, ColorExtensions {

	val tabLayout by lazy { find<TabLayout>(R.id.tab_layout) }
	val viewPager by lazy { find<ViewPager>(R.id.view_pager) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		supportActionBar?.elevation = 0f

		viewPager.adapter = PagerAdapter(fragmentManager)
		tabLayout.setupWithViewPager(viewPager)
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)

		/**
		 * Tint menu items easily
		 */
		menu?.tintItemsColor(resources.color[R.color.grey_material_control])

		return super.onCreateOptionsMenu(menu)
	}

	private class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

		val items = arrayOf<Pair<String, Fragment>>(
				"ViewExtension" to ViewExtDemoFragment(),
				"NetworkExtension" to NetworkExtDemoFragment(),
				"PicassoExtension" to PicassoExtDemoFragment()
		)

		override fun getPageTitle(position: Int): CharSequence = items[position].first

		override fun getItem(position: Int): Fragment = items[position].second

		override fun getCount(): Int = items.size

	}

}