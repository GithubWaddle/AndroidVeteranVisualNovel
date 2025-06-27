package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpritePositionTween extends StorySceneEvent {
    public String actorId;
    public String position;
    public int milliseconds;

    public SetActorSpritePositionTween(String actorId, String position, int milliseconds) {
        this.actorId = actorId;
        this.position = position;
        this.milliseconds = milliseconds;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setActorSpritePositionTween(
                LoadedStoryActors.getInstance().getActorById(actorId),
                position,
                milliseconds,
                finished
        );
    }
}
