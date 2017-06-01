package moe.feng.kotlinyan.common

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

interface ViewExtensions {

	// Find views

	operator fun View.get(id : Int): View = findViewById(id)
	operator fun View.get(tag : Any): View = findViewWithTag(tag)

	// Toggle keyboard

	/**
	 * Show keyboard
	 */
	fun View.showKeyboard() {
		this.requestFocus()
		val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
	}

	/**
	 * Hide keyboard when the view is focused.
	 */
	fun View.hideKeyboard() {
		val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(windowToken, 0)
	}

	// Visibility Methods

	fun View.makeVisible() {
		visibility = View.VISIBLE
	}

	fun View.makeInvisible() {
		visibility = View.INVISIBLE
	}

	fun View.makeGone() {
		visibility = View.GONE
	}

	// Inflater Methods

	fun ViewGroup.inflateView(idRes: Int): View = LayoutInflater.from(context).inflate(idRes, this, false)

	fun Context.inflateView(idRes: Int): View = LayoutInflater.from(this).inflate(idRes, null)

	// Convert units (dp, px)

	/**
	 * Convert dp to px
	 */
	fun Float.dpToPx(context : Context) = this * context.resources.displayMetrics.density + 0.5f

	/**
	 * Convert px to dp
	 */
	fun Float.pxToDp(context : Context) = this / context.resources.displayMetrics.density + 0.5f

	/**
	 * Convert px to sp
	 */
	fun Float.pxToSp(context: Context) = this / context.resources.displayMetrics.scaledDensity + 0.5f

	/**
	 * Convert sp to px
	 */
	fun Float.spToPx(context: Context) = this * context.resources.displayMetrics.scaledDensity + 0.5f

	/**
	 * Convert dp to px
	 */
	fun Int.dpToPx(context : Context) = this * context.resources.displayMetrics.density + 0.5f

	/**
	 * Convert px to dp
	 */
	fun Int.pxToDp(context : Context) = this / context.resources.displayMetrics.density + 0.5f

	/**
	 * Convert px to sp
	 */
	fun Int.pxToSp(context: Context) = this / context.resources.displayMetrics.scaledDensity + 0.5f

	/**
	 * Convert sp to px
	 */
	fun Int.spToPx(context: Context) = this * context.resources.displayMetrics.scaledDensity + 0.5f

	/**
	 * Get status bar height (px)
	 */
	fun Context.getStatusBarHeight() : Int {
		val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId)
		}
		return 0
	}

	// Take screenshots for views

	/**
	 * Take screenshot of current view
	 */
	fun View.takeScreenshot() : Bitmap {
		val originState = this.isDrawingCacheEnabled
		this.isDrawingCacheEnabled = true
		this.buildDrawingCache(true)

		val b = Bitmap.createBitmap(this.drawingCache)
		this.isDrawingCacheEnabled = originState
		return b
	}

	/**
	 * Take screenshot of root view.
	 */
	fun View.takeScreenshotOfRootView() = this.rootView.takeScreenshot()

	/**
	 * Take screenshot of current view without any constrains
	 */
	fun View.takeScreenshotWithoutConstrains() : Bitmap {
		this.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
		this.layout(0, 0, this.measuredWidth, this.measuredHeight)
		return this.takeScreenshot()
	}

}