package com.memor.thinkers.jilani.motivationalquotes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Home extends Fragment
{
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.fragment_home, container, false);
        text=(TextView)root.findViewById(R.id.quotes);
        return root;
    }
}
