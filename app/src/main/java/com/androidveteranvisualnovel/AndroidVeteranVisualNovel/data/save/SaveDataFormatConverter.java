package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDataFormatConverter {

    private final Gson gson;

    public SaveDataFormatConverter() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void toFile(SaveData saveDataObject, String relativePath, Context context) {
        File file = new File(context.getFilesDir(), relativePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(saveDataObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SaveData toObject(String relativePath, Context context) {
        File file = new File(context.getFilesDir(), relativePath);

        if (!file.exists()) return null;

        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, SaveData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}