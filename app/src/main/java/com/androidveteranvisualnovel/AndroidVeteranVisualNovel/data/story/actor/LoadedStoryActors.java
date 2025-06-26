package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import android.content.Context;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LoadedStoryActors {
    final String METADATA_FILE_NAME = "metadata.json";
    private static LoadedStoryActors singletonInstance;
    private final Context applicationContext;
    private final Map<String, StoryActor> actors = new HashMap<>();

    public LoadedStoryActors(Context context) {
        this.applicationContext = context;
    }

    public static LoadedStoryActors getInstance() {
        return LoadedStoryActors.getInstance(null);
    }

    public static LoadedStoryActors getInstance(Context context) {
        if (singletonInstance == null) {
            singletonInstance = new LoadedStoryActors(context);
        }
        return singletonInstance;
    }

    public void loadFolder(String assetPath) throws IOException, JSONException {
        String[] actorFolders = applicationContext.getAssets().list(assetPath);
        assert actorFolders != null;
        for (String actorFolder : actorFolders) {
            String actorFolderAssetPath = assetPath + "/" + actorFolder;
            String actorAssetPath = actorFolderAssetPath + "/" + METADATA_FILE_NAME;
            StoryActor actor = StoryActorFileLoader.load(
                    actorAssetPath,
                    applicationContext
            );
            actors.put(actor.id, actor);
        }

    }
    public StoryActor getActorById(String id) {
        return actors.get(id);
    }

}
