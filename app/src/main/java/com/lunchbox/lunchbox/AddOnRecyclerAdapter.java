package com.lunchbox.lunchbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AddOnRecyclerAdapter extends RecyclerView.Adapter<AddOnItemViewHolder> {

    List<AddOnItem> items;
    AddOnAdapterListener listener;

    public AddOnRecyclerAdapter(List<AddOnItem> items,AddOnAdapterListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddOnItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_on_item,parent,false);
        return new AddOnItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddOnItemViewHolder holder, final int position) {
        final AddOnItem addOnItem = items.get(position);

        holder.itemNameTV.setText(addOnItem.itemName);
        holder.itemPriceTV.setText(String.format("₹ %s", Double.toString(addOnItem.itemPrice)));
        if (addOnItem.isVeg){
            holder.vegIV.setVisibility(View.VISIBLE);
            holder.nonVegIV.setVisibility(View.INVISIBLE);
        } else {
            holder.vegIV.setVisibility(View.INVISIBLE);
            holder.nonVegIV.setVisibility(View.VISIBLE);
        }
        if (addOnItem.quantity > 0) {
            holder.addButtonSmall.setVisibility(View.VISIBLE);
            holder.subButton.setVisibility(View.VISIBLE);
            holder.quantityTV.setVisibility(View.VISIBLE);
            holder.quantityTV.setText(String.valueOf(addOnItem.quantity));
            holder.addButton.setVisibility(View.GONE);
        } else {
            holder.addButtonSmall.setVisibility(View.GONE);
            holder.subButton.setVisibility(View.GONE);
            holder.quantityTV.setVisibility(View.GONE);
            holder.addButton.setVisibility(View.VISIBLE);

        }

        holder.listener = listener;
        holder.addOnRecyclerAdapter = this;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
