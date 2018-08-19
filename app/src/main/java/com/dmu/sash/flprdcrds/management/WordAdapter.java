package com.dmu.sash.flprdcrds.management;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.helpers.AudioPronunciation;
import com.dmu.sash.flprdcrds.settings.PreferencesProvider;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class WordAdapter extends RealmBaseAdapter<Word> implements ListAdapter {

    private int fontColor;
    private int backgroundColor;
    private Typeface typeface;
    private Context context;

    private static class ViewHolder {
        TextView wordWithDef;
        ImageButton pronunciation;
        LinearLayout layout;
    }

    WordAdapter(OrderedRealmCollection<Word> data, Context context) {
        super(data);
        this.context = context;
        PreferencesProvider preferencesProvider = new PreferencesProvider(context);
        backgroundColor = preferencesProvider.getBackgroundColorPreference();
        fontColor = preferencesProvider.getFontColorPreference();
        String font = preferencesProvider.getFontStylePreference();
        if (font.equals("1")) {
            typeface = Typeface.DEFAULT;
        } else if (font.equals("2")) {
            typeface = ResourcesCompat.getFont(context, R.font.hanalei_font_family);
        }
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
            viewHolder.pronunciation.setColorFilter(fontColor);
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
        Word wordToPronounce = getItem(position);
        String url = wordToPronounce.getAudioPronunciation();
        if (getCount() > 0) {
            AudioPronunciation.getInstance().play(url, context);
        }
    };
}
