package com.dmu.sash.flprdcrds.management;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class URLAsyncTask extends AsyncTask<String, Integer, String> {
    private static final String APP_ID = "e6e4aac0";
    private static final String APP_KEY = "cef4c78764ba3d3a8395707ad006bb25";
    private AsyncResponse delegate;
    private boolean hasErrors = false;

    public URLAsyncTask(AsyncResponse activity) {
        delegate = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/Response");
            urlConnection.setRequestProperty("app_id", APP_ID);
            urlConnection.setRequestProperty("app_key", APP_KEY);
            if (urlConnection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String data;
                while ((data = reader.readLine()) != null) {
                    stringBuilder.append(data).append("\n");
                }
                return stringBuilder.toString();
            } else if (400 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() < 500) {
                System.out.println("Bad request or word not found");
                hasErrors = true;
                return("Bad request or word not found");
            } else if (urlConnection.getResponseCode() >= 500) {
                System.out.println("Server problem");
                hasErrors = true;
                return("Server problem");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem with connection");
            hasErrors = true;
            return("Problem with connection: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        delegate.processFinish(hasErrors, result);
    }
}
