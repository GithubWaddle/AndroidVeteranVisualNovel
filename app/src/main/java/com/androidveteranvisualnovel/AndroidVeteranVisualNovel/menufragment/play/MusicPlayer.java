package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;
    private Context context;
    private String musicPath;

    public MusicPlayer(Context context) {
        this.context = context;
    }

    public void load(String musicPath) {
        stop();
        this.musicPath = musicPath;

        try {
            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(musicPath);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            assetFileDescriptor.close();

            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!(mediaPlayer != null && !mediaPlayer.isPlaying())) {
            return;
        }
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer == null) {
            return;
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }
}


