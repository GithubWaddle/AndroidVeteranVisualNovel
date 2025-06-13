package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class PlayerSettingsUnitTest {
    final String TAG = "PlayerSettingsUnitTest";

    @Test
    public void test_set_text_speed() {
        final int MINIMUM_TEXT_SPEED = 0;
        final int MAXIMUM_TEXT_SPEED = 20;
        final int to_text_speed = new Random().nextInt(MAXIMUM_TEXT_SPEED) + 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PlayerSettings playerSettings = new PlayerSettings(appContext.getSharedPreferences("test", Context.MODE_PRIVATE));

        Log.println(
                Log.DEBUG,
                TAG,
                String.format("Current text speed value is: `%d`", playerSettings.getTextSpeed())
        );
        Log.println(
                Log.DEBUG,
                TAG,
                String.format("Setting text speed to `%d`", to_text_speed)
        );
        playerSettings.setTextSpeed(to_text_speed);

        Log.println(
                Log.DEBUG,
                TAG,
                "Getting text speed..."
        );
        Log.println(
                Log.DEBUG,
                TAG,
                String.format("Text speed is: %d", playerSettings.getTextSpeed())
        );

    }
}
