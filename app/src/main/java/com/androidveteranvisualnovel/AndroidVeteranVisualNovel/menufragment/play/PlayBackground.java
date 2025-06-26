package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.graphics.Bitmap;
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
        ObjectAnimator animator = ObjectAnimator.ofFloat(backgroundImage, View.ALPHA, backgroundImage.getAlpha(), toTransparency);
        animator.setDuration(milliseconds);

        if (finished != null) {
            animator.addListener(new android.animation.Animator.AnimatorListener() {
                @Override public void onAnimationStart(android.animation.Animator animation) {}
                @Override public void onAnimationCancel(android.animation.Animator animation) {}
                @Override public void onAnimationRepeat(android.animation.Animator animation) {}

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    finished.run();
                }
            });
        }

        animator.start();
    }
}

