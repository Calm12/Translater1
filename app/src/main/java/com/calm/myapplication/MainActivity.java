package com.calm.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.calm.myapplication.Cache.Cache;
import com.calm.myapplication.Cache.CacheRecord;
import com.calm.myapplication.Translater.LangState;
import com.calm.myapplication.Translater.Translater;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TabHost.TabSpec tabSpec;
    ToggleButton toogleButton;
    LangState langState;
    Translater translater;
    Cache cache;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator(getString(R.string.tab1));
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator(getString(R.string.tab2));
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        // вторая вкладка по умолчанию активна
        tabHost.setCurrentTabByTag("tag1");

        langState = new LangState();
        toogleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toogleButton.setOnCheckedChangeListener(this);

        translater = new Translater();
        cache = new Cache();

        tv = (TextView) findViewById(R.id.textView2);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void onClickBtnTranslate(View v) throws IOException {
        String lang = langState.getLang();
        EditText et = (EditText) findViewById(R.id.editText);
        String text = et.getText().toString();
        String json = "";

        CacheRecord record = cache.search(text, lang);
        if(record != null){
            tv.setText(record.getResult());
            return;
        }

        try {
            json = translater.getTranslateJson(lang,text);

        }
        catch (SecurityException e) {
            tv.setText(e.getClass().getName() + ": " + e.getMessage());
            tv.setText("Ошибка. Запрещен доступ к сети.");
            e.printStackTrace();
        }

        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(json).getAsJsonObject();
            String code = mainObject.get("text").getAsString();
            cache.insert(new CacheRecord(text, code, lang));
            tv.setText(code);
        }
        catch (Exception e) {
            tv.setText("Ошибка. Возможно, отсутствует интернет-соединение.");
            e.printStackTrace();
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        langState.setState(isChecked);
    }
}