package com.lunchbox.lunchbox;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DateSelectionAdapter extends RecyclerView.Adapter<DateSelectionAdapter.DateSelectionItemHolder> {

    private List<Meal> meals;
    private List<Boolean> checks;
    private DateSelectionAdapterListener dateSelectionAdapterListener;

    public DateSelectionAdapter(List<Meal> meals, List<Boolean> checks, DateSelectionAdapterListener dateSelectionAdapterListener) {
        this.meals = meals;
        this.checks = checks;
        this.dateSelectionAdapterListener = dateSelectionAdapterListener;
    }

    @NonNull
    @Override
    public DateSelectionItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date_selection,parent,false);
        return new DateSelectionItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateSelectionItemHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.listener = dateSelectionAdapterListener;
        holder.adapter = this;


        holder.checkBox.setChecked(checks.get(position));

        holder.date.setText(new SimpleDateFormat("dd", Locale.getDefault()).format(meal.getDate()));
        holder.month.setText(new SimpleDateFormat("MMM", Locale.getDefault()).format(meal.getDate()).toUpperCase());
        holder.day.setText(new SimpleDateFormat("EEEE", Locale.getDefault()).format(meal.getDate()));
        holder.addOnCount.setText("Add-on selected : " + meal.getAddOn().size());
        holder.mealCount.setText("Meal count : "+ meal.getMealCount());

        if (meal.getMealType() == Meal.LUNCH){
            holder.mealType.setText("LUNCH");
        } else if (meal.getMealType() == Meal.DINNER) {
            holder.mealType.setText("DINNER");
        }

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }






    public class DateSelectionItemHolder extends RecyclerView.ViewHolder {

        TextView day,date,month,mealType,mealCount,addOnCount;
        CheckBox checkBox;
        ConstraintLayout itemLayout;

        DateSelectionAdapterListener listener;
        DateSelectionAdapter adapter;

        boolean isChecked = false;

        public DateSelectionItemHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.month);
            mealType = itemView.findViewById(R.id.meal_type);
            mealCount = itemView.findViewById(R.id.meal_count);
            addOnCount = itemView.findViewById(R.id.add_on_count);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemLayout = itemView.findViewById(R.id.item_layout);

            this.setIsRecyclable(false);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.dateSelectionChanged(v,getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked){
//                        listener.dateSelected(buttonView,getAdapterPosition());
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        listener.dateUnselected(buttonView,getAdapterPosition());
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            });

            itemLayout.setClickable(true);
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.dateSelectionChanged(v,getAdapterPosition());
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

}
