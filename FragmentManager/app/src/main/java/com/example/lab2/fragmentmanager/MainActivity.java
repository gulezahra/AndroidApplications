package com.example.lab2.fragmentmanager;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Blank2Fragment.OnFragmentInteractionListener {

    // have to override method here for that interface... override here what method is made in interface

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new Blank2Fragment())
                .commit();
    }


    @Override
    // it this is shown, communication...
    public void onFragmentInteraction(Uri uri) {
        // not nesting here, so write only this
        Toast.makeText(this,"Fragment communicating with Activity",Toast.LENGTH_SHORT).show();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new BlankFragment())
                .commit();
    }
}
