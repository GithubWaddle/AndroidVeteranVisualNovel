package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LoadedStoryActors {
    final String METADATA_FILE_NAME = "metadata.json";
    private static LoadedStoryActors instance;
    private final Map<String, StoryActor> actors = new HashMap<>();

    public static LoadedStoryActors getInstance() {
        if (instance == null) {
            instance = new LoadedStoryActors();
        }
        return instance;
    }

    public void loadFolder(String assetPath, Context context) throws IOException, JSONException {
        String[] actorFolders = context.getAssets().list(assetPath);
        assert actorFolders != null;
        for (String actorFolder : actorFolders) {
            String actorFolderAssetPath = assetPath + "/" + actorFolder;
            String actorAssetPath = actorFolderAssetPath + "/" + METADATA_FILE_NAME;
            StoryActor actor = StoryActorFileLoader.load(
                    actorAssetPath,
                    context
            );
            actors.put(actor.id, actor);
        }

    }
    public StoryActor getActorById(String id) {
        return actors.get(id);
    }

}
