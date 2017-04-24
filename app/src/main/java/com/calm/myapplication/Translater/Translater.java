package com.calm.myapplication.Translater;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class Translater {


    private static final String TRANSLATE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";

    private static final String DICTIONARY_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup";


    public Translater(){

    }



    public String getTranslateJson(String lang, String text) throws IOException {
        StringBuilder json = new StringBuilder();
        List          list = Transport.getHTML(getTranslateUrl(lang, text));
        for(Object str : list){
            json.append((String) str);
        }
        return json.toString();
    }


    public String getDictionaryJson(String lang, String text) throws IOException {
        StringBuilder json = new StringBuilder();
        List          list = Transport.getHTML(getDictionaryUrl(lang, text));
        for(Object str : list){
            json.append((String) str);
        }
        return json.toString();
    }





    private String getTranslateUrl(String lang, String text){
        return getTransBaseUrl() + "?key=" + Keys.getTranslateKey() + "&text=" + URLEncoder.encode(text) + "&lang=" + lang;
    }

    private String getDictionaryUrl(String lang, String text){
        return getDictBaseUrl() + "?key=" + Keys.getDictionaryKey() + "&text=" + URLEncoder.encode(text) + "&lang=" + lang;
    }

    private String getDictBaseUrl(){
        return DICTIONARY_URL;
    }

    private String getTransBaseUrl(){
        return TRANSLATE_URL;
    }



}
