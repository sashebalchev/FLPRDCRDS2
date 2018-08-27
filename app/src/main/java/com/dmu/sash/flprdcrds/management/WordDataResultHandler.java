package com.dmu.sash.flprdcrds.management;

import com.dmu.sash.flprdcrds.database.entities.Word;

import java.util.List;

interface WordDataResultHandler {
    void handleWordDataResult(String error, List<Word> words);
}
