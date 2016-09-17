package com.example.android.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("EnterpriseActivity.java", "Captain's Log, Stardate 43125.8. We have entered a spectacular binary star system in the Kavis Alpha sector on a most critical mission of astrophysical research.");
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView menuItem1 = (TextView) findViewById(R.id.menu_item_1);
        Log.i("MainActivity.java", String.valueOf(menuItem1.getText()));

        // Find second menu item TextView and print the text to the logs

        /**
         * TextView textViewItem2 = (TextView) findViewById(R.id.menu_item_2);
         * String menuItem2 = textViewItem2.getText().toString();
         * Log.v("Main Activity", menuItem2);
         */
        TextView menuItem2 = (TextView) findViewById(R.id.menu_item_2);
        Log.i("MainActivity.java", String.valueOf(menuItem2.getText()));

        // Find third menu item TextView and print the text to the logs
        TextView menuItem3 = (TextView) findViewById(R.id.menu_item_3);
        Log.i("MainActivity.java", String.valueOf(menuItem3.getText()));
    }
}
