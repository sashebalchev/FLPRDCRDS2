package com.dmu.sash.flprdcrds.management;

public interface AsyncResponse {
    void processFinish(boolean hasErrors, String output);
}
