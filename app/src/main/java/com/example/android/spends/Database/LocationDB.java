package com.example.android.spends.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.spends.Models.Location;
import com.example.android.spends.Database.SpendContract.LocationEntry;

import static android.R.attr.category;

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
}
