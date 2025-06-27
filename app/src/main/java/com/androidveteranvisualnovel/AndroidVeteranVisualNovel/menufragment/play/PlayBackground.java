package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class PlayBackground {
    private ImageView backgroundImage;

    public PlayBackground(ImageView backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackground(Bitmap image) {
        backgroundImage.setImageBitmap(image);
    }

    public void setTransparencyInstant(float toTransparency) {
        backgroundImage.setAlpha(toTransparency);
    }

    public void setTransparencyTween(
            float toTransparency,
            int milliseconds,
            final Runnable finished
    ) {
        new Handler(Looper.getMainLooper()).post(() -> {
            backgroundImage.setVisibility(View.VISIBLE);
            backgroundImage.animate()
                    .alpha(toTransparency)
                    .setDuration(milliseconds)
                    .withEndAction(finished)
                    .start();
        });
    }
}

