package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.AddActorSprite;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Choice;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.If;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Jump;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Label;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.PlayMusic;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.PlaySoundEffect;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.RemoveActorSprite;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteExpressionInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteExpressionTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpritePositionInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpritePositionTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetActorSpriteTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundImage;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetBackgroundTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetDialogBoxTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetDialogBoxTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetSpeakerNameText;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetSpeakerNameTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionColor;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionTransparencyInstant;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetTransitionTransparencyTween;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.SetVariable;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.StopMusic;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.StorySceneEvent;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Switch;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Talk;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event.Wait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorySceneFileLoader {
    static enum TokenizeMode {
        NORMAL,
        STRING
    }
    static final char TOKEN_SEPERATOR = ' ';
    static final char STRING_WRAPPER = '"';
    static final char COMMENT_HEADER = '#';
	public static StoryScene load(String assetPath, Context context) throws IOException {
        StoryScene scene = new StoryScene();

        try (InputStream sceneFileStream = context.getAssets().open(assetPath)) {
            ArrayList<ArrayList<String>> tokens = tokenize(sceneFileStream);
            scene.events = compile(tokens);
        }

        return scene;
    }

    private static ArrayList<ArrayList<String>> tokenize(InputStream sceneFileStream) throws IOException {
        ArrayList<ArrayList<String>> tokens = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(sceneFileStream));
        String line;

        StringBuilder tokenBuilder = new StringBuilder();
        TokenizeMode tokenizeMode = TokenizeMode.NORMAL;

        while ((line = reader.readLine()) != null) {
            if (line.isBlank()) {
                continue;
            }
            ArrayList<String> tokenLine = new ArrayList<>();

            for (int index = 0; index < line.length(); index++) {
                char character = line.charAt(index);

                switch (tokenizeMode) {
                    case NORMAL:
                        switch (character) {
                            case TOKEN_SEPERATOR:
                                tokenLine.add(tokenBuilder.toString());
                                tokenBuilder.delete(0, tokenBuilder.length());
                                continue;
                            case STRING_WRAPPER:
                                tokenizeMode = TokenizeMode.STRING;
                                continue;
                            case COMMENT_HEADER:
                                index = line.length();
                                continue;
                        }
                        break;

                    case STRING:
                        if (character == STRING_WRAPPER) {
                            tokenizeMode = TokenizeMode.NORMAL;
                            continue;
                        }
                        break;
                }

                tokenBuilder.append(character);
            }

            if (tokenBuilder.length() > 0) {
                tokenLine.add(tokenBuilder.toString());
                tokenBuilder.delete(0, tokenBuilder.length());
            }

            if (tokenLine.size() == 0) {
                continue;
            }
            tokens.add(tokenLine);
        }

        return tokens;
    }

    private static ArrayList<StorySceneEvent> compile(ArrayList<ArrayList<String>> tokens) {
        ArrayList<StorySceneEvent> sceneEvents = new ArrayList<>();

        for (ArrayList<String> tokenLine : tokens) {
            StorySceneEvent event = compileTokenLine(tokenLine);
            if (event == null) {
                return null;
            }

            sceneEvents.add(event);
        }

        return sceneEvents;
    }
    private static StorySceneEvent compileTokenLine(ArrayList<String> tokenLine) {
        switch (tokenLine.get(0)) {
            case "switch":
                return Compile.toSwitch(tokenLine);
            case "if":
                return Compile.toIf(tokenLine);
            case "choice":
                return Compile.choice(tokenLine);
            case "jump":
                return Compile.jump(tokenLine);
            case "label":
                return Compile.label(tokenLine);
            case "wait":
                return Compile.toWait(tokenLine);
            case "talk":
                return Compile.talk(tokenLine);
            case "add":
                switch (tokenLine.get(1)) {
                    case "actorSprite":
                        return Compile.addActorSprite(tokenLine);
                }
                break;
            case "remove":
                switch (tokenLine.get(1)) {
                    case "actorSprite":
                        return Compile.removeActorSprite(tokenLine);
                }
                break;
            case "play":
                switch (tokenLine.get(1)) {
                    case "music":
                        return Compile.playMusic(tokenLine);
                    case "soundEffect":
                        return Compile.playSoundEffect(tokenLine);
                }
                break;
            case "stop":
                switch (tokenLine.get(1)) {
                    case "music":
                        return Compile.stopMusic(tokenLine);
                }
                break;
            case "set":
                switch (tokenLine.get(1)) {
                    case "variable":
                        return Compile.setVariable(tokenLine);
                    case "dialogBox":
                        switch (tokenLine.get(2)) {
                            case "transparency":
                                return Compile.setDialogBoxTransparency(tokenLine);
                        }
                        break;
                    case "speakerName":
                        switch (tokenLine.get(2)) {
                            case "text":
                                return Compile.setSpeakerNameText(tokenLine);
                            case "transparency":
                                return Compile.setSpeakerNameTransparency(tokenLine);
                        }
                    case "background":
                        switch (tokenLine.get(2)) {
                            case "image":
                                return Compile.setBackgroundImage(tokenLine);
                            case "transparency":
                                return Compile.setBackgroundTransparency(tokenLine);
                        }
                        break;
                    case "transition":
                        switch (tokenLine.get(2)) {
                            case "color":
                                return Compile.setTransitionColor(tokenLine);
                            case "transparency":
                                return Compile.setTransitionTransparency(tokenLine);
                        }
                        break;
                    case "actorSprite":
                        switch (tokenLine.get(3)) {
                            case "transparency":
                                return Compile.setActorSpriteTransparency(tokenLine);
                            case "position":
                                return Compile.setActorSpritePosition(tokenLine);
                            case "expression":
                                return Compile.setActorSpriteExpression(tokenLine);
                        }
                        break;
                }
                break;
        }

        Log.e("StorySceneFileLoader", "Token line " + tokenLine.toString() + " is invalid!");
        return null;
    }

    private static class Compile {
        public static StorySceneEvent talk(ArrayList<String> tokenLine) {
            return new Talk(tokenLine.get(1), tokenLine.get(2));
        }

        public static StorySceneEvent setSpeakerNameText(ArrayList<String> tokenLine) {
            return new SetSpeakerNameText(tokenLine.get(3));
        }

        public static StorySceneEvent setSpeakerNameTransparency(ArrayList<String> tokenLine) {
            float transparency = Float.parseFloat(tokenLine.get(3));
            return new SetSpeakerNameTransparencyInstant(transparency);
        }

        public static StorySceneEvent setDialogBoxTransparency(ArrayList<String> tokenLine) {
            float transparency = Float.parseFloat(tokenLine.get(3));
            boolean isTween = Objects.equals(tokenLine.get(4), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(5));
                return new SetDialogBoxTransparencyTween(
                        transparency,
                        milliseconds
                );
            }
            return new SetDialogBoxTransparencyInstant(transparency);
        }

        public static StorySceneEvent setBackgroundImage(ArrayList<String> tokenLine) {
            return new SetBackgroundImage(tokenLine.get(3));
        }

        public static StorySceneEvent setBackgroundTransparency(ArrayList<String> tokenLine) {
            float transparency = Float.parseFloat(tokenLine.get(3));
            boolean isTween = Objects.equals(tokenLine.get(4), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(5));
                return new SetBackgroundTransparencyTween(
                        transparency,
                        milliseconds
                );
            }
            return new SetBackgroundTransparencyInstant(transparency);
        }

        public static StorySceneEvent setTransitionColor(ArrayList<String> tokenLine) {
            int r = Integer.parseInt(tokenLine.get(3));
            int g = Integer.parseInt(tokenLine.get(4));
            int b = Integer.parseInt(tokenLine.get(5));

            return new SetTransitionColor(
                    Color.rgb(r, g, b)
            );
        }

        public static StorySceneEvent setTransitionTransparency(ArrayList<String> tokenLine) {
            float transparency = Float.parseFloat(tokenLine.get(3));
            boolean isTween = Objects.equals(tokenLine.get(4), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(5));
                return new SetTransitionTransparencyTween(
                        transparency,
                        milliseconds
                );
            }
            return new SetTransitionTransparencyInstant(transparency);
        }

        public static StorySceneEvent removeActorSprite(ArrayList<String> tokenLine) {
            return new RemoveActorSprite(tokenLine.get(2));
        }

        public static StorySceneEvent setVariable(ArrayList<String> tokenLine) {
            return new SetVariable(
                    tokenLine.get(2),
                    tokenLine.get(3)
            );
        }

        public static StorySceneEvent addActorSprite(ArrayList<String> tokenLine) {
            return new AddActorSprite(
                    tokenLine.get(2),
                    tokenLine.get(3),
                    tokenLine.get(4)
            );
        }

        public static StorySceneEvent playMusic(ArrayList<String> tokenLine) {
            return new PlayMusic(tokenLine.get(2));
        }

        public static StorySceneEvent playSoundEffect(ArrayList<String> tokenLine) {
            return new PlaySoundEffect(tokenLine.get(2));
        }

        public static StorySceneEvent stopMusic(ArrayList<String> tokenLine) {
            return new StopMusic();
        }

        public static StorySceneEvent setActorSpriteTransparency(ArrayList<String> tokenLine) {
            String actorId = tokenLine.get(2);
            float transparency = Float.parseFloat(tokenLine.get(4));
            boolean isTween = Objects.equals(tokenLine.get(5), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(6));
                return new SetActorSpriteTransparencyTween(
                        actorId,
                        transparency,
                        milliseconds
                );
            }
            return new SetActorSpriteTransparencyInstant(
                    actorId,
                    transparency
            );
        }

        public static StorySceneEvent setActorSpritePosition(ArrayList<String> tokenLine) {
            String actorId = tokenLine.get(2);
            String position = tokenLine.get(4);
            boolean isTween = Objects.equals(tokenLine.get(5), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(6));
                return new SetActorSpritePositionTween(
                        actorId,
                        position,
                        milliseconds
                );
            }
            return new SetActorSpritePositionInstant(
                    actorId,
                    position
            );
        }

        public static StorySceneEvent setActorSpriteExpression(ArrayList<String> tokenLine) {
            String actorId = tokenLine.get(2);
            String expression = tokenLine.get(4);
            boolean isTween = Objects.equals(tokenLine.get(5), "tween");

            if (isTween) {
                int milliseconds = Integer.parseInt(tokenLine.get(6));
                return new SetActorSpriteExpressionTween(
                        actorId,
                        expression,
                        milliseconds
                );
            }
            return new SetActorSpriteExpressionInstant(
                    actorId,
                    expression
            );
        }

        public static StorySceneEvent toWait(ArrayList<String> tokenLine) {
            int milliseconds = Integer.parseInt(tokenLine.get(1));
            return new Wait(milliseconds);
        }

        public static StorySceneEvent toSwitch(ArrayList<String> tokenLine) {
            return new Switch(tokenLine.get(1));
        }

        public static StorySceneEvent toIf(ArrayList<String> tokenLine) {
            String operand1 = tokenLine.get(1);
            String operator = tokenLine.get(2);
            String operand2 = tokenLine.get(3);
            String jumpLabel = tokenLine.get(4);
            return new If(
                    operand1,
                    operator,
                    operand2,
                    jumpLabel
            );
        }

        public static StorySceneEvent choice(ArrayList<String> tokenLine) {
            Map<String, String> choiceToLabel = new HashMap<>();
            choiceToLabel.put(
                    tokenLine.get(1),
                    tokenLine.get(2)
            );
            if (tokenLine.size() > 3) {
                choiceToLabel.put(
                        tokenLine.get(3),
                        tokenLine.get(4)
                );
            }

            if (tokenLine.size() > 5) {
                choiceToLabel.put(
                        tokenLine.get(5),
                        tokenLine.get(6)
                );
            }
            return new Choice(
                    choiceToLabel
            );
        }

        public static StorySceneEvent jump(ArrayList<String> tokenLine) {
            return new Jump(tokenLine.get(1));
        }

        public static StorySceneEvent label(ArrayList<String> tokenLine) {
            return new Label(tokenLine.get(1));
        }
    }
}
