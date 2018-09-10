package com.lunchbox.lunchbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 1605272 on 8/23/2018.
 */

public class homeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);

    }
    public void dpm(View view)
    {
        Fragment fragment=null;
        fragment=new dailyFragment();
        if(fragment!=null)
        {
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction ft =fragmentManager.beginTransaction();
            ft.replace(R.id.screen,fragment);
            ft.commit();
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Fragment fragment=null;
                fragment=new dailyFragment();
                if(fragment!=null)
                {
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction ft =fragmentManager.beginTransaction();
                    ft.replace(R.id.screen,fragment);
                    ft.commit();
                }
            }
        });


    }
}
