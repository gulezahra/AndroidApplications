package com.example.android.businesscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** This method is called when Contact Me button is pressed
    public void contactMethodCalling() {
        Intent intent = getIntent();
        String message = intent.getStringExtra("Reach me through any of the given contact information. \nTHANK YOU.");
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.main_front_page);
        layout.addView(textView);
    } */

    /** This method is called when Contact Me button is pressed */

    public void callMe (View view) {
        Toast.makeText(MainActivity.this, "The dealer is here for you to consult.", Toast.LENGTH_SHORT).show();
    }
}
