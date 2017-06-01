package moe.feng.kotlinyan.common

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import moe.feng.kotlinyan.common.picasso.R
import java.io.File

interface PicassoExtensions {

	companion object {
		private val ANIMATION_DURATION : Int = 1000
	}

	var ImageView.picassoCallback : Callback?
		get() { return this.getTag(R.id.tag_picasso_callback) as Callback }
		set(value) { this.setTag(R.id.tag_picasso_callback, value) }

	var ImageView.loadUrl : String
		get() { return this.getTag(R.id.tag_loading_url) as String }
		set(value) {
			this.setTag(R.id.tag_loading_url, value)
			val request = Picasso.with(this.context).load(value)
			if (picassoCallback != null) request.into(this, picassoCallback) else request.into(this)
		}

	var ImageView.loadUri : Uri
		get() { return this.getTag(R.id.tag_loading_uri) as Uri }
		set(value) {
			this.setTag(R.id.tag_loading_uri, value)
			val request = Picasso.with(this.context).load(value)
			if (picassoCallback != null) request.into(this, picassoCallback) else request.into(this)
		}

	var ImageView.loadFile : File
		get() { return File(this.getTag(R.id.tag_loading_url) as String) }
		set(value) {
			this.setTag(R.id.tag_loading_url, value.absolutePath)
			val request = Picasso.with(this.context).load(value)
			if (picassoCallback != null) request.into(this, picassoCallback) else request.into(this)
		}

	fun ImageView.enableMaterialPicassoAnimation() {
		this.picassoCallback = object : Callback {
			override fun onSuccess() {
				MaterialImageLoader.animate(this@enableMaterialPicassoAnimation).setDuration(ANIMATION_DURATION).start()
			}
			override fun onError() {}
		}
	}

	fun RequestCreator.intoMaterialStyle(view : ImageView, callback : Callback? = null) {
		into(view, object : Callback {
			override fun onSuccess() {
				MaterialImageLoader.animate(view).setDuration(ANIMATION_DURATION).start()
				callback?.onSuccess()
			}

			override fun onError() {
				callback?.onError()
			}
		})
	}

}