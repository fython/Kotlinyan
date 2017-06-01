package moe.feng.kotlinyan.common

import android.graphics.Color

interface ColorExtensions {

	companion object {

		private val materialColors = arrayListOf(
				0xffe57373,
				0xfff06292,
				0xffba68c8,
				0xff9575cd,
				0xff7986cb,
				0xff64b5f6,
				0xff4fc3f7,
				0xff4dd0e1,
				0xff4db6ac,
				0xff81c784,
				0xffaed581,
				0xffff8a65,
				0xffd4e157,
				0xffffd54f,
				0xffffb74d,
				0xffa1887f,
				0xff90a4ae
		)

	}

	private fun Int.getMiddleValue(next: Int, factor: Float) = Math.round(this + (this - next) * factor)

	fun Int.applyToMiddleColor(curColor: Int, factor: Float) : Int {
		if (this == curColor) return curColor

		if (factor == 0f)
			return this
		else if (factor == 1f)
			return curColor

		val a = Color.alpha(this).getMiddleValue(Color.alpha(curColor), factor)
		val r = Color.red(this).getMiddleValue(Color.red(curColor), factor)
		val g = Color.green(this).getMiddleValue(Color.green(curColor), factor)
		val b = Color.blue(this).getMiddleValue(Color.blue(curColor), factor)

		return Color.argb(a, r, g, b)
	}

	fun Int.applyColorAlpha(alphaPercent: Float) : Int {
		val alpha = Math.round(Color.alpha(this) * alphaPercent)
		return this and 0x00FFFFFF or (alpha shl 24)
	}

	fun Any.generateRandomColor()
		= materialColors[Math.abs(this.hashCode()) % materialColors.size]

}