package com.dmu.sash.flprdcrds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class WordAdapter extends RealmBaseAdapter<Word> implements ListAdapter{

    private MainActivity activity;

    private static class ViewHolder{
        TextView wordWithDef;
    }

    WordAdapter(MainActivity mainActivity, OrderedRealmCollection<Word> data){
        super(data);
        this.activity = mainActivity;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.wordWithDef = convertView.findViewById(R.id.fetched_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (adapterData != null) {
            Word word = adapterData.get(position);
            String text = word.getWord() + word.getDefinition();
            viewHolder.wordWithDef.setText(text);
        }
        return convertView;
    }
}
