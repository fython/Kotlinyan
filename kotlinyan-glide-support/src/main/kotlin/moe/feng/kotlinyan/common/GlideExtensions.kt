package moe.feng.kotlinyan.common

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import moe.feng.kotlinyan.common.glide.R
import java.io.File

/**
 * Glide Extensions
 *
 * Help developers use Glide library to load image async.
 *
 * @see import com.bumptech.glide.Glide
 * @see <a href="https://github.com/fython/Kotlinyan/wiki/GlideExtensions">GlideExtensions Wiki</a>
 */
interface GlideExtensions {

	var ImageView.placeholderResource : Int?
		get() { return this.getTag(R.id.tag_glide_placeholder_res) as? Int }
		set(value) {
			this.setTag(R.id.tag_glide_placeholder_drawable, null)
			this.setTag(R.id.tag_glide_placeholder_res, value)
		}

	var ImageView.placeholder : Drawable?
		get() { return this.getTag(R.id.tag_glide_placeholder_drawable) as? Drawable }
		set(value) {
			this.setTag(R.id.tag_glide_placeholder_drawable, value)
			this.setTag(R.id.tag_glide_placeholder_res, 0)
		}

	var ImageView.glideListener : RequestListener<*, Drawable>?
		get() { return this.getTag(R.id.tag_request_listener) as? RequestListener<*, Drawable> }
		set(value) { this.setTag(R.id.tag_request_listener, value) }

	var ImageView.loadUrl : String?
		get() { return this.getTag(R.id.tag_loading_url) as? String }
		set(value) {
			if (value == null) return
			this.setTag(R.id.tag_loading_url, value)
			val request = Glide.with(this.context).load(value)
			this.makeGlideRequest(request)
		}

	var ImageView.loadUri : Uri?
		get() { return this.getTag(R.id.tag_loading_uri) as? Uri }
		set(value) {
			if (value == null) return
			this.setTag(R.id.tag_loading_uri, value)
			val request = Glide.with(this.context).load(value)
			this.makeGlideRequest(request)
		}

	var ImageView.loadFile : File?
		get() { return File(this.getTag(R.id.tag_loading_url) as? String) }
		set(value) {
			if (value == null) return
			this.setTag(R.id.tag_loading_url, value.absolutePath)
			val request = Glide.with(this.context).load(value)
			this.makeGlideRequest(request)
		}

	/**
	 * Set common options of requests
	 *
	 * @param transformer Methods to modify request options
	 */
	fun ImageView.glideBuilderTransform(transformer: GenericRequestBuilder<*, *, *, *>.() -> Unit) {
		setTag(R.id.tag_glide_builder_transformer, transformer)
	}

	private fun <A, B, C, D> ImageView.makeGlideRequest(request: GenericRequestBuilder<A, B, C, D>) {
		if (placeholderResource != null && placeholderResource != 0) request.placeholder(placeholderResource!!)
		if (placeholder != null) request.placeholder(placeholder)
		if (glideListener != null) request.listener(glideListener as RequestListener<A, D>)
		val transformer = getTag(R.id.tag_glide_builder_transformer) as? GenericRequestBuilder<A, B, C, D>.() -> Unit
		transformer?.let { it(request) }
		request.into(this)
	}

}