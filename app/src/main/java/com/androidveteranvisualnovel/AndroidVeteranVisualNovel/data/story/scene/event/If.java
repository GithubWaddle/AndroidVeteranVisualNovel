package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.data.story.scene.event;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.StoryScenePlayer;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play.VisualNovelInterface;

import java.util.Objects;

public class If extends StorySceneEvent {
    public String operandLeft;
    public String operandRight;
    public String operator;
    public String jumpLabel;

    public If(String operandLeft, String operator, String operandRight, String jumpLabel) {
        this.operandLeft = operandLeft;
        this.operator = operator;
        this.operandRight = operandRight;
        this.jumpLabel = jumpLabel;
    }

    @Override
    public void execute(VisualNovelInterface visualNovel, StoryScenePlayer storyScenePlayer, Runnable finished) {
        super.execute(visualNovel, storyScenePlayer, finished);

        boolean resultant = false;
        switch (operator) {
            case "==":
                resultant = Objects.equals(operandLeft, operandRight);
                break;

            case ">":
                resultant = Float.parseFloat(operandLeft) > Float.parseFloat(operandRight);
                break;

            case "<":
                resultant = Float.parseFloat(operandLeft) < Float.parseFloat(operandRight);
                break;
        }

        if (resultant) {
            Jump jump = new Jump(jumpLabel);
            jump.execute(
                    visualNovel,
                    storyScenePlayer,
                    finished
            );
            return;
        }


        finished.run();
    }
}
