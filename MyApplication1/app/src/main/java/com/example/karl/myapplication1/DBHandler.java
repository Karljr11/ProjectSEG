package com.example.karl.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "serviceDB.db";
    public static final String TABLE_SERVICES = "services";
    // public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVICETYPE = "servicetype";
    public static final String COLUMN_HOURLYSALARY = "hourlysalary";

    // ...
    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_SERVICETYPE +
                " TEXT," + COLUMN_HOURLYSALARY + " DOUBLE" + ")";

        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }
    public void addProduct(Services service){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICETYPE, service.getType());
        values.put(COLUMN_HOURLYSALARY, service.getHourlysalary());

        db.insert(TABLE_SERVICES, null, values);
        db.close();
    }
    public Services findService(String servicetype){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM "
                + TABLE_SERVICES
                + " WHERE "
                + COLUMN_SERVICETYPE
                + " = \""
                + servicetype
                + "\""
                ;

        Cursor cursor = db.rawQuery(query, null);
        Services service = new Services();

        if(cursor.moveToFirst()){
            service.setType(cursor.getString(0));
            service.setHourlysalary(Double.parseDouble(cursor.getString(1)));


            cursor.close();
        } else {
            service = null;
        }
        db.close();
        return service;
    }
    public boolean deleteProduct(String servicetype){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_SERVICES
                + " WHERE "
                + COLUMN_SERVICETYPE
                + " = \""
                + servicetype
                + "\""
                ;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String serviceStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, COLUMN_SERVICETYPE + " = " + serviceStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
