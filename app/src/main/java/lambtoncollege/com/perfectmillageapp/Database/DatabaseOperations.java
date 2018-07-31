package lambtoncollege.com.perfectmillageapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;

    public DatabaseOperations(Context context) {
        super(context, TableData.Tableinfo.DATABASE_NAME, null, database_version);
    }


    public String CREATE_LOC_QUERY = "CREATE TABLE "
            + TableData.Tableinfo.LOC_TABLE_NAME + "("
            + TableData.Tableinfo.LOC_LAT + " TEXT,"
            + TableData.Tableinfo.LOC_LONG + " TEXT);";


    public String CREATE_PROFILE_QUERY = "CREATE TABLE "
            + TableData.Tableinfo.PROFILE_TABLE_NAME + "("
            + TableData.Tableinfo.FIRST_NAME + " TEXT,"
            + TableData.Tableinfo.LAST_NAME + " TEXT);";


    public String CREATE_PAYMENT_QUERY = "CREATE TABLE "
            + TableData.Tableinfo.PAYMENT_TABLE_NAME + "("
            + TableData.Tableinfo.CARD_NUMBER + " TEXT,"
            + TableData.Tableinfo.CVV + " TEXT,"
            + TableData.Tableinfo.EXP_DATE + " TEXT);";

    @Override
    public void onCreate(SQLiteDatabase sdb) {
//        sdb.execSQL(CREATE_QUERY);
        sdb.execSQL(CREATE_LOC_QUERY);
        sdb.execSQL(CREATE_PROFILE_QUERY);
        sdb.execSQL(CREATE_PAYMENT_QUERY);
        Log.d("Database operations", "Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableData.Tableinfo.LOC_TABLE_NAME);
        onCreate(db);
    }





    public void putLocation(DatabaseOperations dop, String lat, String lng){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.Tableinfo.LOC_LAT, lat);
        cv.put(TableData.Tableinfo.LOC_LONG, lng);
        long k = SQ.insert(TableData.Tableinfo.LOC_TABLE_NAME, null, cv);
        Log.d("Database putLatLon", "true");

    }


        public void putProfile(DatabaseOperations dop, String fName, String lName){

            SQLiteDatabase SQ = dop.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TableData.Tableinfo.FIRST_NAME, fName);
            cv.put(TableData.Tableinfo.LAST_NAME, lName);
            long k = SQ.insert(TableData.Tableinfo.PROFILE_TABLE_NAME, null, cv);
            Log.d("Database putProfile", "true");
        }



    public void putPayment(DatabaseOperations dop, String cardNumber, String cvv, String expDate){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.Tableinfo.CARD_NUMBER, cardNumber);
        cv.put(TableData.Tableinfo.CVV, cvv);
        cv.put(TableData.Tableinfo.EXP_DATE, expDate);
        long k = SQ.insert(TableData.Tableinfo.PAYMENT_TABLE_NAME, null, cv);
        Log.d("Database putPayment", "true");

    }

    public Cursor getLocationInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.Tableinfo.LOC_LAT, TableData.Tableinfo.LOC_LONG,};
        Cursor CR = SQ.query(TableData.Tableinfo.LOC_TABLE_NAME, coloumns, null, null, null, null, null);
        return CR;

    }

    public Cursor getProfileInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.Tableinfo.FIRST_NAME, TableData.Tableinfo.LAST_NAME,};
        Cursor CR = SQ.query(TableData.Tableinfo.PROFILE_TABLE_NAME, coloumns, null, null, null, null, null);
        return CR;

    }

    public Cursor getPaymentInformation(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {TableData.Tableinfo.CARD_NUMBER, TableData.Tableinfo.CVV,TableData.Tableinfo.EXP_DATE,};
        Cursor CR = SQ.query(TableData.Tableinfo.PAYMENT_TABLE_NAME, coloumns, null, null, null, null, null);
        return CR;

    }

    public int getCount(String tableName) {
        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }











}
