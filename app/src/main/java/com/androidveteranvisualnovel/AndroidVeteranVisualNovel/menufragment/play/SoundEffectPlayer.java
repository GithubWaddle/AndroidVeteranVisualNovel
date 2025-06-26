package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.io.IOException;

public class SoundEffectPlayer {
    private SoundPool soundPool;
    private Context context;
    private int soundId = -1;
    private int streamId = -1;

    public SoundEffectPlayer(Context context) {
        this.context = context;

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();
    }

    public void load(String soundEffectPath) {
        try {
            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(soundEffectPath);
            soundId = soundPool.load(assetFileDescriptor, 1);
            assetFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (soundId == -1) {
            return;
        }
        streamId = soundPool.play(soundId, 1f, 1f, 1, 0, 1f);
    }

    public void stop() {
        if (streamId == -1) {
            return;
        }
        soundPool.stop(streamId);
    }

    public void release() {
        soundPool.release();
        soundPool = null;
    }
}

