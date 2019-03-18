package com.lunchbox.lunchbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder> {

    List<Meal> meals;
    OrderRecyclerAdapter(List<Meal> meal)
    {
        meals=meal;
    }

    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {
        Meal temp = meals.get(position);
        if(temp.getMealType()==0)
            holder.mealType.setText("Lunch x"+String.valueOf(temp.getMealCount()));
        else
            holder.mealType.setText("Dinner x"+String.valueOf(temp.getMealCount()));
        holder.dateText.setText("Date :"+temp.getDate().toString().substring(0,10));
        StringBuilder listOfAddOns= new StringBuilder();
        List<Integer> addOnList=temp.getAddOn();
        for(int i : addOnList)
            listOfAddOns.append(String.valueOf(i)).append(",");

        holder.addOnList.setText("AddOn Ids :"+listOfAddOns);
        //holder.mealCount.setText(String.valueOf(temp.getMealCount()));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateText,mealType,mealCount,addOnList;

        public ViewHolder(View itemView) {
            super(itemView);
            dateText=itemView.findViewById(R.id.dateText);
            mealType=itemView.findViewById(R.id.mealType);
            addOnList=itemView.findViewById(R.id.addOnList);
            //mealCount=itemView.findViewById(R.id.mealCount);
        }
    }
}
