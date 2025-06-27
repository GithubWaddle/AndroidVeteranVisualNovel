package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.play;

import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Choice {
    private ConstraintLayout clChoice;
    private Button bChoice1;
    private Button bChoice2;
    private Button bChoice3;

    private Runnable choicePicked;
    public String pickedChoice = "";

    public Choice(
            ConstraintLayout clChoice,
            Button bChoice1,
            Button bChoice2,
            Button bChoice3
    ) {
        this.clChoice = clChoice;
        this.bChoice1 = bChoice1;
        this.bChoice2 = bChoice2;
        this.bChoice3 = bChoice3;
    }

    public void show() {
        clChoice.setVisibility(View.VISIBLE);
    }

    public void hide() {
        clChoice.setVisibility(View.GONE);
    }

    public void ask(List<String> choices, Runnable finished) {
        bChoice1.setVisibility(View.GONE);
        bChoice2.setVisibility(View.GONE);
        bChoice3.setVisibility(View.GONE);
        Button[] buttons = {bChoice1, bChoice2, bChoice3};

        for (int i = 0; i < choices.size() && i < 3; i++) {
            String entry = choices.get(i);

            Button button = buttons[i];
            button.setText(entry);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(v -> {
                hide();
                pickedChoice = entry;
                choicePicked.run();
            });
        }

        choicePicked = finished;
        show();
    }
}
