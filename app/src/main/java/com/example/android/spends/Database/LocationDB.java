package com.example.android.spends.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.spends.Models.Location;
import com.example.android.spends.Database.SpendContract.LocationEntry;

import static android.R.attr.category;
import static android.R.attr.theme;

public class LocationDB {
    private Context context;

    public LocationDB(Context context) {
        this.context = context;
    }

    /**
     * Insert a new entry to categories database table
     *
     * @param location Location Object
     * @return location
     */
    public Location createLocation(Location location) {

        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_NAME, location.getName());
        values.put(LocationEntry.COLUMN_LAT, location.getLatitude());
        values.put(LocationEntry.COLUMN_LONG, location.getLongitude());

        long newRowId = db.insert(LocationEntry.TABLE_NAME, null, values);

        Integer id = (int) (long) newRowId;

        location.setId(id);

        return location;
    }

    public Location getLocation(String latitude, String longitude){
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + LocationEntry.TABLE_NAME + " WHERE " +
                          LocationEntry.COLUMN_LAT + " = " + latitude + " AND " +
                          LocationEntry.COLUMN_LONG + " = " + longitude;

        Cursor cursor = db.rawQuery(sqlQuery, null);

        Location location = new Location();


        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            location.setId(cursor.getInt(cursor.getColumnIndex(LocationEntry._ID)));
            location.setName(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_NAME)));
            location.setLatitude(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_LAT)));
            location.setLongitude(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_LONG)));
        }

        cursor.close();

        return location;
    }

    public Location getLocationById(Integer id){
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + LocationEntry.TABLE_NAME + " WHERE "
                + LocationEntry._ID  + " = " + id;

        Cursor cursor = db.rawQuery(sqlQuery, null);

        Location location = new Location();


        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            location.setId(cursor.getInt(cursor.getColumnIndex(LocationEntry._ID)));
            location.setName(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_NAME)));
            location.setLatitude(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_LAT)));
            location.setLongitude(cursor.getString(cursor.getColumnIndex(LocationEntry.COLUMN_LONG)));
        }

        cursor.close();

        return location;
    }
}
