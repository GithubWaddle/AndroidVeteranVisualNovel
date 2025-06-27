package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.PlayFragment;

public class LoadMenuFragment extends BaseSaveListFragment {
    public static LoadMenuFragment newInstance(String storyId) {
        
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STORY_ID, storyId);
        
        LoadMenuFragment fragment = new LoadMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                menuFragmentManager.removeTopMenuFragment();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        assert view != null;
        TextView tvText = view.findViewById(R.id.tvTitle);
        tvText.setText("Load");

        return view;
    }

    @Override
    void onSaveClicked(SaveData saveData, int saveId) {
        if (saveData == null) {
            return;
        }

        menuFragmentManager.switchToMenuFragment(
                PlayFragment.newInstance(
                        storyId,
                        saveId
                )
        );
    }
}
