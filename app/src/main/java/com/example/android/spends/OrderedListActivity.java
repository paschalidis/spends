package com.example.android.spends;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.android.spends.Database.SpendDB;

import static com.example.android.spends.SpendEditActivity.SPEND_EDIT_ID;

public class OrderedListActivity extends AppCompatActivity {

    public static final String DATE_FROM = "date_from";
    public static final String DATE_TO = "date_to";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_list);
        setTitle("Ordered List");

        Integer dateFrom = (Integer) getIntent().getExtras().get(DATE_FROM);
        Integer dateTo = (Integer) getIntent().getExtras().get(DATE_TO);

        SpendDB spendDB = new SpendDB(this);
        Cursor cursor = spendDB.getOrderedCursor(dateFrom, dateTo);

        ListView spendListView = (ListView) findViewById(R.id.spend_ordered_list_view);

        SpendCursorAdapter adapter = new SpendCursorAdapter(this, cursor);

        spendListView.setAdapter(adapter);
    }
}
