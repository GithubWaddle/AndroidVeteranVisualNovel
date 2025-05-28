package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import java.util.Map;

public class LoadedStoryActors {
    private static LoadedStoryActors instance;
    private Map<String, StoryActor> actors;

    public static LoadedStoryActors getInstance() {
        if (instance == null) {
            instance = new LoadedStoryActors();
        }
        return instance;
    }

    public void load_folder() {}
    public StoryActor get_actor_by_id(String id) {
        StoryActor actor = new StoryActor();
        return actor;
    }

}
