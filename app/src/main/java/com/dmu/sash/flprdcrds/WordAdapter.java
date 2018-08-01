package com.dmu.sash.flprdcrds;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class WordAdapter extends RealmBaseAdapter<Word> implements ListAdapter{

    private int fontColor;
    private int backgroundColor;

    private static class ViewHolder{

        TextView wordWithDef;
        ImageButton pronunciation;
    }

    WordAdapter(Fragment fragment, OrderedRealmCollection<Word> data, Activity activity){
        super(data);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        backgroundColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_BGs", "#FFFFFF"));
//        String fontColor = sharedPreferences.getString("PREF_COLOR_FONT", "Black");
//        int test = Color.parseColor(fontColor);
//        System.out.println(test);
        fontColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.wordWithDef = convertView.findViewById(R.id.fetched_text);
            viewHolder.wordWithDef.setTextColor(fontColor);
            viewHolder.wordWithDef.setBackgroundColor(backgroundColor);
//            viewHolder.pronunciation = convertView.findViewById(R.id.pronunciation);
//            viewHolder.pronunciation.setOnClickListener(listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (adapterData != null) {
            Word word = adapterData.get(position);
            String text = word.getWord() + " - " + word.getDefinition();
            viewHolder.wordWithDef.setText(text);
//            viewHolder.pronunciation.setTag(position);
        }
        return convertView;
    }
    private View.OnClickListener listener = (View view) -> {
        int position = (Integer) view.getTag();
        if (adapterData != null) {
            Word word = adapterData.get(position);
            AudioPronunciation audioPronunciation = new AudioPronunciation();
            audioPronunciation.playPronunciation(word.getId());
        }
    };
}
