package moe.feng.kotlinyan.common

import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.drawable.Drawable

interface ResourcesExtensions {

	val Resources.animation get() = AnimationResources(this)
	val Resources.boolean get() = BooleanResources(this)
	val Resources.color get() = ColorResources(this)
	val Resources.drawable get() = DrawableResources(this)
	val Resources.dimension get() = DimensionResources(this)
	val Resources.ints get() = IntResources(this)
	val Resources.intArrays get() = IntArrayResources(this)
	val Resources.layout get() = LayoutResources(this)
	val Resources.string get() = StringResources(this)
	val Resources.stringArrays get() = StringArrayResources(this)
	val Resources.text get() = TextResources(this)
	val Resources.quantityString get() = QuantityResources(this)

	class AnimationResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : XmlResourceParser? = res.getAnimation(id)
	}

	class BooleanResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : Boolean = res.getBoolean(id)
	}

	class ColorResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : Int = res.getColor(id)
	}

	class DrawableResources internal constructor(private val res: Resources) {
		operator fun get(id: Int): Drawable? = res.getDrawable(id)
	}

	class DimensionResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : Float = res.getDimension(id)
	}

	class IntArrayResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : IntArray = res.getIntArray(id)
	}

	class IntResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : Int = res.getInteger(id)
	}

	class LayoutResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : XmlResourceParser? = res.getLayout(id)
	}

	class StringResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : String? = res.getString(id)
	}

	class StringArrayResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : Array<String> = res.getStringArray(id)
	}

	class TextResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : CharSequence = res.getText(id)
	}

	class QuantityResources internal constructor(private val res: Resources) {
		operator fun get(id: Int) : (Int) -> String = { quantity -> res.getQuantityString(id, quantity) }
	}

}