package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpritePositionInstant extends StorySceneEvent {
    public String actorId;
    public String position;

    public SetActorSpritePositionInstant(String actorId, String position) {
        this.actorId = actorId;
        this.position = position;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        visualNovel.setActorSpritePositionInstant(
                LoadedStoryActors.getInstance().getActorById(actorId),
                position
        );
        finished.run();
    }
}
