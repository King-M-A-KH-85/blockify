package ir.blockify;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.multidex.MultiDexApplication;

import java.net.InetAddress;
import java.util.Calendar;

import ir.tapsell.sdk.Tapsell;

public class Application extends MultiDexApplication {
    public static Application INSTANCE;
    private static final String TASSEL_KEY = "lsharpqrpmmellkbmmlebnipqmlirlsbltqhbrsjpcspqnohepplhkcrrpenrtdbijapls";

    @Override
    public void onCreate() {
        super.onCreate();
        // create static instance from App
        INSTANCE = this;

        // initialize adds
        Tapsell.initialize(this, TASSEL_KEY);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }

    public static float dipToPixels(float dipValue) {
        DisplayMetrics metrics = INSTANCE.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static void showToast(String message) {
        Toast.makeText(INSTANCE, message, Toast.LENGTH_SHORT).show();
    }

    public static void addSystemWindowInsetToPadding(View view, boolean left, boolean top, boolean right,
                                                     boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int paddingLeft, paddingTop, paddingRight, paddingBottom;
            if (left) {
                paddingLeft = insets.left;
            } else {
                paddingLeft = 0;
            }
            if (top) {
                paddingTop = insets.top;
            } else {
                paddingTop = 0;
            }
            if (right) {
                paddingRight = insets.right;
            } else {
                paddingRight = 0;
            }
            if (bottom) {
                paddingBottom = insets.bottom;
            } else {
                paddingBottom = 0;
            }
            v.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            return windowInsets;
        });
    }

    public static void addSystemWindowInsetToMargin(View view, boolean left, boolean top, boolean right,
                                                    boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int marginLeft, marginTop, marginRight, marginBottom;
            if (left) {
                marginLeft = insets.left;
            } else {
                marginLeft = 0;
            }
            if (top) {
                marginTop = insets.top;
            } else {
                marginTop = 0;
            }
            if (right) {
                marginRight = insets.right;
            } else {
                marginRight = 0;
            }
            if (bottom) {
                marginBottom = insets.bottom;
            } else {
                marginBottom = 0;
            }
            if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                p.setMargins(marginLeft, marginTop, marginRight, marginBottom);
                v.requestLayout();
            }
            return windowInsets;
        });
    }

    public static int getNavigationBarHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        context.getDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight)
            return realHeight - usableHeight;
        else
            return 0;
    }

    public static String greetingText() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 10 && hour < 15) {
            return "Hallo, Selamat Siang!";
        } else if (hour >= 15 && hour < 18) {
            return "Hallo, Selamat Sore!";
        } else if (hour == 18) {
            return "Hallo, Selamat Petang!";
        } else if (hour >= 19 && hour < 24) {
            return "Hallo, Selamat Malam!";
        } else {
            return "Hallo, Selamat Pagi!";
        }
    }

    public static boolean isDarkTheme() {
        int currentNightMode = INSTANCE.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
