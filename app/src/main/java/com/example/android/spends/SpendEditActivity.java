package com.example.android.spends;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.spends.Database.CategoryDB;
import com.example.android.spends.Database.LocationDB;
import com.example.android.spends.Database.SpendDB;
import com.example.android.spends.Models.Category;
import com.example.android.spends.Models.Location;
import com.example.android.spends.Models.Spend;

import java.util.ArrayList;

import static android.R.attr.theme;
import static android.R.attr.value;
import static com.example.android.spends.SpendDetailsActivity.SPEND_NO;

public class SpendEditActivity extends AppCompatActivity {

    public static final String SPEND_EDIT_ID = "spend_number";
    private Integer spendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_edit);

        setTitle("Spend Edit");

        this.spendId = (Integer) getIntent().getExtras().get(SPEND_EDIT_ID);
    }

    @Override
    protected void onStart(){
        super.onStart();
        this.fillEditData();
    }

    private void fillEditData(){
        SpendDB spendDB = new SpendDB(this);
        Spend spend = spendDB.getSpend(this.spendId);

        CategoryDB categoryDB = new CategoryDB(this);
        Category category = categoryDB.getCategory(spend.getCategoryID());

        this.setCategorySpinnerData(category);

        TextView spendAmount = (TextView) findViewById(R.id.spend_edit_amount);
        spendAmount.setText(spend.getAmount());

        TextView spendDescription = (TextView) findViewById(R.id.spend_edit_description);
        spendDescription.setText(spend.getDescription());
    }

    private void setCategorySpinnerData(Category categorySelected){

        CategoryDB categoryDB = new CategoryDB(this);

        ArrayList<Category> categoryList = categoryDB.getCategories();

        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner categorySpinner = (Spinner) findViewById(R.id.spend_edit_spinner);
        categorySpinner.setAdapter(adapter);

        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItemId(position) == categorySelected.getId()) {
                categorySpinner.setSelection(position-1);
                return;
            }
        }
    }

    public void updateSpend(View view){
        //Category Spinner
        Spinner categorySpinner = (Spinner) findViewById(R.id.spend_edit_spinner);
        Category category = (Category) categorySpinner.getSelectedItem();

        //Get Amount text
        EditText amountField = (EditText) findViewById(R.id.spend_edit_amount);
        String amountString = amountField.getText().toString().trim();

        if(amountString.isEmpty()){
            Toast.makeText(this, "Amount is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //Get description text
        EditText descriptionField = (EditText) findViewById(R.id.spend_edit_description);
        String descriptionString = descriptionField.getText().toString().trim();

        Spend spend = new Spend();
        spend.setId(this.spendId);
        spend.setAmount(amountString);
        spend.setDescription(descriptionString);
        spend.setCategoryID(category.getId());

        SpendDB spendDB = new SpendDB(this);

        try{
            spendDB.updateSpend(spend);
        }catch (SQLiteException e){
            Toast.makeText(this, "Internal DB Error On Spend Update", Toast.LENGTH_SHORT).show();
        }

        finish();
        Toast.makeText(this, "Spend Successfully Updated", Toast.LENGTH_SHORT).show();
    }
}
