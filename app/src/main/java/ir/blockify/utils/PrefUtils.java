package ir.blockify.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import java.util.Objects;

import ir.blockify.Application;

public class PrefUtils {

    private static final String PREFS = "ir.blockify_preferences";
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public PrefUtils(@Nullable String pref) {
		this.preferences = Application.INSTANCE.getSharedPreferences(Objects.requireNonNullElse(pref, PREFS), Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public void saveAs(String tag, boolean value) {
        editor.putBoolean(tag, value).commit();
    }

    public boolean getBoolean(String tag) {
        return preferences.getBoolean(tag, false);
    }

    public void saveAs(String tag, float value) {
        editor.putFloat(tag, value).commit();
    }

    public float getFloat(String tag) {
        return preferences.getFloat(tag, 0);
    }

    public void saveAs(String tag, int value) {
        editor.putInt(tag, value).commit();
    }

    public int getInteger(String tag) {
        return preferences.getInt(tag, 0);
    }

    public void saveAs(String tag, long value) {
        editor.putLong(tag, value).commit();
    }

    public long getLong(String tag) {
        return preferences.getLong(tag, 0);
    }

    public void saveAs(String tag, String value) {
        editor.putString(tag, value).commit();
    }

    public String getString(String tag) {
        return preferences.getString(tag, "");
    }
}