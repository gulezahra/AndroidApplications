package com.example.android.fragmentapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Blank4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1"; //single argument

    // TODO: Rename and change types of parameters
    private String mParam1;

    public Blank4Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Blank4Fragment newInstance(String param1) {
        Blank4Fragment fragment = new Blank4Fragment(); //return type is this fragemnt
        //This bundle will take the data
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        // this will set the arguments
        fragment.setArguments(args);
        return fragment;
    }

    // Life Cycle of Fragments are below :-

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // checks whether bundle is set, get in getArguments
        if (getArguments() != null) {
            //getString will take that bundle and is saved in a private String variable
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and receive it in a view data type.
        View view = inflater.inflate(R.layout.fragment_blank4, container, false);

        // Have to work on view, so not directly return it
        TextView textView = (TextView) view.findViewById(R.id.data);
        textView.setText(mParam1);
        return view;
    }

}
