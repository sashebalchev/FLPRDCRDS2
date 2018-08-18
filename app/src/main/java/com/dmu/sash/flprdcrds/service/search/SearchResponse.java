package com.dmu.sash.flprdcrds.service.search;

import java.util.List;

public class SearchResponse {

    /**
     * metadata : {"provider":"Oxford University Press","sourceLanguage":"en","limit":5000,"offset":0,"total":14}
     * results : [{"inflection_id":"ace","matchType":"inflection","matchString":"ace","word":"ace","score":12.682389,"region":"gb","id":"ace"},{"matchType":"headword","matchString":"ace","word":"ace point","score":1.1217691,"region":"gb","id":"ace_point"},{"matchType":"headword","matchString":"ace","word":"ACE inhibitor","score":1.1217691,"region":"gb","id":"ace_inhibitor"},{"matchType":"headword","matchString":"ace","word":"ace-face","score":1.1217691,"region":"gb","id":"ace-face"},{"matchType":"headword","matchString":"ace","word":"ace-deuce","score":1.1217691,"region":"gb","id":"ace-deuce"},{"matchType":"headword","matchString":"ace","word":"-acity","score":0.99696666,"region":"gb","id":"-acity"},{"matchType":"headword","matchString":"ace","word":"acer","score":0.99696666,"region":"gb","id":"acer"},{"matchType":"headword","matchString":"ace","word":"-acal","score":0.99696666,"region":"gb","id":"-acal"},{"matchType":"headword","matchString":"ace","word":"within an ace of","score":0.8974153,"region":"gb","id":"within_an_ace_of"},{"matchType":"headword","matchString":"ace","word":"play one's ace","score":0.8974153,"region":"gb","id":"play_one%27s_ace"},{"matchType":"headword","matchString":"ace","word":"ace boon coon","score":0.8974153,"region":"gb","id":"ace_boon_coon"},{"matchType":"headword","matchString":"ace","word":"an ace up one's sleeve","score":0.8446262,"region":"gb","id":"an_ace_up_one%27s_sleeve"},{"matchType":"headword","matchString":"ace","word":"ace (or king or queen etc.) high","score":0.6730615,"region":"gb","id":"ace_%28or_king_or_queen_etc.%29_high"},{"matchType":"headword","matchString":"ace","word":"hold all the aces","score":0.49848333,"region":"gb","id":"hold_all_the_aces"}]
     */

    private Metadata metadata;
    private List<Result> results;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
