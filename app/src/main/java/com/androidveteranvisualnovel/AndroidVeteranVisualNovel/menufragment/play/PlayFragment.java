package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.SettingsMenuFragment;

public class PlayFragment extends MenuFragment implements VisualNovelInterface {
    StoryScenePlayer storyScenePlayer;

    public static PlayFragment newInstance(SaveData saveData) {

        Bundle args = new Bundle();

        PlayFragment fragment = new PlayFragment();
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
        View view = inflater.inflate(R.layout.fragment_play, container, false);

        view.findViewById(R.id.bPause).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new PauseMenuFragment())
        );
        view.findViewById(R.id.bSettings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        return view;
    }

    public void play() {

    }

    public void stop() {

    }

    @Override
    public void setDialogText(String text) {

    }

    @Override
    public void typeDialogText(String text, float charactersPerSecond, Runnable finished) {

    }

    @Override
    public void setSpeakerName(String text) {

    }

    @Override
    public void setBackground(String path) {

    }

    @Override
    public void setBackgroundTransparencyInstant(float toTransparency) {

    }

    @Override
    public void setBackgroundTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {

    }

    @Override
    public void addActorSprite(StoryActor actor, String expression) {

    }

    @Override
    public void setActorSpriteTransparencyInstant(StoryActor actor, float toTransparency) {

    }

    @Override
    public void setActorSpriteTransparencyTween(StoryActor actor, float toTransparency, int milliseconds, Runnable finished) {

    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, int x, int y) {

    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, String toPosition) {

    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, int x, int y, int milliseconds, Runnable finished) {

    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, String toPosition, int milliseconds, Runnable finished) {

    }

    @Override
    public void setActorSpriteExpressionInstant(StoryActor actor, String expression) {

    }

    @Override
    public void setActorSpriteExpressionTween(StoryActor actor, String expression, int milliseconds, Runnable finished) {

    }

    @Override
    public void removeActorSprite(StoryActor actor) {

    }

    @Override
    public void waitForContinue(Runnable finished) {

    }

    @Override
    public void setTransitionTransparencyInstant(float toTransparency) {

    }

    @Override
    public void setTransitionTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {

    }

    @Override
    public void playMusic(String path) {

    }

    @Override
    public void playSoundEffect(String path) {

    }

    @Override
    public void stopMusic() {

    }

    @Override
    public void setDialogBoxTransparencyInstant(float toTransparency) {

    }

    @Override
    public void setDialogBoxTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {

    }

    @Override
    public void setSpeakerNameTransparencyInstant(float toTransparency) {

    }

    @Override
    public void setTransitionColor(int color) {

    }

    @Override
    public void setVariable(String name, Object value) {

    }
}
