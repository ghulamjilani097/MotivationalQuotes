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
    static Boolean mode,language;
    static int i;
    boolean bc;

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        v= inflater.inflate(R.layout.fragment_settings, container, false);
        theme=(Switch)v.findViewById(R.id.theme);
        language2=(Switch)v.findViewById(R.id.language);

        Bundle b=getArguments();
        Bundle c=getArguments();
        Bundle d=getArguments();
        Bundle e=getArguments();
        mode=b.getBoolean("mode");
        i=c.getInt("i");
        language=d.getBoolean("language");
        bc=e.getBoolean("b");

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean bc) {
                if(bc){
                    if (i == 1 && mode == true) {
                Toast.makeText(getContext(), "Already In Night Mode", Toast.LENGTH_SHORT).show();
            } else {
                i = 1;
                mode = true;
                Intent a = new Intent(getContext(), MainActivity.class);
                a.putExtra("mode", mode);
                a.putExtra("i", i);
                a.putExtra("b",bc);
//                a.putExtra("language",language);
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
                    mode = false;
                    Intent a = new Intent(getContext(), MainActivity.class);
                    a.putExtra("mode", mode);
                    a.putExtra("i", i);
//                    a.putExtra("language",language);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);
                    getActivity().overridePendingTransition(0, 0);
                }

                }
            }
        });
        return v;
    }
}
