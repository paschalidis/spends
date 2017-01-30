package com.example.android.spends.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.android.spends.Models.Category;
import com.example.android.spends.Database.SpendContract.CategoryEntry;
import com.example.android.spends.Models.Spend;

import java.util.ArrayList;

import static android.R.attr.category;
import static android.R.attr.id;

public class CategoryDB {

    private Context context;

    public CategoryDB(Context context) {
        this.context = context;
    }

    /**
     * Insert a new entry to categories database table
     *
     * @param category Category Object
     * @return category
     */
    public Category createCategory(Category category) {

        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CategoryEntry.COLUMN_TITLE, category.getTitle());
        values.put(CategoryEntry.COLUMN_DESCRIPTION, category.getDescription());

        long newRowId = db.insert(CategoryEntry.TABLE_NAME, null, values);

        Integer id = (int) (long) newRowId;

        category.setId(id);

        return category;
    }

    /**
     * Return all rows of database table category
     *
     * @return ArrayList<Category>
     */
    public ArrayList<Category> getCategories() {
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(CategoryEntry.TABLE_NAME, null, null, null, null, null, null, null);

        ArrayList<Category> categoryList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Category category = new Category();
            category.setId(cursor.getInt(cursor.getColumnIndex(CategoryEntry._ID)));
            category.setTitle(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_TITLE)));
            category.setDescription(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_DESCRIPTION)));

            categoryList.add(category);
        }

        cursor.close();

        return categoryList;
    }

    public Category getCategory(Integer id){
        SpendDbHelper mDbHelper = new SpendDbHelper(this.context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String sqlQuery = "SELECT * FROM " + CategoryEntry.TABLE_NAME + " WHERE "
                + CategoryEntry._ID  + " = " + id;

        Cursor cursor = db.rawQuery(sqlQuery, null);

        Category category = new Category();

        if(cursor.getCount() > 0)
        {
            cursor.moveToNext();
            category.setId(cursor.getInt(cursor.getColumnIndex(CategoryEntry._ID)));
            category.setTitle(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_TITLE)));
            category.setDescription(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_DESCRIPTION)));
        }

        cursor.close();

        return category;
    }
}
