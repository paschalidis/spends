package com.example.android.spends;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.spends.Database.CategoryDB;
import com.example.android.spends.Database.SpendContract;
import com.example.android.spends.Database.SpendDbHelper;
import com.example.android.spends.Models.Category;

import static android.R.attr.category;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        setTitle("Create Category");
    }

    public void createCategory(View view) {
        //Get title text
        EditText titleField = (EditText) findViewById(R.id.category_title);
        String titleString = titleField.getText().toString().trim();

        //Get description text
        EditText descriptionField = (EditText) findViewById(R.id.category_description);
        String descriptionString = descriptionField.getText().toString().trim();

        //Validation Check
        if (titleString.isEmpty()) {
            Toast.makeText(this, "Title is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Category category = new Category();
        category.setTitle(titleString);
        category.setDescription(descriptionString);

        CategoryDB categoryDB = new CategoryDB(this);
        try{
            categoryDB.createCategory(category);
        }catch (SQLiteException e){
            Toast.makeText(this, "Internal DB Error", Toast.LENGTH_SHORT).show();
        }

        finish();
        Toast.makeText(this, "Category Successfully Created", Toast.LENGTH_SHORT).show();
    }
}
