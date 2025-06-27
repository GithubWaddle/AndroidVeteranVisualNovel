package com.androidveteranvisualnovel.AndroidVeteranVisualNovel.menufragment.settings;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidveteranvisualnovel.AndroidVeteranVisualNovel.R;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<SettingsMenuFragment.OptionProperties> items;
    private final LayoutInflater inflater;

    public SettingsAdapter(Context context, List<SettingsMenuFragment.OptionProperties> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type.ordinal();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SettingsMenuFragment.OptionType type = SettingsMenuFragment.OptionType.values()[viewType];
        View view;

        switch (type) {
            case TITLE:
                view = inflater.inflate(R.layout.settings_menu_option_title, parent, false);
                return new TitleViewHolder(view);
            case SLIDER:
                view = inflater.inflate(R.layout.settings_menu_option_slider, parent, false);
                return new SliderViewHolder(view);
            case SWITCH:
                view = inflater.inflate(R.layout.settings_menu_option_switch, parent, false);
                return new SwitchViewHolder(view);
            case RADIO:
                view = inflater.inflate(R.layout.settings_menu_option_radio, parent, false);
                return new RadioViewHolder(view);
            default:
                throw new IllegalStateException("Unknown type: " + type);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SettingsMenuFragment.OptionProperties item = items.get(position);

        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).label.setText(item.label);
        } else if (holder instanceof SliderViewHolder) {
            ((SliderViewHolder) holder).label.setText(item.label);
        } else if (holder instanceof SwitchViewHolder) {
            ((SwitchViewHolder) holder).label.setText(item.label);
        } else if (holder instanceof RadioViewHolder) {
            RadioViewHolder radioViewHolder = (RadioViewHolder) holder;
            radioViewHolder.label.setText(item.label);
            radioViewHolder.llSelections.removeAllViews();
            for (Object option : item.data) {
                Button button = new Button(new ContextThemeWrapper(radioViewHolder.itemView.getContext(), R.style.menu_button_primary_border), null, 0);
                button.setText((String) option);
                radioViewHolder.llSelections.addView(button);
            }
        }
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        TitleViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tvLabel);
        }
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        SliderViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tvLabel);
        }
    }

    static class SwitchViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        SwitchViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tvLabel);
        }
    }

    static class RadioViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        LinearLayout llSelections;
        RadioViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tvLabel);
            llSelections = itemView.findViewById(R.id.llSelections);
        }
    }
}
