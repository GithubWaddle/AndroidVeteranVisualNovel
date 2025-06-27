package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;
import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.MenuFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SettingsMenuFragment extends MenuFragment {
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
                    new OptionProperties("Text Speed", OptionType.SLIDER),
                    new OptionProperties("Skip Unseen Text", OptionType.SWITCH),
            },
            SettingsTab.VIDEOAUDIO, new OptionProperties[]{
                    new OptionProperties("Video", OptionType.TITLE),
                    new OptionProperties("Image Quality", OptionType.RADIO, Arrays.asList("Low", "Medium", "High")),
                    new OptionProperties("Video Effects", OptionType.SWITCH),
                    new OptionProperties("Audio", OptionType.TITLE),
                    new OptionProperties("BGM Volume", OptionType.SLIDER),
                    new OptionProperties("Voice Volume", OptionType.SLIDER),
            },
            SettingsTab.LANGUAGE, new OptionProperties[]{
                    new OptionProperties("English", OptionType.SWITCH),
                    new OptionProperties("Bahasa Indonesia", OptionType.SWITCH),
            },
            SettingsTab.ACCESSIBILITY, new OptionProperties[]{
                    new OptionProperties("High Contrast Text", OptionType.SWITCH),
                    new OptionProperties("Text Size", OptionType.RADIO, Arrays.asList("Small", "Medium", "Large")),
            }
    );

    private RecyclerView rvContent;
    private SettingsAdapter adapter;

    public static SettingsMenuFragment newInstance() {
        Bundle args = new Bundle();
        SettingsMenuFragment fragment = new SettingsMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings_menu, container, false);

        rvContent = view.findViewById(R.id.rvContent);
        rvContent.setLayoutManager(new LinearLayoutManager(requireContext()));

        view.findViewById(R.id.ibBack).setOnClickListener(v ->
                menuFragmentManager.removeTopMenuFragment()
        );

        view.findViewById(R.id.bGeneral).setOnClickListener(v -> setContent(SettingsTab.GENERAL));
        view.findViewById(R.id.bVideoAndAudio).setOnClickListener(v -> setContent(SettingsTab.VIDEOAUDIO));
        view.findViewById(R.id.bLanguage).setOnClickListener(v -> setContent(SettingsTab.LANGUAGE));
        view.findViewById(R.id.bAccessibility).setOnClickListener(v -> setContent(SettingsTab.ACCESSIBILITY));

        setContent(SettingsTab.GENERAL);

        return view;
    }

    public void setContent(SettingsTab tab) {
        List<OptionProperties> options = Arrays.asList(Objects.requireNonNull(settingsContent.get(tab)));
        adapter = new SettingsAdapter(requireContext(), options);
        rvContent.setAdapter(adapter);
    }
}
