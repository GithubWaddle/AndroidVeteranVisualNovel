package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.util.Log;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.StoryScene;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.AddActorSprite;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Jump;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Label;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.PlayMusic;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteExpressionInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteExpressionTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpritePositionInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpritePositionTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundImage;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetDialogBoxTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetDialogBoxTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetSpeakerNameText;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionColor;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetVariable;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.StopMusic;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.StorySceneEvent;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Switch;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Wait;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryScenePlayer {
    final String TAG = "StoryScenePlayer";
    enum Status {
        PLAYING,
        STOP
    }
    public SaveData saveData = new SaveData();
    private StoryData storyData;
    public StoryScene storyScene;
    private VisualNovelInterface visualNovelInterface;
    public Status currentStatus = Status.STOP;

    public StoryScenePlayer(VisualNovelInterface visualNovelInterface) {
        this.visualNovelInterface = visualNovelInterface;
    }

    public void load(SaveData toSaveData) throws IOException, JSONException {
        this.saveData = toSaveData;
        this.storyData = StoryDatabase.getInstance().getStoryById(toSaveData.storyId);
        this.storyScene = this.storyData.getSceneById(toSaveData.sceneId);
        LoadedStoryActors.getInstance().loadFolder(this.storyData.getActorsFolderAssetPath());

        // Latest state holders
        StorySceneEvent lastSetBackgroundImage = null;
        StorySceneEvent lastSetBackgroundTransparency = null;
        StorySceneEvent lastSetDialogBoxTransparency = null;
        StorySceneEvent lastSetSpeakerNameText = null;
        StorySceneEvent lastSetTransitionColor = null;
        StorySceneEvent lastSetTransitionTransparency = null;

        Map<String, StorySceneEvent> lastAddActorSprites = new HashMap<>();
        Map<String, StorySceneEvent> lastSetActorExpressions = new HashMap<>();
        Map<String, StorySceneEvent> lastSetActorPositions = new HashMap<>();
        Map<String, StorySceneEvent> lastSetActorTransparency = new HashMap<>();

        StorySceneEvent lastPlayMusic = null;

        int lineNumber = 0;
        while (lineNumber < saveData.lineNumber) {
            StorySceneEvent event = storyScene.events.get(lineNumber);

            if (event instanceof Wait || event instanceof Jump || event instanceof Label || event instanceof SetVariable || event instanceof Switch) {
                lineNumber++;
                continue;
            }

            if (event instanceof SetBackgroundImage) {
                lastSetBackgroundImage = event;

            } else if (event instanceof SetBackgroundTransparencyInstant) {
                lastSetBackgroundTransparency = event;

            } else if (event instanceof SetBackgroundTransparencyTween) {
                float transparency = ((SetBackgroundTransparencyTween) event).transparency;
                lastSetBackgroundTransparency = new SetBackgroundTransparencyInstant(transparency);

            } else if (event instanceof SetDialogBoxTransparencyInstant) {
                lastSetDialogBoxTransparency = event;

            } else if (event instanceof SetDialogBoxTransparencyTween) {
                float transparency = ((SetDialogBoxTransparencyTween) event).transparency;
                lastSetDialogBoxTransparency = new SetDialogBoxTransparencyInstant(transparency);

            } else if (event instanceof SetTransitionTransparencyInstant) {
                lastSetTransitionTransparency = event;

            } else if (event instanceof SetTransitionTransparencyTween) {
                float transparency = ((SetTransitionTransparencyTween) event).transparency;
                lastSetTransitionTransparency = new SetTransitionTransparencyInstant(transparency);

            } else if (event instanceof SetSpeakerNameText) {
                lastSetSpeakerNameText = event;

            } else if (event instanceof SetTransitionColor) {
                lastSetTransitionColor = event;

            } else if (event instanceof AddActorSprite) {
                String actorId = ((AddActorSprite) event).actorId;
                lastAddActorSprites.put(actorId, event);

            } else if (event instanceof SetActorSpriteExpressionInstant) {
                String actorId = ((SetActorSpriteExpressionInstant) event).actorId;
                lastSetActorExpressions.put(actorId, event);

            } else if (event instanceof SetActorSpriteExpressionTween) {
                SetActorSpriteExpressionTween tween = (SetActorSpriteExpressionTween) event;
                lastSetActorExpressions.put(tween.actorId, new SetActorSpriteExpressionInstant(tween.actorId, tween.expression));

            } else if (event instanceof SetActorSpritePositionInstant) {
                String actorId = ((SetActorSpritePositionInstant) event).actorId;
                lastSetActorPositions.put(actorId, event);

            } else if (event instanceof SetActorSpritePositionTween) {
                SetActorSpritePositionTween tween = (SetActorSpritePositionTween) event;
                lastSetActorPositions.put(tween.actorId, new SetActorSpritePositionInstant(tween.actorId, tween.position));

            } else if (event instanceof SetActorSpriteTransparencyInstant) {
                String actorId = ((SetActorSpriteTransparencyInstant) event).actorId;
                lastSetActorTransparency.put(actorId, event);

            } else if (event instanceof SetActorSpriteTransparencyTween) {
                SetActorSpriteTransparencyTween tween = (SetActorSpriteTransparencyTween) event;
                lastSetActorTransparency.put(tween.actorId, new SetActorSpriteTransparencyInstant(tween.actorId, tween.transparency));

            } else if (event instanceof PlayMusic) {
                lastPlayMusic = event;

            } else if (event instanceof StopMusic) {
                lastPlayMusic = null;
            }

            lineNumber++;
        }

        reloadSavedScene(
                visualNovelInterface,
                this,
                lastSetBackgroundImage,
                lastSetBackgroundTransparency,
                lastSetTransitionColor,
                lastSetTransitionTransparency,
                lastSetDialogBoxTransparency,
                lastSetSpeakerNameText,
                lastPlayMusic,
                lastAddActorSprites,
                lastSetActorPositions,
                lastSetActorExpressions,
                lastSetActorTransparency
        );

        if (this.saveData.lineNumber == 0) {
            return;
        }
        this.saveData.lineNumber--;
    }

    public void reloadSavedScene(
            VisualNovelInterface visualNovel,
            StoryScenePlayer storyScenePlayer,
            StorySceneEvent lastSetBackgroundImage,
            StorySceneEvent lastSetBackgroundTransparency,
            StorySceneEvent lastSetTransitionColor,
            StorySceneEvent lastSetTransitionTransparency,
            StorySceneEvent lastSetDialogBoxTransparency,
            StorySceneEvent lastSetSpeakerNameText,
            StorySceneEvent lastPlayMusic,
            Map<String, StorySceneEvent> lastAddActorSprites,
            Map<String, StorySceneEvent> lastSetActorPositions,
            Map<String, StorySceneEvent> lastSetActorExpressions,
            Map<String, StorySceneEvent> lastSetActorTransparency
    ) {
        Runnable noOp = () -> {};

        if (lastSetBackgroundImage != null)
            lastSetBackgroundImage.execute(visualNovel, storyScenePlayer, noOp);

        if (lastSetBackgroundTransparency != null)
            lastSetBackgroundTransparency.execute(visualNovel, storyScenePlayer, noOp);

        if (lastSetTransitionColor != null)
            lastSetTransitionColor.execute(visualNovel, storyScenePlayer, noOp);

        if (lastSetTransitionTransparency != null)
            lastSetTransitionTransparency.execute(visualNovel, storyScenePlayer, noOp);

        if (lastSetDialogBoxTransparency != null)
            lastSetDialogBoxTransparency.execute(visualNovel, storyScenePlayer, noOp);

        if (lastSetSpeakerNameText != null)
            lastSetSpeakerNameText.execute(visualNovel, storyScenePlayer, noOp);

        if (lastPlayMusic != null)
            lastPlayMusic.execute(visualNovel, storyScenePlayer, noOp);

        // Actor sprites
        for (String actorId : lastAddActorSprites.keySet()) {
            StorySceneEvent addSprite = lastAddActorSprites.get(actorId);
            StorySceneEvent setPosition = lastSetActorPositions.get(actorId);
            StorySceneEvent setExpression = lastSetActorExpressions.get(actorId);
            StorySceneEvent setTransparency = lastSetActorTransparency.get(actorId);

            if (addSprite != null)
                addSprite.execute(visualNovel, storyScenePlayer, noOp);

            if (setPosition != null)
                setPosition.execute(visualNovel, storyScenePlayer, noOp);

            if (setExpression != null)
                setExpression.execute(visualNovel, storyScenePlayer, noOp);

            if (setTransparency != null)
                setTransparency.execute(visualNovel, storyScenePlayer, noOp);
        }
    }

    public void switchScene(String sceneId) throws IOException {
        this.saveData.sceneId = sceneId;
        this.saveData.lineNumber = 0;
        this.storyScene = this.storyData.getSceneById(sceneId);
    }

    public void play() {
        currentStatus = Status.PLAYING;
        
        StoryScenePlayer storyScenePlayer = this;
        Runnable nextEvent = new Runnable() {
            @Override
            public void run() {
                if (saveData.lineNumber >= storyScenePlayer.storyScene.events.size()) {
                    Log.println(
                            Log.INFO,
                            TAG,
                            "No more events to play. Scene finished!"
                    );
                    stop();
                    return;
                }
                if (currentStatus == Status.STOP) {
                    return;
                }

                StorySceneEvent event = storyScenePlayer.storyScene.events.get(saveData.lineNumber);
                setCurrentEventIndex(
                        saveData.lineNumber + 1
                );

                event.execute(
                        storyScenePlayer.visualNovelInterface, storyScenePlayer,
                        this);
            };
        };

        nextEvent.run();
    }

    public void stop() {
        currentStatus = Status.STOP;
    }

    public void setCurrentEventIndex(int currentEventIndex) {
        this.saveData.lineNumber = currentEventIndex;
    }
}
