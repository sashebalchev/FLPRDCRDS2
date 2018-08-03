package com.dmu.sash.flprdcrds;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class URLAsyncTaskPrepare extends AsyncTask<String, Integer, String> {


    public URLAsyncTaskPrepare(){}
    @Override
    protected String doInBackground(String... params) {
        final String app_id = "e6e4aac0";
        final String app_key = "cef4c78764ba3d3a8395707ad006bb25";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/Response");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return null;
    }
}
