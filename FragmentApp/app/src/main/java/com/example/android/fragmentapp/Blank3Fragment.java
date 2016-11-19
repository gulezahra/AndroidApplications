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
public class Blank3Fragment extends Fragment {

    FragmentManager fragmentManager;

    public Blank3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank3, container, false);

        // FragmentManager has now get the support library.
        fragmentManager = getActivity().getSupportFragmentManager();

        Button btn3 = (Button) view.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, Blank4Fragment.newInstance("My fourth fragment is made."))
                        .addToBackStack("test")
                        .commit();
            }
        });
        return view;
    }

}