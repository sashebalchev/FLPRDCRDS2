package com.dmu.sash.flprdcrds.learning;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dmu.sash.flprdcrds.R;
import com.dmu.sash.flprdcrds.database.RealmFactory;
import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.helpers.AudioPronunciation;
import com.dmu.sash.flprdcrds.settings.PreferencesProvider;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;
import java.util.Objects;

import io.realm.Realm;

public class WordCardAdapter extends ArrayAdapter<Word> implements ListAdapter {

    private Realm realm;
    private Context context;
    private int numberOfWords;

    private int bgColor;
    private int fontColor;
    private Typeface typeface;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            Word wordToPronounce = getItem(position);
            if (getCount() > 0) {
                String url = wordToPronounce.getAudioPronunciation();
                AudioPronunciation.getInstance().play(url, context);
            }
        }
    };

    WordCardAdapter(Context context, List<Word> data) {
        super(context, R.layout.word_card, data);
        realm = RealmFactory.getRealm();
        this.context = context;
        numberOfWords = getCount();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.word_card, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.flipView = convertView.findViewById(R.id.flipper_single);
            viewHolder.progressBar = convertView.findViewById(R.id.learning_prograssbar);
            viewHolder.progressBar.setMax(numberOfWords);
            viewHolder.progressBar.setProgress(numberOfWords - getCount());
            viewHolder.dontKnowWordButton = convertView.findViewById(R.id.dont_know_word);
            viewHolder.knowWordButton = convertView.findViewById(R.id.know_word);
            viewHolder.flipView.setOnClickListener(v -> viewHolder.flipView.flipTheView());
            viewHolder.word = convertView.findViewById(R.id.front);
            viewHolder.definition = convertView.findViewById(R.id.back);
            viewHolder.pronunciation = convertView.findViewById(R.id.pronunciation_card_front);
            setPreferences(viewHolder.word, viewHolder.definition, viewHolder.pronunciation);
            viewHolder.pronunciation.setOnClickListener(listener);


            viewHolder.dontKnowWordButton.setOnClickListener(v -> {
                Word currentWord = getItem(position);
                rankDown(currentWord.getId());
                this.remove(currentWord);
                viewHolder.progressBar.setProgress(numberOfWords - getCount());
            });

            viewHolder.knowWordButton.setOnClickListener(v -> {
                Word currentWord = getItem(position);
                rankUp(currentWord.getId());
                this.remove(currentWord);
                viewHolder.progressBar.setProgress(numberOfWords - getCount());
            });

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (getCount() > 0) {
            Word wordFromData = getItem(position);
            viewHolder.word.setText(wordFromData.getWord());
            viewHolder.definition.setText(wordFromData.getDefinition());
            viewHolder.pronunciation.setTag(position);
        } else {
            System.out.println("NO MORE DATA");
            convertView.setVisibility(View.GONE);
            SessionManager.getInstance(context).nextSession();
        }
        return convertView;
    }

    private void setPreferences(TextView word, TextView definition, ImageButton pronunciationButton) {
        PreferencesProvider preferencesProvider = new PreferencesProvider(context);
        bgColor = preferencesProvider.getBackgroundColorPreference();
        fontColor = preferencesProvider.getFontColorPreference();
        String font = preferencesProvider.getFontStylePreference();
        if (font.equals("1")) {
            typeface = Typeface.DEFAULT;
        } else if (font.equals("2")) {
            typeface = ResourcesCompat.getFont(context, R.font.hanalei_font_family);
        }

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

    class ViewHolder {
        TextView word, definition;
        ProgressBar progressBar;
        EasyFlipView flipView;
        Button dontKnowWordButton, knowWordButton;
        ImageButton pronunciation;
    }
}