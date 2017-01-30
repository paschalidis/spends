package com.example.android.spends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}
