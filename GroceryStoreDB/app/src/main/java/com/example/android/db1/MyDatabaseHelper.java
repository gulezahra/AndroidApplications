package com.example.android.db1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zeeshanhanif-pc on 11/27/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "newinventory.db";
    private static final int DB_VERSION = 1;
    private static final String PRODUCT_TABLE_NAME = "product";

    // Products Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + PRODUCT_TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + PRODUCT_TABLE_NAME);

        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());

        // Store these values in the Table
        db.insert(PRODUCT_TABLE_NAME, null, values);
        db.close();

    }

    public ArrayList<Product> getProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + PRODUCT_TABLE_NAME, null);

        ArrayList<Product> products = new ArrayList<Product>();
        while (cursor.moveToNext()) {
            //int index = cursor.getColumnIndex("name");
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);

            products.add(new Product(id, name, price));


            Log.d("DBResult", "Id = " + id + " - Name = " + name + " - Price = " + price);

        }
        db.close();
        return products;

    }

    public ArrayList<Product> searchProductById(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + PRODUCT_TABLE_NAME + " where _id = '" + productId + "'", null);

        ArrayList<Product> products = new ArrayList<Product>();
        while (cursor.moveToNext()) {
            //int index = cursor.getColumnIndex("name");
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);

            products.add(new Product(id, name, price));

            Log.d("DBResult", "Id = " + id + " - Name = " + name + " - Price = " + price);
        }
        db.close();
        return products;
    }

    public ArrayList<Product> searchProductByName(String productName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + PRODUCT_TABLE_NAME + " where name like '%" + productName + "%'", null);

        ArrayList<Product> products = new ArrayList<Product>();
        while (cursor.moveToNext()) {
            //int index = cursor.getColumnIndex("name");
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);

            products.add(new Product(id, name, price));

            Log.d("DBResult", "Id = " + id + " - Name = " + name + " - Price = " + price);
        }
        db.close();
        return products;
    }

    // Delete complete Table
    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete * from is not working
        // but delete from is working
        db.execSQL("DELETE FROM " + PRODUCT_TABLE_NAME);
        db.close();
    }

    // Deleting single product
    public void deleteProduct(String productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Deleting the product with this ID, Pressing GetProducts or Search option
        // is not showing that product again
        db.execSQL("delete from " + PRODUCT_TABLE_NAME + " where id='" + productId + "'");
    }
}