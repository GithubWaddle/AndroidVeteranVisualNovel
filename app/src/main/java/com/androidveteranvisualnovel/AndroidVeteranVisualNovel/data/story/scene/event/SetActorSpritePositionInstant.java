package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class SetActorSpritePositionInstant extends StorySceneEvent {
    private String actorId;
    private String position;

    public SetActorSpritePositionInstant(String actorId, String position) {
        this.actorId = actorId;
        this.position = position;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setActorSpritePositionInstant(
                LoadedStoryActors.getInstance().getActorById(actorId),
                position
        );
        finished.run();
    }
}
