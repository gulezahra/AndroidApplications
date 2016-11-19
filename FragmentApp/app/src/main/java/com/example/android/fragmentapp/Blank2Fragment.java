package com.example.android.fragmentapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Blank2Fragment extends Fragment {

    FragmentManager fragmentManager;
    // This will not work, because it is a fragment have to make object and get Support library of fragment manager from Activity class

    public Blank2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);

        // FragmentManager has now get the support library.
        fragmentManager = getActivity().getSupportFragmentManager();

        Button btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new Blank3Fragment())
                        .addToBackStack("test")
                        .commit();
            }
        });
        return view;
    }

}