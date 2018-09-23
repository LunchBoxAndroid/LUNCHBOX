package com.lunchbox.lunchbox;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddOnItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView vegIV;
    public ImageView nonVegIV;
    public TextView itemNameTV;
    public TextView itemPriceTV;
    public Button addButton;
    public Button subButton;
    public Button addButtonSmall;
    public TextView quantityTV;
    public AddOnAdapterListener listener;
    public AddOnRecyclerAdapter addOnRecyclerAdapter;

    public AddOnItemViewHolder(View itemView) {
        super(itemView);
        vegIV = itemView.findViewById(R.id.vegImageView);
        nonVegIV = itemView.findViewById(R.id.nonVegImageView);
        itemNameTV = itemView.findViewById(R.id.itemNameTextView);
        itemPriceTV = itemView.findViewById(R.id.itemPriceTextView);
        addButton = itemView.findViewById(R.id.addButton);
        addButtonSmall = itemView.findViewById(R.id.addButtonSmall);
        subButton = itemView.findViewById(R.id.subButton);
        quantityTV = itemView.findViewById(R.id.quantityTextView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addButtonOnClick(v,getAdapterPosition());
                addOnRecyclerAdapter.notifyDataSetChanged();
            }
        });

        addButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addButtonSmallOnClick(v,getAdapterPosition());
                addOnRecyclerAdapter.notifyDataSetChanged();
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.subButtonOnClick(v,getAdapterPosition());
                addOnRecyclerAdapter.notifyDataSetChanged();
            }
        });
        
    }
}
