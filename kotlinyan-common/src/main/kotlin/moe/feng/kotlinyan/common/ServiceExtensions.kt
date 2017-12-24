package moe.feng.kotlinyan.common

import android.accounts.AccountManager
import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.content.ClipboardManager
import android.content.Context
import android.content.RestrictionsManager
import android.content.pm.LauncherApps
import android.content.pm.ShortcutManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.net.wifi.p2p.WifiP2pManager
import android.nfc.NfcManager
import android.os.*
import android.os.storage.StorageManager
import android.print.PrintManager
import android.support.annotation.RequiresApi
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.inputmethod.InputMethodManager
import android.view.textservice.TextServicesManager

val Context.displayManager: DisplayManager get() = systemServices[Context.DISPLAY_SERVICE]

val Context.powerManager: PowerManager get() = systemServices[Context.POWER_SERVICE]

val Context.windowManager: WindowManager get() = systemServices[Context.WINDOW_SERVICE]

val Context.layoutInflater: LayoutInflater get() = systemServices[Context.LAYOUT_INFLATER_SERVICE]

val Context.accountManager: AccountManager get() = systemServices[Context.ACCOUNT_SERVICE]

val Context.activityManager: ActivityManager get() = systemServices[Context.ACTIVITY_SERVICE]

val Context.alarmManager: AlarmManager get() = systemServices[Context.ALARM_SERVICE]

val Context.notificationManager: NotificationManager get() = systemServices[Context.NOTIFICATION_SERVICE]

val Context.accessibilityManager: AccessibilityManager get() = systemServices[Context.ACCESSIBILITY_SERVICE]

val Context.captioningManager: CaptioningManager get() = systemServices[Context.CAPTIONING_SERVICE]

val Context.keyguardManager: KeyguardManager get() = systemServices[Context.KEYGUARD_SERVICE]

val Context.locationManager: LocationManager get() = systemServices[Context.LOCATION_SERVICE]

val Context.searchManager: SearchManager get() = systemServices[Context.SEARCH_SERVICE]

val Context.sensorManager: SensorManager get() = systemServices[Context.SENSOR_SERVICE]

val Context.storageManager: StorageManager get() = systemServices[Context.STORAGE_SERVICE]

val Context.wallpaperManager: WallpaperManager get() = systemServices[Context.WALLPAPER_SERVICE]

val Context.vibratorManager: Vibrator get() = systemServices[Context.VIBRATOR_SERVICE]

val Context.connectivityManager: ConnectivityManager get() = systemServices[Context.CONNECTIVITY_SERVICE]

val Context.networkStatsManager: NetworkStatsManager get() = systemServices[Context.NETWORK_STATS_SERVICE]

val Context.wifiManager: WifiManager get() = systemServices[Context.WIFI_SERVICE]

val Context.wifiP2pManager: WifiP2pManager get() = systemServices[Context.WIFI_P2P_SERVICE]

val Context.nsdManager: NsdManager get() = systemServices[Context.NSD_SERVICE]

val Context.audioManager: AudioManager get() = systemServices[Context.AUDIO_SERVICE]

val Context.fingerprintManager: FingerprintManager get() = systemServices[Context.FINGERPRINT_SERVICE]

val Context.mediaRouter: MediaRouter get() = systemServices[Context.MEDIA_ROUTER_SERVICE]

val Context.telephonyManager: TelephonyManager get() = systemServices[Context.TELEPHONY_SERVICE]

val Context.telephonySubscriptionManager: SubscriptionManager get() = systemServices[Context.TELEPHONY_SUBSCRIPTION_SERVICE]

val Context.carrierConfigManager: CarrierConfigManager get() = systemServices[Context.CARRIER_CONFIG_SERVICE]

val Context.telecomManager: TelecomManager get() = systemServices[Context.TELECOM_SERVICE]

val Context.clipboardManager: ClipboardManager get() = systemServices[Context.CLIPBOARD_SERVICE]

val Context.inputMethodManager: InputMethodManager get() = systemServices[Context.INPUT_METHOD_SERVICE]

val Context.textServicesManager: TextServicesManager get() = systemServices[Context.TEXT_SERVICES_MANAGER_SERVICE]

val Context.appWidgetManager: AppWidgetManager get() = systemServices[Context.APPWIDGET_SERVICE]

val Context.dropBoxManager: DropBoxManager get() = systemServices[Context.DROPBOX_SERVICE]

val Context.devicePolicyManager: DevicePolicyManager get() = systemServices[Context.DEVICE_POLICY_SERVICE]

val Context.uiModeManager: UiModeManager get() = systemServices[Context.UI_MODE_SERVICE]

val Context.downloadManager: DownloadManager get() = systemServices[Context.DOWNLOAD_SERVICE]

val Context.nfcManager: NfcManager get() = systemServices[Context.NFC_SERVICE]

val Context.bluetoothManager: BluetoothManager get() = systemServices[Context.BLUETOOTH_SERVICE]

val Context.usbManager: UsbManager get() = systemServices[Context.USB_SERVICE]

val Context.launcherApps: LauncherApps get() = systemServices[Context.LAUNCHER_APPS_SERVICE]

val Context.inputManager: InputManager get() = systemServices[Context.INPUT_SERVICE]

val Context.userManager: UserManager get() = systemServices[Context.USER_SERVICE]

val Context.restrictionsManager: RestrictionsManager get() = systemServices[Context.RESTRICTIONS_SERVICE]

val Context.appOpsManager: AppOpsManager get() = systemServices[Context.APP_OPS_SERVICE]

val Context.cameraManager: CameraManager get() = systemServices[Context.CAMERA_SERVICE]

val Context.printManager: PrintManager get() = systemServices[Context.PRINT_SERVICE]

val Context.consumerIrManager: ConsumerIrManager get() = systemServices[Context.CONSUMER_IR_SERVICE]

val Context.tvInputManager: TvInputManager get() = systemServices[Context.TV_INPUT_SERVICE]

val Context.usageStatsManager: UsageStatsManager get() = systemServices[Context.USAGE_STATS_SERVICE]

val Context.mediaSessionManager: MediaSessionManager get() = systemServices[Context.MEDIA_SESSION_SERVICE]

val Context.batteryManager: BatteryManager get() = systemServices[Context.BATTERY_SERVICE]

val Context.jobScheduler: JobScheduler get() = systemServices[Context.JOB_SCHEDULER_SERVICE]

val Context.mediaProjectionManager: MediaProjectionManager get() = systemServices[Context.MEDIA_PROJECTION_SERVICE]

val Context.midiManager: MidiManager get() = systemServices[Context.MIDI_SERVICE]

val Context.hardwarePropertiesManager: HardwarePropertiesManager get() = systemServices[Context.HARDWARE_PROPERTIES_SERVICE]

val Context.shortcutManager: ShortcutManager get() = systemServices[Context.SHORTCUT_SERVICE]

val Context.systemServices: Services get() = Services(this)

// WindowManager Extensions

val WindowManager.screenHeight: Int
	get() = DisplayMetrics().apply { defaultDisplay.getMetrics(this) }.heightPixels

val WindowManager.screenWidth: Int
	get() = DisplayMetrics().apply { defaultDisplay.getMetrics(this) }.widthPixels

val WindowManager.trueScreenHeight: Int
	get() {
		var dpi = 0
		val display = defaultDisplay
		val dm = DisplayMetrics()
		if (Build.VERSION.SDK_INT >= 17) {
			display.getRealMetrics(dm)
			dpi = dm.heightPixels
		} else {
			try {
				val c = Class.forName("android.view.Display")
				val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
				method.invoke(display, dm)
				dpi = dm.heightPixels
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
		return dpi
	}

val WindowManager.trueScreenWidth: Int
	get() {
		var dpi = 0
		val display = defaultDisplay
		val dm = DisplayMetrics()
		if (Build.VERSION.SDK_INT >= 17) {
			display.getRealMetrics(dm)
			dpi = dm.widthPixels
		} else {
			try {
				val c = Class.forName("android.view.Display")
				val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
				method.invoke(display, dm)
				dpi = dm.widthPixels
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
		return dpi
	}

val WindowManager.defaultDisplayMetrics: DisplayMetrics
    get() = DisplayMetrics().apply { defaultDisplay.getMetrics(this) }

val WindowManager.defaultDisplayRealMetrics: DisplayMetrics
    get() = DisplayMetrics().apply { defaultDisplay.getRealMetrics(this) }

operator fun DisplayMetrics.component1(): Int {
    return widthPixels
}

operator fun DisplayMetrics.component2(): Int {
    return heightPixels
}

// BatteryManager Extensions

val BatteryManager.capacity: Int
	@RequiresApi(Build.VERSION_CODES.LOLLIPOP) get() = getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

val BatteryManager.chargeCounter: Int
	@RequiresApi(Build.VERSION_CODES.LOLLIPOP) get() = getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER)

val BatteryManager.currentAverage: Int
	@RequiresApi(Build.VERSION_CODES.LOLLIPOP) get() = getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)

val BatteryManager.currentNow: Int
	@RequiresApi(Build.VERSION_CODES.LOLLIPOP) get() = getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)

val BatteryManager.energyCounter: Long
	@RequiresApi(Build.VERSION_CODES.LOLLIPOP) get() = getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)

class Services(private val context: Context) {

	operator fun <T> get(serviceName: String): T = context.getSystemService(serviceName) as T

}