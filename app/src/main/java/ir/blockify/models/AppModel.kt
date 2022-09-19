package ir.blockify.models

import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import ir.blockify.app.BlockifyApp
import ir.blockify.utils.FileWareUtils

class AppModel(path: String) : FileWareUtils() {
    init {
        // if app target sdk version is 30 or more than this will never work
        // most of code are from different websites and projects , thanks for every hand helped me , and it has been modified by me hardly to make it work

        val targetSdkVersion: Int =
            BlockifyApp.INSTANCE.applicationContext.applicationInfo.targetSdkVersion

        if (targetSdkVersion >= 30)
            throw RuntimeException(Exception("android 11 sdk 30 is not supported"))
    }

    private val packageInfo: PackageInfo? =
        BlockifyApp.INSTANCE.packageManager.getPackageArchiveInfo(path, 0)
    private val ai = packageInfo?.applicationInfo?.apply {
        sourceDir = path
        publicSourceDir = path
    }

    val drawable: Drawable =
        BlockifyApp.INSTANCE.packageManager.getApplicationIcon(packageInfo?.applicationInfo!!)
    val name: CharSequence =
        BlockifyApp.INSTANCE.packageManager.getApplicationLabel(packageInfo?.applicationInfo!!)
    val version = packageInfo?.versionName
    val pkg = packageInfo?.packageName
}
