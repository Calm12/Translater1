package com.calm.myapplication.Cache;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cache {

    private static final String TABLE_SQL = "CREATE TABLE IF NOT EXISTS query_cache ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "query TEXT NOT NULL, " +
            "result TEXT NOT NULL, " +
            "lang TEXT NOT NULL, " +
            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "favorite INTEGER DEFAULT 0 )";

    public Cache(){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            Statement  stmt = c.createStatement();
            stmt.executeUpdate(TABLE_SQL);
            stmt.close();
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public boolean insert(CacheRecord record){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            stmt.executeUpdate("INSERT INTO query_cache (query,result,lang) " +
                    "VALUES ('"+record.getQuery()+"', '"+record.getResult()+"', '"+record.getLang()+"');");
            c.commit();
            stmt.close();

            return true;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public CacheRecord search(String query, String lang){

        try {
            Connection c    = DataBase.getInstance().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT * FROM query_cache WHERE query = '"+query+"' AND lang = '"+lang+"' LIMIT 1");
            CacheRecord ret = null;
            while ( rs.next() ) {
                ret = new CacheRecord(
                        rs.getInt("id"),
                        rs.getString("query"),
                        rs.getString("result"),
                        rs.getString("lang"),
                        rs.getString("date"),
                        rs.getInt("favorite")
                );
            }
            stmt.close();

            return ret;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List<CacheRecord> selectAll(){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT * FROM query_cache");
            List<CacheRecord> list = new ArrayList<>();
            while ( rs.next() ) {
                list.add(new CacheRecord(
                        rs.getInt("id"),
                        rs.getString("query"),
                        rs.getString("result"),
                        rs.getString("lang"),
                        rs.getString("date"),
                        rs.getInt("favorite")
                ));
            }
            stmt.close();

            return list;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<CacheRecord> selectFavorites(){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs   = stmt.executeQuery("SELECT * FROM query_cache WHERE favorite = 1");
            List<CacheRecord> list = new ArrayList<>();
            while ( rs.next() ) {
                list.add(new CacheRecord(
                        rs.getInt("id"),
                        rs.getString("query"),
                        rs.getString("result"),
                        rs.getString("lang"),
                        rs.getString("date"),
                        rs.getInt("favorite")
                ));
            }
            stmt.close();

            return list;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean setFavorite(int id){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            stmt.executeUpdate("UPDATE query_cache SET favorite = 1 WHERE id = " + id);
            c.commit();
            stmt.close();

            return true;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean clean(){
        try {
            Connection c    = DataBase.getInstance().getConnection();
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM query_cache");
            c.commit();
            stmt.close();

            return true;
        }
        catch(SQLException e) {
            Log.e("Cache", e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

}
