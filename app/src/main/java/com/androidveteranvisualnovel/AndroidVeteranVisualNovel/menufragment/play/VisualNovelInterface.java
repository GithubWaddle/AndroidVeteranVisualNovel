package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;

public interface VisualNovelInterface {
    void setDialogText();
    void typeDialogText(String text, float charactersPerSecond, Runnable finished);
    void setSpeakerName(String text);
    void setBackground(String path);
    void setBackgroundTransparencyInstant(float toTransparency);
    void setBackgroundTransparencyTween(
            float toTransparency,
            int milliseconds,
            Runnable finished
    );
    void addActorSprite(StoryActor actor, String expression);
    void setActorSpriteTransparencyInstant(
            StoryActor actor,
            float toTransparency
    );
    void setActorSpriteTransparencyTween(
            StoryActor actor,
            float toTransparency,
            int milliseconds,
            Runnable finished
    );
    void setActorSpritePositionInstant(
            StoryActor actor,
            int x,
            int y
    );
    void setActorSpritePositionInstant(
            StoryActor actor,
            String toPosition
    );
    void setActorSpritePositionTween(
            StoryActor actor,
            int x,
            int y,
            int milliseconds,
            Runnable finished
    );
    void setActorSpritePositionTween(
            StoryActor actor,
            String toPosition,
            int milliseconds,
            Runnable finished
    );
    void setActorSpriteExpressionInstant(
            StoryActor actor,
            String expression
    );
    void setActorSpriteExpressionTween(
            StoryActor actor,
            String expression,
            int milliseconds,
            Runnable finished
    );
    void removeActorSprite(StoryActor actor);
    void waitForContinue(Runnable finished);
    void setTransitionTransparencyInstant(float toTransparency);
    void setTransitionTransparencyTween(
            float toTransparency,
            int milliseconds,
            Runnable finished
    );

    void playMusic(
            String path
    );
    void playSoundEffect(
            String path
    );
    void stopMusic();

    void setDialogBoxTransparencyInstant(
            float toTransparency
    );

    void setDialogBoxTransparencyTween(
            float toTransparency,
            int milliseconds,
            Runnable finished
    );

    void setSpeakerNameTransparencyInstant(
            float toTransparency
    );

    void setTransitionColor(
            int color
    );

    void setVariable(
            String name,
            Object value
    );
}
