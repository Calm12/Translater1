package com.calm.myapplication.Cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {

    private static Date ourInstance = new Date();

    public static Date getInstance() {
        return ourInstance;
    }

    private Date() {
    }

    public String reformat(String date, String format){
        if(format == null){
            format = "HH:mm dd.MM.yyyy";
        }
        SimpleDateFormat parser   = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(parser.parse(date));
        }
        catch(ParseException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
}
