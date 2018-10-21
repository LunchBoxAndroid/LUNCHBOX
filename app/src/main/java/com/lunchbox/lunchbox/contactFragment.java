package com.lunchbox.lunchbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class contactFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_contact, container, false);
        ImageView dp=(ImageView) view.findViewById(R.id.dipayan);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dipayan);
        RoundedBitmapDrawable roundbitmap = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundbitmap.setCircular(true);
        dp.setImageDrawable(roundbitmap);

        ImageView dp1=(ImageView) view.findViewById(R.id.dipika);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.dipika);
        RoundedBitmapDrawable roundbitmap1 = RoundedBitmapDrawableFactory.create(getResources(),bitmap1);
        roundbitmap1.setCircular(true);
        dp1.setImageDrawable(roundbitmap1);
        return view;
    }
}
