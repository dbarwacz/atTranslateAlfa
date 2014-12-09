package com.example.arek.attranslate_ver10;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * Created by Arek on 2014-12-09.
 */
public class TranslatorAsyncTask extends AsyncTask<String,Void,String> {

    HttpClient httpClient;
    HttpGet httpRequest;
    HttpResponse httpResponse;
    String translatedData;

    public TranslatorAsyncTask() {
        httpClient = new DefaultHttpClient();
        httpRequest = new HttpGet();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            httpRequest.setURI(new URI(params[0]));
            httpResponse = httpClient.execute(httpRequest);

            BufferedReader input = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer sBuffer = new StringBuffer();

            String read = new String("");
            String space = System.getProperty("line.separator");
            int httpResponseCode = Integer.parseInt(input.readLine());

            if(httpResponseCode == 0)
            {
                while((read = input.readLine()) != null)
                {
                    sBuffer.append(read + space);
                }
            }

            //Load data from buffer
            translatedData = sBuffer.toString();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return translatedData;
    }
}
