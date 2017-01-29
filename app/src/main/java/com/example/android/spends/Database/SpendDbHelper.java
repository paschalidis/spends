package com.example.android.spends.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpendDbHelper extends SQLiteOpenHelper {

    private Context context;

    //Name of database file
    private static final String DATABASE_NAME = "spends.db";

    //Database version
    private static final int DATABASE_VERSION = 1;

    public SpendDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE " + SpendContract.CategoryEntry.TABLE_NAME
                + "(" + SpendContract.CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SpendContract.CategoryEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + SpendContract.CategoryEntry.COLUMN_DESCRIPTION + " TEXT);";

        String SQL_CREATE_TABLE_SPEND = "CREATE TABLE " + SpendContract.SpendEntry.TABLE_NAME
                + "(" + SpendContract.SpendEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SpendContract.SpendEntry.COLUMN_AMOUNT + " TEXT NOT NULL, "
                + SpendContract.SpendEntry.COLUMN_DESCRIPTION + " TEXT, "
                + SpendContract.SpendEntry.COLUMN_CATEGORY_ID + " INTEGER NOT NULL, "
                + SpendContract.SpendEntry.COLUMN_LOCATION_ID + " INTEGER, "
                + SpendContract.SpendEntry.COLUMN_DATE + " INTEGER NOT NULL);";

        String SQL_CREATE_TABLE_LOCATION = "CREATE TABLE " + SpendContract.LocationEntry.TABLE_NAME
                + "(" + SpendContract.LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SpendContract.LocationEntry.COLUMN_NAME + " TEXT, "
                + SpendContract.LocationEntry.COLUMN_LAT + " TEXT, "
                + SpendContract.LocationEntry.COLUMN_LONG + " TEXT);";

        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_SPEND);
        db.execSQL(SQL_CREATE_TABLE_LOCATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + SpendContract.CategoryEntry.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXIST " + SpendContract.SpendEntry.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXIST " + SpendContract.LocationEntry.TABLE_NAME + ";");

        onCreate(db);
    }
}
