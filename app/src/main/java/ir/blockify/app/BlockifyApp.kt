package ir.blockify.app

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.multidex.MultiDexApplication
import java.net.InetAddress

class BlockifyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    fun isNetworkConnected(): Boolean {
        val cm = INSTANCE.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr = InetAddress.getByName("google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }

    fun showToast(text: String) {
        Toast.makeText(INSTANCE, text, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    fun getNavigationBarHeight(context: Context): Int {
        val metrics = DisplayMetrics()
        context.display!!.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        context.display!!.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        return if (realHeight > usableHeight) realHeight - usableHeight else 0
    }

    fun isDarkTheme(): Boolean {
        val currentNightMode =
            INSTANCE.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

    fun addSystemWindowInsetToPadding(
        view: View?, left: Boolean, top: Boolean, right: Boolean,
        bottom: Boolean
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view!!) { v: View, windowInsets: WindowInsetsCompat ->
            val insets =
                windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val paddingLeft: Int
            val paddingTop: Int
            val paddingRight: Int
            val paddingBottom: Int
            paddingLeft = if (left) {
                insets.left
            } else {
                0
            }
            paddingTop = if (top) {
                insets.top
            } else {
                0
            }
            paddingRight = if (right) {
                insets.right
            } else {
                0
            }
            paddingBottom = if (bottom) {
                insets.bottom
            } else {
                0
            }
            v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            windowInsets
        }
    }

    fun addSystemWindowInsetToMargin(
        view: View?, left: Boolean, top: Boolean, right: Boolean,
        bottom: Boolean
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view!!) { v: View, windowInsets: WindowInsetsCompat ->
            val insets =
                windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            val marginLeft: Int
            val marginTop: Int
            val marginRight: Int
            val marginBottom: Int
            marginLeft = if (left) {
                insets.left
            } else {
                0
            }
            marginTop = if (top) {
                insets.top
            } else {
                0
            }
            marginRight = if (right) {
                insets.right
            } else {
                0
            }
            marginBottom = if (bottom) {
                insets.bottom
            } else {
                0
            }
            if (v.layoutParams is MarginLayoutParams) {
                val p = v.layoutParams as MarginLayoutParams
                p.setMargins(marginLeft, marginTop, marginRight, marginBottom)
                v.requestLayout()
            }
            windowInsets
        }
    }


    companion object {
        lateinit var INSTANCE: BlockifyApp
    }
}
