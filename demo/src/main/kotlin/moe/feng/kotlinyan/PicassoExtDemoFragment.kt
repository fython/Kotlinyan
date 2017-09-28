package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import moe.feng.kotlinyan.common.*
import moe.feng.kotlinyan.picasso.enableMaterialPicassoAnimation
import moe.feng.kotlinyan.picasso.loadUrl
import moe.feng.kotlinyan.picasso.picassoRequestTransform
import moe.feng.kotlinyan.picasso.placeholderResource
import org.jetbrains.anko.sdk25.coroutines.onClick

class PicassoExtDemoFragment : Fragment() {

	private val imageView: ImageView by findNonNullView(R.id.image_lazy_load)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_picasso_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		imageView.enableMaterialPicassoAnimation()
		imageView.placeholderResource = R.mipmap.ic_launcher
		imageView.picassoRequestTransform {
			memoryPolicy(MemoryPolicy.NO_CACHE)
		}
		imageView.loadUrl = "http://wx1.sinaimg.cn/large/6f76b6dagy1fg5rg39y07j20r80hskbd.jpg"

		view[R.id.btn_1].onClick {
			imageView.loadUrl = "http://wx1.sinaimg.cn/large/6f76b6dagy1fg5rg39y07j20r80hskbd.jpg"
		}
		view[R.id.btn_2].onClick {
			imageView.loadUrl = "http://static.zerochan.net/Sawamura.Spencer.Eriri.full.1850259.jpg"
		}
		view[R.id.btn_3].onClick {
			imageView.loadUrl = "http://static.zerochan.net/Akatsuki.%28Kantai.Collection%29.full.2098696.jpg"
		}
	}

}