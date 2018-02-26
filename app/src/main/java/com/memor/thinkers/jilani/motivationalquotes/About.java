package com.memor.thinkers.jilani.motivationalquotes;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {



    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_about, container, false);
        MainActivity activity=(MainActivity) getActivity();
      //  Toolbar tool=(Toolbar)root.findViewById(R.id.toolbar);
        //activity.setSupportActionBar(tool);
        activity.getSupportActionBar().setTitle("About Us");


        return root;

           }


}
