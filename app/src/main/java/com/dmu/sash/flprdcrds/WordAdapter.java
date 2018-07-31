package com.dmu.sash.flprdcrds;

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


    private static class ViewHolder{
        TextView wordWithDef;
        ImageButton pronunciation;
    }

    WordAdapter(Fragment fragment, OrderedRealmCollection<Word> data){
        super(data);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.wordWithDef = convertView.findViewById(R.id.fetched_text);
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
