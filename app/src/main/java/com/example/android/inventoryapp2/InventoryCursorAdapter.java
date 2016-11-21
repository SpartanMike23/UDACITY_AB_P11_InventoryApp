package com.example.android.inventoryapp2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.android.inventoryapp2.data.InventoryContract.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {

    public static final String LOG_TAG = InventoryCursorAdapter.class.getSimpleName();

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context   app context
     * @param cursor    The cursor from which to get the data. The cursor is already
     *                  moved to the correct position.
     * @param viewGroup The parent to which the new view is attached to
     * @return the newly created list item view.
     */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    /**
     * This method binds the product data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        final Button saleBtn = (Button) view.findViewById(R.id.saleBtn);
        final TextView productTextView = (TextView) view.findViewById(R.id.productNameView);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantityNameView);
        final TextView priceTextView = (TextView) view.findViewById(R.id.priceNameView);

        // Find the columns that we're interested in
        final int mRowId = cursor.getInt(cursor.getColumnIndex(InventoryEntry._ID));

        int productColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRODUCT);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_INVENTORY_PRICE);

        // Read the product attributes from the Cursor for the current product
        String productName = cursor.getString(productColumnIndex);
        final int quantityName = cursor.getInt(quantityColumnIndex);
        final String priceName = cursor.getString(priceColumnIndex);

        //When salesBtn clicked, decrease product quantity by 1.
        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG_TAG, "Sale button was pressed");
                Log.e(LOG_TAG, "Value of mRowID: " + mRowId);

                if (quantityName > 0) {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(InventoryEntry.COLUMN_INVENTORY_QUANTITY, quantityName - 1);

                    Uri currentProductUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI,
                            mRowId);

                    int mRowsAffected = context.getContentResolver().update(currentProductUri, contentValues,
                            null, null);

                    if(mRowsAffected != 0) {
                        quantityTextView.setText(String.valueOf(quantityName));
                    }
                }

            }
        });

        //if product name is empty,
        //set name to Unknown Product
        if (TextUtils.isEmpty(productName)) {
            productName = context.getString(R.string.unknown_product);
        }

        // Update the TextViews with the attributes for the current product
        productTextView.setText(productName);
        quantityTextView.setText(String.valueOf(quantityName));
        priceTextView.setText(priceName);

    }

}
