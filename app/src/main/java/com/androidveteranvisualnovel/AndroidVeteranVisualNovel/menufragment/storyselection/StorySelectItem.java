package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection;

public class StorySelectItem {
    public String title;
    public String subtitle;
    public String description;
    public int thumbnailResId;
    public StorySelectItem(String title, String subtitle, String description, int thumbnailResId) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.thumbnailResId = thumbnailResId;
    }
}
