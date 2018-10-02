package com.lunchbox.lunchbox;

import android.view.View;

public interface AddOnAdapterListener {

    void addButtonOnClick(View v, int position);
    void addButtonSmallOnClick(View v, int position);
    void subButtonOnClick(View v,int position);

}
