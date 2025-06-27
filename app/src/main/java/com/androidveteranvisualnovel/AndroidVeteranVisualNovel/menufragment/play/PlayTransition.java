package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.animation.AnimatorListenerAdapter;
import android.os.Looper;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.os.Handler;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

public class PlayTransition {
    private ImageView transitionPanel;

    public PlayTransition(ImageView transitionPanel) {
        this.transitionPanel = transitionPanel;
    }

    // Set the color of the transition panel
    public void setColor(int color) {
        transitionPanel.setBackgroundColor(color);
    }

    public void setTransparencyInstant(float toTransparency) {
        transitionPanel.setAlpha(toTransparency);
        //transitionPanel.setVisibility(toTransparency == 0 ? View.GONE : View.VISIBLE);
    }

    public void setTransparencyTween(
            float toTransparency,
            int milliseconds,
            Runnable finished
    ) {
        new Handler(Looper.getMainLooper()).post(() -> {
            transitionPanel.setVisibility(View.VISIBLE);
            transitionPanel.animate()
                    .alpha(toTransparency)
                    .setDuration(milliseconds)
                    .withEndAction(finished)
                    .start();
        });
    }
}
