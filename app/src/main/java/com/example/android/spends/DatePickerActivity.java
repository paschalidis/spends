package com.example.android.spends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class DatePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        setTitle("Date Picker");
    }

    public void orderedList(View view){

        CalendarView calendarFrom = (CalendarView) findViewById(R.id.date_from);
        long from = calendarFrom.getDate() / 1000L;

        CalendarView calendarTo = (CalendarView) findViewById(R.id.date_to);

        long to = calendarTo.getDate() / 1000L;

        Intent orderedListActivity = new Intent(this, OrderedListActivity.class);
        startActivity(orderedListActivity);
    }
}
