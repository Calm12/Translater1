package com.calm.myapplication.Translater.TransportImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHtmlTransport implements com.calm.myapplication.Translater.Transport{

    public List getHTML(String uri){
        OkHttpClient client = new OkHttpClient();

        /*RequestBody formBody = new FormBody.Builder()
                .add("message", "Your message")
                .build();*/
        Request request = new Request.Builder()
                .url(uri)
                /*.post(formBody)*/
                .build();

        try {
            Response response = client.newCall(request).execute();

            return new ArrayList();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
