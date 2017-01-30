package com.example.android.spends;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.spends.Database.CategoryDB;
import com.example.android.spends.Database.LocationDB;
import com.example.android.spends.Database.SpendContract;
import com.example.android.spends.Database.SpendDB;
import com.example.android.spends.Database.SpendDbHelper;
import com.example.android.spends.Models.Category;
import com.example.android.spends.Models.Location;
import com.example.android.spends.Models.Spend;

import java.util.ArrayList;

import static android.R.attr.id;
import static android.R.attr.lockTaskMode;
import static android.R.attr.theme;

public class SpendActivity extends AppCompatActivity {

    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);
        setCategorySpinnerData();

        setTitle("Create Spend");

        this.latitude = "40.741895";
        this.longitude = "-73.989308";

        this.setLocationName();
    }

    public void createSpend(View view) {

        //Category Spinner
        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        Category category = (Category) categorySpinner.getSelectedItem();

        //Create new category check box
        CheckBox createCategoryCheckBox = (CheckBox) findViewById(R.id.category_check_box);

        //Get category title text
        EditText categoryTitleField = (EditText) findViewById(R.id.category_edit_text);
        String categoryTitleString = categoryTitleField.getText().toString().trim();

        if(createCategoryCheckBox.isChecked()){
            if(categoryTitleString.isEmpty()){
                Toast.makeText(this, "Category Title is empty", Toast.LENGTH_SHORT).show();
                return;
            } else { //Create new category
             category = new Category(0, categoryTitleString);
            }
        } else {
            if(category == null){
                Toast.makeText(this, "Create New Category", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        //Get Amount text
        EditText amountField = (EditText) findViewById(R.id.amount_edit_text);
        String amountString = amountField.getText().toString().trim();

        if(amountString.isEmpty()){
            Toast.makeText(this, "Amount is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //Get description text
        EditText descriptionField = (EditText) findViewById(R.id.description_edit_text);
        String descriptionString = descriptionField.getText().toString().trim();

        EditText locationField = (EditText) findViewById(R.id.location_edit_text);
        String locationString = locationField.getText().toString().trim();

        //Create new category entry
        if(category.getId() == 0){
            CategoryDB categoryDB = new CategoryDB(this);
            try{
                category = categoryDB.createCategory(category);
            } catch (SQLiteException e){
                Toast.makeText(this, "Internal DB Error On Category Creation", Toast.LENGTH_SHORT).show();
            }
        }

        //Create new Location
        Location location = new Location();
        location.setName(locationString);
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);

        LocationDB locationDB = new LocationDB(this);
        try{
            location = locationDB.createLocation(location);
        } catch (SQLiteException e){
            Toast.makeText(this, "Internal DB Error On Location Creation", Toast.LENGTH_SHORT).show();
        }

        // divide by 1000 gets you to Unix Timestamp
        long unixTimestamp = System.currentTimeMillis() / 1000L;
        int date = (int) (long) unixTimestamp;

        Spend spend = new Spend();
        spend.setAmount(amountString);
        spend.setDescription(descriptionString);
        spend.setCategoryID(category.getId());
        spend.setDate(date);
        spend.setLocationID(location.getId());

        SpendDB spendDB = new SpendDB(this);

        try{
            spendDB.createSpend(spend);
        }catch (SQLiteException e){
            Toast.makeText(this, "Internal DB Error On Spend Creation", Toast.LENGTH_SHORT).show();
        }

        finish();
        Toast.makeText(this, "Spend Successfully Created", Toast.LENGTH_SHORT).show();

    }

    private void setCategorySpinnerData(){

        CategoryDB categoryDB = new CategoryDB(this);

        ArrayList<Category> categoryList = categoryDB.getCategories();

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        categorySpinner.setAdapter(adapter);

    }

    private void setLocationName(){
        LocationDB locationDB = new LocationDB(this);
        Location location = locationDB.getLocation(this.latitude, this.longitude);

        if(location.getId() == null){
            return;
        }

        EditText locationEdit = (EditText) findViewById(R.id.location_edit_text);
        locationEdit.setText(location.getName());
    }
}
