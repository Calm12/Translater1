package com.calm.myapplication.Cache;

public class CacheRecord {

    private int id;
    private String query;
    private String result;
    private String lang;
    private String date;
    private int favorite;


    public CacheRecord(int id, String query, String result, String lang, String date, int favorite) {
        this.id = id;
        this.query = query;
        this.result = result;
        this.lang = lang;
        this.date = date;
        this.favorite = favorite;
    }

    public CacheRecord(String query, String result, String lang) {
        this.query = query;
        this.result = result;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "CacheRecord{" + "id=" + id + ", query='" + query + '\'' + ", result='" + result + '\'' + ", lang='" + lang + '\'' + ", date='" + date + '\'' + ", favorite=" + favorite + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

}
