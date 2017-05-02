package com.calm.myapplication;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.calm.myapplication.Cache.CacheRecord;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends ListActivity {

    public static List<CacheRecord> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        values = MainActivity.cache.selectAll();

        ArrayAdapter<CacheRecord> adapter = new CacheAdapter(this);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
         CacheRecord item = (CacheRecord) getListAdapter().getItem(position);
        Toast.makeText(this, item.getQuery() + " добавлен в избранное", Toast.LENGTH_LONG).show();
    }

    public class CacheAdapter extends ArrayAdapter<CacheRecord> {

        public CacheAdapter(Context context) {
            super(context, R.layout.list_item, values);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CacheRecord cacheRecord = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            }
            ((TextView) convertView.findViewById(R.id.query)).setText(cacheRecord.getQuery());
            ((TextView) convertView.findViewById(R.id.result)).setText(cacheRecord.getResult());
            ((CheckBox)convertView.findViewById(R.id.checkBox)).setChecked(cacheRecord.getFavorite() == 1);

            return convertView;
        }
    }

}
