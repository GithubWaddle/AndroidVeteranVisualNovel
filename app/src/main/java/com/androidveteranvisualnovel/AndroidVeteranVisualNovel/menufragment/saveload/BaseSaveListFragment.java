package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;

import java.util.List;

public abstract class BaseSaveListFragment extends MenuFragment {
    final static protected String ARGUMENT_STORY_ID = "story_id";
    final static private int MAXIMUM_SAVES = 6;
    protected String storyId;
    private boolean isDragging = false;
    private float lastY = 0f;
    protected List<SaveData> saves;
    protected SaveBoxAdapter saveBoxAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saveload_menu, container, false);

        storyId = getArguments() != null ? getArguments().getString(ARGUMENT_STORY_ID) : null;
        saves = SaveDatabase.getInstance().getAllSavesOfStoryWithoutAutosave(storyId);
        while (saves.size() < MAXIMUM_SAVES) {
            saves.add(null);
        }
        saveBoxAdapter = new SaveBoxAdapter(saves, this::onSaveClicked);


        view.findViewById(R.id.ibBack).setOnClickListener(v ->
                menuFragmentManager.removeTopMenuFragment()
        );

        RecyclerView recyclerView = view.findViewById(R.id.rvSaves);
        View vScrollbarTrack = view.findViewById(R.id.vScrollbarTrack);
        View vScrollbarThumb = view.findViewById(R.id.vScrollbarThumb);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(saveBoxAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (isDragging) return;

                int extent = recyclerView.computeVerticalScrollExtent();
                int range = recyclerView.computeVerticalScrollRange();
                int offset = recyclerView.computeVerticalScrollOffset();

                float proportion = offset / (float) (range - extent);
                int trackHeight = vScrollbarTrack.getHeight();
                int thumbHeight = vScrollbarThumb.getHeight();
                float thumbY = proportion * (trackHeight - thumbHeight);
                vScrollbarThumb.setTranslationY(thumbY);
            }
        });

        vScrollbarThumb.setOnTouchListener((v, event) -> {
            int trackHeight = vScrollbarTrack.getHeight();
            int thumbHeight = vScrollbarThumb.getHeight();
            int range = recyclerView.computeVerticalScrollRange();
            int extent = recyclerView.computeVerticalScrollExtent();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDragging = true;
                    lastY = event.getRawY();
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float dy = event.getRawY() - lastY;
                    lastY = event.getRawY();
                    float newY = vScrollbarThumb.getTranslationY() + dy;
                    newY = Math.max(0f, Math.min(newY, trackHeight - thumbHeight));
                    vScrollbarThumb.setTranslationY(newY);

                    float proportion = newY / (float)(trackHeight - thumbHeight);
                    int scrollOffset = (int) (proportion * (range - extent));
                    recyclerView.scrollBy(0, scrollOffset - recyclerView.computeVerticalScrollOffset());
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    isDragging = false;
                    return true;
            }
            return false;
        });

        return view;
    }

    abstract void onSaveClicked(SaveData saveData, int saveId);
}
