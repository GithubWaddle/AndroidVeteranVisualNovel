package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;

import java.util.List;
import java.util.Locale;

public class SaveBoxAdapter extends RecyclerView.Adapter<SaveBoxAdapter.SaveViewHolder> {

    private final List<SaveData> saves;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(SaveData data, int saveId);
    }

    public SaveBoxAdapter(List<SaveData> saves, OnItemClickListener listener) {
        this.saves = saves;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_save_box, parent, false);
        return new SaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int saveId) {
        SaveData data = saves.get(saveId);
        holder.bind(data, listener, saveId);
    }

    @Override
    public int getItemCount() {
        return saves.size();
    }

    public void updateData(List<SaveData> newData) {
        saves.clear();
        saves.addAll(newData);
        notifyDataSetChanged();
    }

    static class SaveViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvSubtitle;
        private final ImageView ivThumbnail;

        public SaveViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }

        public void bind(SaveData data, OnItemClickListener listener, int saveId) {
            itemView.setOnClickListener(v -> listener.onClick(data, saveId));

            if (data == null) {
                tvTitle.setText(String.format("Empty Save"));
                tvSubtitle.setText("---");
                return;
            }
            tvTitle.setText(String.format(Locale.ENGLISH, "Save %d", (saveId + 1)));
            tvSubtitle.setText(data.sceneId);
            ivThumbnail.setImageBitmap(data.thumbnail);
        }
    }
}

