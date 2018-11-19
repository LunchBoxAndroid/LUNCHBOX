package com.lunchbox.lunchbox;

import android.view.View;

interface DateSelectionAdapterListener {

    Void dateSelected(View v, int position);
    Void dateUnselected(View v, int position);
    void dateSelectionChanged(View v, int adapterPosition);
}

