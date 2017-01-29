package com.example.android.spends;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        //displayDatabaseInfo();
    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    public void addCategory(View view) {
        Intent categoryIntent = new Intent(this, CategoryActivity.class);
        startActivity(categoryIntent);
    }

    public void addSpend(View view) {
        Intent spendActivity = new Intent(this, SpendActivity.class);
        startActivity(spendActivity);
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        SpendDbHelper mDbHelper = new SpendDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + SpendContract.CategoryEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView categoryView = (TextView) findViewById(R.id.categories_view);
            categoryView.setText("Number of rows in category database table: " + cursor.getCount());

            cursor = db.rawQuery("SELECT * FROM " + SpendContract.LocationEntry.TABLE_NAME, null);
            TextView locationView = (TextView) findViewById(R.id.location_view);
            locationView.setText("Number of rows in location database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

        displaySpends();
    }

    private void displaySpends(){
        SpendDB spendDB = new SpendDB(this);

        ArrayList<Spend> spendList = spendDB.getSpends();

        TextView spendView = (TextView) findViewById(R.id.spends_view);
        spendView.setText("Number of rows in spend database table: " + spendList.size() + "\n\n");
        spendView.append(SpendContract.SpendEntry._ID + " - " + SpendContract.SpendEntry.COLUMN_AMOUNT
                + " - " + SpendContract.SpendEntry.COLUMN_DESCRIPTION + " - " + SpendContract.SpendEntry.COLUMN_DATE
                + " - " + SpendContract.SpendEntry.COLUMN_CATEGORY_ID);

        spendView.append("\n");

        for (Spend s : spendList){

            spendView.append(s.getId() + " - " + s.getAmount() + " - " +  s.getDescription()
            + " - " + s.getDateToString() + " - " + s.getCategoryID());
            spendView.append("\n");
        }
    }
}
