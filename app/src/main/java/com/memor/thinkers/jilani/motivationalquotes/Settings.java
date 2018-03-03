package com.memor.thinkers.jilani.motivationalquotes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends Fragment
{
    Switch theme,language2;
    static boolean mode,language;
    static int i;
    static boolean bc;

    View v;
    static int j;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        v= inflater.inflate(R.layout.fragment_settings, container, false);
        theme=(Switch)v.findViewById(R.id.theme);
        language2=(Switch)v.findViewById(R.id.language);

        Bundle d=getArguments();
        mode=d.getBoolean("mode");
        i=d.getInt("i");
        language=d.getBoolean("language");
        Toast.makeText(getContext(), "sunita"+i, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "theme"+mode, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "lang"+language, Toast.LENGTH_SHORT).show();

        if(i==1)
        {
            theme.setChecked(true);
            theme.setText("Night");
        }
        else
        {
            theme.setChecked(false);
            theme.setText("Day");
        }

        if(language)
        {
            language2.setChecked(true);
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
            } else {
//                        theme.setChecked(true);
                        theme.setText("Night");
                i = 1;
                mode = true;
                Intent a = new Intent(getContext(), MainActivity.class);
                a.putExtra("mode", mode);
                a.putExtra("i", i);
                a.putExtra("language",language);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
                getActivity().overridePendingTransition(0, 0);

        }
                }
                else{
                if (i == 0 && mode == false) {
                    Toast.makeText(getContext(), "Already In Day Mode", Toast.LENGTH_SHORT).show();
                } else {
                    i = 0;
                    theme.setText("Day");
                    mode = false;
                    Intent a = new Intent(getContext(), MainActivity.class);
                    a.putExtra("mode", mode);
                    a.putExtra("i", i);
                    a.putExtra("language",language);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);
                    getActivity().overridePendingTransition(0, 0);
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
//                        i=0;
                        language=true;
//                    mode=false;
                        Intent d=new Intent(getContext(), MainActivity.class);
                        d.putExtra("mode",mode);
                        d.putExtra("i",i);
                        d.putExtra("language",language);
                        d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(d);
                        getActivity().overridePendingTransition(0,0);


//                        Bundle bd=new Bundle();
//                        bd.putInt("i",i);
//                        bd.putBoolean("mode",mode);
//                        bd.putBoolean("language",language);
//                        MainActivity mb=(MainActivity) getActivity();


                    }
//
                }

                else
                {
                    if(language=false)
                    {
                        Toast.makeText(getContext(), "Already In English", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
//                        i=0;
                        language=false;
//                    mode=false;
                        Intent d=new Intent(getContext(), MainActivity.class);
                        d.putExtra("mode",mode);
                        d.putExtra("i",i);
                        d.putExtra("language",language);
                        d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(d);
                        getActivity().overridePendingTransition(0,0);
                        Toast.makeText(getContext(), "English", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        return v;
    }
}
