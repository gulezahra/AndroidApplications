package com.example.android.db1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by zeeshanhanif-pc on 11/27/2016.
 */
public class ProductListArrayAdapter extends ArrayAdapter<Product> {

    MyDatabaseHelper myDatabaseHelper;
    ArrayList<Product> productList;

    public ProductListArrayAdapter(Context context,ArrayList<Product> productList){
        super(context,0,productList);
        this.productList = productList;
    }

    public void doRemoveProduct(int id){
        // This loop gets the id that is clicked from the ArrayList
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                // Remove that ID
                this.productList.remove(i);
                // This method updates the DataBase
                this.notifyDataSetChanged();
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.product_item,parent,false);

        // In Context(), get the Context
        myDatabaseHelper = new MyDatabaseHelper(getContext());

        final TextView productName = (TextView) view.findViewById(R.id.productNameView);
        TextView productPrice = (TextView) view.findViewById(R.id.productPriceView);
        final TextView productId = (TextView) view.findViewById(R.id.productIdView);
        Button deleteProduct = (Button) view.findViewById(R.id.deleteThisProduct);

        Product product = getItem(position);
        productId.setText(String.valueOf(product.getId()));
        productName.setText(product.getName());
        productPrice.setText(""+product.getPrice());

        // The button delete with each product
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete Product of this ID
                String idProduct = productId.getText().toString();
                myDatabaseHelper.deleteProduct(idProduct);
                // Call this method and give ID
                doRemoveProduct(Integer.parseInt(productId.getText().toString()));
            }
        });

        return view;
    }
}