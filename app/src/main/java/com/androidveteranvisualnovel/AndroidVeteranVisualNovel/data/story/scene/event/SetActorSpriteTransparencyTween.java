package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpriteTransparencyTween extends StorySceneEvent {
    public String actorId;
    public float transparency;
    private int milliseconds;

    public SetActorSpriteTransparencyTween(String actorId, float transparency, int milliseconds) {
        this.actorId = actorId;
        this.transparency = transparency;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setActorSpriteTransparencyTween(
                LoadedStoryActors.getInstance().getActorById(actorId),
                transparency,
                milliseconds,
                finished
        );
    }
}
