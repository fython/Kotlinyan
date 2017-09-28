package moe.feng.kotlinyan

import android.Manifest
import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import moe.feng.kotlinyan.common.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), PermissionActivity {

	private val tabLayout: TabLayout by lazyFindNonNullView(R.id.tab_layout)
	private val viewPager: ViewPager by lazyFindNonNullView(R.id.view_pager)

	private val testSettings = getSharedPreferencesProvider<TestSettings>()

	private val TAG = MainActivity::class.java.simpleName

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		supportActionBar?.elevation = 0f

		viewPager.adapter = PagerAdapter(fragmentManager)
		tabLayout.setupWithViewPager(viewPager)

		Log.i(TAG, "TestSettings: a=${testSettings.a} b=${testSettings.b} c=${testSettings.c} d=${testSettings.d} e=${testSettings.e}")
		testSettings.a += "1"
		if (testSettings.a!!.length > 10) testSettings.a = "Test"
		testSettings.b += 1
		testSettings.c = !testSettings.c
		testSettings.d += .001F
		testSettings.e += 1
		Log.i(TAG, "TestSettings: a=${testSettings.a} b=${testSettings.b} c=${testSettings.c} d=${testSettings.d} e=${testSettings.e}")
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_main, menu)

		// Tint menu items easily
		menu?.tintItemsColor(resources.color[R.color.grey_material_control])

		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_test_permission -> {
				// Permission Utils Sample
				runWithPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
					// Do something...
					toast("Permission granted")
				} ?: run {
					toast("Permission should show rationale.")
					buildAlertDialog {
						title = "Permission Denied"
						message = "Without storage permission, you cannot read or save files."
						positiveButton("Request") {
							_, _ -> continueRunWithPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
						}
						negativeButton(android.R.string.cancel) { _, _ -> }
					}.show()
				}
			}
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		handleOnRequestPermissionsResult(requestCode, permissions, grantResults) {
			deniedPermission -> toast("onPermissionDenied: $deniedPermission")
		}
	}

	private class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

		val items = arrayOf<Pair<String, Fragment>>(
				"ViewExtension" to ViewExtDemoFragment(),
				"Activity" to ActivityExtDemoFragment(),
				"Network" to NetworkExtDemoFragment(),
				"Picasso" to PicassoExtDemoFragment(),
				"Glide" to GlideExtDemoFragment(),
				"RecyclerView" to RecyclerViewExtDemoFragment()
		)

		override fun getPageTitle(position: Int): CharSequence = items[position].first

		override fun getItem(position: Int): Fragment = items[position].second

		override fun getCount(): Int = items.size

	}

}