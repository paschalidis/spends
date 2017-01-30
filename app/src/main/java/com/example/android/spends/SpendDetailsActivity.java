package com.example.android.spends;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.spends.Database.CategoryDB;
import com.example.android.spends.Database.LocationDB;
import com.example.android.spends.Database.SpendDB;
import com.example.android.spends.Models.Category;
import com.example.android.spends.Models.Location;
import com.example.android.spends.Models.Spend;

import static android.R.attr.id;

public class SpendDetailsActivity extends AppCompatActivity {

    public static final String SPEND_NO = "spend_number";
    private Integer spendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_details);

        setTitle("Spend Details");

        Integer spendNo = (Integer) getIntent().getExtras().get(SPEND_NO);
        this.spendId = spendNo;
        this.fillSpendDetails(spendNo);
    }

    private void fillSpendDetails(Integer spendId){
        SpendDB spendDB = new SpendDB(this);
        Spend spend = spendDB.getSpend(spendId);

        CategoryDB categoryDB = new CategoryDB(this);
        Category category = categoryDB.getCategory(spend.getCategoryID());

        LocationDB locationDB = new LocationDB(this);
        Location location = locationDB.getLocationById(spend.getLocationID());

        TextView spendCategory = (TextView) findViewById(R.id.spend_view_category);
        spendCategory.setText(category.getTitle());

        TextView spendAmount = (TextView) findViewById(R.id.spend_view_amount);
        spendAmount.setText(spend.getAmount());

        TextView spendDescription = (TextView) findViewById(R.id.spend_view_description);
        spendDescription.setText(spend.getDescription());

        TextView spendDate = (TextView) findViewById(R.id.spend_view_date);
        spendDate.setText(spend.getDateToString());

        TextView spendLocation = (TextView) findViewById(R.id.spend_view_location);
        spendLocation.setText(location.getName());
    }

    public void deleteSpend(View view){
        SpendDB spendDB = new SpendDB(this);

        try{
            spendDB.deleteSpend(this.spendId);
        }catch (SQLiteException e){
            Toast.makeText(this, "Internal DB Error On Spend Delete", Toast.LENGTH_SHORT).show();
        }

        finish();
        Toast.makeText(this, "Spend Successfully Deleted", Toast.LENGTH_SHORT).show();
    }

    public void editSpend(View view){
        Intent intent = new Intent(SpendDetailsActivity.this, SpendEditActivity.class);
        intent.putExtra(SpendEditActivity.SPEND_EDIT_ID, (int) this.spendId);
        startActivity(intent);
    }
}
