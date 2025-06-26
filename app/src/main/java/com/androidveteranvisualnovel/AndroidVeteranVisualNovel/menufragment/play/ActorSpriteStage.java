package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;

import java.util.HashMap;
import java.util.Map;

public class ActorSpriteStage {
    private final Context context;
    private final FrameLayout stageLayout;
    private final Map<StoryActor, ActorSprite> actorMap = new HashMap<>();

    public ActorSpriteStage(Context context, FrameLayout stageLayout) {
        this.context = context;
        this.stageLayout = stageLayout;
    }

    public void addActor(StoryActor actor) {
        if (actorMap.containsKey(actor)) return;

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT
        ));

        stageLayout.addView(imageView);

        ActorSprite sprite = new ActorSprite(context, imageView, actor, "default");
        actorMap.put(actor, sprite);
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
        if (sprite != null) {
            sprite.actorSprite.setAlpha(toTransparency);
            sprite.actorSprite.setVisibility(toTransparency == 0f ? View.GONE : View.VISIBLE);
        }
    }

    public void setActorTransparencyTween(StoryActor actor, float toTransparency, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            sprite.actorSprite.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(sprite.actorSprite, "alpha", sprite.actorSprite.getAlpha(), toTransparency);
            animator.setDuration(milliseconds);
            animator.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    if (toTransparency == 0f) {
                        sprite.actorSprite.setVisibility(View.GONE);
                    }
                    if (finished != null) finished.run();
                }
                public void onAnimationStart(Animator animator) {}
                public void onAnimationCancel(Animator animator) {}
                public void onAnimationRepeat(Animator animator) {}
            });
            animator.start();
        }
    }

    public void setActorPositionInstant(StoryActor actor, int x, int y) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            sprite.actorSprite.setX(x);
            sprite.actorSprite.setY(y);
        }
    }

    public void setActorPositionInstant(StoryActor actor, String toPosition) {
        int[] xy = resolvePosition(toPosition);
        setActorPositionInstant(actor, xy[0], xy[1]);
    }

    public void setActorPositionTween(StoryActor actor, int x, int y, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            ObjectAnimator animX = ObjectAnimator.ofFloat(sprite.actorSprite, "x", sprite.actorSprite.getX(), x);
            ObjectAnimator animY = ObjectAnimator.ofFloat(sprite.actorSprite, "y", sprite.actorSprite.getY(), y);
            animX.setDuration(milliseconds);
            animY.setDuration(milliseconds);

            animY.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    if (finished != null) finished.run();
                }
                public void onAnimationStart(Animator animator) {}
                public void onAnimationCancel(Animator animator) {}
                public void onAnimationRepeat(Animator animator) {}
            });

            animX.start();
            animY.start();
        }
    }

    public void setActorPositionTween(StoryActor actor, String toPosition, int milliseconds, Runnable finished) {
        int[] xy = resolvePosition(toPosition);
        setActorPositionTween(actor, xy[0], xy[1], milliseconds, finished);
    }

    public void setActorExpressionInstant(StoryActor actor, String expression) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            sprite.setExpression(expression);
        }
    }

    public void setActorExpressionTween(StoryActor actor, String expression, int milliseconds, Runnable finished) {
        ActorSprite sprite = actorMap.get(actor);
        if (sprite != null) {
            // Optional: crossfade or placeholder for tweening expression change
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
        }
    }

    private int[] resolvePosition(String keyword) {
        int x = 0;
        int y = stageLayout.getHeight() / 2; // default Y center

        switch (keyword.toLowerCase()) {
            case "left":
                x = (int)(stageLayout.getWidth() * 0.1);
                break;
            case "center":
                x = (int)(stageLayout.getWidth() * 0.5);
                break;
            case "right":
                x = (int)(stageLayout.getWidth() * 0.9);
                break;
            default:
                x = (int)(stageLayout.getWidth() * 0.5);
        }
        return new int[] {x, y};
    }
}
