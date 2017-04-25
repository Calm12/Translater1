package com.calm.myapplication.Translater.TransportImpl;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BufferedReaderTransport implements com.calm.myapplication.Translater.Transport{

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public List getHTML(String uri) throws SecurityException, IOException {
        List<String> html = new ArrayList<>();

        URL url = new URL(uri);

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))){
            for(String line; (line = reader.readLine()) != null;){
                html.add(line);
            }
        }

        return html;
    }

}

