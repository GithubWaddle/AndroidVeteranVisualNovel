package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class RemoveActorSprite extends StorySceneEvent {
    private String actorId;

    public RemoveActorSprite(String actorId) {
        this.actorId = actorId;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.removeActorSprite(LoadedStoryActors.getInstance().get_actor_by_id(actorId));
        finished.run();
    }
}
