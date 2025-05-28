package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.LoadedStoryActors;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

public class Talk extends StorySceneEvent {
    final float CHARACTERS_PER_SECOND = 4; // replace with configuration reference later
    private String actor_id = null;
    private String dialog = "";

    public Talk(String actor_id, String dialog) {
        this.actor_id = actor_id;
        this.dialog = dialog;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, Runnable finished) {
        super.execute(visualNovel, finished);

        visualNovel.setSpeakerName(LoadedStoryActors.getInstance().get_actor_by_id(actor_id).name);
        visualNovel.typeDialogText(this.dialog, CHARACTERS_PER_SECOND, () -> visualNovel.waitForContinue(finished));
    }
}
