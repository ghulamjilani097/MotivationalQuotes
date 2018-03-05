package com.memor.thinkers.jilani.motivationalquotes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class About extends Fragment
{
    static boolean mode;
    TextView about;
    FrameLayout aboutframe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root=inflater.inflate(R.layout.fragment_about, container, false);
        MainActivity activity=(MainActivity) getActivity();

        about=(TextView)root.findViewById(R.id.abouttext);
        aboutframe=(FrameLayout) root.findViewById(R.id.fragment_about);

        activity.getSupportActionBar().setTitle("About Us");
        Bundle b=getArguments();
        mode=b.getBoolean("mode");
        if(mode==true)
        {
            about.setTextColor(Color.parseColor("#ffffff"));
            aboutframe.setBackgroundColor(Color.parseColor("#000000"));
        }
        return root;
    }
}