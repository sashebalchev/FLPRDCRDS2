package com.dmu.sash.flprdcrds.json;

import java.util.List;

public class Response {

    /**
     * metadata : {}
     * results : [{"id":"string","language":"string","lexicalEntries":[{"derivativeOf":[{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}],"derivatives":[{"domains":["string"],"id":"string","language":"string","regions":["string"],"registers":["string"],"text":"string"}],"entries":[{"etymologies":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"homographNumber":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"senses":[{"crossReferenceMarkers":["string"],"crossReferences":[{"id":"string","text":"string","type":"string"}],"definitions":["string"],"domains":["string"],"examples":[{"definitions":["string"],"domains":["string"],"notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"senseIds":["string"],"text":"string","translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}]}],"id":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"regions":["string"],"registers":["string"],"short_definitions":["string"],"subsenses":[{}],"thesaurusLinks":[{"entry_id":"string","sense_id":"string"}],"translations":[{"domains":["string"],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","notes":[{"id":"string","text":"string","type":"string"}],"regions":["string"],"registers":["string"],"text":"string"}],"variantForms":[{"regions":["string"],"text":"string"}]}],"variantForms":[{"regions":["string"],"text":"string"}]}],"grammaticalFeatures":[{"text":"string","type":"string"}],"language":"string","lexicalCategory":"string","notes":[{"id":"string","text":"string","type":"string"}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"text":"string","variantForms":[{"regions":["string"],"text":"string"}]}],"pronunciations":[{"audioFile":"string","dialects":["string"],"phoneticNotation":"string","phoneticSpelling":"string","regions":["string"]}],"type":"string","word":"string"}]
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
