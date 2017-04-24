package com.calm.myapplication.Translater;

public class LangState {
    private static final String [] langs = {"ru-en", "en-ru"} ;
    private boolean state = false;

    public String getLang(){
        if(state){
            return langs[1];
        }
        else{
            return langs[0];
        }
    }

    public void setState(boolean state){
        this.state = state;
    }
}
