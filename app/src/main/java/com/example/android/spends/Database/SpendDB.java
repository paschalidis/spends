package com.example.android.spends.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.spends.Models.Spend;
import com.example.android.spends.Database.SpendContract.SpendEntry;

import java.util.ArrayList;

import static android.R.attr.category;

public class SpendDB {

    private Context context;

    public SpendDB(Context context) {
        this.context = context;
    }

    /**
     * Insert a new entry to spend database table
     *
     * @param spend Spend Object
     * @return spend
     */
    public Spend createSpend(Spend spend) {

        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SpendEntry.COLUMN_AMOUNT, spend.getAmount());
        values.put(SpendEntry.COLUMN_DESCRIPTION, spend.getDescription());
        values.put(SpendEntry.COLUMN_CATEGORY_ID, spend.getCategoryID());
        values.put(SpendEntry.COLUMN_DATE, spend.getDate());
        values.put(SpendEntry.COLUMN_LOCATION_ID, spend.getLocationID());

        long newRowId = db.insert(SpendEntry.TABLE_NAME, null, values);

        Integer id = (int) (long) newRowId;

        spend.setId(id);

        return spend;
    }

    /**
     * Return all rows of database table category
     *
     * @return ArrayList<Spend>
     */
    public ArrayList<Spend> getSpends() {
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(SpendEntry.TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Spend> spendList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Spend spend = new Spend();
            spend.setId(cursor.getInt(cursor.getColumnIndex(SpendEntry._ID)));
            spend.setAmount(cursor.getString(cursor.getColumnIndex(SpendEntry.COLUMN_AMOUNT)));
            spend.setDescription(cursor.getString(cursor.getColumnIndex(SpendEntry.COLUMN_DESCRIPTION)));
            spend.setCategoryID(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_CATEGORY_ID)));
            spend.setLocationID(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_LOCATION_ID)));
            spend.setDate(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_DATE)));

            spendList.add(spend);
        }

        cursor.close();

        return spendList;
    }

    public Cursor getSpendsCursor(){
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(SpendEntry.TABLE_NAME, null, null, null, null, null, null, null);

        return  cursor;
    }

    public Spend getSpend(Integer id){
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + SpendEntry.TABLE_NAME + " WHERE " + SpendEntry._ID
                        + " = " + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Spend spend = new Spend();

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            spend.setId(cursor.getInt(cursor.getColumnIndex(SpendEntry._ID)));
            spend.setAmount(cursor.getString(cursor.getColumnIndex(SpendEntry.COLUMN_AMOUNT)));
            spend.setDescription(cursor.getString(cursor.getColumnIndex(SpendEntry.COLUMN_DESCRIPTION)));
            spend.setCategoryID(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_CATEGORY_ID)));
            spend.setLocationID(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_LOCATION_ID)));
            spend.setDate(cursor.getInt(cursor.getColumnIndex(SpendEntry.COLUMN_DATE)));
        }

        cursor.close();

        return spend;
    }
}
