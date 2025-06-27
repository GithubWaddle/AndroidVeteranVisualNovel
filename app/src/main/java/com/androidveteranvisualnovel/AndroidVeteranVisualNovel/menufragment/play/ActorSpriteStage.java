package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;

import java.util.HashMap;
import java.util.Map;

public class ActorSpriteStage {
    private final Context context;
    private final FrameLayout stageLayout;
    private final Map<StoryActor, ActorSprite> actorMap = new HashMap<>();
    private final StoryData storyData;

    public ActorSpriteStage(Context context, FrameLayout stageLayout, StoryData storyData) {
        this.context = context;
        this.stageLayout = stageLayout;
        this.storyData = storyData;
    }

    public void addActor(StoryActor actor, String expression, String position) {
        if (actorMap.containsKey(actor)) return;

        ImageView imageView = new ImageView(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        imageView.setLayoutParams(params);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setAlpha(0.0f);
        stageLayout.addView(imageView);

        ActorSprite sprite = new ActorSprite(context, imageView, storyData, actor, expression);
        actorMap.put(actor, sprite);

        setActorPositionInstant(actor, position);
    }

    public void removeActor(StoryActor actor) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            stageLayout.removeView(sprite.actorSprite);
            actorMap.remove(actor);
        }
    }

    public void setActorTransparencyInstant(StoryActor actor, float toTransparency) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }

        sprite.actorSprite.setAlpha(toTransparency);
        sprite.actorSprite.setVisibility(toTransparency == 0f ? View.GONE : View.VISIBLE);
    }

    public void setActorTransparencyTween(StoryActor actor, float toTransparency, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(() -> {
            sprite.actorSprite.setVisibility(View.VISIBLE);
            sprite.actorSprite.animate()
                    .alpha(toTransparency)
                    .setDuration(milliseconds)
                    .withEndAction(finished)
                    .start();
        });
    }

    public void setActorPositionInstant(StoryActor actor, int x, int y) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }
        sprite.actorSprite.setX(x);
        sprite.actorSprite.setY(y);
    }

    public void setActorPositionInstant(StoryActor actor, String toPosition) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }
        
        int[] xyLocal = getLocalNudgePosition(toPosition, sprite.actorSprite);
        setActorPositionInstant(actor, xyLocal[0], xyLocal[1]);
    }

    public void setActorPositionTween(StoryActor actor, int x, int y, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(() -> {
            sprite.actorSprite.animate()
                    .translationX(x)
                    .setDuration(milliseconds)
                    .start();
            sprite.actorSprite.animate()
                    .translationY(y)
                    .setDuration(milliseconds)
                    .withEndAction(finished)
                    .start();
        });
    }

    public void setActorPositionTween(StoryActor actor, String toPosition, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }

        int[] xyLocal = getLocalNudgePosition(toPosition, sprite.actorSprite);
        setActorPositionTween(actor, xyLocal[0], xyLocal[1], milliseconds, finished);
    }

    public void setActorExpressionInstant(StoryActor actor, String expression) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            sprite.setExpression(expression);
        }
    }

    public void setActorExpressionTween(StoryActor actor, String expression, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(() -> {
            sprite.actorSprite.animate()
                    .alpha(0f)
                    .setDuration(milliseconds / 2)
                    .withEndAction(() -> {
                        sprite.setExpression(expression);
                        sprite.actorSprite.animate()
                                .alpha(1f)
                                .setDuration(milliseconds / 2)
                                .withEndAction(finished)
                                .start();
                    }).start();
        });
    }


    private int[] getLocalNudgePosition(String keyword, View view) {
        int stageSizeX = stageLayout.getWidth();
        int stageSizeY = stageLayout.getHeight();
        int viewSizeX = view.getWidth();
        int viewSizeY = view.getHeight();

        if (stageSizeX == 0 || stageSizeY == 0 || viewSizeX == 0 || viewSizeY == 0) {
            return new int[]{0, 0};
        }

        int viewRelativeX = view.getLeft();
        int viewRelativeY = view.getTop();
        int[] stageAbsolutePosition = new int[2];
        stageLayout.getLocationOnScreen(stageAbsolutePosition);

        int xAbsoluteNudge;
        switch (keyword.toLowerCase()) {
            case "left":
                xAbsoluteNudge = (int)(stageSizeX * 0.2);
                break;
            case "right":
                xAbsoluteNudge = (int)(stageSizeX * 0.8);
                break;
            case "center":
            default:
                xAbsoluteNudge = (int)(stageSizeX * 0.5);
                break;
        }

        int xLocal = -viewRelativeX + xAbsoluteNudge - (viewSizeX / 2);
        int yLocal = -viewRelativeY + (stageSizeY - viewSizeY);

        return new int[]{xLocal, yLocal};
    }
}

