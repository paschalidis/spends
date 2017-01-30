package com.example.android.spends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.spends.Database.CategoryDB;
import com.example.android.spends.Database.LocationDB;
import com.example.android.spends.Database.SpendDB;
import com.example.android.spends.Models.Category;
import com.example.android.spends.Models.Location;
import com.example.android.spends.Models.Spend;

public class SpendDetailsActivity extends AppCompatActivity {

    public static final String SPEND_NO = "spend_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_details);

        setTitle("Spend Details");

        Integer spendNo = (Integer) getIntent().getExtras().get(SPEND_NO);
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
}
