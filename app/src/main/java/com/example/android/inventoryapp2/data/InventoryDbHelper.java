package com.example.android.inventoryapp2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
*InventoryDbHelper class created for database creation and version management
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 2;
    public static String DATABASE_NAME = "inventory.db";

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVENTORY_TABLE = "Create TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + " ("
                        + InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRODUCT + " TEXT NOT NULL, "
                        + InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                        + InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE + " FLOAT NOT NULL DEFAULT 0.0, "
                        //Pic column uses TEXT instead of BLOB.
                        //Will store URI pathway of a picture already stored photo in
                       //internal storage
                        + InventoryContract.InventoryEntry.COLUMN_INVENTORY_PIC + " TEXT);";

        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

