package com.example.android.spends;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.spends.Database.SpendContract;
import com.example.android.spends.Database.SpendDB;
import com.example.android.spends.Database.SpendDbHelper;
import com.example.android.spends.Models.Spend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Spends List");
    }

    @Override
    protected void onStart(){
        super.onStart();
        listSpends();
    }

    public void addCategory(View view) {
        Intent categoryIntent = new Intent(this, CategoryActivity.class);
        startActivity(categoryIntent);
    }

    public void addSpend(View view) {
        Intent spendActivity = new Intent(this, SpendActivity.class);
        startActivity(spendActivity);
    }

    private void listSpends(){
        SpendDB spendDB = new SpendDB(this);
        Cursor cursor = spendDB.getSpendsCursor();

        ListView spendListView = (ListView) findViewById(R.id.spend_list_view);

        SpendCursorAdapter adapter = new SpendCursorAdapter(this, cursor);

        spendListView.setAdapter(adapter);

        spendListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, SpendDetailsActivity.class);
                    intent.putExtra(SpendDetailsActivity.SPEND_NO, (int) id);
                    startActivity(intent);
            }
        });

    }

}
