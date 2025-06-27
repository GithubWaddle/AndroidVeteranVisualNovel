package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;

public class SaveDataFormatConverter {

    private static File getThumbnailFile(String jsonFilePath) {
        return new File(jsonFilePath.replace(".json", "_thumb.png"));
    }

    public static void toFile(SaveData saveDataObject, String filePath, Context applicationContext) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("storyId", saveDataObject.storyId);
            jsonObject.put("sceneId", saveDataObject.sceneId);
            jsonObject.put("lineNumber", saveDataObject.lineNumber);
            jsonObject.put("storyState", new JSONObject(saveDataObject.storyState));

            File file = new File(filePath);
            try (FileOutputStream outputStream = new FileOutputStream(file);
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                writer.write(jsonObject.toString());
            }

            if (saveDataObject.thumbnail != null) {
                File thumbnailFile = getThumbnailFile(filePath);
                try (FileOutputStream out = new FileOutputStream(thumbnailFile)) {
                    saveDataObject.thumbnail.compress(Bitmap.CompressFormat.PNG, 100, out);
                }
            }

        } catch (JSONException | IOException e) {
            throw new RuntimeException("Failed to save data at: " + filePath, e);
        }
    }

    public static SaveData toObject(File saveFile, Context applicationContext) {
        SaveData saveData = new SaveData();
        try (InputStream inputStream = new FileInputStream(saveFile);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder fileContents = new StringBuilder();
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                fileContents.append(currentLine);
            }

            JSONObject jsonObject = new JSONObject(fileContents.toString());
            saveData.storyId = jsonObject.getString("storyId");
            saveData.sceneId = jsonObject.getString("sceneId");
            saveData.lineNumber = jsonObject.getInt("lineNumber");

            JSONObject storyStateJson = jsonObject.getJSONObject("storyState");
            Map<String, Object> storyStateMap = new HashMap<>();
            Iterator<String> keysIterator = storyStateJson.keys();
            while (keysIterator.hasNext()) {
                String key = keysIterator.next();
                storyStateMap.put(key, storyStateJson.get(key));
            }
            saveData.storyState = storyStateMap;

            File thumbnailFile = getThumbnailFile(saveFile.getAbsolutePath());
            if (thumbnailFile.exists()) {
                Bitmap thumbnail = BitmapFactory.decodeFile(thumbnailFile.getAbsolutePath());
                saveData.thumbnail = thumbnail;
            }

            Log.println(Log.DEBUG, "SOMETHING", "Nice.");
        } catch (JSONException | IOException e) {
            throw new RuntimeException("Failed to parse save data at: " + saveFile.getAbsolutePath(), e);
        }

        return saveData;
    }
}
