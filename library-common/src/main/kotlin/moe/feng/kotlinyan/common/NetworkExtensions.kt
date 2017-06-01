package moe.feng.kotlinyan.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException

interface NetworkExtensions {

	// Get network state & type

	fun Context.getNetworkType() : Int {
		val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		return cm.activeNetworkInfo?.type ?: -1
	}

	fun Context.getCurrentNetworkState() : NetworkInfo.State
			= (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.state

	fun Context.getCurrentNetworkType() : Int
			= (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.type

	fun Context.isConnectedNetwork() : Boolean = getCurrentNetworkState() == NetworkInfo.State.CONNECTED

	fun Context.isConnectingNetwork() : Boolean = getCurrentNetworkState() == NetworkInfo.State.CONNECTING

	fun Context.isDisconnectedNetwork() : Boolean = getCurrentNetworkState() == NetworkInfo.State.DISCONNECTED

	fun Context.isMobileNetwork() : Boolean = getCurrentNetworkType() == ConnectivityManager.TYPE_MOBILE

	/**
	 * Get my ip address
	 */
	fun getIpAddress() : String? {
		try {
			var networkInterface: NetworkInterface
			var inetAddress: InetAddress
			val en = NetworkInterface.getNetworkInterfaces()
			while (en.hasMoreElements()) {
				networkInterface = en.nextElement()
				val enumIpAddr = networkInterface.inetAddresses
				while (enumIpAddr.hasMoreElements()) {
					inetAddress = enumIpAddr.nextElement()
					if (!inetAddress.isLoopbackAddress) {
						return inetAddress.hostAddress.toString()
					}
				}
			}
			return null
		} catch (ex: SocketException) {
			ex.printStackTrace()
			return null
		}
	}

}