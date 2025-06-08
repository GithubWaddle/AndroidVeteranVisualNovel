package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class StoryActorFileLoader {
    public static StoryActor load(String metadataAssetPath, Context context) throws IOException, JSONException {
        StoryActor actor = new StoryActor();
        JSONObject metadataJson;

        try (InputStream metadataFileStream = context.getAssets().open(metadataAssetPath)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(metadataFileStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            metadataJson = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        actor.id = metadataJson.getString("id");
        actor.name = metadataJson.getString("name");

        JSONObject expressionPaths = metadataJson.getJSONObject("storyFolderRelativeExpressionImagePaths");
        Iterator<String> expressionNames = expressionPaths.keys();
        while (expressionNames.hasNext()) {
            String expressionName = expressionNames.next();
            String expressionPath = expressionPaths.getString(expressionName);
            actor.expressionPaths.put(expressionName, expressionPath);
        }

        return actor;
    }
}
