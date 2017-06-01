package moe.feng.kotlinyan

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.feng.kotlinyan.common.AndroidExtensions
import moe.feng.kotlinyan.common.NetworkExtensions

class NetworkExtDemoFragment : Fragment(), NetworkExtensions, AndroidExtensions {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
		= inflater.inflate(R.layout.fragment_network_ext, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

	}

}