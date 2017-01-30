package com.example.android.spends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpendDetailsActivity extends AppCompatActivity {

    public static final String SPEND_NO = "spend_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend_details);

        setTitle("Spend Details");

        int spendNo = (Integer) getIntent().getExtras().get(SPEND_NO);
    }
}
