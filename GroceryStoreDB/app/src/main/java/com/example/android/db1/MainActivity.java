package com.example.android.db1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProductListArrayAdapter productListArrayAdapter;
    ArrayList<Product> list;

    private Button deleteTable;
    private Button addProduct;
    private Button getProducts;
    private Button searchProduct;
    private EditText nameEditText;
    private EditText priceEditText;
    private EditText searchProductEditText;
    private ListView productListView;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);

        initComponents();
        addListeners();
    }

    private void initComponents(){
        deleteTable = (Button) findViewById(R.id.deleteOldTable);
        addProduct = (Button) findViewById(R.id.addProduct);
        getProducts = (Button) findViewById(R.id.getProducts);
        searchProduct = (Button) findViewById(R.id.searchProduct);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        searchProductEditText = (EditText) findViewById(R.id.searchProductEditText);
        productListView = (ListView) findViewById(R.id.productListView);
    }

    private void setArrayAdapterOnList(ArrayList<Product> list){
        productListArrayAdapter = new ProductListArrayAdapter(MainActivity.this,list);
        productListView.setAdapter(productListArrayAdapter);
        productListArrayAdapter.notifyDataSetChanged();
    }

    private void addListeners(){
        deleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.deleteTable();
                list = myDatabaseHelper.getProducts();
                setArrayAdapterOnList(list);
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // App was crashing when this button is clicked with empty EditBox

                // Can also use this line *******
                // if (text1.getText().toString().compareTo("") == 0)
                try
                {
                    Product p = new Product(nameEditText.getText().toString(), Integer.parseInt(priceEditText.getText().toString()));
                    myDatabaseHelper.addProduct(p);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Add a Product", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = myDatabaseHelper.getProducts();
                setArrayAdapterOnList(list);
            }
        });

        searchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search By Name
                String search = searchProductEditText.getText().toString();
                list = myDatabaseHelper.searchProductByName(search);
                setArrayAdapterOnList(list);
            }
        });
    }
}