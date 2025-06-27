package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.util.Log;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CommandLineVisualNovel implements VisualNovelInterface {
    final String TAG = "CommandLineVisualNovel";
    public void print(String message) {
        Log.println(
                Log.INFO,
                TAG,
                message
        );
    }

    @Override
    public void setDialogText(String text) {
        print(String.format("\"%s\"(immediate)", text));
    }

    @Override
    public void typeDialogText(String text, float charactersPerSecond, Runnable finished) {
        print(String.format("\"%s\"(typed)", text));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, (int) (text.length() / charactersPerSecond), TimeUnit.MILLISECONDS);
    }

    @Override
    public void setSpeakerName(String text) {
        print(String.format("Speaker is %s", text));
    }

    @Override
    public void setBackground(String storyPath) {
        print(String.format("background is %s", storyPath));
    }

    @Override
    public void setBackgroundTransparencyInstant(float toTransparency) {
        print(String.format(Locale.US, "background transparency is instantly %f", toTransparency));
    }

    @Override
    public void setBackgroundTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "background transparency is tweened to %f. This process will take `%d` milliseconds.", toTransparency, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void addActorSprite(StoryActor actor, String expression, String position) {
        print(String.format("%s is here now. They have a %s expression.", actor.name, expression));
    }

    @Override
    public void setActorSpriteTransparencyInstant(StoryActor actor, float toTransparency) {
        print(String.format(Locale.US,"%s is instantly %f see-through", actor.name, toTransparency));
    }

    @Override
    public void setActorSpriteTransparencyTween(StoryActor actor, float toTransparency, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "%s is tweened to be %f see-through. This process will take `%d` milliseconds.", actor.name, toTransparency, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, int x, int y) {
        print(String.format(Locale.US, "%s is instantly positioned exactly at (%d, %d).", actor.name, x, y));
    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, String toPosition) {
        print(String.format(Locale.US, "%s is instantly positioned generally at %s.", actor.name, toPosition));
    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, int x, int y, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "%s is tweened to be positioned exactly at (%d, %d). This process will take %d milliseconds.", actor.name, x, y, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, String toPosition, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "%s is tweened to be positioned generally at %s. This process will take %d milliseconds.", actor.name, toPosition, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setActorSpriteExpressionInstant(StoryActor actor, String expression) {
        print(String.format(Locale.US, "%s is instantly %s now.", actor.name, expression));
    }

    @Override
    public void setActorSpriteExpressionTween(StoryActor actor, String expression, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "%s is becoming %s now. This process will take %d milliseconds", actor.name, expression, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void removeActorSprite(StoryActor actor) {
        print("`%s` is not here now.");
    }

    @Override
    public void waitForContinue(Runnable finished) {
        print(">>>");
        finished.run();
    }

    @Override
    public void setTransitionTransparencyInstant(float toTransparency) {
        print(String.format(Locale.US, "Transition is instantly %f transparent now.", toTransparency));
    }

    @Override
    public void setTransitionTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "Transition is tweened to be %f transparent now. This process will take %d milliseconds", toTransparency, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void playMusic(String path) {
        print(String.format("Playing music from path `%s`", path));
    }

    @Override
    public void playSoundEffect(String path) {
        print(String.format("Playing sound effect from path `%s`", path));
    }

    @Override
    public void stopMusic() {
        print("There is no music now.");
    }

    @Override
    public void setDialogBoxTransparencyInstant(float toTransparency) {
        print(String.format(Locale.US, "Dialog box is instantly %f transparent now.", toTransparency));
    }

    @Override
    public void setDialogBoxTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        print(String.format(Locale.US, "Dialog box is tweened to be %f transparent now. This process will take %d milliseconds", toTransparency, milliseconds));
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(finished, milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setSpeakerNameTransparencyInstant(float toTransparency) {
        print(String.format(Locale.US, "Speaker name is instantly %f transparent now.", toTransparency));
    }

    @Override
    public void setTransitionColor(int color) {
        print(String.format(Locale.US, "Transition pane is instantly %d now.", color));
    }

    @Override
    public void askChoice(List<String> choices, Runnable finished) {

    }

    @Override
    public String getPickedChoice() {
        return "";
    }
}
