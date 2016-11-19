package com.example.lab2.fragmentmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Blank2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Blank2Fragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Blank2Fragment() {
        // Required empty public constructor
    }

    // we mostly work here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        Button btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass on a null value in this method , this method is called in MainActivity, so see that fragment is comm. with activity
                mListener.onFragmentInteraction(null);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    // deleted onButtonPressed

    // fragment life cycle discussing

    // first call to fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // for commuication, fragment to activity, call this interface... below given method of interface

        // here, gets the reference of activity, if it is as it is, then save it in mListener
        if (context instanceof OnFragmentInteractionListener) {
            // save reference in here mListener
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    // last call back to fragment
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    // made interface here, this has to implent in MainActivity.java for communication
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
