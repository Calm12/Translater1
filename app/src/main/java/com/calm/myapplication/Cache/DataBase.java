package com.calm.myapplication.Cache;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.calm.myapplication.MainActivity;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {

    private static DataBase instance = new DataBase();

    private static final String DB_NAME = "storage.local";

    private Connection connection = null;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            try {
                DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
            }
            catch (Exception e) {
                Log.e("DB", e.getClass().getName() + ":  " + e.getMessage());
            }
            //String path = "/data/user/0/com.calm.myapplication/files";
            String path = MainActivity.filesDir;
            File appDir = new File(path);
            appDir.mkdir();
            String jdbcUrl = "jdbc:sqldroid:" + path + "/database.db";
            Log.e("jdbcUrl", jdbcUrl);
            try {
                connection = new org.sqldroid.SQLDroidDriver().connect(jdbcUrl , new Properties());
                return connection;
            }
            catch(Exception e) {
                Log.e("DB", e.getClass().getName() + ": " + e.getMessage());
                return null;
            }
        }
        else{
            return connection;
        }
    }

}
