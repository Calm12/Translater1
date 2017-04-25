package com.calm.myapplication.Translater;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface Transport {

    List getHTML(String uri) throws UnsupportedEncodingException, IOException;

}
