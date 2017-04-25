package com.calm.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.calm.myapplication.Translater.LangState;
import com.calm.myapplication.Translater.Translater;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener {

    TabHost.TabSpec tabSpec;
    ToggleButton toogleButton;
    LangState langState;
    Translater translater;
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

        tv = (TextView) findViewById(R.id.textView2);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void onClickBtnTranslate(View v) throws IOException {
        String lang = langState.getLang();
        EditText et = (EditText) findViewById(R.id.editText);
        String text = et.getText().toString();
        String json = "";
        try {
            json = translater.getTranslateJson(lang,text);

        }
        catch (SecurityException e) {
            //json = "Нет прав на подключение к интернету";
            e.printStackTrace();
        }
        //JSONObject jsonObject = new JSONObject();
        System.out.println(json);
        tv.setText(json);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        langState.setState(isChecked);
    }
}