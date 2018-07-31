package lambtoncollege.com.perfectmillageapp.Database;

import android.provider.BaseColumns;


public class TableData {
    public TableData() {

    }

    public static abstract class Tableinfo implements BaseColumns {


        public static final String LOC_LAT = "loc_lat";
        public static final String LOC_LONG = "loc_long";
        public static final String LOC_TABLE_NAME = "location_table";


        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";

        public static final String PROFILE_TABLE_NAME = "profile_table";

        public static final String CARD_NUMBER = "card_number";
        public static final String CVV = "cvv";
        public static final String EXP_DATE = "exp_date";

        public static final String PAYMENT_TABLE_NAME = "payment_table";

        public static final String DATABASE_NAME = "dbRides";
    }
}
