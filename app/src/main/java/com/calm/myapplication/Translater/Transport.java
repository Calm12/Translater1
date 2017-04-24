package com.calm.myapplication.Translater;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Transport {


    public static List getHTML(String uri) throws UnsupportedEncodingException, IOException {
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
