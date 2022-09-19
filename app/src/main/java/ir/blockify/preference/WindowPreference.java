package ir.blockify.preference;

import static android.graphics.Color.TRANSPARENT;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static android.view.View.SYSTEM_UI_FLAG_VISIBLE;
import static com.google.android.material.color.MaterialColors.isColorLight;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.Window;

import androidx.core.graphics.ColorUtils;

import com.google.android.material.color.MaterialColors;

public class WindowPreference {

    private static final String PREFERENCES_NAME = "ir.blockify_preferences";
    private static final String KEY_EDGE_TO_EDGE_ENABLED = "edge_to_edge_enabled";
    private static final int EDGE_TO_EDGE_BAR_ALPHA = 128;

    private static final int EDGE_TO_EDGE_FLAGS = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

    private final Context context;

    public WindowPreference(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ApplySharedPref")
    public void toggleEdgeToEdgeEnabled() {
        getSharedPreferences().edit().putBoolean(KEY_EDGE_TO_EDGE_ENABLED, !isEdgeToEdgeEnabled()).commit();
    }

    public boolean isEdgeToEdgeEnabled() {
        return getSharedPreferences().getBoolean(KEY_EDGE_TO_EDGE_ENABLED, VERSION.SDK_INT >= VERSION_CODES.Q);
    }

    @SuppressWarnings("RestrictTo")
    public void applyEdgeToEdgePreference(Window window, int color) {
        boolean edgeToEdgeEnabled = isEdgeToEdgeEnabled();

        int statusBarColor = getStatusBarColor(isEdgeToEdgeEnabled());
        int navbarColor = getNavBarColor(isEdgeToEdgeEnabled());

        boolean lightBackground = isColorLight(
            MaterialColors.getColor(context, android.R.attr.colorBackground, Color.BLACK));
        boolean showDarkStatusBarIcons = isColorLight(color);
        boolean lightNavbar = isColorLight(navbarColor);
        boolean showDarkNavbarIcons = lightNavbar || (navbarColor == TRANSPARENT && lightBackground);

        View decorView = window.getDecorView();
        int currentStatusBar = showDarkStatusBarIcons
            ? SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            : 0;
        int currentNavBar = showDarkNavbarIcons
            ? SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            : 0;

        window.setNavigationBarColor(navbarColor);
        window.setStatusBarColor(statusBarColor);
        int systemUiVisibility = (edgeToEdgeEnabled ? EDGE_TO_EDGE_FLAGS : SYSTEM_UI_FLAG_VISIBLE) | currentStatusBar
            | currentNavBar;

        decorView.setSystemUiVisibility(systemUiVisibility);
    }

    @SuppressWarnings("RestrictTo")
    private int getStatusBarColor(boolean isEdgeToEdgeEnabled) {
        if (isEdgeToEdgeEnabled) {
            return TRANSPARENT;
        }
        return MaterialColors.getColor(context, android.R.attr.statusBarColor, Color.BLACK);
    }

    @SuppressWarnings("RestrictTo")
    private int getNavBarColor(boolean isEdgeToEdgeEnabled) {
        if (isEdgeToEdgeEnabled && VERSION.SDK_INT < VERSION_CODES.O_MR1) {
            int opaqueNavBarColor = MaterialColors.getColor(context, android.R.attr.navigationBarColor, Color.BLACK);
            return ColorUtils.setAlphaComponent(opaqueNavBarColor, EDGE_TO_EDGE_BAR_ALPHA);
        }
        if (isEdgeToEdgeEnabled) {
            return TRANSPARENT;
        }
        return MaterialColors.getColor(context, android.R.attr.navigationBarColor, Color.BLACK);
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
