package com.dmu.sash.flprdcrds;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class URLAsyncTask extends AsyncTask<String, Integer, String> {
    private String data = "";
    private static final String app_id = "e6e4aac0";
    private static final String app_key = "cef4c78764ba3d3a8395707ad006bb25";
    public AsyncResponse delegate;

    public URLAsyncTask(AsyncResponse activity){
        delegate = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/Response");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);
            if (urlConnection.getResponseCode() == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                while ((data = reader.readLine()) != null) {
                    stringBuilder.append(data).append("\n");
                    data = stringBuilder.toString();
                }
                return stringBuilder.toString();
            } else if (400 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() < 500  ){
                System.out.println("Bad request or word not found");
            } else if (urlConnection.getResponseCode() >= 500){
                System.out.println("Server problem");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(result);
    }
}
