package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveData;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.save.SaveDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.StoryDatabase;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.actor.StoryActor;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings.SettingsMenuFragment;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PlayFragment extends MenuFragment implements VisualNovelInterface {
    final static private String ARGUMENT_STORY_ID = "story_id";
    final static private String ARGUMENT_SAVE_ID = "save_id";

    private View touchDetector;

    private StoryScenePlayer storyScenePlayer;
    private DialogBox dialogBox;
    private SpeakerNameBox speakerNameBox;
    private PlayBackground playBackground;
    private PlayTransition playTransition;
    private ActorSpriteStage actorSpriteStage;
    private MusicPlayer musicPlayer;
    private SoundEffectPlayer soundEffectPlayer;
    private Choice choice;

    public static PlayFragment newInstance(String storyId, int saveId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_STORY_ID, storyId);
        args.putInt(ARGUMENT_SAVE_ID, saveId);

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




        String storyId = getArguments() != null ? getArguments().getString(ARGUMENT_STORY_ID) : null;
        int saveId = getArguments() != null ? getArguments().getInt(ARGUMENT_SAVE_ID) : SaveDatabase.AUTOSAVE_ID;
        SaveData saveData = SaveDatabase.getInstance().getSave(storyId, saveId);

        storyScenePlayer = new StoryScenePlayer(
                this
        );
        dialogBox = new DialogBox(
                view.findViewById(R.id.rlDialogPanel),
                view.findViewById(R.id.tvDialogText)
        );
        speakerNameBox = new SpeakerNameBox(
                view.findViewById(R.id.tvSpeakerName)
        );
        playBackground = new PlayBackground(
                view.findViewById(R.id.ivBackground)
        );
        playTransition = new PlayTransition(
                view.findViewById(R.id.ivTransition)
        );
        actorSpriteStage = new ActorSpriteStage(
                getContext(),
                view.findViewById(R.id.clActorSpriteStage),
                StoryDatabase.getInstance().getStoryById(storyId)
        );
        musicPlayer = new MusicPlayer(
                getContext()
        );
        soundEffectPlayer = new SoundEffectPlayer(
                getContext()
        );
        touchDetector = view.findViewById(R.id.vTouchDetector);
        choice = new Choice(
                view.findViewById(R.id.rlChoice),
                view.findViewById(R.id.bChoice1),
                view.findViewById(R.id.bChoice2),
                view.findViewById(R.id.bChoice3)
        );


        view.findViewById(R.id.bPause).setOnClickListener(v ->
                {
                    autosave();
                    menuFragmentManager.overlayToMenuFragment(PauseMenuFragment.newInstance(
                            storyId,
                            SaveDatabase.AUTOSAVE_ID
                    ));
                }
        );
        view.findViewById(R.id.bSettings).setOnClickListener(v ->
                menuFragmentManager.overlayToMenuFragment(new SettingsMenuFragment())
        );

        try {
            storyScenePlayer.load(saveData);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        play();

        return view;
    }

    public void autosave() {
        SaveDatabase.getInstance().updateSave(
                storyScenePlayer.saveData.storyId,
                SaveDatabase.AUTOSAVE_ID,
                storyScenePlayer.saveData
        );
    }

    public void play() {
        storyScenePlayer.play();
    }

    public void stop() {
        storyScenePlayer.stop();
    }

    @Override
    public void setDialogText(String text) {
        dialogBox.setText(text);
    }

    @Override
    public void typeDialogText(String text, float charactersPerSecond, Runnable finished) {
        dialogBox.typeText(
                text,
                charactersPerSecond,
                finished
        );
    }

    @Override
    public void setSpeakerName(String text) {
        speakerNameBox.setName(text);
    }

    @Override
    public void setBackground(String storyPath) {
        String assetPath = getAssetPathFromStoryPath(storyPath);
                ;
        AssetManager assetManager = getContext().getApplicationContext().getAssets();
        InputStream inputStream = null;
        Bitmap image;
        try {
            inputStream = assetManager.open(assetPath);
            image = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        playBackground.setBackground(image);
    }

    @Override
    public void setBackgroundTransparencyInstant(float toTransparency) {
        playBackground.setTransparencyInstant(toTransparency);
    }

    @Override
    public void setBackgroundTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        playBackground.setTransparencyTween(
                toTransparency,
                milliseconds,
                finished
        );
    }

    @Override
    public void addActorSprite(StoryActor actor, String expression, String position) {
        actorSpriteStage.addActor(
                actor,
                expression,
                position
        );
    }

    @Override
    public void setActorSpriteTransparencyInstant(StoryActor actor, float toTransparency) {
        actorSpriteStage.setActorTransparencyInstant(
                actor,
                toTransparency
        );
    }

    @Override
    public void setActorSpriteTransparencyTween(StoryActor actor, float toTransparency, int milliseconds, Runnable finished) {
        actorSpriteStage.setActorTransparencyTween(
                actor,
                toTransparency,
                milliseconds,
                finished
        );
    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, int x, int y) {
        actorSpriteStage.setActorPositionInstant(
                actor,
                x,
                y
        );
    }

    @Override
    public void setActorSpritePositionInstant(StoryActor actor, String toPosition) {
        actorSpriteStage.setActorPositionInstant(
                actor,
                toPosition
        );
    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, int x, int y, int milliseconds, Runnable finished) {
        actorSpriteStage.setActorPositionTween(
                actor,
                x,
                y,
                milliseconds,
                finished
        );
    }

    @Override
    public void setActorSpritePositionTween(StoryActor actor, String toPosition, int milliseconds, Runnable finished) {
        actorSpriteStage.setActorPositionTween(
                actor,
                toPosition,
                milliseconds,
                finished
        );
    }

    @Override
    public void setActorSpriteExpressionInstant(StoryActor actor, String expression) {
        actorSpriteStage.setActorExpressionInstant(
                actor,
                expression
        );
    }

    @Override
    public void setActorSpriteExpressionTween(StoryActor actor, String expression, int milliseconds, Runnable finished) {
        actorSpriteStage.setActorExpressionTween(
                actor,
                expression,
                milliseconds,
                finished
        );
    }

    @Override
    public void removeActorSprite(StoryActor actor) {
        actorSpriteStage.removeActor(actor);
    }

    @Override
    public void waitForContinue(Runnable finished) {
        touchDetector.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                finished.run();
                touchDetector.setOnTouchListener(null);
            }
            return true;
        });
    }

    @Override
    public void setTransitionTransparencyInstant(float toTransparency) {
        playTransition.setTransparencyInstant(toTransparency);
    }

    @Override
    public void setTransitionTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        playTransition.setTransparencyTween(
                toTransparency,
                milliseconds,
                finished
        );
    }

    @Override
    public void playMusic(String storyPath) {
        musicPlayer.load(getAssetPathFromStoryPath(storyPath));
        musicPlayer.play();
    }

    @Override
    public void playSoundEffect(String storyPath) {
        soundEffectPlayer.load(getAssetPathFromStoryPath(storyPath));
        soundEffectPlayer.play();
    }

    @Override
    public void stopMusic() {
        musicPlayer.stop();
    }

    @Override
    public void setDialogBoxTransparencyInstant(float toTransparency) {
        dialogBox.setTransparencyInstant(toTransparency);
    }

    @Override
    public void setDialogBoxTransparencyTween(float toTransparency, int milliseconds, Runnable finished) {
        dialogBox.setTransparencyTween(
                toTransparency,
                milliseconds,
                finished
        );
    }

    @Override
    public void setSpeakerNameTransparencyInstant(float toTransparency) {
        speakerNameBox.setTransparencyInstant(toTransparency);
    }

    @Override
    public void setTransitionColor(int color) {
        playTransition.setColor(color);
    }

    @Override
    public void askChoice(List<String> choices, Runnable finished) {
        choice.ask(
                choices,
                finished
        );
    }

    @Override
    public String getPickedChoice() {
        return choice.pickedChoice;
    }

    public String getAssetPathFromStoryPath(String storyPath) {
        return StoryDatabase.getInstance().getStoryById(
                storyScenePlayer.saveData.storyId
        ).storyDataAssetPath + "/" + storyPath;
    }
}
