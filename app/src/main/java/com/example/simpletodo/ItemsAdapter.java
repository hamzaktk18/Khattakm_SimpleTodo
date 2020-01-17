package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// take data from model and display into a row in the recycler viewpublic class ItemsAdapter {
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

//    public ItemsAdapter(List<String> items) {
//        this.items = items;
//    }
////
////
//    OnLongClickListener longClickListener;
//    public interface OnLongClickListener {
//        void onItemLongClicked(int position);
//    }


    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener)  {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use lnflator to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false );
        // wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }
    // responsible to bind data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // grab the item at the position
        String item = items.get(position);

        // bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    // Tell the RV how many items are in the list
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the vie winside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
