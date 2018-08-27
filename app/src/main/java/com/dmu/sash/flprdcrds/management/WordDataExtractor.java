package com.dmu.sash.flprdcrds.management;

import android.support.annotation.NonNull;

import com.dmu.sash.flprdcrds.database.entities.Word;
import com.dmu.sash.flprdcrds.service.objects.Entry;
import com.dmu.sash.flprdcrds.service.objects.LexicalEntry;
import com.dmu.sash.flprdcrds.service.objects.Pronunciation;
import com.dmu.sash.flprdcrds.service.objects.Response;
import com.dmu.sash.flprdcrds.service.objects.Result;
import com.dmu.sash.flprdcrds.service.objects.Sense;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WordDataExtractor implements AsyncResponse {
    private static final String DEFINITION_URL = "https://od-api.oxforddictionaries.com/api/v1/entries/en/";
    private WordDataResultHandler handler;

    public void extractWordData(@NonNull WordDataResultHandler handler, @NonNull String word) {
        this.handler = handler;
        URLAsyncTask urlAsyncTask = new URLAsyncTask(this);
        urlAsyncTask.execute(DEFINITION_URL + word);
    }

    @Override
    public void processFinish(boolean hasErrors, String output) {
        if (hasErrors) {
            handler.handleWordDataResult(output, null);
        } else {
            Response response = extractResponse(output);
//            String word = extractWord(response);
//            LexicalEntry lexicalEntry = extractLexicalEntry(response);
//            String definition = extractWordData(lexicalEntry);
//            String audioPronunciation = extractAudioPronunciation(lexicalEntry);
            List<Word> words = extractWords(response);
            handler.handleWordDataResult(null, words);
        }
    }

    private Response extractResponse(String output) {
        Gson gson = new Gson();
        return gson.fromJson(output, Response.class);
    }

    private String extractWord(Response response) {
        String word = null;
        if (response != null) {
            Result result = response.getResults().get(0);
            if (result != null) {
                word = result.getWord();
            }
        }
        return word;
    }

    private LexicalEntry extractLexicalEntry(Response response) {
        LexicalEntry lexicalEntry = null;
        if (response != null) {
            Result result = response.getResults().get(0);
            if (result != null) {
                lexicalEntry = result.getLexicalEntries().get(0);
            }
        }
        return lexicalEntry;
    }

    private String extractWordData(LexicalEntry lexicalEntry) {
        String definition = null;
        if (lexicalEntry != null) {
            Entry entry = lexicalEntry.getEntries().get(0);
            if (entry != null) {
                Sense sense = entry.getSenses().get(0);
                if (sense != null) {
                    if (sense.getShort_definitions() != null) {
                        definition = sense.getShort_definitions().get(0);
                    }
                }
            }
        }
        return definition;
    }

    private String extractAudioPronunciation(LexicalEntry lexicalEntry) {
        String audioPronunciation = null;
        List<Pronunciation> pronunciationList;
        for (Entry entry : lexicalEntry.getEntries()) {
            pronunciationList = entry.getPronunciations();
            if (pronunciationList != null) {
                for (Pronunciation pronunciationEntry : pronunciationList) {
                    audioPronunciation = pronunciationEntry.getAudioFile();
                    if (audioPronunciation != null) break;
                }
            }
            if (audioPronunciation != null) break;
        }
        if (audioPronunciation == null) {
            pronunciationList = lexicalEntry.getPronunciations();
            if (pronunciationList != null) {
                for (Pronunciation pronunciationEntry : pronunciationList) {
                    audioPronunciation = pronunciationEntry.getAudioFile();
                    if (audioPronunciation != null) break;
                }
            }
        }
        return audioPronunciation;
    }

    public List<Word> extractWords(Response response) {
        List<Word> words = new ArrayList<>();
        if (response == null) {
            return words;
        }
        Result result = response.getResults().get(0);
        if (result == null) {
            return words;
        }
        String wordStr = result.getWord();
        List<LexicalEntry> lexicalEntries = result.getLexicalEntries();
        if (lexicalEntries == null) {
            return words;
        }
        for (LexicalEntry lexicalEntry : lexicalEntries) {
            List<Entry> entries = lexicalEntry.getEntries();
            if (entries != null) {
                for (Entry entry : entries) {
                    Sense sense = entry.getSenses().get(0);
                    if (sense.getShort_definitions() == null || sense.getShort_definitions().size() == 0) {
                        continue;
                    }
                    Word word = new Word();
                    word.setWord(wordStr);
                    word.setDefinition(sense.getShort_definitions().get(0));
                    List<Pronunciation> pronunciations = sense.getPronunciations();
                    word.setAudioPronunciation(getAudioPronunciation(pronunciations));
                    if (word.getAudioPronunciation() == null) {
                        word.setAudioPronunciation(getAudioPronunciation(entry.getPronunciations()));
                    }
                    words.add(word);
                }
                for (Word word : words) {
                    if (word.getAudioPronunciation() == null) {
                        word.setAudioPronunciation(getAudioPronunciation(lexicalEntry.getPronunciations()));
                    }
                }
            }
        }
        for (Word word : words) {
            if (word.getAudioPronunciation() == null) {
                word.setAudioPronunciation(getAudioPronunciation(result.getPronunciations()));
            }
        }
        return words;
    }

    private String getAudioPronunciation(List<Pronunciation> pronunciations) {
        String audioPronunciation = null;
        if (pronunciations == null) {
            return null;
        }
        for (Pronunciation pronunciationEntry : pronunciations) {
            audioPronunciation = pronunciationEntry.getAudioFile();
            if (audioPronunciation != null) break;
        }
        return audioPronunciation;
    }
}
