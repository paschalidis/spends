package com.example.android.spends.Database;


import android.provider.BaseColumns;

public final class SpendContract {

    private SpendContract(){}

    public static final class CategoryEntry implements BaseColumns{

        public final static String TABLE_NAME = "categories";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_TITLE = "title";
        public final static String COLUMN_DESCRIPTION = "description";

    }

    public static final class SpendEntry implements BaseColumns{

        public final static String TABLE_NAME = "spends";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_AMOUNT = "amount";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_CATEGORY_ID = "category_id";
        public final static String COLUMN_LOCATION_ID = "location_id";
        public final static String COLUMN_DATE = "date";
    }

    public static final class LocationEntry implements BaseColumns{

        public final static String TABLE_NAME = "locations";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_LAT = "lat";
        public final static String COLUMN_LONG = "long";
    }
}
