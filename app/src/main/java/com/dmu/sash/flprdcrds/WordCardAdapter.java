package com.dmu.sash.flprdcrds;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ViewFlipper;

import com.dmu.sash.flprdcrds.Word;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmRecyclerViewAdapter;

public  class WordCardAdapter extends  RealmBaseAdapter<Word> {




    private Context context;
    private GestureDetector gestureDetector;
    private AdapterViewFlipper viewFlipper;

    class ViewHolder {
        TextView word, definition;
        ViewFlipper flipView;
        Button nextWordButton;
    }
    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
    WordCardAdapter(android.support.v4.app.Fragment fragment, OrderedRealmCollection<Word> data, Context context, AdapterViewFlipper viewFlipper) {
        super(data);
        this.context = context;
        this.viewFlipper = viewFlipper;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_card, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.flipView = convertView.findViewById(R.id.flipper_single);
            viewHolder.word = convertView.findViewById(R.id.front);
            viewHolder.definition = convertView.findViewById(R.id.back);
            viewHolder.nextWordButton = convertView.findViewById(R.id.next_word_button);
            viewHolder.nextWordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewFlipper.showNext();
                }
            });
            viewHolder.flipView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.flipView.showNext();
                }
            });
//            viewHolder.definition = LayoutInflater.from(viewHolder.flipView.getContext())
//            viewHolder.flipView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (gestureDetector.onTouchEvent(event)) {
//                        viewHolder.flipView.flipTheView();
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            });
//            viewHolder.flipView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewHolder.flipView.flipTheView();
////                    if (viewHolder.definition.getVisibility()==View.GONE){
//////                        viewHolder.definition.setVisibility(View.VISIBLE);
//////                        viewHolder.word.setVisibility(View.GONE);
//////                    } if (viewHolder.definition.getVisibility()==View.VISIBLE){
//////                        viewHolder.definition.setVisibility(View.GONE);
//////                        viewHolder.word.setVisibility(View.VISIBLE);
//////                    }
//                }
//            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (adapterData != null) {
            Word wordFromData = adapterData.get(position);
            viewHolder.word.setText(wordFromData.getWord());
            viewHolder.definition.setText(wordFromData.getDefinition());
        }
        return convertView;
    }

}
