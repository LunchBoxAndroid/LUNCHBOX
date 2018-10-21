package com.lunchbox.lunchbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class shareFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_share, container, false);
        final Button shareButton = (Button)view.findViewById(R.id.b1);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String URL = "Sharing Successful";
                shareIntent.putExtra(Intent.EXTRA_TEXT, URL);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
        return view;
    }
}


