package com.dmu.sash.flprdcrds;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmConfiguration;

public class WordCardAdapter extends RealmBaseAdapter<Word> {

    private Realm realm;
    private Context context;
    private GestureDetector gestureDetector;
    private AdapterViewFlipper viewFlipper;

    class ViewHolder {
        TextView word, definition;
        ViewFlipper flipView;
        Button dontKnowWordButton, knowWordButton;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }

    WordCardAdapter(android.support.v4.app.Fragment fragment, OrderedRealmCollection<Word> data, Context context, AdapterViewFlipper viewFlipper) {
        super(data);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("flprcrds.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);
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





            viewHolder.flipView.setInAnimation(context, R.anim.animate_flip_in);
            viewHolder.flipView.setOutAnimation(context, R.anim.animate_flip_out);



            viewHolder.dontKnowWordButton = convertView.findViewById(R.id.dont_know_word);
            viewHolder.knowWordButton = convertView.findViewById(R.id.know_word);

            viewHolder.dontKnowWordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decreaseScore(adapterData.get(position).getId());
//                    System.out.println(adapterData.get(position).getWord()+ adapterData.get(position).getScore());
                    viewFlipper.showNext();
                }
            });
            viewHolder.knowWordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    increaseScore(adapterData.get(position).getId());
//                    System.out.println(adapterData.get(position).getWord()+ adapterData.get(position).getScore());
                    viewFlipper.showNext();
                }
            });

            viewHolder.flipView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.flipView.showNext();
                }
            });
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

    private void decreaseScore(String id) {
        realm.executeTransactionAsync((realm) -> {
            Objects.requireNonNull(realm.where(Word.class).equalTo("id", id)
                    .findFirst())
                    .decreaseScore();
        });
    }
    private void increaseScore(String id) {
        realm.executeTransactionAsync((realm) -> {
            Objects.requireNonNull(realm.where(Word.class).equalTo("id", id)
                    .findFirst())
                    .increaseScore();
        });
    }
}
