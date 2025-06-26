package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import android.content.Context;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveDatabase {
    private static final String SAVE_DIRECTORY_NAME = "saves";
    private static SaveDatabase singletonInstance;
    private final Context applicationContext;

    private SaveDatabase(Context applicationContext) {
        this.applicationContext = applicationContext.getApplicationContext();

        File saveRootDirectory = new File(applicationContext.getFilesDir(), SAVE_DIRECTORY_NAME);
        if (!saveRootDirectory.exists()) {
            saveRootDirectory.mkdirs();
        }
    }

    public static synchronized SaveDatabase getInstance() {
        return getInstance(null);
    }

    public static synchronized SaveDatabase getInstance(Context context) {
        if (singletonInstance == null) {
            singletonInstance = new SaveDatabase(context);
        }
        return singletonInstance;
    }

    public ArrayList<SaveData> getAllSavesOfStory(String storyId) {
        ArrayList<SaveData> saveDataList = new ArrayList<>();
        File[] saveFiles = getStorySaveFolder(storyId).listFiles();

        if (saveFiles == null) return saveDataList;

        for (File saveFile : saveFiles) {
            if (saveFile.isFile()) {
                saveDataList.add(SaveDataFormatConverter.toObject(saveFile, applicationContext));
            }
        }

        return saveDataList;
    }

    public void createSave(String storyId, int saveId, SaveData saveData) {
        File storySaveFolder = getStorySaveFolder(storyId);
        File saveFile = new File(storySaveFolder, saveId + ".json");

        SaveDataFormatConverter.toFile(saveData, saveFile.getAbsolutePath(), applicationContext);
    }

    public SaveData getSave(String storyId, int saveId) {
        File saveFile = new File(getStorySaveFolder(storyId), saveId + ".json");
        if (!saveFile.exists()) return null;

        return SaveDataFormatConverter.toObject(saveFile, applicationContext);
    }

    public void updateSave(String storyId, int saveId, SaveData updatedSaveData) {
        File saveFile = new File(getStorySaveFolder(storyId), saveId + ".json");
        if (saveFile.exists()) {
            SaveDataFormatConverter.toFile(updatedSaveData, saveFile.getAbsolutePath(), applicationContext);
        }
    }

    public void deleteSave(String storyId, int saveId) {
        File saveFile = new File(getStorySaveFolder(storyId), saveId + ".json");
        if (saveFile.exists()) {
            saveFile.delete();
        }
    }

    private File getStorySaveFolder(String storyId) {
        File storyDirectory = new File(applicationContext.getFilesDir(), SAVE_DIRECTORY_NAME + "/" + storyId);
        if (!storyDirectory.exists()) {
            storyDirectory.mkdirs();
        }
        return storyDirectory;
    }

}
