package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends Fragment
{
    Switch theme,language2;
    static boolean mode,language;
    static int i;
    LinearLayout frag;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        v= inflater.inflate(R.layout.fragment_settings, container, false);
        MainActivity activity=(MainActivity) getActivity();

        theme=(Switch)v.findViewById(R.id.theme);
        language2=(Switch)v.findViewById(R.id.language);
        frag=(LinearLayout)v.findViewById(R.id.frag_settings);

        activity.getSupportActionBar().setTitle("Settings");

        Bundle d=getArguments();
        mode=d.getBoolean("mode");
        i=d.getInt("i");

        if(i==1)
        {
            theme.setChecked(true);
            theme.setText("Night");
            frag.setBackgroundColor(Color.parseColor("#000000"));
            theme.setTextColor(Color.parseColor("#ffffff"));
            language2.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            theme.setChecked(false);
        }

        if(language)
        {
            language2.setChecked(true);
            language2.setText("Hindi");
        }
        else
        {
            language2.setChecked(false);
        }

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean bc) {
                if(bc){
                    if (i == 1 && mode == true) {
                    Toast.makeText(getContext(), "Already In Night Mode", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        theme.setText("Night");
                        i = 1;
                        mode = true;
                        shabana();
                    }
                }
                else
                {
                    if (i == 0 && mode == false) {
                        Toast.makeText(getContext(), "Already In Day Mode", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        i = 0;
                        theme.setText("Day");
                        mode = false;
                        shabana();
                     }
                }
            }
        });

        language2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(language)
                    {
                        Toast.makeText(getContext(), "Already In Hindi", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        language=true;
                        shabana();
                    }
                }
                else
                {
                    if(language=false)
                    {
                        Toast.makeText(getContext(), "Already In English", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        language=false;
                        shabana();
                        Toast.makeText(getContext(), "English", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public void shabana(){
        Intent d=new Intent(getContext(), MainActivity.class);
        d.putExtra("mode",mode);
        d.putExtra("i",i);
        d.putExtra("language",language);
        d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(d);
        getActivity().finish();
        getActivity().overridePendingTransition(0,0);
    }
}
