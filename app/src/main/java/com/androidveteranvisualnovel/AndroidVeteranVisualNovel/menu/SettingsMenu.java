package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menu;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SettingsMenu extends Menu {
    public enum SettingsTab {
        GENERAL,
        VIDEOAUDIO,
        LANGUAGE,
        ACCESSIBILITY
    }
    public enum OptionType {
        SWITCH,
        SLIDER,
        RADIO,
        TITLE
    }

    public static class OptionProperties {
        public String label;
        public OptionType type;
        public List data = null;

        public OptionProperties(String label, OptionType type, List data) {
            this.label = label;
            this.type = type;
            this.data = data;
        }
        public OptionProperties(String label, OptionType type) {
            this.label = label;
            this.type = type;
        }
    }

    public static final Map<SettingsTab, OptionProperties[]> settingsContent = Map.of(
            SettingsTab.GENERAL, new OptionProperties[]{
                    new OptionProperties(
                            "Text Speed",
                            OptionType.SLIDER
                    ),
                    new OptionProperties(
                            "Skip Unseen Text",
                            OptionType.SWITCH
                    ),
            },
            SettingsTab.VIDEOAUDIO, new OptionProperties[]{
                    new OptionProperties(
                            "Video",
                            OptionType.TITLE
                    ),
                    new OptionProperties(
                            "Image Quality",
                            OptionType.RADIO,
                            Arrays.asList("Low", "Medium", "High")
                    ),
                    new OptionProperties(
                            "Video Effects",
                            OptionType.SWITCH
                    ),
                    new OptionProperties(
                            "Audio",
                            OptionType.TITLE
                    ),
                    new OptionProperties(
                            "BGM Volume",
                            OptionType.SLIDER
                    ),
                    new OptionProperties(
                            "Voice Volume",
                            OptionType.SLIDER
                    ),
            },
            SettingsTab.LANGUAGE, new OptionProperties[]{
                    new OptionProperties(
                            "English",
                            OptionType.SWITCH
                    ),
                    new OptionProperties(
                            "Bahasa Indonesia",
                            OptionType.SWITCH
                    ),
            },
            SettingsTab.ACCESSIBILITY, new OptionProperties[]{
                    new OptionProperties(
                            "High Contrast Text",
                            OptionType.SWITCH
                    ),
                    new OptionProperties(
                            "Text Size",
                            OptionType.RADIO,
                            Arrays.asList("Small", "Medium", "Large")
                    )
            }

    );
    LinearLayout llContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_menu, container,false);

        llContent = view.findViewById(R.id.llContent);

        // binding buttons
        ImageButton ibBack = view.findViewById(R.id.ibBack);
        Button bGeneral = view.findViewById(R.id.bGeneral);
        Button bVideoAndAudio = view.findViewById(R.id.bVideoAndAudio);
        Button bLanguage = view.findViewById(R.id.bLanguage);
        Button bAccessibility = view.findViewById(R.id.bAccessibility);

        ibBack.setOnClickListener(v -> {
           requireActivity().getOnBackPressedDispatcher().onBackPressed();
        });

        bGeneral.setOnClickListener(v -> {
            setContent(SettingsTab.GENERAL);
        });
        bVideoAndAudio.setOnClickListener(v -> {
            setContent(SettingsTab.VIDEOAUDIO);
        });
        bLanguage.setOnClickListener(v -> {
            setContent(SettingsTab.LANGUAGE);
        });
        bAccessibility.setOnClickListener(v -> {
            setContent(SettingsTab.ACCESSIBILITY);
        });

        setContent(SettingsTab.GENERAL);

        return view;
    }

    public void setContent(SettingsTab tab) {
        llContent.removeAllViews();

        for (OptionProperties optionProperty : Objects.requireNonNull(settingsContent.get(tab))) {
            View llOption = null;

            switch (optionProperty.type) {
                case TITLE:
                    llOption = getActivity().getLayoutInflater().inflate(R.layout.settings_menu_option_title, llContent, false);
                    break;
                case SLIDER:
                    llOption = getActivity().getLayoutInflater().inflate(R.layout.settings_menu_option_slider, llContent, false);
                    break;
                case SWITCH:
                    llOption = getActivity().getLayoutInflater().inflate(R.layout.settings_menu_option_switch, llContent, false);
                    break;
                case RADIO:
                    llOption = getActivity().getLayoutInflater().inflate(R.layout.settings_menu_option_radio, llContent, false);
                    LinearLayout llSelections = llOption.findViewById(R.id.llSelections);
                    for (Object selection : optionProperty.data) {
                        Button bSelection = new Button(new ContextThemeWrapper(getActivity().getBaseContext(), R.style.menu_button_primary_border), null, 0);
                        bSelection.setText((String) selection);
                        llSelections.addView(bSelection);
                    }
                    break;
            }

            TextView label = llOption.findViewById(R.id.tvLabel);
            label.setText(optionProperty.label);

            llContent.addView(llOption);
        }
    }


}
