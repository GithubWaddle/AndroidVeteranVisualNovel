package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.storyselection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings.SettingsMenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.StoryStartMenuFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StorySelectionMenuFragment extends MenuFragment {
    final static String ARGUMENT_STARTING_STORY_ID = "starting_story_id";
    private int allStoriesAmount = 0;
    public static StorySelectionMenuFragment newInstance(String startingStoryId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STARTING_STORY_ID, startingStoryId);

        StorySelectionMenuFragment fragment = new StorySelectionMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                return;
            }
        });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_selection_menu, container, false);
        TextView tvCurrentStoryIndex = view.findViewById(R.id.tvCurrentStoryIndex);

        List<StoryData> allStories = new ArrayList<StoryData>(StoryDatabase.getInstance().getAllStories().values());
        allStoriesAmount = allStories.size();
        RecyclerView recyclerView = view.findViewById(R.id.rvStorySelectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        StorySelectAdapter adapter = new StorySelectAdapter(allStories, storyData -> {
            menuFragmentManager.switchToMenuFragment(StoryStartMenuFragment.newInstance(storyData.id));
        });

        recyclerView.setAdapter(adapter);

        String startingStoryId = getArguments() != null ? getArguments().getString(ARGUMENT_STARTING_STORY_ID) : null;
        if (startingStoryId != null) {
            int indexToScrollTo = -1;
            for (int i = 0; i < allStories.size(); i++) {
                if (startingStoryId.equals(allStories.get(i).id)) {
                    indexToScrollTo = i;
                    break;
                }
            }

            if (indexToScrollTo >= 0) {
                final int scrollTo = indexToScrollTo;
                recyclerView.post(() -> recyclerView.smoothScrollToPosition(scrollTo));
            }
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int centerX = recyclerView.getWidth() / 2;
                View centerView = null;
                int centerPosition = RecyclerView.NO_POSITION;

                for (int index = 0; index < recyclerView.getChildCount(); index++) {
                    View child = recyclerView.getChildAt(index);
                    int childCenterX = (child.getLeft() + child.getRight()) / 2;
                    int distance = Math.abs(childCenterX - centerX);

                    if (centerView == null || distance < Math.abs(((centerView.getLeft() + centerView.getRight()) / 2) - centerX)) {
                        centerView = child;
                        centerPosition = recyclerView.getChildAdapterPosition(child);
                    }
                }

                if (centerPosition == RecyclerView.NO_POSITION) {
                    return;
                }

                tvCurrentStoryIndex.setText(String.format(Locale.ENGLISH, "%d/%d", centerPosition + 1, allStoriesAmount));
            }
        });


        view.findViewById(R.id.bSettings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        return view;
    }
}
