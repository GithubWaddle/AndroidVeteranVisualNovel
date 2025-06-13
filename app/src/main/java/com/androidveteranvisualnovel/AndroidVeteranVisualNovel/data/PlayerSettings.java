package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

/**
 * SettingsFile manages user preferences for the Visual Novel app,
 * including text speed, sound volume, and other configurable settings.
 *
 * Usage:
 *   SettingsFile settings = new SettingsFile(context);
 *   int speed = settings.getTextSpeed();
 *   settings.setTextSpeed(50);
 */
public class PlayerSettings {

    private static final String PREFS_NAME = "visual_novel_settings";

    // Preference keys
    private static final String KEY_TEXT_SPEED = "text_speed";
    private static final String KEY_SOUND_VOLUME = "sound_volume";
    private static final String KEY_MUSIC_VOLUME = "music_volume";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_SKIP_MODE = "skip_mode";
    private static final String KEY_AUTO_PLAY = "auto_play";

    // Default values
    private static final int DEFAULT_TEXT_SPEED = 30;  // milliseconds per character
    private static final int DEFAULT_SOUND_VOLUME = 70; // 0-100 scale
    private static final int DEFAULT_MUSIC_VOLUME = 70;
    private static final String DEFAULT_LANGUAGE = "en";
    private static final boolean DEFAULT_SKIP_MODE = false;
    private static final boolean DEFAULT_AUTO_PLAY = false;

    private final SharedPreferences sharedPreferences;

    /**
     * Constructor for SettingsFile.
     * @param context Application or Activity context
     */
    public PlayerSettings(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public PlayerSettings(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    // Text Speed (milliseconds per character)
    public int getTextSpeed() {
        return sharedPreferences.getInt(KEY_TEXT_SPEED, DEFAULT_TEXT_SPEED);
    }

    public void setTextSpeed(int speed) {
        if (speed < 1) speed = 1; // minimum speed
        sharedPreferences.edit().putInt(KEY_TEXT_SPEED, speed).apply();
    }

    // Sound Effects Volume (0-100)
    public int getSoundVolume() {
        return sharedPreferences.getInt(KEY_SOUND_VOLUME, DEFAULT_SOUND_VOLUME);
    }

    public void setSoundVolume(int volume) {
        if (volume < 0) volume = 0;
        else if (volume > 100) volume = 100;
        sharedPreferences.edit().putInt(KEY_SOUND_VOLUME, volume).apply();
    }

    // Music Volume (0-100)
    public int getMusicVolume() {
        return sharedPreferences.getInt(KEY_MUSIC_VOLUME, DEFAULT_MUSIC_VOLUME);
    }

    public void setMusicVolume(int volume) {
        if (volume < 0) volume = 0;
        else if (volume > 100) volume = 100;
        sharedPreferences.edit().putInt(KEY_MUSIC_VOLUME, volume).apply();
    }

    // Language (e.g., "en", "jp")
    @NonNull
    public String getLanguage() {
        return sharedPreferences.getString(KEY_LANGUAGE, DEFAULT_LANGUAGE);
    }

    public void setLanguage(@NonNull String language) {
        sharedPreferences.edit().putString(KEY_LANGUAGE, language).apply();
    }

    // Skip Mode (boolean)
    public boolean isSkipModeEnabled() {
        return sharedPreferences.getBoolean(KEY_SKIP_MODE, DEFAULT_SKIP_MODE);
    }

    public void setSkipModeEnabled(boolean enabled) {
        sharedPreferences.edit().putBoolean(KEY_SKIP_MODE, enabled).apply();
    }

    // Auto Play Mode (boolean)
    public boolean isAutoPlayEnabled() {
        return sharedPreferences.getBoolean(KEY_AUTO_PLAY, DEFAULT_AUTO_PLAY);
    }

    public void setAutoPlayEnabled(boolean enabled) {
        sharedPreferences.edit().putBoolean(KEY_AUTO_PLAY, enabled).apply();
    }

    /**
     * Resets all settings to default values.
     */
    public void resetToDefaults() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_TEXT_SPEED, DEFAULT_TEXT_SPEED);
        editor.putInt(KEY_SOUND_VOLUME, DEFAULT_SOUND_VOLUME);
        editor.putInt(KEY_MUSIC_VOLUME, DEFAULT_MUSIC_VOLUME);
        editor.putString(KEY_LANGUAGE, DEFAULT_LANGUAGE);
        editor.putBoolean(KEY_SKIP_MODE, DEFAULT_SKIP_MODE);
        editor.putBoolean(KEY_AUTO_PLAY, DEFAULT_AUTO_PLAY);
        editor.apply();
    }
}

