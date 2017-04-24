package com.calm.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.calm.myapplication.Translater.LangState;
import com.calm.myapplication.Translater.Translater;

import org.json.JSONObject;

import java.io.IOException;


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
    }


    public void onClickBtnTranslate(View v) {
        String lang = langState.getLang();
        String json = "";
        try {
            json = translater.getTranslateJson(lang,"собака");
        } catch (IOException e) {
            json = "Ошибка";
            e.printStackTrace();
        }
        //JSONObject jsonObject = new JSONObject();

        tv.setText(json);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        langState.setState(isChecked);
    }
}