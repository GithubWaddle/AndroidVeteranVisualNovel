package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveDatabase {

    private static SaveDatabase instance;
    private final File baseFolder;
    private final Gson gson;

    private SaveDatabase(Context context) {
        this.baseFolder = new File(context.getFilesDir(), "SaveDatabase");
        if (!baseFolder.exists()) baseFolder.mkdirs();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static synchronized SaveDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new SaveDatabase(context.getApplicationContext());
        }
        return instance;
    }

    public ArrayList<SaveData> getAllSavesOfStory(String storyId) {
        ArrayList<SaveData> saves = new ArrayList<>();
        File storyFolder = new File(baseFolder, storyId);
        if (storyFolder.exists() && storyFolder.isDirectory()) {
            File[] files = storyFolder.listFiles((dir, name) -> name.matches("save_\\d+\\.json"));
            if (files != null) {
                for (File file : files) {
                    SaveData data = loadSaveDataFromFile(file);
                    if (data != null) saves.add(data);
                }
            }
        }
        return saves;
    }

    public int createSave(String storyId) {
        int newId = generateNextId(storyId);
        SaveData newSave = new SaveData();
        newSave.storyId = storyId;
        newSave.lineNumber = 0;
        newSave.sceneId = "";
        newSave.storyState = null;
        updateSave(storyId, newId, newSave);
        return newId;
    }

    public SaveData getSave(String storyId, int saveId) {
        File file = getSaveFile(storyId, saveId);
        return loadSaveDataFromFile(file);
    }

    public void updateSave(String storyId, int saveId, SaveData saveData) {
        File file = getSaveFile(storyId, saveId);
        file.getParentFile().mkdirs();
        try (var writer = new java.io.FileWriter(file)) {
            gson.toJson(saveData, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSave(String storyId, int saveId) {
        File file = getSaveFile(storyId, saveId);
        if (file.exists()) file.delete();
    }

    // Util methods
    private File getSaveFile(String storyId, int saveId) {
        return new File(new File(baseFolder, storyId), "save_" + saveId + ".json");
    }

    private SaveData loadSaveDataFromFile(File file) {
        try (var reader = new java.io.FileReader(file)) {
            return gson.fromJson(reader, SaveData.class);
        } catch (Exception e) {
            return null;
        }
    }

    private int generateNextId(String storyId) {
        File storyFolder = new File(baseFolder, storyId);
        int maxId = 0;
        if (storyFolder.exists() && storyFolder.isDirectory()) {
            File[] files = storyFolder.listFiles();
            if (files != null) {
                Pattern pattern = Pattern.compile("save_(\\d+)\\.json");
                for (File file : files) {
                    Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.matches()) {
                        int id = Integer.parseInt(matcher.group(1));
                        if (id > maxId) maxId = id;
                    }
                }
            }
        }
        return maxId + 1;
    }
}