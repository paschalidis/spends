package com.example.android.spends;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.spends.Database.SpendContract;
import com.example.android.spends.Models.Spend;

import org.w3c.dom.Text;

import static android.R.attr.id;

public class SpendCursorAdapter extends CursorAdapter{

    public SpendCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_spend, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView spendDateView = (TextView) view.findViewById(R.id.spent_item_date_view);
        TextView spendAmountView = (TextView) view.findViewById(R.id.spent_item_amount_view);

        Spend spend = new Spend();
        spend.setId(cursor.getInt(cursor.getColumnIndex(SpendContract.SpendEntry._ID)));
        spend.setAmount(cursor.getString(cursor.getColumnIndex(SpendContract.SpendEntry.COLUMN_AMOUNT)));
        spend.setDescription(cursor.getString(cursor.getColumnIndex(SpendContract.SpendEntry.COLUMN_DESCRIPTION)));
        spend.setCategoryID(cursor.getInt(cursor.getColumnIndex(SpendContract.SpendEntry.COLUMN_CATEGORY_ID)));
        spend.setLocationID(cursor.getInt(cursor.getColumnIndex(SpendContract.SpendEntry.COLUMN_LOCATION_ID)));
        spend.setDate(cursor.getInt(cursor.getColumnIndex(SpendContract.SpendEntry.COLUMN_DATE)));

        spendDateView.setText(spend.getDateToString());
        spendAmountView.setText(spend.getAmount());
    }
}
