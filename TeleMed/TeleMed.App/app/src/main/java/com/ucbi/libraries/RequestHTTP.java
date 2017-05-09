package com.ucbi.libraries;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by TungVu on 5/8/2017.
 */

public class RequestHTTP {
    public static void makeGetRequest(String url) {
        new MakeHTTPRequest().execute(url);
    }


}

class MakeHTTPRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String result = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            result = response.toString();
            Log.d("Response of GET request", response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
