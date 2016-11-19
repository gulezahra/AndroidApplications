package com.example.android.fragmentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button popBack = (Button) findViewById(R.id.popBack);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new BlankFragment())
                .addToBackStack("test")
                .commit();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        // This will take to second fragment, but want to go to fourth directly, so below .replace code...
                        // .replace will remove previous one and write this one
                        .replace(R.id.fragment_container, new Blank2Fragment())

                        // Until it move to all the sequence you opened, on pressing back button, your app will not close
                        // pass mparam1 argument in newInstance
                        .replace(R.id.fragment_container, Blank4Fragment.newInstance("My fourth fragment is made."))
                        .addToBackStack("test")
                        .commit();
            }
        });

        popBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // It will show the reverse work of sequences we opened our fragments
                fragmentManager.popBackStack();
            }
        });

    }
}
