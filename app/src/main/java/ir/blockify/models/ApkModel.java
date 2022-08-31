package ir.blockify.models;

import android.graphics.drawable.*;
import android.content.*;

import ir.blockify.utils.FileWareUtils;

public class ApkModel extends FileWareUtils {

    private final Drawable drawable;
    private final String name;
    private final String version;
    private final String pkg;

    public ApkModel(Context c, String path) {
        //if app target sdk version is 30 or more than this will never work
        //most of code are from different websites and projects , thanks for every hand helped me , and it has been modified by me hardly to make it work

        int targetSdkVersion = c.getApplicationContext().getApplicationInfo().targetSdkVersion;

        if (targetSdkVersion >= 30) {
            throw new RuntimeException(new Exception("android 11 sdk 30 is not supported"));
        } else {
            android.content.pm.PackageInfo pckgInfo = c.getPackageManager().getPackageArchiveInfo(path, 0);
            android.content.pm.ApplicationInfo ai = pckgInfo.applicationInfo;
            ai.sourceDir = path;
            ai.publicSourceDir = path;
            drawable = c.getPackageManager().getApplicationIcon(pckgInfo.applicationInfo);
            name = "" + c.getPackageManager().getApplicationLabel(pckgInfo.applicationInfo);
            version = pckgInfo.versionName;
            pkg = pckgInfo.packageName;
        }
    }

    public Drawable getIcon() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getPackage() {
        return pkg;
    }
}
