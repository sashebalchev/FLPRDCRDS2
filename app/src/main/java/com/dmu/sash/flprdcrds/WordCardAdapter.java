package com.dmu.sash.flprdcrds;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;
import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmCollection;
import io.realm.RealmConfiguration;

public class WordCardAdapter extends ArrayAdapter<Word>{

    private Realm realm;
    private Context context;
    private GestureDetector gestureDetector;
    private AdapterViewFlipper viewFlipper;
    private SharedPreferences sharedPreferences;

    private int bgColor;
    private int fontColor;
    private Typeface typeface;

    class ViewHolder {
        TextView word, definition;

        EasyFlipView flipView;
        Button dontKnowWordButton, knowWordButton;
        ImageButton pronunciation;
    }

    WordCardAdapter(android.support.v4.app.Fragment fragment, List<Word> data, Context context, AdapterViewFlipper viewFlipper) {
        super(context, R.layout.word_card, data);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("flprcrds.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfig);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        bgColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_BG", "#FFFFFF"));
        fontColor = Color.parseColor(sharedPreferences.getString("PREF_COLOR_FONT", "#000000"));
        String font = sharedPreferences.getString("PREF_STYLE_FONT", "1");
        if (font.equals("1")) {
            typeface = Typeface.DEFAULT;
        } else if (font.equals("2")) {
            typeface = ResourcesCompat.getFont(context, R.font.hanalei_font_family);
        }

        this.context = context;


//        System.out.println(words.get(0).getWord());
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
            viewHolder.pronunciation = convertView.findViewById(R.id.pronunciation_card_front);
            setPreferences(viewHolder.word, viewHolder.definition, viewHolder.pronunciation);
            viewHolder.pronunciation.setOnClickListener(listener);
//            viewHolder.pronunciation2 = convertView.findViewById(R.id.pronunciation_card_back);
//            viewHolder.pronunciation2.setOnClickListener(listener);


//            viewHolder.flipView.setInAnimation(context, R.anim.animate_flip_in);
//            viewHolder.flipView.setOutAnimation(context, R.anim.animate_flip_out);


            viewHolder.dontKnowWordButton = convertView.findViewById(R.id.dont_know_word);
            viewHolder.knowWordButton = convertView.findViewById(R.id.know_word);

            viewHolder.dontKnowWordButton.setOnClickListener(v -> {
                Word currentWord = getItem(position);
                rankDown(currentWord.getId());
                this.remove(currentWord);

            });

            viewHolder.knowWordButton.setOnClickListener(v -> {
                Word currentWord = getItem(position);
                rankUp(currentWord.getId());
                this.remove(currentWord);

            });

//            viewHolder.flipView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    viewHolder.flipView.flipTheView();
//                }
//            });


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (getCount() > 0) {
            //TODO nullpointer when word is out of bounds (out of current session bounds)
            Word wordFromData = getItem(position);
            viewHolder.word.setText(wordFromData.getWord());
            viewHolder.definition.setText(wordFromData.getDefinition());
            viewHolder.pronunciation.setTag(position);
        } else {
            System.out.println("NO MORE DATA");
        }
        return convertView;
    }

    private void setPreferences(TextView word, TextView definition, ImageButton pronunciationButton) {
        word.setBackgroundColor(bgColor);
        word.setTextColor(fontColor);
        word.setTypeface(typeface);
        definition.setBackgroundColor(bgColor);
        definition.setTextColor(fontColor);
        definition.setTypeface(typeface);
        pronunciationButton.setColorFilter(fontColor);
    }

    private void rankDown(String id) {
        realm.executeTransactionAsync((realm) -> Objects.requireNonNull(realm.where(Word.class)
                .equalTo("id", id)
                .findFirst())
                .decreaseScore());
    }

    private void rankUp(String id) {
        realm.executeTransactionAsync((realm) -> Objects.requireNonNull(realm.where(Word.class)
                .equalTo("id", id)
                .findFirst())
                .increaseScore());
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            if (getCount() > 0) {
                new AudioPronunciation(context).playPronunciation(getItem(position).getId());
            }
        }
    };
}