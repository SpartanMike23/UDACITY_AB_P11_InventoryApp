package com.example.android.inventoryapp2.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
/*
* Contract Class created to create framework for the Database.
* Defines database Columns and Rows
* Contains static helper methods to manipulate URI's
 */
public final class InventoryContract {

    private InventoryContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.android.inventoryapp2";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "inventoryapp2";

    public static final class InventoryEntry implements BaseColumns {

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of products.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY
                + "/" + PATH_INVENTORY;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single product.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY
                        + "/" + PATH_INVENTORY;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_INVENTORY);

        //Variables creating database columns: ID, Table Name, Products ,Quantity, Price
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "Inventory";
        public static final String COLUMN_INVENTORY_PRODUCT = "Product";
        public static final String COLUMN_INVENTORY_QUANTITY = "Quantity";
        public static final String COLUMN_INVENTORY_PRICE = "Price";
        //picture column
        public static final String COLUMN_INVENTORY_PIC = "Picture";

    }
}
