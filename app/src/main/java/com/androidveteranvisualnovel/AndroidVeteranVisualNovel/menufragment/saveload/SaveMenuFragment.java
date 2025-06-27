package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.saveload;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;

public class SaveMenuFragment extends BaseSaveListFragment {
    private boolean isDragging = false;
    private float lastY = 0f;
    public static SaveMenuFragment newInstance(String storyId) {
        
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STORY_ID, storyId);
        
        SaveMenuFragment fragment = new SaveMenuFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        assert view != null;
        TextView tvText = view.findViewById(R.id.tvTitle);
        tvText.setText("Save");

        return view;
    }

    @Override
    void onSaveClicked(SaveData saveData, int saveId) {
        SaveData newSave = SaveDatabase.getInstance().getSave(
                storyId,
                SaveDatabase.AUTOSAVE_ID
        );
        newSave.thumbnail = captureFragmentScreenshot(menuFragmentManager.getMenuFragment(0));

        if (saveData == null) {
            SaveDatabase.getInstance().createSave(
                    storyId,
                    saveId,
                    newSave
            );
        } else {
            SaveDatabase.getInstance().updateSave(
                    storyId,
                    saveId,
                    newSave
            );
        }

        saves.set(saveId, newSave);
        saveBoxAdapter.notifyItemChanged(saveId);
    }

    public Bitmap captureFragmentScreenshot(Fragment fragment) {
        View fragmentView = fragment.getView();
        assert fragmentView != null;
        Bitmap bitmap = Bitmap.createBitmap(fragmentView.getWidth(), fragmentView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        fragmentView.draw(canvas);
        return bitmap;
    }

}
