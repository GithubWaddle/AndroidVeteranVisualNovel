package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import  com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import java.util.List;

public class StorySelectAdapter extends RecyclerView.Adapter<StorySelectAdapter.StoryViewHolder> {

    public interface OnStoryClickListener {
        void onStoryClicked(StoryData storyData);
    }

    private final List<StoryData> storyList;
    private final OnStoryClickListener listener;

    public StorySelectAdapter(List<StoryData> storyList, OnStoryClickListener listener) {
        this.storyList = storyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story_select, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        StoryData data = storyList.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView subtitle;
        TextView description;

        StoryViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            subtitle = itemView.findViewById(R.id.tvSubtitle);
            thumbnail = itemView.findViewById(R.id.storyThumbnail);
            description = itemView.findViewById(R.id.tvDescription);
        }

        void bind(StoryData storyData) {
            title.setText(storyData.title);
            subtitle.setText(String.format("By: %s", storyData.author));
            description.setText(storyData.synopsisTagline);

            try (InputStream inputStream = itemView.getContext().getAssets().open(storyData.storyFolderRelativePathToArtCover)) {
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                thumbnail.setImageDrawable(drawable);
            } catch (IOException e) {
                e.printStackTrace();
                thumbnail.setImageResource(android.R.drawable.ic_menu_report_image);
            }

            itemView.setOnClickListener(v -> listener.onStoryClicked(storyData));
        }
    }
}


