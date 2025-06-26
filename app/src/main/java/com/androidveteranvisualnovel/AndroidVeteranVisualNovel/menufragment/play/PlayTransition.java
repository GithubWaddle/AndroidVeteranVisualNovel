package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.widget.ImageView;

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
        transitionPanel.setVisibility(toTransparency == 0 ? View.GONE : View.VISIBLE);
    }

    public void setTransparencyTween(
            float toTransparency,
            int milliseconds,
            Runnable finished
    ) {
        transitionPanel.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(transitionPanel, View.ALPHA, transitionPanel.getAlpha(), toTransparency);
        animator.setDuration(milliseconds);

        animator.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(android.animation.Animator animation) {}
            @Override public void onAnimationCancel(android.animation.Animator animation) {}
            @Override public void onAnimationRepeat(android.animation.Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                if (toTransparency == 0f) {
                    transitionPanel.setVisibility(View.GONE);
                }
                if (finished == null) {
                    return;
                }
                finished.run();
            }
        });

        animator.start();
    }
}
