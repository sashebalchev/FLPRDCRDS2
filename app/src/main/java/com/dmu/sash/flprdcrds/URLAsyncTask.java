package com.dmu.sash.flprdcrds;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DefinitionTask extends AsyncTask<String, Integer, String> {
    String data = "";
    public AsyncResponse delegate = null;

    public DefinitionTask(AsyncResponse activity){
        delegate = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        final String app_id = "e6e4aac0";
        final String app_key = "cef4c78764ba3d3a8395707ad006bb25";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);
            System.out.println(urlConnection.getResponseCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while ((data = reader.readLine()) != null) {
                stringBuilder.append(data + "\n");
                data = stringBuilder.toString();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        MainActivity.results(result);
        delegate.processFinish2(result);
    }
}
