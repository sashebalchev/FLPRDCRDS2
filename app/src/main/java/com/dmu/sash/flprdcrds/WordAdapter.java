package com.dmu.sash.flprdcrds;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Type;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class WordAdapter extends RealmBaseAdapter<Word> implements ListAdapter{

    private int fontColor;
    private int backgroundColor;
    private Typeface typeface;
    private Context context;

    private static class ViewHolder{
        TextView wordWithDef;
        ImageButton pronunciation;
        LinearLayout layout;
    }

    WordAdapter(Fragment fragment, OrderedRealmCollection<Word> data, Activity activity){
        super(data);
        context = activity;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String font = sharedPreferences.getString("PREF_STYLE_FONT", "1");
        if (font.equals("1")){
            typeface = Typeface.DEFAULT;
        } else if (font.equals("2")){
            typeface = ResourcesCompat.getFont(activity, R.font.hanalei_font_family);
        }
        backgroundColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF"));
        fontColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.layout = convertView.findViewById(R.id.word_row);
            viewHolder.layout.setBackgroundColor(backgroundColor);
            viewHolder.wordWithDef = convertView.findViewById(R.id.fetched_text);
            viewHolder.wordWithDef.setTextColor(fontColor);
            viewHolder.wordWithDef.setTypeface(typeface);
            viewHolder.pronunciation = convertView.findViewById(R.id.pronunciation);
            viewHolder.pronunciation.setOnClickListener(listener);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (adapterData != null) {
            Word word = adapterData.get(position);
            String text = word.getWord() + " - " + word.getDefinition();
            viewHolder.wordWithDef.setText(text);
            viewHolder.pronunciation.setTag(position);
        }
        return convertView;
    }
    private View.OnClickListener listener = (View view) -> {
        int position = (Integer) view.getTag();
        if (adapterData != null) {
            Word word = adapterData.get(position);
            AudioPronunciation audioPronunciation = new AudioPronunciation(context);
            audioPronunciation.playPronunciation(word.getId());
        }
    };
}
